--1. t_user表结构
create table t_user(
	u_id int not null auto_increment,
	u_name varchar(50) not null,
	password varchar(100) not null,
	email varchar(50) not null,
	state int default 0,
	active_code varchar(255) not null,
	primary key (user_id)
	
)

--2.t_addr表结构
create table t_addr(
	addr_id int not null auto_increment,
	address varchar(255) not null,
	receiver varchar(50) not null,
	receiver_phone varchar(11) not null,
	user_id int not null,
	primary key (addr_id),
	foreign key (user_id) references t_user(u_id)
)

--3. t_order表结构
create table t_order(
	--订单id
	o_id int not null auto_increment,
	--订单号
	o_no varchar(100) not null,
	--订单总价
	o_totalprice double not null,
	--订单产生时间
	o_time datetime default now(),
	--是否付款
	isPay boolean default false,
	--订单所属用户的id
	user_id int not null,
	primary key (o_id),
	foreign key (user_id) references t_user(u_id)
)

--4. t_orderitem表结构
create table t_orderitem(
	oi_id int not null auto_increment,
	oi_num int not null,
	oi_subtotal double not null,
	order_id int not null,
	good_id int not null,
	primary key (oi_id),
	foreign key (order_id) references t_order(o_id),
	foreign key (good_id) references t_goods(g_id),
	check (oi_num>0)
)

--5. t_cart
create table t_cart(
	c_id int not null auto_increment,
	c_num int not null,
	c_subtotal double not null,
	user_id int not null,
	good_id int not null,
	primary key (c_id),
	foreign key (user_id) references t_user(u_id),
	foreign key (good_id) references t_goods(g_id),
	check (c_num>0)
)

--6. t_goods表结构
create table t_goods(
	g_id int not null auto_increment,
	g_name varchar(200) not null,
	g_price double not null,
	g_describe varchar(255) not null,
	primary key (g_id)
)