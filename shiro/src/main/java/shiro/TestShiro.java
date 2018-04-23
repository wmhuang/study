package shiro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class TestShiro {
	private void login(String configFile, String userName, String passWord) {
		// 1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(
				configFile);

		// 2、得到SecurityManager实例 并绑定给SecurityUtils
		org.apache.shiro.mgt.SecurityManager securityManager = factory
				.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		// 3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(userName,
				passWord);

		subject.login(token);
	}

	// @Test
	// public void testAllSuccessfulStrategyWithSuccess() {
	// login("classpath:shiro-authenticator-all-success.ini");
	// Subject subject = SecurityUtils.getSubject();
	//
	// // 得到一个身份集合，其包含了Realm验证成功的身份信息
	// PrincipalCollection principalCollection = subject.getPrincipals();
	// Assert.assertEquals(2, principalCollection.asList().size());
	// }

	// @Test(expected = UnknownAccountException.class)
	// public void testAllSuccessfulStrategyWithFail() {
	// login("classpath:shiro-authenticator-all-fail.ini");
	// Subject subject = SecurityUtils.getSubject();
	// }

	// @Test
	public void testHelloworld() {
		// 1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(
				"classpath:shiro.ini");

		// 2、得到SecurityManager实例 并绑定给SecurityUtils
		org.apache.shiro.mgt.SecurityManager securityManager = factory
				.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		// 3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();

		UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");

		try {
			// 4、登录，即身份验证
			subject.login(token);
		} catch (AuthenticationException e) {
			// 5、身份验证失败
			System.out.println("验证失败");
		}

		Assert.assertEquals(true, subject.isAuthenticated()); // 断言用户已经登录

		// 6、退出
		subject.logout();
	}

	public Subject subject() {
		Subject subject= SecurityUtils.getSubject();
		return subject;
	}

	// @Test
	public void testHasRole() {
		login("classpath:shiro-roles.ini", "zhang", "123");
		// 判断拥有角色：role1
		Assert.assertTrue(subject().hasRole("role1"));
		// 判断拥有角色：role1 and role2
		Assert.assertTrue(subject()
				.hasAllRoles(Arrays.asList("role1", "role2")));
		// 判断拥有角色：role1 and role2 and !role3
		boolean[] result = subject().hasRoles(
				Arrays.asList("role1", "role2", "role3"));
		Assert.assertEquals(true, result[0]);
		Assert.assertEquals(true, result[1]);
		Assert.assertEquals(false, result[2]);
	}

	
	@Test
	public void testIsPermitted() {
		login("classpath:shiro-permission.ini", "zhang", "123");
		subject().checkPermissions("user:*");
		boolean result[] =subject().isPermitted("user:delete","user:create","user:update");
		System.out.println(result[0]);
		System.out.println(result[1]);
		System.out.println(result[2]);
		
		// 判断拥有权限：user:create
//		Assert.assertTrue(subject().isPermitted("user:create"));
		// 判断拥有权限：user:update and user:delete
//		Assert.assertTrue(subject()
//				.isPermittedAll("user:update", "user:delete"));
		// 判断没有权限：user:view
//		Assert.assertFalse(subject().isPermitted("user:view"));
	}
}
