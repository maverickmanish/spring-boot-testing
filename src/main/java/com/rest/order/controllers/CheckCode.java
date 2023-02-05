package com.rest.order.controllers;

import com.rest.order.controllers.reftype.PrivilegeType;
import com.rest.order.controllers.reftype.RoleType;
import com.rest.order.models.roles.AdminRoleEntity;
import org.springframework.util.CollectionUtils;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckCode {
    public static void main(String[] args) {
        Map<String, List<Object>> e1 = new HashMap<>();
        e1.put("superadmin", null);
        List<Map<String, List<Object>>> adminRolesMapList = new ArrayList<>();
        adminRolesMapList.add(e1);

        AdminRoleEntity rolePrivilegesList = new AdminRoleEntity();
        Map<String, List<Object>> rolesMap = adminRolesMapList.stream().findFirst()
                .orElse(null);
        if (!CollectionUtils.isEmpty(rolesMap)) {
            List<String> privilegeList = new ArrayList<>();
            rolesMap.forEach((roleName, privileges) ->
                    {
                      //  rolePrivilegesList.setRoleName(RoleType.getEnum(roleName));

                        if (!CollectionUtils.isEmpty(privileges)) {
                            privileges.stream().map(p -> (String) p).forEach(privilegeList::add);
                        }
                          //  rolePrivilegesList.setPrivileges(privilegeList);
                    }
            );
        }
        System.out.println(rolePrivilegesList);
    }
}
