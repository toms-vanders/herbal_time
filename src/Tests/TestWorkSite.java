package Tests;

import DB.Exception.DataAccessException;
import DB.DBConnection;
import DB.WorkSiteDB;
import Model.WorkSite;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Random;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

/**
 * Tests the WorkSite table of the database
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
public class TestWorkSite {

    private final Random r = new Random();
    private final Integer randomGeneratedNum = 10000000 + r.nextInt(90000000);
    private final String randomGeneratedNumString = Integer.toString(randomGeneratedNum);
    private static DBConnection dbConnection;
    private int wSiteID;
    private WorkSite ws;

    @BeforeEach
    public void testDBWorkSite() {
        System.out.println("Initiating DB Connection");
        dbConnection = DBConnection.getInstance();
        System.out.println(dbConnection.toString() + "\n");
    }

    /**
     * Inserts a WorkSite into the database
     *
     * @throws DataAccessException
     */
    @Test
    @Order(1)
    public void testInsertWorkSite() throws DataAccessException {
        ws = new WorkSite("site"+randomGeneratedNumString, "Come work with us, you'll be in good hands",
                "Egensevej", "155", "9270", "Denmark", "DK", "test",
                7250.6);
        WorkSiteDB wsDB = new WorkSiteDB();
        wSiteID = wsDB.insertWorkSite("45678932", ws);
    }

    /**
     * Lists all WorkSites from the database
     *
     * @throws DataAccessException
     */
    @Test
    @Order(2)
    public void testGetAllWorkSites() throws DataAccessException {
        WorkSiteDB wsDB = new WorkSiteDB();
        List<WorkSite> res1 = wsDB.findAll(false);
        for (WorkSite ws : res1) {
            System.out.println(ws.toString());
        }
    }

    /**
     * Lists all WorkSites belonging to a Client from the database
     *
     * @throws DataAccessException
     */
    @Test
    @Order(3)
    public void testGetAllWorkSitesFromClient() throws DataAccessException {
        WorkSiteDB wsDB = new WorkSiteDB();
        List<WorkSite> res1 = wsDB.findWorkSitesOfClient("45678932", false);
        for (WorkSite ws : res1) {
            System.out.println(ws.toString());
        }
    }

    /**
     * Updates an existing WorkSite in the database
     *
     * @throws DataAccessException
     */
    @Test
    @Order(4)
    public void testUpdateWorkSite() throws DataAccessException {
        WorkSiteDB wsDB = new WorkSiteDB();
        ws = new WorkSite("UPDATED"+randomGeneratedNumString, "Come work with us, you'll be in good hands",
                "Egensevej", "155", "9270", "Denmark", "DK", "test",
                7250.6);
        wsDB.updateWorkSiteByName("site"+randomGeneratedNumString, ws);
    }

    /**
     * Deletes an existing WorkSite from the database
     *
     * @throws DataAccessException
     */
    @Test
    @Order(5)
    public void testCleanUp() throws DataAccessException {
        WorkSiteDB wsDB = new WorkSiteDB();
        wsDB.deleteWorkSiteByName("UPDATED"+randomGeneratedNumString);
    }


}
