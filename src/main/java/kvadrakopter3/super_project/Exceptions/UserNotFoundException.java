package kvadrakopter3.super_project.Exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String userName) {
        super(String.format("User with name: %s not found", userName));
    }

    public UserNotFoundException(long id) {
        super(String.format("User with id: %d not found", id));
    }
}
