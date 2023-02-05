package com.rest.order.dao;

import com.rest.order.models.roles.AdminPrivilegeEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminPrivilegeDao {

    List<AdminPrivilegeEntity> getAdminUserPrivilegesByUserName(String userName);

    List<AdminPrivilegeEntity> getRolePrivileges(String roleName);

    List<AdminPrivilegeEntity> getAllPrivileges();

    List<AdminPrivilegeEntity> getUserPrivilegesByEmail(String email);

    List<AdminPrivilegeEntity> getAllPrivilegesByRoleName(String roleName);

    List<AdminPrivilegeEntity> getUserPrivilegesByUserId(String userId);
}
