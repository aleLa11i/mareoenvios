package sube.interviews.mareoenvios.exception;

public class RepositoryException extends Exception{

    public RepositoryException(String message) { super(message); }

    public RepositoryException(Throwable cause) { super(cause); }

    public RepositoryException(String message, Exception e) { super(message, e); }
}
