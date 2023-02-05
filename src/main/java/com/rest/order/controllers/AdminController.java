package com.rest.order.controllers;

import com.rest.order.dao.JWTUtil;
import com.rest.order.dao.UserRolePrivilegesService;
import com.rest.order.models.privilege.PrivilegeResponse;
import com.rest.order.models.privilege.RoleResponse;
import com.rest.order.models.privilege.UserPrivilegeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    private UserRolePrivilegesService userRolePrivilegesService;

    @GetMapping("/privileges")
    public ResponseEntity<List<PrivilegeResponse>> getAllPrivileges() {
        List<PrivilegeResponse> privilegesResponse = userRolePrivilegesService.getAllPrivileges();
        return new ResponseEntity<>(privilegesResponse, HttpStatus.OK);
    }

    @GetMapping("/{roleId}/privileges")
    public ResponseEntity<List<PrivilegeResponse>> getAllPrivileges(@PathVariable(name = "roleId") String roleId) {
        List<PrivilegeResponse> privilegesResponse = userRolePrivilegesService.getAllPrivilegesByRoleId(roleId);
        return new ResponseEntity<>(privilegesResponse, HttpStatus.OK);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<RoleResponse>> getAllRoles() {
        List<RoleResponse> privilegesResponse = userRolePrivilegesService.getAllRoles();
        return new ResponseEntity<>(privilegesResponse, HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserPrivilegeResponse>> getAllUsersWithPrivileges(
            @RequestParam(required = false, value = "pageNo", defaultValue = "0") int pageNo,
            @RequestParam(required = false, value = "pageSize", defaultValue = "5") int pageSize) {
        List<UserPrivilegeResponse> userPrivilegesResponse = userRolePrivilegesService.getAllUsersWithPrivileges(pageNo, pageSize);
        return new ResponseEntity<>(userPrivilegesResponse, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserPrivilegeResponse> getUsersWithPrivilegesById(
            @PathVariable(name = "userId") String userId) {
        UserPrivilegeResponse userPrivilegesResponse = userRolePrivilegesService.getUsersWithPrivilegesById(userId);
        return new ResponseEntity<>(userPrivilegesResponse, HttpStatus.OK);
    }

    @GetMapping("/getAllPrivileges")
    public ResponseEntity<List<String>> userPrivilegesPending(@RequestHeader(name = "Authorization") String authToken) {
        String userName = JWTUtil.getDecodedJWTUserName(authToken);
        List<String> privilegesResponse = userRolePrivilegesService.privilegesPending(userName);
        return new ResponseEntity<>(privilegesResponse, HttpStatus.OK);
    }

}
