package com.imooc.mall.exception;

/**
 * Error Enumeration
 */
public enum ImoocMallExceptionEnum {
    NEED_USER_NAME(10001, "username cannot be empty."),
    NEED_PASSWORD(10002, "password cannot be empty."),
    PASSWORD_TOO_SHORT(10003, "Password length cannot be less than 8 digits"),
    NAME_EXISTED(10004, "No duplicate names allowed"),
    INSERT_FAILED(10005, "Insert failed, please try again"),
    WRONG_PASSWORD(10006, "Wrong password"),
    NEED_LOGIN(10007, "User not logged in"),
    UPDATE_FAILED(10008, "Update failed"),
    NEED_ADMIN(10009, "No administrator privileges"),
    PARA_NOT_NULL(10010, "Parameters cannot be empty"),
    CREATE_FAILED(10011, "Add Failure"),
    REQUEST_PARAM_ERROR(10012, "Parameter errors"),
    DELETE_FAILED(10013, "Delete failed"),
    MKDIR_FAILED(10014, "Folder creation failed"),
    UPLOAD_FAILED(10015, "Image upload failed"),
    NOT_SALE(10016, "Product status not available for sale"),
    NOT_ENOUGH(10017, "Insufficient merchandise inventory"),
    CART_EMPTY(10018, "Shopping cart is empty for checked items"),
    NO_ENUM(10019, "No corresponding enumeration found"),
    NO_ORDER(10020, "Order does not exist"),
    NOT_YOUR_ORDER(10021, "The order does not belong to you"),
    WRONG_ORDER_STATUS(10022, "Order status does not match"),
    WRONG_EMAIL(10023, "Illegal email addresses"),
    EMAIL_ALREADY_BEEN_REGISTERED(10024, "The email address has been registered"),
    EMAIL_ALREADY_BEEN_SEND(10025, "The email has been sent, if you cannot receive it, please try again later."),
    NEED_EMAIL_ADDRESS(10026, "email cannot be empty"),
    NEED_VERIFICATION_CODE(10027, "Verification code cannot be empty"),
    WRONG_VERIFICATION_CODE(10028, "Verification code error"),
    TOKEN_EXPIRED(10029, "token expires"),
    TOKEN_WRONG(10030, "token parsing failed"),
    PRICE_TOO_LOW(10031, "price too low"),
    STOCK_TOO_MANY(10032, "Inventory cannot be greater than 10,000"),
    CANCEL_WRONG_ORDER_STATUS(10033, "The order status is wrong, after you have paid, you cannot cancel the order"),
    PAY_WRONG_ORDER_STATUS(10034, "Order status is wrong, payment can only be made in case you have not paid"),
    DELIVER_WRONG_ORDER_STATUS(10035, "Order status is wrong, can only be shipped after paid"),
    FINISH_WRONG_ORDER_STATUS(10036, "Please complete the order by \"confirming receipt\" in the background management system"),
    SYSTEM_ERROR(20000, "System error, please check the specific error message from the console or log");
    /**
     * Error Code
     */
    Integer code;
    /**
     * Error message
     */
    String msg;

    ImoocMallExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
