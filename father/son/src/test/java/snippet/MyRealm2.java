package snippet;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

public class MyRealm2 implements Realm {

	public String getName() {
		return "myRealm2";
	}

	public boolean supports(AuthenticationToken token) {
		// ��֧��UsernamePasswordToken���͵�token
		return token instanceof UsernamePasswordToken;
	}

	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		String userName = (String) token.getPrincipal();
		String passWord = new String((char[]) token.getCredentials());
		if (!"wang".equals(userName)) {
			throw new UnknownAccountException();// δ֪���˺�
		}
		if (!"123".equals(passWord)) {
			throw new IncorrectCredentialsException();// �������
		}

		// ��������֤��֤�ɹ�������һ��AuthenticationInfoʵ�֣�
		return new SimpleAuthenticationInfo(userName, passWord, getName());
	}

}
