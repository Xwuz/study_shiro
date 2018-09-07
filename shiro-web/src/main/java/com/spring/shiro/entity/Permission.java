package com.spring.shiro.entity;

/**
 * @author tiandao
 * @date 2018/9/4 13:52
 */
public class Permission {

    private Integer permissionId;
    private String roleName;
    private String permission;

    @Override
    public String toString() {
        return "Permission{" +
                "permissionId=" + permissionId +
                ", roleName='" + roleName + '\'' +
                ", permission='" + permission + '\'' +
                '}';
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
