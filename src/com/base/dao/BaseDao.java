package com.base.dao;

import java.util.List;

import com.base.utils.ResponseList;

/**
 * 
 * @author zhang
 *
 */
public class BaseDao {

	protected <E> ResponseList<E> buildResponseList(List<E> paramList) {
		ResponseList<E> localResponseList = new ResponseList<E>(paramList.size());
		localResponseList.addAll(paramList);
		paramList = null;
		return localResponseList;
	}
}
