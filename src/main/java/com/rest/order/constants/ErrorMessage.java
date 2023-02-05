package com.rest.order.constants;


import lombok.experimental.UtilityClass;

/**
 * @author poojaagrawal
 */
@UtilityClass
public class ErrorMessage {
    public final String ERR_MSG_BAD_REQ = "Bad Request";
    public final String ERR_MSG_UNPROCESS_ENTITY = "Unprocessable Entity";
    public final String CONTENT_CANNOT_BE_NULL = "Content cannot be null";
    public final String DATA_ALREADY_EXISTS = "Data already exists";
    // --------------------Error Messages---------------------------------------------//
    public final String SEARCH_TERM_NOT_VALID = "Search term should have 3 or more characters";
    public final String SEARCH_TERM_TYPE_NOT_VALID = "Tag type should be " +  " or "
            ;
    public final String CONTENT_ID_CANNOT_BE_NULL = "Content Id can not be null";
    public final String CONTENT_ID_IS_NOT_VALID = "Content ID is not Valid";
    public final String PARTNER_NAME_IS_NOT_VALID = "Partner Name is not Valid";
    public final String READ_TIME_NEGATIVE = "Read Time cannot be negative";
    public final String CME_CERTIFICATE_URL_MEDIATYPE_MISMATCHED = "File format not supported , upload .pdf file";
    public final String CME_COUNTER_NEGATIVE = "CME Counter cannot be negative";
    public final String CME_COUNTER_SHOULD_NUMBER = "CME Counter should be number";
    public final String VIDEO_MEDIATYPE_MISMATCHED = "File format not supported , upload .mp4 file";
    public final String MEDICAL_TAG_COUNT = "Medical Hashtags should be at least 3";
    public final String NON_MEDICAL_TAG_COUNT = "Non Medical Hashtags should be at least 2";
    public final String MEDICAL_TAG_N_COUNT = "Medical Hashtags should be at least %d";
    public final String NON_MEDICAL_TAG_N_COUNT = "Non Medical Hashtags should be at least %d";
    public final String CANNOT_BE_MORE_THAN_5000_CHARACTERS = "Field can not be more than 5000 characters";
    public final String CANNOT_BE_MORE_THAN_500_CHARACTERS = "Field can not be more than 500 characters";
    public final String CANNOT_BE_MORE_THAN_N_CHARACTERS = "Field can not be more than %d characters";
    public final String CANNOT_BE_MORE_THAN_2500_CHARACTERS = "Field can not be more than 2500 characters";
    public final String CANNOT_BE_MORE_THAN_250_CHARACTERS = "Field can not be more than 250 characters";
    public final String CANNOT_BE_MORE_THAN_100_CHARACTERS = "Field can not be more than 100 characters";
    public final String CANNOT_EMPTY_OR_NULL = "Field cannot be empty or null";
    public final String CANNOT_EMPTY = "Field cannot be Blank/Empty";
    public final String SHOULD_NULL = "Field should be null";
    public final String CANNOT_ZERO_OR_NULL = "Field cannot be zero or less than zero";
    public final String START_DATE_TIME_CANNOT_BE_AFTER_END_DATE_TIME = "Start Date time can not be after End Date time";
    public final String IMAGE_MEDIATYPE_MISMATCHED = "Accepted file formats are jpg, jpeg, png";
    public final String PROVIDE_MEDIA = "Provide either the video link or the video";
    public final String CONTENT_NOT_FOUND = "Content not found";
    public final String CONTENT_TYPE_CHANGED = "Content type changed";
    public final String GROUP_ID_CANNOT_BE_NULL = "Group Id can not be null";
    public final String GROUP_ID_NOT_EXIST = "Group id does not exists";
    public final String ERROR_DELETING_GROUP_ENTITY = "Error deleting Group Entity";
    public final String USER_ID_IS_INVALID = "User Id Doesn't Exist";
    public final String USER_EMAIL_ID_IS_INVALID = "User Doesn't Exist";
    public final String USERS_NOT_FOUND = "Users Doesn't Exist";
    public final String PRIVILEGES_NOT_FOUND = "Privileges doesn't exist with given role Id";
    public final String PRIVILEGE_NOT_FOUND = "Privileges doesn't exist";
    public final String ROLES_NOT_FOUND = "Roles doesn't exist";

    public final String ACCESS_CODE_ID_IS_INVALID = "Access Code ID Is Invalid";
}
