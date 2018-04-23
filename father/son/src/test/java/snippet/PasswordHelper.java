package snippet;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordHelper {
	private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	private String algorithmName = "md5";
	private final int hashIterations = 2;

	public void encryptPassword(String password) {
		// user.setSalt(randomNumberGenerator.nextBytes().toHex());
		String salt = randomNumberGenerator.nextBytes().toHex();
		String newPassword = new SimpleHash(algorithmName, password,
				ByteSource.Util.bytes(salt), hashIterations).toHex();
		System.out.println(newPassword);
	}

	public static void main(String[] args) {
		PasswordHelper a = new PasswordHelper();
		a.encryptPassword("123456");
	}
}