package kvadrakopter3.super_project.ExceptionHandler;

public class UserPasswordHaveNoMatches extends Exception {
    public UserPasswordHaveNoMatches() {
        super("password no match");
    }
}
