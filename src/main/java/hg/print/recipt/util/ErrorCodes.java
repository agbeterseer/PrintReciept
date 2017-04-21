
package hg.print.recipt.util;

import java.util.HashMap;
import java.util.Map;

public enum ErrorCodes {
    CLIENT_ID_EXIST(400, "Client Id already exist"),
    CLIENT_DOES_NOT_EXIST(400, "Client Id do not exist for this notification event"),
    CLIENT_ID_EMPTY(400, "Id is empty,please provide the id"),
    CLIENT_NOTIFICATION_UPDATED(200, "Notification updated"),
    NO_TOKEN(400, "Where are you from? Did you really set your X-Client-ID ?"),
    SUCCESSFUL(103200, "success"),
    SUCCESSFUL_MESSAGE(103200, "successfully Logged"),
    UN_SUCCESSFUL(103400, "failed"),
    UN_SUCCESSFUL_MESSAGE(103400, "fail to log"),
    
    //David
    LEADER_UNSUCCESSFUL_MSG(103404, "leader does not exist"),
    QUEUE_NAME_EXIST(400, "Queue name already exist"),
    BAD_JSON(103400, "Bad Json Request"),
    QUEUE_CREATED(103200, "Queue Created"),
	QUEUE_NOT_AVAILABLE(103400, "The Specified Queue does not exist"),
	NO_CONTENT(103200, "No content available");

    private final int value;
    private final String desciption;
    private static final Map<Integer, ErrorCodes> map = new HashMap<>();

    static {
        for (ErrorCodes errorMap : ErrorCodes.values()) {
            map.put(errorMap.ordinal(), errorMap);
        }
    }

    public int getCode() {
        return value;
    }

    public String getDesciption() {
        return desciption;
    }

    private static ErrorCodes fromOrdinal(int val) {
        return map.get(val);
    }

    private ErrorCodes(int value, String desciption) {
        this.desciption = desciption;
        this.value = value;
    }
}
