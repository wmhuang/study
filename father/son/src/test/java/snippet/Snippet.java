package snippet;

import junit.framework.Assert;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class Snippet {
	@Test
	public void testHelloworld() {
		// 1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(
				"classpath:shiro-permission.ini");
		// 2、得到SecurityManager实例 并绑定给SecurityUtils
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		// 3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
//		UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
		UsernamePasswordToken token = new UsernamePasswordToken("wang", "123");
		try {
			// 4、登录，即身份验证
			subject.login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
			// 5、身份验证失败
		}
		Assert.assertEquals(true, subject.isAuthenticated()); // 断言用户已经登录
		System.out.println("登录成功");
		System.out.println(subject.getPrincipal()+"用户属于角色role1："+subject.hasRole("role1"));
		System.out.println(subject.getPrincipal()+"用户属于角色role2："+subject.hasRole("role2"));
		System.out.println(subject.getPrincipal()+"用户属于角色role3："+subject.hasRole("role3"));
		System.out.println(subject.isPermitted("user:delete"));
		// 6、退出
		subject.logout();
		System.out.println("注销成功");
	}
}
