import GUI.DBStatus.StatusThread;
import GUI.MainScreen;

public class Main {

    private static final StatusThread connectionStatus = new StatusThread();

    public static void main(String[] args) {
        connectionStatus.start();
        System.out.println("Hello world");
    }
}
