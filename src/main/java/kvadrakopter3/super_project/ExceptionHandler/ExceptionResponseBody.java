package kvadrakopter3.super_project.ExceptionHandler;

import java.util.Date;

public class ExceptionResponseBody {
    private final String exceptionName;

    private final String exceptionMessage;

    private final Date exceptionDate;

    private final String exceptionTape;

    public ExceptionResponseBody(Exception e,String exceptionTape) {
        this.exceptionName = e.getClass().getName();
        this.exceptionMessage = e.getMessage();
        this.exceptionDate = new Date();
        this.exceptionTape = exceptionTape;
    }

    public String getExceptionName() {
        return exceptionName;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public Date getExceptionDate() {
        return exceptionDate;
    }

    public String getExceptionTape() {
        return exceptionTape;
    }
}
