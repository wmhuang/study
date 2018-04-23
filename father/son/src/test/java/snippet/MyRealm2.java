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
		// 仅支持UsernamePasswordToken类型的token
		return token instanceof UsernamePasswordToken;
	}

	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		String userName = (String) token.getPrincipal();
		String passWord = new String((char[]) token.getCredentials());
		if (!"wang".equals(userName)) {
			throw new UnknownAccountException();// 未知的账号
		}
		if (!"123".equals(passWord)) {
			throw new IncorrectCredentialsException();// 密码错误
		}

		// 如果身份认证验证成功，返回一个AuthenticationInfo实现；
		return new SimpleAuthenticationInfo(userName, passWord, getName());
	}

}
