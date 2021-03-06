package com.cab.graduation.dao;

import java.io.Serializable;
import java.util.List;

import com.cab.graduation.entities.Address;

public interface AddressDAO {
	
	void add(Address address);
	void delete(Address address);
	void update(Address address);
	void updateDefaultAddressStatusByAddrId(String defaultAddress);
	Address findAddressById(Serializable addrId);
	Address findAddressByUserIdAnddefaultAddressStatus(Integer userId,String defaultAddressStatus);
	List<Address> list(Integer userId);
	
}
