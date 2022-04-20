package com.example.projectTestDemo.environment;

public class Constant {
    public final static String TEMPLATE_PATH = "src/main/resources/template/";
    public final static String VALIDATION_FILE = "/validation.json";

    public final static String UTF_8 = "UTF-8";
    public final static String REQUIRED = "required";
    public final static String TYPE = "type";
    public final static String MIN_LENGTH = "minLength";
    public final static String MAX_LENGTH = "maxLength";
    public final static String PATTERN = "pattern";
    //request
    public final static String REQUEST_LOGIN = "LOGINREQUEST";

    //response
    public final static String SUCCESS = "SUCCESS";

    //unit test
    public final static String CASE_SUCCESS = "success_case";
    public final static String NULL_ALLOWED = "Null allowed";
    public final static String NULL_CASE = "%s_is_null";
    public final static String BLANK_CASE = "%s_is_blank";
    public final static String MORE_THAN_CASE = "%s_length_more_than_%d";
    public final static String NULL_CASE_EXPECTED = "%s: expected type: String, found: Null";

    public final static String BLANK_CASE_EXPECTED = "%s: expected minLength: %d, actual: 0";
    public final static String MORE_THAN_CASE_EXPECTED = "%s: expected maxLength: %d, actual: %d";
    public final static String NULL_ALLOWED_FOR_OPTIONAL_FIELD = "Null allowed for optional field.";
    public final static String NO_EXCEPTION_FOUND = "no exception found";
    public final static String REGEX_DOES_NOT_MATCH_EXPECTED = "%s: string [%s] does not match pattern %s";
    public final static String EXPECTED_RESULT = "Expected : -> %s";
    public final static String ACTUAL_RESULT = "Actual   : -> %s";
    public final static String CASE_INFO = "%s_%s";
    public final static String DOES_NOT_MATCH_PATTERN = "_does_not_match_pattern_";
    public final static String RAISED_EXCEPTION_IS_NULL = "raisedException is null.";
}
