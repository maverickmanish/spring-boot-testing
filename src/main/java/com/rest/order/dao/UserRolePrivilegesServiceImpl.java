package com.rest.order.dao;

import com.rest.order.constants.ErrorConstants;
import com.rest.order.constants.ErrorMessage;
import com.rest.order.exception.SupernovaException;
import com.rest.order.models.privilege.PrivilegeResponse;
import com.rest.order.models.privilege.RoleResponse;
import com.rest.order.models.privilege.UserPrivilegeResponse;
import com.rest.order.models.roles.AdminPrivilegeEntity;
import com.rest.order.models.roles.AdminRoleEntity;
import com.rest.order.models.roles.AdminUserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class UserRolePrivilegesServiceImpl implements UserRolePrivilegesService {

    private static final String ACTUAL_MSG = "";
    @Autowired
    private AdminPrivilegeDao adminPrivilegeDao;

    @Autowired
    private AdminRoleDao adminRoleDao;

    @Autowired
    private AdminUserDao adminUserDao;


    @Override
    public List<RoleResponse> getAllRoles() {
        List<RoleResponse> response;
        List<AdminRoleEntity> allRoles;
        try {
            allRoles = adminRoleDao.getAllRoles();
            response = RolePrivilegesUtil.getRoleResponses(allRoles);

        } catch (Exception e) {
            log.error(ErrorMessage.ROLES_NOT_FOUND + ACTUAL_MSG, e.getMessage());
            throw new SupernovaException(ErrorConstants.ROLES_NOT_FOUND, e.getMessage(), null, HttpStatus.BAD_REQUEST);

        }
        return response;
    }

    @Override
    public List<UserPrivilegeResponse> getAllUsersWithPrivileges(int pageNo, int pageSize) {
        List<UserPrivilegeResponse> userPrivilegeResponseList = new ArrayList<>();
        int startRange = pageNo * pageSize;
        int endRange = startRange + pageSize;
        try {
            List<AdminUserEntity> usersList = adminUserDao.getAllUsers(startRange, endRange);
            usersList.forEach(user -> {

                List<RoleResponse> roleResponse = RolePrivilegesUtil.getRoleResponses(user.getRole());
                List<PrivilegeResponse> privilegeResponse = RolePrivilegesUtil
                        .getPrivilegeResponses(user.getPrivileges());
                UserPrivilegeResponse userPrivilegeResponse = RolePrivilegesUtil.userPrivilegeResponseBuilder(user,
                        roleResponse, privilegeResponse);
                userPrivilegeResponseList.add(userPrivilegeResponse);
            });

        } catch (Exception e) {
            log.error(ErrorMessage.USERS_NOT_FOUND + ACTUAL_MSG, e.getMessage());
            throw new SupernovaException(ErrorConstants.USER_NOT_FOUND, e.getMessage(), null, HttpStatus.BAD_REQUEST);
        }
        return userPrivilegeResponseList;
    }

    @Override
    public UserPrivilegeResponse getUsersWithPrivilegesById(String userId) {
        UserPrivilegeResponse userPrivilegeResponse;
        try {
            AdminUserEntity userById = adminUserDao.getUserById(userId);
            List<RoleResponse> roleResponse = RolePrivilegesUtil.getRoleResponses(userById.getRole());
            List<PrivilegeResponse> privilegeResponse = RolePrivilegesUtil
                    .getPrivilegeResponses(userById.getPrivileges());
            userPrivilegeResponse = RolePrivilegesUtil.userPrivilegeResponseBuilder(userById, roleResponse,
                    privilegeResponse);
        } catch (Exception e) {
            log.error(ErrorConstants.USER_NOT_FOUND + ACTUAL_MSG, e.getMessage());
            throw new SupernovaException(ErrorConstants.USER_NOT_FOUND, e.getMessage(), null, HttpStatus.BAD_REQUEST);
        }
        return userPrivilegeResponse;
    }

    @Override
    public List<PrivilegeResponse> getAllPrivilegesByRoleId(String roleId) {
        List<PrivilegeResponse> response;
        List<AdminPrivilegeEntity> allPrivileges;
        try {
            String roleName = adminRoleDao.getRoleName(roleId);
            allPrivileges = adminPrivilegeDao.getAllPrivilegesByRoleName(roleName);
            response = RolePrivilegesUtil.getPrivilegeResponses(allPrivileges);
        } catch (Exception e) {
            log.error(ErrorMessage.PRIVILEGES_NOT_FOUND + ACTUAL_MSG, e.getMessage());
            throw new SupernovaException(ErrorMessage.PRIVILEGES_NOT_FOUND, e.getMessage(), null,
                    HttpStatus.BAD_REQUEST);
        }
        return response;

    }

    @Override
    public List<PrivilegeResponse> getAllPrivileges() {
        List<PrivilegeResponse> response;
        List<AdminPrivilegeEntity> allPrivileges;
        try {
            allPrivileges = adminPrivilegeDao.getAllPrivileges();
            response = RolePrivilegesUtil.getPrivilegeResponses(allPrivileges);
        } catch (Exception e) {
            log.error(ErrorMessage.PRIVILEGE_NOT_FOUND + ACTUAL_MSG, e.getMessage());
            throw new SupernovaException(ErrorMessage.PRIVILEGE_NOT_FOUND, e.getMessage(), null,
                    HttpStatus.BAD_REQUEST);
        }
        return response;

    }

    @Override
    public List<String> privilegesPending(String userName) {

        List<String> response;
        List<AdminPrivilegeEntity> userPrivileges;
        List<AdminPrivilegeEntity> collect;
        try {

            userPrivileges = adminPrivilegeDao.getAdminUserPrivilegesByUserName(userName);
            String adminUserRole = adminRoleDao.getAdminUserRoleByUserName(userName);
            List<AdminPrivilegeEntity> rolePrivileges = adminPrivilegeDao.getRolePrivileges(adminUserRole);
            response = rolePrivileges.stream().filter(privilege -> {
                if (userPrivileges.contains(privilege))
                    return false;
                return true;
            }).map(AdminPrivilegeEntity::getPrivilegeName).collect(Collectors.toList());

        } catch (Exception e) {
            log.error(ErrorMessage.PRIVILEGE_NOT_FOUND + ACTUAL_MSG, e.getMessage());
            throw new SupernovaException(ErrorMessage.PRIVILEGE_NOT_FOUND, e.getMessage(), null,
                    HttpStatus.BAD_REQUEST);

        }
        return response;

    }
}
