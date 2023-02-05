package com.rest.order.models.roles;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminPrivilegeEntity {
    private String privilegeId;
    private boolean isViewPrivilege;
    private boolean isEditorPrivilege;
    private boolean isAdminPrivilege;
    private boolean selected;
    private String privilegeName;
}