package hg.print.recipt.response;

/**
 * Created by Funmite on 12/20/2016.
 */
public class StaticResponse {
    private String status;
    private String message;
    private int code;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
