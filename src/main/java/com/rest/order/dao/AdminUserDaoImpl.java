package com.rest.order.dao;


import com.rest.order.constants.Constants;
import com.rest.order.constants.DaoConstants;
import com.rest.order.constants.ErrorConstants;
import com.rest.order.constants.ErrorMessage;
import com.rest.order.exception.SupernovaException;
import com.rest.order.models.roles.AdminPrivilegeEntity;
import com.rest.order.models.roles.AdminRoleEntity;
import com.rest.order.models.roles.AdminUserEntity;
import org.apache.tinkerpop.gremlin.process.traversal.Order;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.*;

import static org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__.*;

@Repository
public class AdminUserDaoImpl implements AdminUserDao {

    @Autowired
    private GraphTraversalSource readerGraphTraversalSource;

    @Override
    public AdminUserEntity getUserByEmailId(String emailId) throws Exception {
        List<Map<Object, Object>> userList = readerGraphTraversalSource.V()
                .has(DaoConstants.ADMIN_USER_ENTITY, DaoConstants.EMAIL, emailId).elementMap().toList();
        AdminUserEntity adminUserEntity;
        Map<Object, Object> userDetails = userList.stream().findFirst().orElse(null);
        if (CollectionUtils.isEmpty(userDetails)) {
            throw new SupernovaException(ErrorMessage.USER_EMAIL_ID_IS_INVALID, ErrorConstants.USER_NOT_FOUND);
        }

        return RolePrivilegesUtil.AdminUserEntityBuilder(userDetails);
    }

    @Override
    public AdminUserEntity getUserById(String userId) {
        List<Map<Object, Map<Object, List<Object>>>> userList = readerGraphTraversalSource.V(userId)
                .hasLabel(DaoConstants.ADMIN_USER_ENTITY).<Object, Map<Object, List<Object>>> group().by(elementMap())
                .by(bothE().<Object, Object> group().by(T.label).by(inV().elementMap())).toList();
        AdminUserEntity adminUserEntity = null;
        Map<Object, Map<Object, List<Object>>> usersDetailsMap = userList.stream().findFirst().orElse(null);
        if (!CollectionUtils.isEmpty(usersDetailsMap)) {
            Map.Entry<Object, Map<Object, List<Object>>> objectMapEntry = usersDetailsMap.entrySet().stream()
                    .findFirst().orElse(null);
            if (Objects.nonNull(objectMapEntry)) {
                Object userDetails = objectMapEntry.getKey();
                Object rolesPrivilegesDetails = objectMapEntry.getValue();
                adminUserEntity = RolePrivilegesUtil.getUserEntityDetails((LinkedHashMap) userDetails,(Map<Object, List<Object>>) rolesPrivilegesDetails, readerGraphTraversalSource,false);
            }
        } else {
            throw new SupernovaException(ErrorMessage.USER_ID_IS_INVALID, ErrorConstants.USER_ID_NOT_FOUND);
        }

        return adminUserEntity;

    }

    @Override
    public List<AdminUserEntity> getAllUsers(int startRange, int endRange) {
        List<Map<Object, Map<Object, List<Object>>>> userList = readerGraphTraversalSource.V()
                .hasLabel(DaoConstants.ADMIN_USER_ENTITY).order().by(Constants.NAME, Order.asc)
                .range(startRange, endRange).<Object, Map<Object, List<Object>>> group().by(elementMap())
                .by(bothE().<Object, Object> group().by(T.label).by(inV().elementMap())).toList();


        List<AdminUserEntity> adminUserEntityList = new ArrayList<>();
        Map<Object, Map<Object, List<Object>>> usersDetailsMap = userList.stream().findFirst().orElse(null);
        if (!CollectionUtils.isEmpty(usersDetailsMap)) {
            usersDetailsMap.forEach((userDetails, rolesPrivilegesDetails) -> {
                AdminUserEntity adminUserEntity = RolePrivilegesUtil.getUserEntityDetails((LinkedHashMap) userDetails, rolesPrivilegesDetails, readerGraphTraversalSource,false);
                adminUserEntityList.add(adminUserEntity);
            });
        } else {
            throw new SupernovaException(ErrorMessage.USERS_NOT_FOUND, ErrorConstants.USER_NOT_FOUND);
        }

        return adminUserEntityList;

    }
}