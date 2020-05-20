package Tests;

import Controller.*;
import Model.*;
import DB.*;
import org.junit.jupiter.api.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Random;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class TestSeasonalWorker {

    private DBConnection dbConnection;
    private Random r = new Random();
    private Integer randomGeneratedCPR = 10000000 + r.nextInt(90000000);
    private String randomGeneratedCPRString = Integer.toString(randomGeneratedCPR);
    private Date dob = Date.valueOf(LocalDate.of(1995,06,11));
    private Date start = Date.valueOf(LocalDate.of(2020, 06, 11));
    private Date end = Date.valueOf(LocalDate.of(2025, 06, 11));
    private SeasonalWorker testGeneratedSeasonalWorker = new SeasonalWorker("01234567890", "firstName",
            "lastName", dob, 'f', "test@gmail.com", "000", "testStreet",
            "0", "9000", "DK", "Denmark");

    @BeforeEach
    public void testDBConnection() {
        dbConnection = DBConnection.getInstance();
    }

    @Test
    @Order(1)
    public void testCreateNewSeasonalWorker() throws DataAccessException {
        SeasonalWorkerDB swDB = new SeasonalWorkerDB();
        SeasonalWorker newTestGeneratedSeasonalWorker = new SeasonalWorker("01234567890", "firstName",
                "lastName", dob, 'f', "test@gmail.com", "000", "testStreet",
                "0", "9000", "DK", "Denmark");
        //, "012345678", "01234567890",
        //                "012345678901234567890123456789012" , "01234567890123456789012345678901234567890123456789",
        //        false, testGeneratedSeasonalWorker
        // TODO: this was causing an error - not sure how to fix
        swDB.insertSeasonalWorker(newTestGeneratedSeasonalWorker, 1, SeasonalWorker.class); //todo - ensure there is worksite with ID 1
    }


    @Test
    @Order(2)
    public void testListAllSeasonalWorkers() throws DataAccessException {
        SeasonalWorkerDB swDB = new SeasonalWorkerDB();
        System.out.println(swDB.findAll(false, SeasonalWorker.class));
    }

    @Test
    @Order(3)
    public void testUpdateSeasonalWorker() throws DataAccessException {
        SeasonalWorkerDB swDB = new SeasonalWorkerDB();
        testGeneratedSeasonalWorker = new SeasonalWorker("01234567890", "firstName", "lastName", dob, 'f', "test@gmail.com",
                "000", "testStreet", "0", "9000", "DK",
                "Denmark");
        System.out.println(testGeneratedSeasonalWorker.toString());
        swDB.updateSeasonalWorker(randomGeneratedCPRString,testGeneratedSeasonalWorker,SeasonalWorker.class);
    }


    @Test
    @Order(4)
    public void testCleanup() throws DataAccessException{
        SeasonalWorkerDB swDB = new SeasonalWorkerDB();
        swDB.deleteSeasonalWorker(randomGeneratedCPRString, SeasonalWorker.class);

    }
}

