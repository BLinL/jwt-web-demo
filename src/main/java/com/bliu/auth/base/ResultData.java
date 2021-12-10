package com.bliu.auth.base;

/**
 * 统一返回实体
 * @param <T> data type
 */
public class ResultData <T> {
    private int code;
    private String msg;
    private T data;

    private static final ResultData<?> SUCCESS_RESPONSE = new ResultData<>(200, "成功", null);
    private static final ResultData<?> FAIL_RESPONSE = new ResultData<>(400, "异常", null);

    public ResultData(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <P> ResultData<P>  successWithData(String msg, P data){
        return new ResultData<P>(200, msg, data);
    }

    public static <P> ResultData<P>  failWithData(String ms, P data){
        return new ResultData<P>(440, ms, data);
    }

    public int getCode() {
        return code;
    }

    public static ResultData<?> success(){
        return SUCCESS_RESPONSE;
    }

    public static ResultData<?> fail(){
        return FAIL_RESPONSE;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
