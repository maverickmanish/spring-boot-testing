package com.rest.order.dao;


import com.rest.order.constants.DaoConstants;
import com.rest.order.constants.ErrorConstants;
import com.rest.order.models.roles.AdminRoleEntity;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__.outE;
import static org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__.values;

@Repository
public class AdminRoleDaoImpl implements AdminRoleDao {

    @Autowired
    private GraphTraversalSource readerGraphTraversalSource;

    @Override
    public List<AdminRoleEntity> getUserRoleByEmail(String emailId) {
        List<Map<Object, Object>> adminRoleList = readerGraphTraversalSource.V()
                .has(DaoConstants.ADMIN_USER_ENTITY, DaoConstants.EMAIL, emailId).out()
                .hasLabel(DaoConstants.ADMIN_ROLE_ENTITY).elementMap().toList();
        List<AdminRoleEntity> roleList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(adminRoleList)) {
            adminRoleList.forEach((role) -> {
                AdminRoleEntity roleEntity = AdminRoleEntity.builder()
                        .roleName((String) role.get(DaoConstants.ROLE_NAME)).roleId((String) role.get(T.id).toString()).build();
                roleList.add(roleEntity);
            });
        }
        return roleList;
    }

    @Override
    public List<AdminRoleEntity> getUserRoleById(String userId) {
        List<Map<Object, Object>> adminRoleList = readerGraphTraversalSource.V(userId).out()
                .hasLabel(DaoConstants.ADMIN_ROLE_ENTITY).elementMap().toList();
        List<AdminRoleEntity> roleList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(adminRoleList)) {
            adminRoleList.forEach((role) -> {
                AdminRoleEntity roleEntity = AdminRoleEntity.builder()
                        .roleName((String) role.get(DaoConstants.ROLE_NAME)).roleId((String) role.get(T.id).toString()).build();
                roleList.add(roleEntity);
            });
        }
        return roleList;
    }

    @Override
    public String getRoleName(String roleId) throws Exception {
        List<Map<Object, Object>> adminRoleList = readerGraphTraversalSource.V(roleId)
                .hasLabel(DaoConstants.ADMIN_ROLE_ENTITY).elementMap().toList();
        String roleName;
        if (!CollectionUtils.isEmpty(adminRoleList)) {
            Map<Object, Object> role = adminRoleList.stream().findFirst()
                    .orElseThrow(() -> new Exception(ErrorConstants.ROLE_NOT_FOUND));
            roleName = ((String) role.get(DaoConstants.ROLE_NAME));
        } else {
            throw new Exception(ErrorConstants.ROLE_NOT_FOUND);
        }
        return roleName;
    }

    @Override
    public List<AdminRoleEntity> getAllRoles() throws Exception {
        List<Map<Object, Object>> adminRoleList = readerGraphTraversalSource.V()
                .hasLabel(DaoConstants.ADMIN_ROLE_ENTITY).elementMap().toList();
        List<AdminRoleEntity> roleList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(adminRoleList)) {
            adminRoleList.forEach((role) -> {
                AdminRoleEntity roleEntity = AdminRoleEntity.builder()
                        .roleName((String) role.get(DaoConstants.ROLE_NAME)).roleId((String) role.get(T.id).toString()).build();
                roleList.add(roleEntity);
            });
        }
        return roleList;
    }

    @Override
    public String getAdminUserRoleByUserName(String userName) throws Exception {

        List<Map<String, List<Object>>> adminRolePrivilegeList = readerGraphTraversalSource.V()
                .has(DaoConstants.ADMIN_USER_ENTITY, DaoConstants.NAME, userName).out()
                .hasLabel(DaoConstants.ADMIN_ROLE_ENTITY).<String, List<Object>> group().by(values())
                .by(outE().elementMap().fold()).toList();
        AdminRoleEntity rolePrivilegesList = new AdminRoleEntity();
        Map<String, List<Object>> rolePrivilegeMap = adminRolePrivilegeList.stream().findFirst().orElse(null);
        if (CollectionUtils.isEmpty(rolePrivilegeMap)) {
            throw new Exception(ErrorConstants.ROLE_NOT_FOUND);
        }
        return rolePrivilegeMap.keySet().stream().findFirst().orElse(null);
    }

}
