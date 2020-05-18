package Tests;
import Controller.*;
import Model.*;
import DB.*;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class TestDBConection {

    /**
     * I'm not sure what this class could be used for.
     *
     * idea 1 - "SELECT * FROM INFORMATION_SCHEMA.TABLES;" and check that all table names are correct?
     */

    private DBConnection dbConnection;

    @BeforeEach
    public void testDBConnection() {
        System.out.println("Initiating DB Connection");
        dbConnection = DBConnection.getInstance();
        System.out.println(dbConnection.toString());
    }
}
