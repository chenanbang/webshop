package com.cab.graduation.web.handlers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cab.graduation.entities.User;
import com.cab.graduation.service.CommonService;
import com.cab.graduation.service.UserService;
import com.cab.graduation.utils.Conditions;
import com.cab.graduation.utils.Page;
import com.cab.graduation.utils.SaltEncryptionUtils;
import com.cab.graduation.utils.SendEmailUtil;
import com.cab.graduation.utils.WebShopUtils;

@Controller
public class UserHandler {

	@Autowired
	private CommonService commonService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(path="/showRegisterPage")
	public String showRegisterPage() {
		
		return "register";
	}
	
	@RequestMapping(path="/executeRegister")
	public String executeRegister(@ModelAttribute("registerUser")User user,Map<String,String> map,HttpSession session) {
		
		user.setPassword(SaltEncryptionUtils.generate(user.getPassword()));
		user.setActiveCode(WebShopUtils.generateActiveSerialNum());
		user.setRegisterTime(new Date());
		user.setState(0);
		user.setRank(0);
		user.setDel(0);
		
		//邮件主题
		String title="请激活你在WebShop官网上注册的用户";
		///users/{username}/{password}/confirm_verification/{active_code}
		//邮件内容
		String content="Hi @"+user.getUsername()+"!<br/><br/>"
				+ "<a href="+"'http://localhost:8080/webshop/users/confirm_verification/"+user.getActiveCode()+"'>点击链接进行激活操作!</a>"
				+ "<br/><hr/><br/>"
				+ "如果链接不起作用,你也可以把下面的链接地址直接粘到浏览器地址栏中进行直接访问:<br/>"
				+ "<a href="+"'http://localhost:8080/webshop/users/confirm_verification/"+user.getActiveCode()+"'>http://localhost:8080/webshop/users/confirm_verification/"+user.getActiveCode()+"</a><br/><br/>"
				+ "你收到这个邮件是由于你最近创建了一个新的WebShop账号. 如果你没有进行这些操作,请忽略这个邮件,谢谢!";
		
		//邮件接收方
		String toEmail=user.getEmail();
		
		//进行用户的添加操作
		userService.saveOrUpdate(user);
		
		//进行实际的邮件发送操作
		SendEmailUtil.sendActiveEmail(title, content, toEmail);
		
		map.put("message", "恭喜您,注册成功,但需要前往注册时填写的邮箱进行激活,激活后方可登陆!");
		return "redirect:/index";
	}
	
	/**
	 * 删除用户的操作接口(软删除)
	 * @param userId
	 * @return
	 */
	@RequestMapping(path="user/delete/{userId}",method=RequestMethod.DELETE)
	public String delete(@PathVariable("userId") Integer userId){
		User user=userService.findUserById(userId);
		//进行软删除操作
		user.setDel(1);
		userService.saveOrUpdate(user);
		return "redirect:/user/userManage/1";
	}
	
	/**
	 * 展示修改页面(管理员修改用户信息时所调用的接口)
	 * @param userId
	 * @param map
	 * @return
	 */
	@RequestMapping(path="/user/update/{userId}",method=RequestMethod.GET)
	public String update(@PathVariable(value="userId") Integer userId,Map<String,User> map){
		User user=userService.findUserById(userId);
		map.put("user", user);
		return "admin/user/edit";
	}
	
	
	/**
	 * 用户自己修改自己信息时所调用的接口
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(path="/user/update",method=RequestMethod.GET)
	public String updateUserInfo(HttpServletRequest request,Map<String,User> map){
		User user=(User) request.getSession().getAttribute("user");
		if(user==null){
			return "redirect:/login";
		}
		map.put("user", user);
		//暂时先使用管理页面来进行信息的显示，实际开发中应该为client/user/edit
		return "admin/user/edit";
	}
	
	/**
	 * 执行实际修改操作的接口
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(path="/user/update",method=RequestMethod.PUT)
	public String executeUpdate(HttpServletRequest request,User user){
		userService.saveOrUpdate(user);
		
		User userInSession=(User) request.getSession().getAttribute("user");
		
		//根据当前登录用户的Rank来控制跳转到那个操作页面
		if(userInSession.getRank()==1){
			return "redirect:/user/userManage/1";
		}else{
//			request.getSession().setAttribute("user", user);
			return "redirect:/goEditSuccessPage";
		}
		
	}
	
	@RequestMapping(path="/user/userManage/{currentPageNum}")
	public String listUser(@PathVariable("currentPageNum") Integer currentPageNum,Map<String,Page<User>> map){
		Page<User> page=new Page<>(currentPageNum, 6);
		Conditions conditions=new Conditions(" where user.del=?", new String[]{"0"});
		commonService.pageQuery(User.class, page, conditions);
		
		map.put("pageBean", page);
		
		return "admin/user/list";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String showLoginPage(){
		return "login";
	}
	
	@RequestMapping(path="/login",method=RequestMethod.POST)
	public String login(@RequestParam(value="admin",required=false) String admin,
						@RequestParam(value="username",required=false) String username,
						@RequestParam(value="password",required=false) String password,
						@RequestParam(value="isRememberMe",required=false) String isRememberMe,
						HttpSession session,Map<String,String> map,
						HttpServletRequest request,
						HttpServletResponse response){
		User user = userService.findUserByNameAndPassword(username, password);
		//登录失败时提示用户
		if(user==null){
			map.put("message", "用户名或密码错误!");
			return "login";
		}
		if(user.getState()==0){
			map.put("message", "抱歉,您的账号还没有激活,请激活后再来登陆!");
			return "login";
		}
		//成功则将用户的登录信息写回用户的客户端
		try {
			//先判断是否为普通用户(若是则进行是否记住密码的判断)
			if(user.getRank()!=1){
				if("true".equals(isRememberMe)){
					Cookie cookie = new Cookie("userInfo", URLEncoder.encode(username+","+password, "UTF-8"));
					//设置有效期为一周
					cookie.setMaxAge(7*24*60*60);
					response.addCookie(cookie);
				}else{
					Cookie[] cookies=request.getCookies();
					if(cookies!=null){
						for(Cookie cookie : cookies){
							if("userInfo".equals(cookie.getName())){
								//立即清除cookie
								cookie.setMaxAge(0);
								response.addCookie(cookie);
							}
						}
					}
				}
			}
			
			
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		
		if("admin".equals(admin)&&user.getRank()==1){
			session.setAttribute("user", user);
			return "admin/home";
		}
		if("admin".equals(admin)&&user.getRank()==0){
			if(session.getAttribute("user")!=null){
				session.removeAttribute("user");
			}
			map.put("message", "抱歉,您不是管理员,无法进入管理系统!");
			return "redirect:/index";
		}
		
		session.setAttribute("user", user);
		
		return "redirect:/index";
	}
	
	
	@RequestMapping(path="/findUserByUsername")
	public void findUserByUsername(@RequestParam("username") String username,HttpServletResponse response) throws IOException{
		
		User user = userService.findUserByName(username);
		if(user==null){
			response.getWriter().print("OK");
		}else{
			response.getWriter().print("用户名已存在,请重新输入!");
		}
	}
	
	
	/**
	 * 用于激活用户的接口
	 * @param activeCode
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/users/confirm_verification/{active_code}")
	public String confirmVerification(@PathVariable("active_code") String activeCode,
			Map<String, String> map,HttpSession session){
		userService.executeActiveUser(activeCode,session);
		if((int) session.getAttribute("activeStatus")==1){
			map.put("message", "恭喜您,激活成功,请登录!");
		}else{
			map.put("message", "您的账号已经激活过了,请直接登录!");
		}
		return "login";
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session){
		session.removeAttribute("user");
		return "redirect:/index";
	}
	
	
	@RequestMapping(path="/user/shouCangInfo",method=RequestMethod.GET)
	public String shouCangInfo(){
		
		return "client/user/shouCangInfo";
		
	}
	
	@RequestMapping(path="/user/jifenInfo",method=RequestMethod.GET)
	public String jifenInfo(){
		
		return "client/user/jifenInfo";
		
	}
	
}
