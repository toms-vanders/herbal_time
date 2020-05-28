package Tests;

import DB.DataAccessException;
import DB.ClientDB;
import DB.DBConnection;
import Model.Client;
import org.junit.jupiter.api.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Random;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

/**
 * Tests the Client table of the database
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
public class TestClient {

    private DBConnection dbConnection;
    private Client testGeneratedClient;
    private final Random r = new Random();
    private final Integer randomGeneratedCVR = 10000000 + r.nextInt(90000000);
    private final String randomGeneratedCVRString = Integer.toString(randomGeneratedCVR);
    private final Date start = Date.valueOf(LocalDate.of(2020, 6, 11));
    private final Date end = Date.valueOf(LocalDate.of(2025, 6, 11));

    @BeforeEach
    public void testDBConnection() {
        dbConnection = DBConnection.getInstance();
    }

    /**
     * Inserts a Client into the database
     *
     * @throws DataAccessException
     */
    @Test
    @Order(1)
    public void testCreateNewClient() throws DataAccessException {
        ClientDB cDB = new ClientDB();
        testGeneratedClient = new Client(randomGeneratedCVRString, "Test_Client", "jobs@foetex.dk", "+4512325162", "Slotsgade", "8", "9000", "DK", "Denmark", start, end);
        cDB.insertClient(testGeneratedClient,Client.class);
        System.out.println("Successfully inserted test client!");
    }

    /**
     * Lists all Clients from the database
     *
     * @throws DataAccessException
     */
    @Test
    @Order(2)
    public void testListAllClients() throws DataAccessException {
        ClientDB cDB = new ClientDB();
        System.out.println(cDB.findAll(false, Client.class));
        System.out.println("Successfully listed all clients!");
    }

    /**
     * Updates an existing Client in the database
     *
     * @throws DataAccessException
     */
    @Test
    @Order(3)
    public void testUpdateClient() throws DataAccessException {
        ClientDB cDB = new ClientDB();
        testGeneratedClient = new Client(randomGeneratedCVRString, "TEST-"+randomGeneratedCVRString, "jobs@foetex.dk", "+4512325162", "Slotsgade", "8", "9000", "DK", "Denmark", start, end);
        cDB.updateClient(randomGeneratedCVRString,testGeneratedClient,Client.class);
        System.out.println("Successfully updated test client!");
    }

    /**
     * Deletes an existing Client from the database
     *
     * @throws DataAccessException
     */
    @Test
    @Order(4)
    public void testCleanup() throws DataAccessException{
        ClientDB cDB = new ClientDB();
        cDB.deleteClient(randomGeneratedCVRString);
        System.out.println("Successfully cleaned up tests!");

    }
}
