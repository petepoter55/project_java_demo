package com.example.projectTestDemo.environment;

public class Constant {
    // status code
    public final static String STATUS_CODE_SUCCESS = "200";
    public final static String STATUS_CODE_FAIL = "500";
    public final static String STATUS_CODE_ERROR = "400";
    public final static String STATUS_CODE_FOUND = "404";

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
    public final static String REQUEST_GETDATA = "GET_MANAGEIDREQUEST";

    //response
    public final static String SUCCESS = "SUCCESS";

    //date
    public static final String RQ_DATE_PATTERN = "yyyyMMdd";
    public static final String RQ_TIME_PATTERN = "hhmmss";

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
    public final static String THROW_EXCEPTION = "Error : %s";
    public final static String DOES_NOT_MATCH_PATTERN = "_does_not_match_pattern_";
    public final static String RAISED_EXCEPTION_IS_NULL = "raisedException is null.";

    //update data
    public final static String SUCCESS_UPDATE_PEOPLE = "อัปเดตข้อมูลเสร็จเรียบร้อย";
    public final static String ERROR_UPDATE_PEOPLE = "อัปเดตข้อมูลไม่สำเร็จ";

    //create peopleData
    public final static String ERROR_PEOPLE_CHECKDATA_DUPLICATE = "ข้อมูลนี้ได้่ทำการลงทะเบียน เรียบร้อยแล้ว";
    public final static String ERROR_PEOPLE_CHECKDATA_FOUND = "ไม่พบข้อมูลที่อยู่ในระบบ";
    public final static String SUCCESS_PEOPLE = "ลงทะเบียนเรียบร้อย";

    //create Account 
    public final static String SUCCESS_CREATE_ACCOUNT = "สร้างบัญชีผู้ใช้งานเสร็จเรียบร้อย";
    public final static String ERROR_CREATE_ACCOUNT = "สร้างบัญชีผู้ใช้งานไม่สำเร็จ";
    public final static String ERROR_INPUT_CREATE_ACCOUNT = "รหัสผ่านระบุไม่ถูกต้อง เนื่องจาก ระบุรหัสผ่านไม่ตรงกับยืนยันรหัสผ่าน";

    //login
    public final static String SUCCESS_LOGIN = "เข้าสู่ระบบสำเร็จ";
    public final static String ERROR_INPUT_LOGIN = "รหัสผ่านระบุไม่ถูกต้อง เนื่องจาก ระบุรหัสผ่านไม่ตรงกับยืนยันรหัสผ่าน";
    public final static String ERROR_FOUND_DATA_LOGIN = "บัญชีผู้ใช้งานไม่มีในระบบ";
    public final static String ERROR_LOGIN = "เข้าสู่ระบบไม่สำเร็จ";

    //rabbit MQ
    public final static String SUCCESS_SEND_MQ = "ส่งสำเร็จ";
    public final static String ERROR_SEND_MQ = "ส่งไม่สำเร็จ";

    //generate JWT token
    public final static String SUCCESS_GENERATE_TOKEN = "สร้าง token สำเร็จ";
    public final static String ERROR_GENERATE_TOKEN = "สร้าง token ไม่สำเร็จ";

    //verify interceptor
    public final static String ERROR_DATA_INTERCEPTOR = "Data Not Found";
    public final static String ERROR_TOKEN_INTERCEPTOR = "Token Not Found";
}
