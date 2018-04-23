package com.css.etax.service;

import com.css.etax.utils.Result;

public interface PublicMethodService {
	public Result setRequestRedis(String key, String value);

	public Result getRequestRedis(String key);

	public Result setResponseRedis(String key, String value);

	public Result getResponseRedis(String key);
	
	public Result delRequestRedis(String key);
	
	public Result delResponseRedis(String key);
	
	public Result delAllValueByKey(String key);
	
	public Result flushRequestDb(String validMsg);

	public Result flushResponseDb(String validMsg);
	
	public Result flushAll(String validMsg);

}
