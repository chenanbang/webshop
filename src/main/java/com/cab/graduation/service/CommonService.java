package com.cab.graduation.service;

import com.cab.graduation.utils.Conditions;
import com.cab.graduation.utils.Page;

public interface CommonService {
	long queryCountWidthConditions(Class clazz,Conditions conditions);
	<T> void pageQuery(Class clazz,Page<T> page,Conditions conditions);
}
