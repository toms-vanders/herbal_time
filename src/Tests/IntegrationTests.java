package Tests;

import Controller.DataAccessException;
import DB.*;
import Model.*;
import org.junit.jupiter.api.*;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

/**
 * Tests how multiple database tables interact together
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
public class IntegrationTests {
    private static DBConnection dbConnection;
    private final Date dob = Date.valueOf(LocalDate.of(1995,06,11));
    private final Date start = Date.valueOf(LocalDate.of(2020, 06, 11));
    private final Date end = Date.valueOf(LocalDate.of(2025, 06, 11));
    private Random r = new Random();
    private Integer randomGeneratedNum = 10000000 + r.nextInt(90000000);
    private String randomGeneratedNumString = Integer.toString(randomGeneratedNum);


    @BeforeEach
    public void testDBConnection() {
        System.out.println("Initiating DB Connection");
        dbConnection = DBConnection.getInstance();
        System.out.println(dbConnection.toString());
    }

    /**
     * A comprehensive test covering many parts of the code & database
     *
     * @throws DataAccessException
     */
    @Test
    @Order(1)
    public void testIntegration() throws DataAccessException {

        ClientDB cDB = new ClientDB();
        WorkSiteDB wsDB = new WorkSiteDB();
        WorkTypeDB wtyDB = new WorkTypeDB();
        WorkTaskDB wtaDB = new WorkTaskDB();
        SeasonalWorkerDB sDB = new SeasonalWorkerDB();
        System.out.println("Successfully created Database access objects");

        /* Inserting data into Tables*/

        // Client
        Client testGeneratedClient = new Client(randomGeneratedNumString, "Test_Client", "jobs@foetex.dk", "+4512325162", "Slotsgade", "8", "9000", "DK", "Denmark", start, end);
        cDB.insertClient(testGeneratedClient,Client.class);
        //WorkSite
        WorkSite testGeneratedwSite = new WorkSite("site"+randomGeneratedNumString, "Come work with us, you'll be in good hands",
                "Egensevej", "155", "9270", "Denmark", "DK", "test",
                7250.6);
        // Site is attached to the client
        wsDB.insertWorkSite(randomGeneratedNumString, testGeneratedwSite);
        // Site is updated with the proper workSiteID
        testGeneratedwSite = wsDB.findByName("site"+randomGeneratedNumString, false);
        // SeasonalWorker
        SeasonalWorker testSeasonalWorker = new SeasonalWorker(randomGeneratedNumString, "firstName",
                "lastName", dob, 'f', "test@gmail.com", "000", "testStreet",
                "0", "9000", "DK", "Denmark", "123456789", "12345678910", "99161721446", "12312193581230971231", false);
        // SeasonalWorker inserted and assigned to test WorkSite
        sDB.insertSeasonalWorker(testSeasonalWorker, testGeneratedwSite.getWorkSiteID(), SeasonalWorker.class);
        //WorkType
        WorkType testGeneratedwType = new WorkType(1, "d"+randomGeneratedNumString,"typeofProduce","hourly",70.00);
        wtyDB.insertWorkType(testGeneratedwSite.getWorkSiteID(),testGeneratedwType);
        //WorkTask
        WorkTask testWorkTask = new WorkTask(1, 8, 40.0,start,end,"PENDING", testGeneratedwType);
        wtaDB.insertWorkTask(testWorkTask,randomGeneratedNumString);

        /* Test for update & delete (cascade) */
        // TODO, make this part


        /* Additional cleanup that was not covered by the previous part*/
    }

    @AfterAll
    static void cleanUp() throws DataAccessException {
        // There probably won't need to be anything here
    }
}
