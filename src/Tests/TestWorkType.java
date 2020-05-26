package Tests;

import Controller.DataAccessException;
import DB.DBConnection;
import DB.WorkTypeDB;
import Model.WorkType;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TestWorkType {
    private static DBConnection dbConnection;

    private static final String deleteWorkType = "DELETE FROM WorkType WHERE typeOfProduce = 'test'";

    @BeforeEach
    public void testDBWorkType() {
        System.out.println("Initiating DB Connection");
        dbConnection = DBConnection.getInstance();
        System.out.println(dbConnection.toString() + "\n");
    }

    @Test
    public void createNewWorkType() throws DataAccessException {
        WorkType wt = new WorkType("cotton picking", "test", "Amount", 100);
        WorkTypeDB wtDB = new WorkTypeDB();
        wtDB.insertWorkType(1, wt);
        WorkType wt2 = new WorkType("cotton picking days", "test", "Amount", 100);
        wtDB.insertWorkType(1, wt2);
    }

    @Test
    public void testGetAllWorkTypesFromWorkSite() throws DataAccessException {
        createNewWorkType();
        WorkTypeDB wtDB = new WorkTypeDB();
        List<WorkType> res1 = wtDB.findAllWorkTypesOfWorkSite(1);
        for (WorkType wt : res1) {
            System.out.println(wt.toString());
        }
    }

    @AfterAll
    static void cleanUp() throws DataAccessException {
        PreparedStatement ps = null;
        Integer affectedRows = 0;
        Connection con = dbConnection.getConnection();
        try {
            ps = con.prepareStatement(deleteWorkType);
        } catch (SQLException e) {
            throw new DataAccessException("Issue cleaning up after the tests (preparing statement).", e);
        }

        try {
            affectedRows += ps.executeUpdate();
            System.out.println("cleanUp() affected rows: " + affectedRows);
        } catch (SQLException e) {
            throw new DataAccessException("Issue cleaning up after the tests (executing update).", e);
        }

        Assertions.assertEquals(2, affectedRows);
    }


}
