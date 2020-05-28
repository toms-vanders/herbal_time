package Tests;
import DB.DBConnection;
import org.junit.jupiter.api.*;

/**
 * Tests connection to the database
 *
 * @author Daniel Zoltan Ban
 * @author Mikuláš Dobrodej
 * @author Adrian Mihai Dohot
 * @author Damian Hrabąszcz
 * @author Toms Vanders
 * @version 1.0
 *
 * Date: 29.05.2020
 */
public class TestDBConnection {

    private DBConnection dbConnection;

    @Test
    public void testDBConnection() {
        System.out.println("Initiating DB Connection");
        dbConnection = DBConnection.getInstance();
        System.out.println(dbConnection.toString());
        dbConnection.disconnect();
    }
}
