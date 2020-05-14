package Tests;

import Controller.DataAccessException;
import DB.ClientDB;
import DB.DBConnection;
import DB.WorkSiteDB;
import DB.WorkTypeDB;
import Model.Client;
import Model.WorkSite;
import Model.WorkType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class IntegrationTests {
    private static DBConnection dbConnection;

    @BeforeEach
    public void testDBConnection() {
        System.out.println("Initiating DB Connection");
        dbConnection = DBConnection.getInstance();
        System.out.println(dbConnection.toString());
    }


    // workSite + workType integration test
    // TODO
    @Test
    public void testGetAllWorkTypeFromWorkSite() throws DataAccessException {
        WorkTypeDB wtDB = new WorkTypeDB();

        List<WorkType> res1 = wtDB.findAll(3, false, WorkType.class);
        Assertions.assertFalse(res1.isEmpty(), "There no work types associated with the work site 3");
        for (WorkType wt : res1) {
            System.out.println(wt.toString());
        }
        List<WorkType> res2 = wtDB.findAll(9999, false, WorkType.class);
        Assertions.assertTrue(res2.isEmpty(), "There are some work types associated with the work site 9999");
    }

    // workSite + workType + client integration test
    // insert, update, select, delete
    @Test
    public void testClientWorkSiteWorkTypeIntegrationTest() throws DataAccessException {
        ClientDB cDB = new ClientDB();
        WorkSiteDB wsDB = new WorkSiteDB();
        WorkTypeDB wtDB = new WorkTypeDB();

        System.out.println("Successfully created Database access objects");


        // Adding Client test article
        String testClient1CVR = "integrationTest1234";
        String testClient1Name = "The best client";
        String testClient1Email = "testing@tests.org";
        String testClient1PhoneNum = "+4580124996";
        String testClient1StreetName = "Flødalvej";
        String testClient1StreetNum = "25";
        String testClient1ZIP = "9230";
        String testClient1CC = "DK";
        String test1ClientCountry = "Denmark";
        Date testClient1start = Date.valueOf(LocalDate.of(2020, 06, 11));
        Date testClient1end = Date.valueOf(LocalDate.of(2025, 06, 11));
        Client testClient1 = new Client(testClient1CVR, testClient1Name, testClient1Email,
                testClient1PhoneNum, testClient1StreetName, testClient1StreetNum, testClient1ZIP, testClient1CC,
                test1ClientCountry, testClient1start, testClient1end);
        Integer affectedRows = cDB.insertClient(testClient1, Client.class);
        if (affectedRows <= 0) {
            System.err.println("! ERROR: Adding test article Client(CVR: + " + testClient1CVR + ") failed.");
        } else if (affectedRows == 1) {
            System.out.println("Successfully added test article Client(CVR: " + testClient1CVR + ")");
        } else if (affectedRows == 2) {
            System.out.println("Successfully added test articles: 1. Client(CVR: " + testClient1CVR +
                    "), 2. ZipCity(ZIP: " + testClient1ZIP + ", Country Code: " + testClient1CC + ").");
        }

        Client testClient1Check = cDB.findClientByCVR(testClient1CVR, false, Client.class);
        Assertions.assertEquals(testClient1CVR, testClient1Check.getCvr(), "CVR invalid");
        Assertions.assertEquals(testClient1Name, testClient1Check.getName(), "Name invalid");
        Assertions.assertEquals(testClient1Email, testClient1Check.getEmail(), "Email invalid");
        Assertions.assertEquals(testClient1PhoneNum, testClient1Check.getPhoneNum(), "PhoneNum invalid");
        Assertions.assertEquals(testClient1StreetName, testClient1Check.getStreetName(), "StreetName invalid");
        Assertions.assertEquals(testClient1StreetNum, testClient1Check.getStreetNum(), "StreetNum invalid");
        Assertions.assertEquals(testClient1ZIP, testClient1Check.getZip(), "ZIP invalid");
        Assertions.assertEquals(testClient1CC, testClient1Check.getCountryCode(), "CountryCode invalid");
        Assertions.assertEquals(test1ClientCountry, testClient1Check.getCountry(), "Country invalid");
        Assertions.assertEquals(testClient1start, testClient1Check.getDateStart(), "Date start invalid");
        Assertions.assertEquals(testClient1end, testClient1Check.getDateEnd(), "Date end invalid");


        String testWorkSite1Name = "integrationTest1234";
        String testWorkSite1Description = "description of test work site integrationTest1234";

        WorkSite testWorkSite1 = new WorkSite();

        //brb





        String testClient1NewCVR;
//        cDB.updateClient()

    }

    @AfterAll
    static void cleanUp() throws DataAccessException {
//        PreparedStatement ps = null;
//        Integer affectedRows = 0;
//        Connection con = dbConnection.getConnection();
//        try {
//            ps = con.prepareStatement(deleteWorkSite);
//        } catch (SQLException e) {
//            throw new DataAccessException("Issue cleaning up after the tests (preparing statement).", e);
//        }
//
//        try {
//            affectedRows += ps.executeUpdate();
//            System.out.println("cleanUp() affected rows: " + affectedRows);
//        } catch (SQLException e) {
//            throw new DataAccessException("Issue cleaning up after the tests (executing update).", e);
//        }
//
//
//
//        Assertions.assertEquals(1, affectedRows);
    }

}
