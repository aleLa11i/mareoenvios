package sube.interviews.mareoenvios.exception;

public class BusinessException extends Exception{

    public BusinessException(String message) { super(message); }

    public BusinessException(Throwable cause) { super(cause); }

    public BusinessException(String message, Exception e) { super(message, e); }
}
