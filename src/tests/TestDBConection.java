package tests;
import controller.*;
import model.*;
import db.*;
import org.junit.jupiter.api.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Random;

public class TestDBConection {

    private DBConnection dbConnection;


    @BeforeEach
    public void testDBConnection() {
        System.out.println("Initiaing DB Connection");
        dbConnection = DBConnection.getInstance();
        System.out.println(dbConnection.toString());

    }

    @Test
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
        int cvr_random = 10000000 + rnd.nextInt(90000000);
        System.out.println("Random CVR is -> "+cvr_random);
        Client testClient = new Client(Integer.toString(cvr_random), "Rema 1000", "slavery@rema1000.dk", "+4512325262", "Saltumvej", "42", "9220", "DK", "Denmark", start, end);
        cDB.insertClient(testClient,Client.class);
    }


    @Test
    public void testListAllClients() throws DataAccessException {
        ClientDB cDB = new ClientDB();
        cDB.findAll(false, Client.class);
    }
}
