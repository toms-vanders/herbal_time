package Tests;

import Controller.*;
import Model.*;
import DB.*;
import org.junit.jupiter.api.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Random;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestClient {

    private DBConnection dbConnection;
    private Client testGeneratedClient;
    private Random r = new Random();
    private Integer randomGeneratedCVR = 10000000 + r.nextInt(90000000);
    private String randomGeneratedCVRString = Integer.toString(randomGeneratedCVR);
    private Date start = Date.valueOf(LocalDate.of(2020, 06, 11));
    private Date end = Date.valueOf(LocalDate.of(2025, 06, 11));

    @BeforeEach
    public void testDBConnection() {
        dbConnection = DBConnection.getInstance();
    }

    @Test
    @Order(1)
    public void testCreateNewClient() throws DataAccessException {
        ClientDB cDB = new ClientDB();
        testGeneratedClient = new Client(randomGeneratedCVRString, "Test_Client", "jobs@foetex.dk", "+4512325162", "Slotsgade", "8", "9000", "DK", "Denmark", start, end);
        // Assertions.assertEquals(cDB.insertClient(testGeneratedClient,Client.class),1);
        cDB.insertClient(testGeneratedClient,Client.class);
    }


    @Test
    @Order(2)
    public void testListAllClients() throws DataAccessException {
        ClientDB cDB = new ClientDB();
        System.out.println(cDB.findAll(false, Client.class));
    }

    @Test
    @Order(3)
    public void testUpdateClient() throws DataAccessException {
        ClientDB cDB = new ClientDB();
        testGeneratedClient = new Client(randomGeneratedCVRString, "TEST-"+randomGeneratedCVRString, "jobs@foetex.dk", "+4512325162", "Slotsgade", "8", "9000", "DK", "Denmark", start, end);
        System.out.println(testGeneratedClient.toString());
        //Assertions.assertEquals(cDB.updateClient(randomGeneratedCVRString,testGeneratedClient,Client.class), 1);
        cDB.updateClient(randomGeneratedCVRString,testGeneratedClient,Client.class);
    }


    @Test
    @Order(4)
    public void testCleanup() throws DataAccessException{
        ClientDB cDB = new ClientDB();
        cDB.deleteClient(randomGeneratedCVRString, Client.class);

    }
}
