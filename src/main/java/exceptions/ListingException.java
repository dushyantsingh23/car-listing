package exceptions;

import javax.ws.rs.WebApplicationException;
import java.io.Serializable;

public class ListingException extends WebApplicationException implements Serializable {

    private String code;

    public ListingException(ExceptionType exceptionType, String description) {
        super(exceptionType.getResponseEntity());
        exceptionType.addDescription(description);
        code = exceptionType.getexceptionInfo().getCode();
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
