package com.spring.shiro.entity;

/**
 * @author tiandao
 * @date 2018/9/4 13:44
 */
public class UserRoles {

    private Integer roleId;
    private String username;
    private String roleName;

    @Override
    public String toString() {
        return "UserRoles{" +
                "roleId=" + roleId +
                ", username='" + username + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
