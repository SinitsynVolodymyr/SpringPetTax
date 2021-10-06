package ua.sinitsyn.exception;

public class ThisEmailIsBusyException extends Exception {

    String email;

    public ThisEmailIsBusyException(String email) {
        this.email = email;
    }

    @Override
    public String getMessage() {
        return "Email "+ email +" is busy.";
    }
}
