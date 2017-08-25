package exceptions;

public class ExceptionInfo {

    private Integer httpStatusCode;
    private String code;
    private String userMessage;
    private String description;

    public ExceptionInfo() {
    }

    public ExceptionInfo(Integer httpStatusCode, String code, String userMessage, String description) {
        this.httpStatusCode = httpStatusCode;
        this.code = code;
        this.userMessage = userMessage;
        this.description = description;
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    public String getCode() {
        return code;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
