package com.t3k.fams.scantest.Permission;

public interface PermissionInterface {

    int getRequestCode();
    String[] getPermissions();
    void getPermissionSuccess();
    void getPermissionFail();
}
