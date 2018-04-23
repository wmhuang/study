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
		// 1����ȡSecurityManager�������˴�ʹ��Ini�����ļ���ʼ��SecurityManager
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(
				"classpath:shiro-permission.ini");
		// 2���õ�SecurityManagerʵ�� ���󶨸�SecurityUtils
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		// 3���õ�Subject�������û���/���������֤Token�����û����/ƾ֤��
		Subject subject = SecurityUtils.getSubject();
//		UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
		UsernamePasswordToken token = new UsernamePasswordToken("wang", "123");
		try {
			// 4����¼���������֤
			subject.login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
			// 5�������֤ʧ��
		}
		Assert.assertEquals(true, subject.isAuthenticated()); // �����û��Ѿ���¼
		System.out.println("��¼�ɹ�");
		System.out.println(subject.getPrincipal()+"�û����ڽ�ɫrole1��"+subject.hasRole("role1"));
		System.out.println(subject.getPrincipal()+"�û����ڽ�ɫrole2��"+subject.hasRole("role2"));
		System.out.println(subject.getPrincipal()+"�û����ڽ�ɫrole3��"+subject.hasRole("role3"));
		System.out.println(subject.isPermitted("user:delete"));
		// 6���˳�
		subject.logout();
		System.out.println("ע���ɹ�");
	}
}
