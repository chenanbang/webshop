package com.cab.graduation.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cab.graduation.dao.AddressDAO;
import com.cab.graduation.entities.Address;
import com.cab.graduation.service.AddressService;

@Service("addressService")
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressDAO addressDAO;
	
	@Override
	public void add(Address address) {
		addressDAO.add(address);

	}

	@Override
	public void delete(Address address) {
		addressDAO.delete(address);

	}

	@Override
	public void update(Address address) {
		addressDAO.update(address);

	}

	@Override
	public Address findAddressById(Serializable addrId) {
		return addressDAO.findAddressById(addrId); 
		
	}

	@Override
	public List<Address> list(Integer userId) {
		return addressDAO.list(userId);
		
	}

	@Override
	public void updateDefaultAddressStatusByAddrId(String defaultAddress) {
		addressDAO.updateDefaultAddressStatusByAddrId(defaultAddress);
		
	}

	@Override
	public Address findAddressByUserIdAnddefaultAddressStatus(Integer userId, String defaultAddressStatus) {
		return addressDAO.findAddressByUserIdAnddefaultAddressStatus(userId, defaultAddressStatus);
		
	}
	
	
}
