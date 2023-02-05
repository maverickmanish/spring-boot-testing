package com.rest.order.dao;


import com.rest.order.constants.DaoConstants;
import com.rest.order.controllers.reftype.RoleType;
import com.rest.order.models.roles.AdminPrivilegeEntity;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Repository
public class AdminPrivilegeDaoImpl implements AdminPrivilegeDao {

    @Autowired
    private GraphTraversalSource readerGraphTraversalSource;

    @Override
    public List<AdminPrivilegeEntity> getAllPrivileges() {
        List<Map<Object, Object>> adminPrivilegeList = readerGraphTraversalSource.V()
                .hasLabel(DaoConstants.ADMIN_PRIVILEGE_ENTITY).elementMap().toList();
        List<AdminPrivilegeEntity> privilegesList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(adminPrivilegeList)) {
            adminPrivilegeList.forEach((privilege) -> {
                Object selected = privilege.get(DaoConstants.SELECTED);
                AdminPrivilegeEntity privilegeEntity = AdminPrivilegeEntity.builder()
                        .privilegeName((String) privilege.get(DaoConstants.PRIVILEGE_NAME))
                        .privilegeId((String) privilege.get(T.id).toString())
                        .selected(Objects.nonNull(selected) ? ((boolean) selected) : false).build();
                privilegesList.add(privilegeEntity);
            });
        }
        return privilegesList;

    }

    @Override
    public List<AdminPrivilegeEntity> getAllPrivilegesByRoleName(String roleName) {
        String privilegeType = RolePrivilegesUtil.getPrivilegeType(roleName);
        List<Map<Object, Object>> adminPrivilegeList = readerGraphTraversalSource.V()
                .hasLabel(DaoConstants.ADMIN_PRIVILEGE_ENTITY).elementMap().toList();
        List<AdminPrivilegeEntity> privilegesList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(adminPrivilegeList)) {

            adminPrivilegeList.forEach((privilege) -> {
                AdminPrivilegeEntity privilegeEntity = AdminPrivilegeEntity.builder()
                        .privilegeName((String) privilege.get(DaoConstants.PRIVILEGE_NAME))
                        .privilegeId((String) privilege.get(T.id).toString())
                        .selected((boolean) privilege.get(privilegeType))
                        .build();
                privilegesList.add(privilegeEntity);
            });
        }
        return privilegesList;

    }

    @Override
    public List<AdminPrivilegeEntity> getAdminUserPrivilegesByUserName(String userName) {

        List<Map<Object, Object>> adminPrivilegeList = readerGraphTraversalSource.V()
                .has(DaoConstants.ADMIN_USER_ENTITY, DaoConstants.NAME, userName).out()
                .hasLabel(DaoConstants.ADMIN_PRIVILEGE_ENTITY).elementMap().toList();

        List<AdminPrivilegeEntity> privilegesList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(adminPrivilegeList)) {
            adminPrivilegeList.forEach((privilege) -> {
                AdminPrivilegeEntity privilegeEntity = AdminPrivilegeEntity.builder()
                        .privilegeName((String) privilege.get(DaoConstants.PRIVILEGE_NAME))
                        .isViewPrivilege((boolean) privilege.get(DaoConstants.IS_VIEW_PRIVILEGE))
                        .isEditorPrivilege((boolean) privilege.get(DaoConstants.IS_EDITOR_PRIVILEGE)).build();
                privilegesList.add(privilegeEntity);
            });
        }
        return privilegesList;
    }

    @Override
    public List<AdminPrivilegeEntity> getUserPrivilegesByEmail(String email) {
        List<Map<Object, Object>> userPrivilegeList = readerGraphTraversalSource.V()
                .has(DaoConstants.ADMIN_USER_ENTITY, DaoConstants.EMAIL, email).out()
                .hasLabel(DaoConstants.ADMIN_USER_PRIVILEGE_ENTITY).elementMap().toList();

        List<String> privileges = new ArrayList<>();
        Map<Object, Object> userPrivilegeMap = userPrivilegeList.stream().findFirst().orElse(null);
        if (!CollectionUtils.isEmpty(userPrivilegeMap)) {
            String[] privilegesArray = ((String) userPrivilegeMap.get(DaoConstants.PRIVILEGES)).split(",");
            privileges = Arrays.asList(privilegesArray);
        }
        List<AdminPrivilegeEntity> privilegesList = new ArrayList<>();
        RolePrivilegesUtil.privilegeEntityFilter(privileges, privilegesList, readerGraphTraversalSource);
        return privilegesList;
    }

    @Override
    public List<AdminPrivilegeEntity> getUserPrivilegesByUserId(String userId) {
        List<Map<Object, Object>> userPrivilegeList = readerGraphTraversalSource.V(userId).out()
                .hasLabel(DaoConstants.ADMIN_USER_PRIVILEGE_ENTITY).elementMap().toList();

        List<String> privileges = new ArrayList<>();
        Map<Object, Object> userPrivilegeMap = userPrivilegeList.stream().findFirst().orElse(null);
        if (!CollectionUtils.isEmpty(userPrivilegeMap)) {
            String[] privilegesArray = ((String) userPrivilegeMap.get(DaoConstants.PRIVILEGES)).split(",");
            privileges = Arrays.asList(privilegesArray);
        }
        List<AdminPrivilegeEntity> privilegesList = new ArrayList<>();
        RolePrivilegesUtil.privilegeEntityFilter(privileges, privilegesList, readerGraphTraversalSource);
        return privilegesList;
    }

    @Override
    public List<AdminPrivilegeEntity> getRolePrivileges(String roleName) {
        String view = DaoConstants.IS_VIEW_PRIVILEGE;
        if (roleName.equalsIgnoreCase(RoleType.ADMIN_EDITOR.toString()))
            view = DaoConstants.IS_EDITOR_PRIVILEGE;

        List<Map<Object, Object>> adminPrivilegeList = readerGraphTraversalSource.V()
                .has(DaoConstants.ADMIN_PRIVILEGE_ENTITY, view, true).elementMap().toList();
        List<AdminPrivilegeEntity> privilegesList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(adminPrivilegeList)) {
            adminPrivilegeList.forEach((privilege) -> {
                AdminPrivilegeEntity privilegeEntity = AdminPrivilegeEntity.builder()
                        .privilegeName((String) privilege.get(DaoConstants.PRIVILEGE_NAME))
                        .isViewPrivilege((boolean) privilege.get(DaoConstants.IS_VIEW_PRIVILEGE))
                        .isEditorPrivilege((boolean) privilege.get(DaoConstants.IS_EDITOR_PRIVILEGE)).build();
                privilegesList.add(privilegeEntity);
            });
        }
        return privilegesList;
    }
}
