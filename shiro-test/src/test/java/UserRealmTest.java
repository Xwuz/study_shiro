import com.spring.shiro.realm.UserRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @author tiandao
 * @date 2018/9/3 20:40
 */
public class UserRealmTest {

    @Test
    public void testAuthentication(){
        Md5Hash md5Hash = new Md5Hash("mmm","mary");
        System.out.println(md5Hash.toString());

        UserRealm userRealm = new UserRealm();

        // 1.构建securityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(userRealm);

        // 使用散列加密
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        // 加密算法
        matcher.setHashAlgorithmName("md5");
        // 加密次数
        matcher.setHashIterations(1);

        userRealm.setCredentialsMatcher(matcher);
        // 2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("mary", "mmm");
        subject.login(token);
        System.out.println("subject.isAuthenticated():" + subject.isAuthenticated());
//        subject.isAuthenticated();
        subject.checkRole("admin");
        subject.checkPermission("user:update");
//        subject.logout();
//        System.out.println("subject.isAuthenticated():" + subject.isAuthenticated());
    }
}
