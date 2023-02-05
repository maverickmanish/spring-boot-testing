package com.rest.order.models.roles;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminUserEntity {
    private String id;
    private String name;
    private String email;
    private String employeeCode;
    private String phoneNumber;

    // edge = "HAS_ROLE", direction = OUTGOING
    private List<AdminRoleEntity> role;

    // edge = "HAS_PRIVILEGE", direction = OUTGOING
    private List<AdminPrivilegeEntity> privileges;
}