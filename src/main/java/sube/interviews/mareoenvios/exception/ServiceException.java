package sube.interviews.mareoenvios.exception;

public class ServiceException extends Exception{
    public ServiceException(String message) { super(message); }

    public ServiceException(Throwable cause) { super(cause); }

    public ServiceException(String message, Exception e) { super(message, e); }
}
