package Tests;
import Controller.*;
import Model.*;
import DB.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions.*;

import javax.xml.crypto.Data;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Random;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class TestDBConection {

    private DBConnection dbConnection;
    private Client testGeneratedClient;
    private Integer randomGeneratedCVR;


    @BeforeEach
    public void testDBConnection() {
        System.out.println("Initiating DB Connection");
        dbConnection = DBConnection.getInstance();
        System.out.println(dbConnection.toString());
    }

    @Test
    @Order(1)
    public void testCreateNewClient() throws DataAccessException {
        LocalDate dateStart = LocalDate.of(2020, 06, 11);
        Date start = Date.valueOf(dateStart);

        LocalDate dateEnd = LocalDate.of(2025, 06, 11);
        Date end = Date.valueOf(dateEnd);
        ClientDB cDB = new ClientDB();
        // this Random part here is because CVR is primary key. To avoid
        // having to remove the test Client every time, just generate a
        // random CVR everytime :shrug:
        Random rnd = new Random();
        randomGeneratedCVR = 10000000 + rnd.nextInt(90000000);
        System.out.println("Random CVR is -> "+randomGeneratedCVR);
        testGeneratedClient = new Client(Integer.toString(randomGeneratedCVR), "Føtex", "jobs@foetex.dk", "+4512325162", "Slotsgade", "8", "9000", "DK", "Denmark", start, end);
        Assertions.assertEquals(cDB.insertClient(testGeneratedClient,Client.class),1);
    }


    @Test
    @Order(2)
    public void testListAllClients() throws DataAccessException {
        ClientDB cDB = new ClientDB();
        cDB.findAll(false, Client.class);
    }

    @Test
    @Order(3)
    public void testCleanup() {

    }

    @Test
    @Order(4)
    public void testUpdateClient() throws DataAccessException {
        LocalDate dateStart = LocalDate.of(2020, 06, 11);
        Date start = Date.valueOf(dateStart);
        LocalDate dateEnd = LocalDate.of(2025, 06, 11);
        Date end = Date.valueOf(dateEnd);
        ClientDB cDB = new ClientDB();
        Random rnd = new Random();
        randomGeneratedCVR = 10000000 + rnd.nextInt(90000000);
        System.out.println("Random CVR is -> "+randomGeneratedCVR);
        testGeneratedClient = new Client("35225432", "TEST-"+Integer.toString(randomGeneratedCVR), "jobs@foetex.dk", "+4512325162", "Slotsgade", "8", "9000", "DK", "Denmark", start, end);
        System.out.println(testGeneratedClient.toString());
        Assertions.assertEquals(cDB.updateClient("35225432",testGeneratedClient,Client.class), 1);
    }

    @Test
    @Order(5)
    public void testDeleteClient() throws DataAccessException {
        ClientDB cDB = new ClientDB();
        Date start = Date.valueOf(LocalDate.of(2020, 06, 11));
        Date end = Date.valueOf(LocalDate.of(2025, 06, 11));
//        Client testClientToDelete = new Client("00012345", "TO-BE-DELETED", "test@deleted.dk", "+4500000000", "n/a", "n/a", "9000", "DK", "Denmark", start, end);
//        cDB.insertClient(testClientToDelete, Client.class);
        cDB.deleteClient("00012345", Client.class);
    }
}
