package Tests;

import Controller.DataAccessException;
import DB.DBConnection;
import DB.WorkSiteDB;
import Model.WorkSite;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TestWorkSite {
    private static DBConnection dbConnection;

    private static final String deleteWorkSite = "DELETE FROM WorkSite where typeOfJob = 'test'";

    @BeforeEach
    public void testDBWorkSite() {
        System.out.println("Initiating DB Connection");
        dbConnection = DBConnection.getInstance();
        System.out.println(dbConnection.toString() + "\n");
    }

    @Test
    public void createNewWorkSite() throws DataAccessException {
        WorkSite ws = new WorkSite("The best work site", "Come work with us, you'll be in good hands",
                "Egensevej", "155", "9270", "Denmark", "DK", "test",
                7250.6);
        WorkSiteDB wsDB = new WorkSiteDB();
        wsDB.insertWorkSite("45678932", ws);
    }

    @Test
    public void testGetAllWorkSites() throws DataAccessException {
        WorkSiteDB wsDB = new WorkSiteDB();
        List<WorkSite> res1 = wsDB.findAll(false);
        for (WorkSite ws : res1) {
            System.out.println(ws.toString());
        }
    }

    @Test
    public void testGetAllWorkSitesFromClient() throws DataAccessException {
        createNewWorkSite();
        WorkSiteDB wsDB = new WorkSiteDB();
        List<WorkSite> res1 = wsDB.findWorkSitesOfClient("45678932", false);
        for (WorkSite ws : res1) {
            System.out.println(ws.toString());
        }
    }

    @AfterAll
    static void cleanUp() throws DataAccessException {
        PreparedStatement ps = null;
        Integer affectedRows = 0;
        Connection con = dbConnection.getConnection();
        try {
            ps = con.prepareStatement(deleteWorkSite);
        } catch (SQLException e) {
            throw new DataAccessException("Issue cleaning up after the tests (preparing statement).", e);
        }

        try {
            affectedRows += ps.executeUpdate();
            System.out.println("cleanUp() affected rows: " + affectedRows);
        } catch (SQLException e) {
            throw new DataAccessException("Issue cleaning up after the tests (executing update).", e);
        }

        Assertions.assertEquals(1, affectedRows);
    }

}
