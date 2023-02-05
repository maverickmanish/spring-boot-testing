package com.rest.order.dao;


import com.rest.order.constants.DaoConstants;
import com.rest.order.models.privilege.PrivilegeResponse;
import com.rest.order.models.privilege.RoleResponse;
import com.rest.order.models.privilege.UserPrivilegeResponse;
import com.rest.order.models.roles.AdminPrivilegeEntity;
import com.rest.order.models.roles.AdminRoleEntity;
import com.rest.order.models.roles.AdminUserEntity;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.T;
import org.springframework.util.CollectionUtils;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class RolePrivilegesUtil {

    public static UserPrivilegeResponse userPrivilegeResponseBuilder(AdminUserEntity user,
                                                                     List<RoleResponse> roleResponse, List<PrivilegeResponse> privilegeResponse) {
        return UserPrivilegeResponse.builder().id(user.getId()).name(Base64Encode(user.getName()))
                .email(Base64Encode(user.getEmail())).employeeCode(Base64Encode(user.getEmployeeCode()))
                .phoneNumber(Base64Encode(user.getPhoneNumber())).roles(roleResponse).privileges(privilegeResponse)
                .build();
    }

    public static List<RoleResponse> getRoleResponses(List<AdminRoleEntity> allRoles) {
        List<RoleResponse> response;
        response = allRoles.stream().map(r -> {
            return RoleResponse.builder().roleId(r.getRoleId()).roleName(r.getRoleName()).build();
        }).collect(Collectors.toList());
        return response;
    }

    public static void privilegeEntityBuilder(List<String> privileges, List<AdminPrivilegeEntity> privilegesList,
            GraphTraversalSource readerGraphTraversalSource) {
        List<Map<Object, Object>> adminPrivilegeList = readerGraphTraversalSource.V()
                .hasLabel(DaoConstants.ADMIN_PRIVILEGE_ENTITY).elementMap().toList();

        if (!CollectionUtils.isEmpty(adminPrivilegeList)) {
            List<String> finalPrivileges = privileges;
            adminPrivilegeList.stream().forEach((privilege) -> {
                AdminPrivilegeEntity privilegeEntity = AdminPrivilegeEntity.builder()
                        .privilegeName((String) privilege.get("privilegeName"))
                        .privilegeId((String) privilege.get(T.id).toString())
                        .selected(finalPrivileges.contains(((String) privilege.get("privilegeName")))).build();
                privilegesList.add(privilegeEntity);
            });
        }
    }

    public static void privilegeEntityFilter(List<String> privileges, List<AdminPrivilegeEntity> privilegesList,
            GraphTraversalSource readerGraphTraversalSource) {
        List<Map<Object, Object>> adminPrivilegeList = readerGraphTraversalSource.V()
                .hasLabel(DaoConstants.ADMIN_PRIVILEGE_ENTITY).elementMap().toList();

        if (!CollectionUtils.isEmpty(adminPrivilegeList)) {
            List<String> finalPrivileges = privileges;
            adminPrivilegeList.stream()
                    .filter(privilege -> finalPrivileges.contains(((String) privilege.get("privilegeName"))))
                    .forEach((privilege) -> {
                        AdminPrivilegeEntity privilegeEntity = AdminPrivilegeEntity.builder()
                                .privilegeName((String) privilege.get("privilegeName"))
                                .privilegeId((String) privilege.get(T.id).toString()).selected(true).build();
                        privilegesList.add(privilegeEntity);
                    });
        }
    }

    public static List<PrivilegeResponse> getPrivilegeResponses(List<AdminPrivilegeEntity> allPrivileges) {
        List<PrivilegeResponse> response;
        response = allPrivileges.stream().map(p -> {
            return PrivilegeResponse.builder().privilegeId(p.getPrivilegeId()).selected(p.isSelected())
                    .privilegeName(p.getPrivilegeName()).build();
        }).collect(Collectors.toList());
        return response;
    }

    public static String getPrivilegeType(String roleName) {
        String privilegeType;
        switch (roleName) {
        case DaoConstants.ADMIN_VIEWER:
            privilegeType = "isViewPrivilege";
            break;
        case DaoConstants.ADMIN_EDITOR:
            privilegeType = "isEditorPrivilege";
            break;
        case DaoConstants.SUPERADMIN:
            privilegeType = "isAdminPrivilege";
            break;
        default:
            privilegeType = "isViewPrivilege";
        }
        return privilegeType;
    }

    public static byte[] Base64Encode(String value) {
        byte[] encode = null;
        if (Objects.nonNull(value)) {
            encode = value.getBytes(StandardCharsets.US_ASCII);

        }
        return encode;
    }


    public static AdminUserEntity getUserEntityDetails(LinkedHashMap k, Map<Object, List<Object>> v, GraphTraversalSource readerGraphTraversalSource,boolean privilegeFilteringEnabled) {
        LinkedHashMap userDetails = k;
        AdminRoleEntity adminRoleEntity = new AdminRoleEntity();
        List<AdminPrivilegeEntity> privilegesList = new ArrayList<>();
        if (Objects.nonNull(v)) {
            LinkedHashMap edges = (LinkedHashMap) v;

            Object has_privileges = ((LinkedHashMap) edges.get(DaoConstants.HAS_PRIVILEGES));

            if (Objects.nonNull(has_privileges)) {
                Object privileges = ((LinkedHashMap) has_privileges).get(DaoConstants.PRIVILEGES);
                String[] privilegesArray = ((String) privileges).split(",");
                List<String> privilegesNameList = Arrays.asList(privilegesArray);

                if(privilegeFilteringEnabled) {
                    RolePrivilegesUtil.privilegeEntityFilter(privilegesNameList, privilegesList,
                            readerGraphTraversalSource);
                }
                else {
                    RolePrivilegesUtil.privilegeEntityBuilder(privilegesNameList, privilegesList,
                            readerGraphTraversalSource);
                }
            }

            Object has_role = ((LinkedHashMap) edges.get(DaoConstants.HAS_ROLE));
            if (Objects.nonNull(has_role)) {
                Object roleName = ((LinkedHashMap) has_role).get(DaoConstants.ROLE_NAME);
                Object roleId = ((LinkedHashMap) has_role).get(T.id);
                adminRoleEntity = AdminRoleEntity.builder().roleName((String) roleName).roleId((String) roleId.toString())
                        .build();
            }
        }

        AdminUserEntity adminUserEntity = AdminUserEntityBuilder(userDetails, adminRoleEntity, privilegesList);
        return adminUserEntity;
    }

    public static AdminUserEntity AdminUserEntityBuilder(LinkedHashMap userDetails, AdminRoleEntity adminRoleEntity, List<AdminPrivilegeEntity> privilegesList) {
        return AdminUserEntity.builder()
                .name((String) userDetails.get(DaoConstants.NAME)).id((String) userDetails.get(T.id).toString())
                .email((String) userDetails.get(DaoConstants.EMAIL))
                .employeeCode((String) userDetails.get(DaoConstants.EMPLOYEE_CODE))
                .phoneNumber((String) userDetails.get(DaoConstants.PHONE_NUMBER)).privileges(privilegesList)
                .role(Collections.singletonList(adminRoleEntity)).build();
    }

    public static AdminUserEntity AdminUserEntityBuilder(Map<Object, Object> userDetails) {
        return AdminUserEntity.builder().name((String) userDetails.get(DaoConstants.NAME))
                .id((String) userDetails.get(T.id).toString()).email((String) userDetails.get(DaoConstants.EMAIL))
                .employeeCode((String) userDetails.get(DaoConstants.EMPLOYEE_CODE))
                .phoneNumber((String) userDetails.get(DaoConstants.PHONE_NUMBER)).build();
    }

}
