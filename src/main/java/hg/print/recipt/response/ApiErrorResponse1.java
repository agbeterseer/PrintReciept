    package hg.print.recipt.response;

/**
 * Created by Funmite on 12/21/2016.
 */
public class ApiErrorResponse1 {
    private String status;
    private int code;
    private String message;

    public ApiErrorResponse1(String status, int code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ApiErrorResponse{" +
                "status=" + status +
                ", code=" + code +
                ", message=" + message +
                '}';
    }
}
