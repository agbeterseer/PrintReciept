    package hg.print.recipt.response;

/**
 * Created by Terseer on 03/14/17.
 */
public class ApiErrorResponse {
    private String status;
    private int code;
    private String message;

    public ApiErrorResponse(String status, int code, String message) {
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
