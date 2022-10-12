package sustech.ooad.a2.utils;

import java.io.Serializable;


public class JsonResult<E> implements Serializable {
    private static final int OK = 200;
    private Integer state;
    private String message;
    private E data;

    public JsonResult() {
        super();
        this.state = OK;
    }

    public JsonResult(E data) {
        super();
        this.state = OK;
        this.data = data;
    }

    public JsonResult(Integer state, String message) {
        super();
        this.state = state;
        this.message = message;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

}
