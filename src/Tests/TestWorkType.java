package Tests;

import DB.DataAccessException;
import DB.DBConnection;
import DB.WorkTypeDB;
import Model.WorkType;
import org.junit.jupiter.api.*;

import java.util.Random;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

/**
 * Tests the WorkType table of the database
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
public class TestWorkType {

    private final Random r = new Random();
    private final Integer randomGeneratedNum = 10000000 + r.nextInt(90000000);
    private final String randomGeneratedNumString = Integer.toString(randomGeneratedNum);
    private static DBConnection dbConnection;
    private WorkType wt;

    @BeforeEach
    public void testDBWorkType() {
        System.out.println("Initiating DB Connection");
        dbConnection = DBConnection.getInstance();
        System.out.println(dbConnection.toString() + "\n");
    }

    /**
     * Inserts a WorkType into the database
     *
     * @throws DataAccessException
     */
    @Test
    @Order(1)
    public void testInsertWorkType() throws DataAccessException {
        wt = new WorkType(1, "d"+randomGeneratedNumString,"typeofProduce","hourly",70.00);
        WorkTypeDB wtDB = new WorkTypeDB();
        wtDB.insertWorkType(1, wt);
        System.out.println("Successfully inserted test WorkType");
    }

    /**
     * Lists all WorkTypes from the database
     *
     * @throws DataAccessException
     */
    @Test
    @Order(2)
    public void testGetAllWorkTypes() throws DataAccessException {
        WorkTypeDB wtDB = new WorkTypeDB();
        System.out.println(wtDB.findAll());
        System.out.println("Successfully fetched all WorkTypes");
    }

    /**
     * Lists all WorkTypes belonging to a WorkSite from the database
     *
     * @throws DataAccessException
     */
    @Test
    @Order(3)
    public void testGetAllWorkTypesFromWorkSite() throws DataAccessException {
        WorkTypeDB wtDB = new WorkTypeDB();
        System.out.println(wtDB.findAllWorkTypesOfWorkSite(1));
        System.out.println("Successfully fetched all WorkTypes from WorkSite #1");
    }

    /**
     * Updates an existing WorkType in the database
     *
     * @throws DataAccessException
     */
    @Test
    @Order(4)
    public void testUpdateWorkType() throws DataAccessException {
        WorkTypeDB wtDB = new WorkTypeDB();
        wt = new WorkType(1, "UPDATED"+randomGeneratedNumString,"typeofProduce","hourly",70.00);
        // This first finds workTypeID of inserted test WorkType, then updates it with Updated WorkType
        wtDB.updateWorkType(wtDB.findWorkTypeIDByDescription("d"+randomGeneratedNumString), wt);
        System.out.println("Successfully updated test WorkType");
    }

    /**
     * Deletes an existing WorkType from the database
     *
     * @throws DataAccessException
     */
    @Test
    @Order(5)
    public void testCleanUp() throws DataAccessException {
        WorkTypeDB wtDB = new WorkTypeDB();
        int wtID = wtDB.findWorkTypeIDByDescription("UPDATED"+randomGeneratedNumString);
        wtDB.deleteWorkType(wtID);
        System.out.println("Successfully deleted test WorkType");
    }

}
