package Tests;

import DB.DataAccessException;
import DB.DBConnection;
import DB.SeasonalWorkerDB;
import Model.SeasonalWorker;
import org.junit.jupiter.api.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Random;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

/**
 * Tests the SeasonalWorker table of the database
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
public class TestSeasonalWorker {

    private DBConnection dbConnection;
    private final Random r = new Random();
    private final Integer randomGeneratedCPR = 10000000 + r.nextInt(90000000);
    private final String randomGeneratedCPRString = Integer.toString(randomGeneratedCPR);
    private final Date dob = Date.valueOf(LocalDate.of(1995, 6,11));
    private final Date start = Date.valueOf(LocalDate.of(2020, 6, 11));
    private final Date end = Date.valueOf(LocalDate.of(2025, 6, 11));

    @BeforeEach
    public void testDBConnection() {
        dbConnection = DBConnection.getInstance();
    }

    /**
     * Inserts a SeasonalWorker into the database
     *
     * @throws DataAccessException
     */
    @Test
    @Order(1)
    public void testCreateNewSeasonalWorker() throws DataAccessException {
        SeasonalWorkerDB swDB = new SeasonalWorkerDB();
        SeasonalWorker testSWleader = new SeasonalWorker(randomGeneratedCPRString, "firstName",
                "lastName", dob, 'f', "test@gmail.com", "000", "testStreet",
                "0", "9000", "DK", "Denmark", "123456789", "12345678910", "99161721446", "12312193581230971231", false);
        swDB.insertSeasonalWorker(testSWleader, 1, SeasonalWorker.class);
        System.out.println("Successfully inserted Seasonal Worker");
    }

    /**
     * Lists all SeasonalWorkers from the database
     *
     * @throws DataAccessException
     */
    @Test
    @Order(2)
    public void testListAllSeasonalWorkers() throws DataAccessException {
        SeasonalWorkerDB swDB = new SeasonalWorkerDB();
        System.out.println(swDB.findAll(false, SeasonalWorker.class));
        System.out.println("Successfully listed all Seasonal Workers");
    }

    /**
     * Updates a SeasonalWorker in the database
     *
     * @throws DataAccessException
     */
    @Test
    @Order(3)
    public void testUpdateSeasonalWorker() throws DataAccessException {
        SeasonalWorkerDB swDB = new SeasonalWorkerDB();
        SeasonalWorker testGeneratedSeasonalWorker = new SeasonalWorker(randomGeneratedCPRString, "UPDATED", "UPDATED", dob, 'f', "UPDATED",
                "UPDATED", "UPDATED", "0", "9000", "DK",
                "Denmark", "421456789", "1234178910", "99161726446", "14212193581230971231", true);
        swDB.updateSeasonalWorker(randomGeneratedCPRString,testGeneratedSeasonalWorker,testGeneratedSeasonalWorker.getClass());
        System.out.println("Successfully updated Seasonal Worker");
    }

    /**
     * Deletes a SeasonalWorker from the database
     *
     * @throws DataAccessException
     */
    @Test
    @Order(4)
    public void testCleanup() throws DataAccessException{
        SeasonalWorkerDB swDB = new SeasonalWorkerDB();
        swDB.deleteSeasonalWorker(randomGeneratedCPRString, SeasonalWorker.class);
        System.out.println("Successfully deleted Seasonal Worker");
    }
}

