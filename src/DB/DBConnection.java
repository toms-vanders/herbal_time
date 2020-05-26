package DB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Provides a fixed connection to UCN's hildur server, and our personal database.
 */
public class DBConnection {

    private static Connection connection;
    private static DBConnection dbConnection;

    /*
     * Configure database connection parameters
     */
    private static final String driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String dbName = "dmaj0919_1081496";
    private static final String serverAddress = "hildur.ucn.dk";
    private static final int serverPort = 1433;
    private static final String userName = "dmaj0919_1081496";
    private static final String password = "Password1!";

    private DBConnection() {
        connect();
    }

    public synchronized static void connect() {
        String connectionString = String.format(
                "jdbc:sqlserver://%s:%d;databaseName=%s;user=%s;password=%s"
                , serverAddress
                , serverPort
                , dbName
                , userName
                , password
        );

        try {
            Class.forName(driverClass);
            connection = DriverManager.getConnection(connectionString);
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC driver not found, ensure it has been properly loaded into the project.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("DB connection failed");
            System.out.println(
                    String.format(
                            "Database: %s \t | Address: %s:%d \t | User: %s"
                            , dbName
                            , serverAddress
                            , serverPort
                            , userName
                    )
            );
        }
    }

    public static synchronized DBConnection getInstance() {
        if (dbConnection == null) {
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }

    public Connection getConnection() {
        return connection;
    }

    public static void disconnect() {
        try {
            connection.close();
            connection = null;
        } catch (SQLException e) {
            System.out.println("Failed to close DB connection.");
            e.printStackTrace();
        }
    }

    public static boolean instanceIsNull() {
        return (connection == null);
    }

}
