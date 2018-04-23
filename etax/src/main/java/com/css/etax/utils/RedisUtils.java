package com.css.etax.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import redis.clients.jedis.Jedis;

@Component
public class RedisUtils {

	@Value("${etax.redis.ip}")
	private String redisIp;

	@Value("${etax.redis.port}")
	private int redisPort;

	@Value("${etax.redis.password}")
	private String password;

	@Value("${etax.redis.request.db}")
	private int requestDb;

	@Value("${etax.redis.response.db}")
	private int responseDb;

	private Jedis jedis;

	/**
	 * 获取jedis连接对象
	 * 
	 * @param isRequest
	 *            true：请求库 false：返回库
	 * @return
	 * @author wmhuang
	 * @throws Exception
	 */
	public Jedis jedis(boolean isRequest) throws Exception {
		Jedis jedis = new Jedis(redisIp, redisPort);
		if (!StringUtils.isEmpty(password)) {
			jedis.auth(password);
		}
		if (isRequest) {
			jedis.select(requestDb);
		} else {
			jedis.select(responseDb);
		}
		return jedis;
	}

	/**
	 * 设置redis中的数据
	 * 
	 * @param key
	 * @param value
	 * @param isRequest
	 *            true：请求库 false：返回库
	 * @return
	 * @throws Exception
	 */
	public String setValue(String key, String value, boolean isRequest)
			throws Exception {
		jedis = jedis(isRequest);
		return jedis.set(key, value);
	}

	/**
	 * 获取redis中的数据
	 * 
	 * @param isRequest
	 *            true：请求库 false：返回库
	 * @return
	 * @throws Exception
	 * @author wmhuang
	 */
	public String getValue(String key, boolean isRequest) throws Exception {
		jedis = jedis(isRequest);
		return jedis.get(key);
	}

	/**
	 * 删除redis中的数据
	 * 
	 * @param key
	 * @param isRequest
	 *            true：请求库 false：返回库
	 * @return 1：删除 0：对应的数据不存在
	 * @throws Exception
	 */
	public Long delKey(String key, boolean isRequest) throws Exception {
		jedis = jedis(isRequest);
		return jedis.del(key);
	}

	/**
	 * 清理选中库的所有数据
	 * 
	 * @param isRequest
	 *            true:请求库 false：返回库
	 * @return
	 * @throws Exception
	 */
	public String flushDb(boolean isRequest) throws Exception {
		jedis = jedis(isRequest);
		return jedis.flushDB();
	}
	
	/**
	 * 清理整个redis服务的所有数据！！！
	 * @return
	 * @throws Exception
	 */
	public String flushAll() throws Exception {
		jedis = jedis(true);
		return jedis.flushAll();
	}

}
