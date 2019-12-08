package com.bawei.cms.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bawei.cms.entity.User;
import com.bawei.cms.utils.TimeRandomUtil;
import com.bawei.cms.utils.UserRandomUtil;

@SuppressWarnings("all")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context.xml")
public class UserTest {
	
	@Resource
	private RedisTemplate redisTemplate;
	
	@Test
	public void userJdkTest(){
		// 当前时间
		long time1 = System.currentTimeMillis();
		
		// string 类型
		//ValueOperations ops = redisTemplate.opsForValue();
		
		//hash类型
		BoundHashOperations ops_hash = redisTemplate.boundHashOps("hash_user");
		
		for (int i = 1; i <= 100000; i++) {
			User user = new User();
			// 序号
			user.setId(i);
			// 姓名
			user.setName(UserRandomUtil.getChineseName());
			// 性别
			user.setSex(UserRandomUtil.getSex());
			// 手机
			user.setPhone(UserRandomUtil.getPhone());
			// 邮箱
			user.setEmail(UserRandomUtil.getEmail());
			// 生日
			user.setBirthday(TimeRandomUtil.randomDate("1949-01-01 00:00:00", "2001-01-01 00:00:00"));
			// System.out.println(user);
			
			// 存入redis中
			//ops.set(i+"", user);
			
			ops_hash.put(i+"", user.toString());
			
		}
		
		long time2 = System.currentTimeMillis();
		long time = time2 - time1;
		
		System.out.println("序列化方式: hash, 耗时: " + time);
	}
	
	
	
}
