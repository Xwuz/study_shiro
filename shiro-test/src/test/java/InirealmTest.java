import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @author tiandao
 * @date 2018/9/3 19:47
 */
public class InirealmTest {

    @Test
    public void testAuthentication(){

        IniRealm iniRealm = new IniRealm("classpath:user.ini");

        // 1.构建securityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(iniRealm);




        // 2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("mary", "mmm");
        subject.login(token);
        System.out.println("subject.isAuthenticated():" + subject.isAuthenticated());
        subject.isAuthenticated();
        subject.checkRole("admin");
        subject.checkPermission("user.update");
//        subject.logout();
//        System.out.println("subject.isAuthenticated():" + subject.isAuthenticated());
    }
}
