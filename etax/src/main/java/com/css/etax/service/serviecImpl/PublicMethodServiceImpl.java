package com.css.etax.service.serviecImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.css.etax.service.PublicMethodService;
import com.css.etax.utils.RedisUtils;
import com.css.etax.utils.Result;

@Component
public class PublicMethodServiceImpl implements PublicMethodService {

	@Autowired
	private RedisUtils redisUtils;

	@Override
	public Result setRequestRedis(String key, String value) {
		if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
			return new Result(false, "参数异常，参数key和value不能为空");
		}
		try {
			String redisResult = redisUtils.setValue(key, value, true);
			return new Result(true, "Redis请求库入库成功", redisResult);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "Redis请求库入库异常" + e.getMessage());
		}

	}

	@Override
	public Result getRequestRedis(String key) {
		if (StringUtils.isEmpty(key)) {
			return new Result(false, "参数异常，参数key不能为空");
		}
		try {
			String value = redisUtils.getValue(key, true);
			if (StringUtils.isEmpty(value)) {
				return new Result(false, "该key=【" + key + "】对应的value为空。", value);
			}
			return new Result(true, "获取Reids请求库数据成功", value);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "获取Reids请求库数据时发生异常，" + e.getMessage());
		}
	}

	@Override
	public Result setResponseRedis(String key, String value) {
		if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
			return new Result(false, "参数异常，参数key和value不能为空");
		}
		try {
			String redisResult = redisUtils.setValue(key, value, false);
			return new Result(true, "Redis返回库入库成功", redisResult);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "Redis返回库入库异常" + e.getMessage());
		}
	}

	@Override
	public Result getResponseRedis(String key) {
		if (StringUtils.isEmpty(key)) {
			return new Result(false, "参数异常，参数key不能为空");
		}
		try {
			String value = redisUtils.getValue(key, false);
			if (StringUtils.isEmpty(value)) {
				return new Result(false, "该key=【" + key + "】对应的value为空。", value);
			}
			return new Result(true, "获取Redis返回库数据成功", value);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "获取Redis返回库数据异常，" + e.getMessage());
		}
	}

	@Override
	public Result delRequestRedis(String key) {
		if (StringUtils.isEmpty(key)) {
			return new Result(false, "参数异常，参数key不能为空");
		}
		try {
			Long redisResult = redisUtils.delKey(key, true);
			return new Result(true, "删除 Redis请求库对应的key=【" + key + "】成功",
					redisResult);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除Redis请求库对应的key=【" + key + "】失败",
					e.getMessage());
		}
	}

	@Override
	public Result delResponseRedis(String key) {
		if (StringUtils.isEmpty(key)) {
			return new Result(false, "参数异常，参数key不能为空");
		}
		try {
			Long redisResult = redisUtils.delKey(key, false);
			return new Result(true, "删除 Redis返回库对应的key=【" + key + "】成功",
					redisResult);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除Redis返回库对应的key=【" + key + "】失败",
					e.getMessage());
		}
	}

	@Override
	public Result delAllValueByKey(String key) {
		Long redisResult[] = new Long[2];
		if (StringUtils.isEmpty(key)) {
			return new Result(false, "参数异常，参数key不能为空");
		}

		try {
			Long redisResult0 = redisUtils.delKey(key, true);
			redisResult[0] = redisResult0;
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除Redis请求库对应的key=【" + key + "】失败",
					e.getMessage());
		}

		try {
			Long redisResult1 = redisUtils.delKey(key, false);
			redisResult[1] = redisResult1;
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除Redis返回库对应的key=【" + key + "】失败",
					e.getMessage());
		}

		return new Result(true, "删除 Redis请求库和返回库对应的key=【" + key + "】成功",
				redisResult);
	}

	@Override
	public Result flushRequestDb(String validMsg) {
		if (StringUtils.isEmpty(validMsg) || !"FLUSHREDIS".equals(validMsg)) {
			return new Result(false, "清空请求库中的所有数据失败，验证失败");
		}
		try {
			String redisResult = redisUtils.flushDb(true);
			return new Result(true, "清空请求库中的所有数据完成", redisResult);
		} catch (Exception e) {
			return new Result(false, "清空请求库中的所有数据失败" + e.getMessage());
		}
	}

	@Override
	public Result flushResponseDb(String validMsg) {
		if (StringUtils.isEmpty(validMsg) || !"FLUSHREDIS".equals(validMsg)) {
			return new Result(false, "清空返回库中的所有数据失败，验证失败");
		}
		try {
			String redisResult = redisUtils.flushDb(false);
			return new Result(true, "清空返回库中的所有数据完成", redisResult);
		} catch (Exception e) {
			return new Result(false, "清空返回库中的所有数据失败" + e.getMessage());
		}
	}

	@Override
	public Result flushAll(String validMsg) {
		if (StringUtils.isEmpty(validMsg) || !"FLUSHREDIS".equals(validMsg)) {
			return new Result(false, "清空Redis库中的所有数据失败，验证失败");
		}
		try {
			String redisResult = redisUtils.flushAll();
			return new Result(true, "清空Redis库中的所有数据完成", redisResult);
		} catch (Exception e) {
			return new Result(false, "清空Redis库中的所有数据失败" + e.getMessage());
		}
	}

}
