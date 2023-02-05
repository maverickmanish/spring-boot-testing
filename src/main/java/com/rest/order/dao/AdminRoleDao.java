package com.rest.order.dao;


import com.rest.order.models.roles.AdminRoleEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRoleDao {

    String getAdminUserRoleByUserName(String userName) throws Exception;

    List<AdminRoleEntity> getAllRoles() throws Exception;

    List<AdminRoleEntity> getUserRoleByEmail(String emailId);

    String getRoleName(String roleId) throws Exception;

    List<AdminRoleEntity> getUserRoleById(String userId);
}
