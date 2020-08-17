package main.java.handler;

public class AppException extends Exception{
    public AppException() {
        super();
    }
    public AppException(String e) {
        super (e);
    }
    public AppException(Throwable e) {
        super (e);
    }
}
