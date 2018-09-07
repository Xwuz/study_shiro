import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * @author tiandao
 * @date 2018/9/3 19:23
 */
public class AuthenticationTest {

    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Before
    public void addUser(){
        simpleAccountRealm.addAccount("123","123","admin");
    }

    /**
     * Shiro认证
     *
     * 流程：创建Security Manager 管理，然后主体进行数据提交认证，转移到相应的Security Manager中，然后权限管理调用相应的Authenticator认证，认证部分调用相应的Realm去连接数据源进行数据验证。
     *
     * ps：Realm是在Security Manager中，所以需要设置Realm到Security Manager中，才能调用。
     */
    @Test
    public void testAuthentication(){

        // 1.构建securityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);
        // 2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("123", "123");
        subject.login(token);
        System.out.println("subject.isAuthenticated():" + subject.isAuthenticated());
        subject.isAuthenticated();
        subject.checkRole("admin");
//        subject.logout();
//        System.out.println("subject.isAuthenticated():" + subject.isAuthenticated());
    }
}
