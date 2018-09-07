import com.alibaba.druid.pool.DruidDataSource;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @author tiandao
 * @date 2018/9/3 19:53
 */
public class JdbcRealmTest {

    DruidDataSource dataSource = new DruidDataSource();
    {
        dataSource.setUrl("jdbc:mysql:///test");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
    }

    @Test
    public void testAuthentication(){

        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(dataSource);
        jdbcRealm.setPermissionsLookupEnabled(true);

        String sql = "select password from users where userName = ?";
        jdbcRealm.setAuthenticationQuery(sql);

        String roleSql = "select role_name from user_roles where username = ?";
        jdbcRealm.setUserRolesQuery(roleSql);

        String permissionSql = "select permission from roles_permissions where role_name = ?";
        jdbcRealm.setPermissionsQuery(permissionSql);
        // 1.构建securityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(jdbcRealm);
        // 2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("mary", "mmm");
        subject.login(token);
        System.out.println("subject.isAuthenticated():" + subject.isAuthenticated());
//        subject.isAuthenticated();
        subject.checkRole("admin");
        subject.checkRoles("admin","user");

        subject.checkPermission("user:update");
//        subject.logout();
//        System.out.println("subject.isAuthenticated():" + subject.isAuthenticated());
    }

}
