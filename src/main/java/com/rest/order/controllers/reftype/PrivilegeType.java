package com.rest.order.controllers.reftype;

public enum PrivilegeType {
    CONTENT_UPLOAD("content_upload"), CONTENT_EDIT("content_edit"), CONTENT_DELETION("content_deletion"), USER_CONTENT_ACCESS_EDIT("user_content_access_edit"), ADD_HCP("add_hcp"), BLOCK_HCP("block_hcp"), EDIT_HCP("edit_hcp"), EXPORT_HCP_LIST("export_hcp_list");

    private String privilege;

    private PrivilegeType(String privilege) {
        this.privilege = privilege;
    }

    public String privilege() {
        return this.privilege;

    }

    @Override
    public String toString() {
        return this.privilege();

    }

    public static PrivilegeType getEnum(String value) {
        for (PrivilegeType p : values())
            if (p.privilege().equalsIgnoreCase(value)) return p;
        throw new IllegalArgumentException("Invalid Privilege Type passed");
    }

}
