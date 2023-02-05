package com.rest.order.models.privilege;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrivilegeResponse {
    private String privilegeId;
    private String privilegeName;
    private Boolean selected;
}