package com.base.utils;

import java.util.Comparator;

import com.yy.yyUser.model.YyUser;

@SuppressWarnings("rawtypes")
public class ComparatorUser implements Comparator{
	public int compare(Object obj0, Object obj1) {
		YyUser user0=(YyUser)obj0;
		YyUser user1=(YyUser)obj1;
		int flag=user1.getStudy_time().compareTo(user0.getStudy_time());
		if(flag==0){
			return user0.getName().compareTo(user1.getName());
		}else{
		   return flag;
		}
	}
}
