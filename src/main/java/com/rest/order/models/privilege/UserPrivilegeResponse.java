package com.rest.order.models.privilege;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPrivilegeResponse {
    private String id;
    private byte[] name;
    private byte[] email;
    private byte[] employeeCode;
    private byte[] phoneNumber;
    private List<PrivilegeResponse> privileges;
    private List<RoleResponse> roles;
}