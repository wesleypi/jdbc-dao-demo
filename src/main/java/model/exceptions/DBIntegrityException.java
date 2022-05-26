package model.exceptions;

public class DBIntegrityException extends Throwable {
    public DBIntegrityException(String msg){
        super(msg);
    }
}
