package com.cab.graduation.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cab.graduation.dao.AddressDAO;
import com.cab.graduation.entities.Address;

@Repository("addressDAO")
public class AddressDAOImpl implements AddressDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void add(Address address) {
		getSession().save(address);

	}

	@Override
	public void delete(Address address) {
		getSession().delete(address);
		
	}

	@Override
	public void update(Address address) {
		getSession().update(address);

	}

	@Override
	public Address findAddressById(Serializable addrId) {
		return (Address) getSession().get(Address.class, addrId);

	}

	@Override
	public List<Address> list(Integer userId) {
		String hql="from Address address where address.user.userId=?";
		
		List<Address> addresses=getSession().createQuery(hql)
											.setInteger(0, userId)
									 		.list();
		return addresses;
		
	}

	@Override
	public void updateDefaultAddressStatusByAddrId(String defaultAddress) {
		String hql="update Address address set address.defaultAddress=? where address.defaultAddress=?";
		Query query=getSession().createQuery(hql);
		if("1".equals(defaultAddress)){
			query.setString(0, "0").setString(1, "1")
				 .executeUpdate();
		}
		
	}

	@Override
	public Address findAddressByUserIdAnddefaultAddressStatus(Integer userId, String defaultAddressStatus) {
		String hql="from Address address where address.user.userId=? and address.defaultAddress=?";
		List<Address> addresses=getSession().createQuery(hql).setInteger(0, userId)
									 .setString(1, defaultAddressStatus)
									 .list();
		if(addresses!=null){
			if(addresses.size()>0){
				return addresses.get(0);
			}
		}
		return null;
	}

}
