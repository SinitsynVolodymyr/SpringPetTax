package ua.sinitsyn.exception;

public class ThisEmailIsBusyException extends Exception {

    private String email;

    public ThisEmailIsBusyException(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getMessage() {
        return "Email "+ email +" is busy.";
    }
}
