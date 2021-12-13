package kvadrakopter3.super_project.ExceptionHandler;

import java.util.Date;

public class ExceptionResponseBody {
    private final String name;

    private final String message;

    private final Date date;

    private final String type;

    public ExceptionResponseBody(Exception e,String exceptionType) {
        this.name = e.getClass().getName();
        this.message = e.getMessage();
        this.date = new Date();
        this.type = exceptionType;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return date;
    }

    public String getType() {
        return type;
    }
}
