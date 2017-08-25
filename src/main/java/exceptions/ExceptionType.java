package exceptions;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public enum ExceptionType {

    BAD_REQUEST("1", Response.Status.BAD_REQUEST, "Params/Data are missing or invalid"),
    NOT_FOUND("2", Response.Status.NOT_FOUND, "Resource not found");
    private Response.Status status;
    private ExceptionInfo exceptionInfo;

    ExceptionType(String code, Response.Status status, String userMessage) {
        this.status = status;
        this.exceptionInfo = new ExceptionInfo(status.getStatusCode(), code, userMessage, null);
    }

    public void addDescription(String description) {
        exceptionInfo.setDescription(description);
    }

    public Response getResponseEntity() {
        return Response.status(this.status)
                .entity(this.exceptionInfo)
                .type(MediaType.APPLICATION_JSON).build();
    }

    public ExceptionInfo getexceptionInfo() {
        return exceptionInfo;
    }

}