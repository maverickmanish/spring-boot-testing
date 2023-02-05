package com.rest.order.controllers.reftype;

import java.util.Map;

public enum RoleType {
    SUPERADMIN("superadmin"), ADMIN_EDITOR("admin_editor"), ADMIN_VIEWER("admin_viewer");

    private String role;

    private RoleType(String role) {
        this.role = role;
    }

    public String role() {
        return this.role;

    }

    @Override
    public String toString() {
        return this.role();
    }

    public static RoleType getEnum(String value) {
        for (RoleType v : values())
            if (v.role().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException("Invalid Role Type passed");
    }

}
