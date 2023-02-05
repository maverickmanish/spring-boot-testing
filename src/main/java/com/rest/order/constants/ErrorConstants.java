package com.rest.order.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorConstants {

    // --------------------Error Messages---------------------------------------------//
    public static final String INTERNAL_SERVER_ERROR_MSG = "Internal server error. Please contact to admin.";
    public static final String AWS_CREDS = "AWS Credentials";
    public static final String INVALID_TOKEN_ID = "Invalid Token";
    public static final String ACCESS_DENIED = "Access Denied";
    // --------------------Error Codes-----------------------------------------------//
    public static final String ERROR_CODE_401 = "401";
    public static final String GROUP_CANNOT_BE_NULL = "Group cannot be null";
    public static final String INVALID_CREATE_GROUP_REQUEST = "Invalid create group request";
    public static final String INVALID_ADD_TAG_REQUEST = "Invalid add tag request";
    public static final String GROUP_NAME_CANNOT_BE_EMPTY = "Group name cannot be empty or null";
    public static final String GROUP_NAME_NOT_EXIST = "Group name not exists";
    public static final String USER_ID_NOT_FOUND = "User Id doesn't Exist";
    public static final String USER_NOT_FOUND = "User doesn't Exist";
    public static final String ROLE_NOT_FOUND = "Role doesn't exist with given role Id";
    public static final String ROLES_NOT_FOUND = "Roles doesn't exist";
    public static final String GROUP_NAME_ALREADY_EXIST = "Group name already exists";
    public static final String INVALID_DELETE_REQUEST_DATA = "incorrect data entered";

    public static final String GROUP_ID_ALREADY_EXIST = "Group id already exists";
    public static final String INVALID_TAG_TYPE = "Invalid tag type entered ";
    public static final String TAG_ID_ALREADY_EXIST = " Tag id already exists";
    public static final String TAG_VALUE_IS_BLANK = " Tag value is empty ";
    public static final String TAG_VALUE_ALREADY_EXISTS = " Tag value already present";
    public static final String TAG_VALUE_ALREADY_EXIST = " tag value already exists in groups ";
    public static final String TAG_VALUE_NOT_EXIST = " tag value not exists in groups ";
    public static final String ERROR_WHILE_CREATING_GROUP = "Error while creating group";
    public static final String ERR_CODE_422 = "422";
    public static final String ERR_MSG_BAD_REQ = "Bad Request";
    public static final String ERR_MSG_UNPROCESS_ENTITY = "Unprocessable Entity";

}
