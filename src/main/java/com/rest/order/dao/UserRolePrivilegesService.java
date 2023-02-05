package com.rest.order.dao;


import com.rest.order.models.privilege.PrivilegeResponse;
import com.rest.order.models.privilege.RoleResponse;
import com.rest.order.models.privilege.UserPrivilegeResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserRolePrivilegesService {

    List<String> privilegesPending(String userName);

    List<PrivilegeResponse> getAllPrivileges();

    List<RoleResponse> getAllRoles();

    List<PrivilegeResponse> getAllPrivilegesByRoleId(String roleId);

    UserPrivilegeResponse getUsersWithPrivilegesById(String userId);

    List<UserPrivilegeResponse> getAllUsersWithPrivileges(int pageNo, int pageSize);
}
