package DB.Exception;

public class ConnectionNotAvailable extends Exception {
    private static final long serialVersionUID = 2L;

    public ConnectionNotAvailable(String message,Throwable e){
        super(message,e);
    }
}
