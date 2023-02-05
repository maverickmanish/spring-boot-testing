package com.rest.order.dao;

import com.rest.order.models.roles.AdminUserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminUserDao {

    AdminUserEntity getUserByEmailId(String emailId) throws Exception;

    List<AdminUserEntity> getAllUsers(int startRange, int endRange);

    AdminUserEntity getUserById(String userId) throws Exception;
}
