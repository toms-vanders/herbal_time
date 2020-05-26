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
    private static final String deleteWorkType = "DELETE FROM WorkType WHERE typeOfProduce = 'IntTest1'";
    private static final String deleteWorkSite = "DELETE FROM WorkSite WHERE siteName = 'IntTest1'";
    private static final String deleteClient = "DELETE FROM Client WHERE cvr = 'IntTest1'";


    @BeforeEach
    public void testDBConnection() {
        System.out.println("Initiating DB Connection");
        dbConnection = DBConnection.getInstance();
        System.out.println(dbConnection.toString());
    }


//    // workSite + workType integration test
//    // TODO
//    @Test
//    public void testGetAllWorkTypeFromWorkSite() throws DataAccessException {
//        WorkTypeDB wtDB = new WorkTypeDB();
//
//        List<WorkType> res1 = wtDB.findAllWorkTypesOfWorkSite(3, false, WorkType.class);
//        Assertions.assertFalse(res1.isEmpty(), "There no work types associated with the work site 3");
//        for (WorkType wt : res1) {
//            System.out.println(wt.toString());
//        }
//        List<WorkType> res2 = wtDB.findAllWorkTypesOfWorkSite(9999, false, WorkType.class);
//        Assertions.assertTrue(res2.isEmpty(), "There are some work types associated with the work site 9999");
//    }

    // workSite + client + workType integration test
    // insert, update, select, delete
    // Pre-requisite for successful results: ZIP and CC already exist in the database;
    @Test
    public void testClientWorkSiteWorkTypeIntegrationTest() throws DataAccessException {
        ClientDB cDB = new ClientDB();
        WorkSiteDB wsDB = new WorkSiteDB();
        WorkTypeDB wtDB = new WorkTypeDB();

        System.out.println("Successfully created Database access objects");


        // Adding Client test article

        String testClient1CVR = "IntTest1";
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


        // This could be moved to the ClientDB. And other xyzDB classes should follow this way also.
//        if (affectedRows <= 0) {
//            System.err.println("! ERROR: Adding test article Client(CVR: + " + testClient1CVR + ") failed.");
//        } else if (affectedRows == 1) {
//            System.out.println("Successfully added test article Client(CVR: " + testClient1CVR + ")");
//        } else if (affectedRows == 2) {
//            System.out.println("Successfully added test articles: 1. Client(CVR: " + testClient1CVR +
//                    "), 2. ZipCity(ZIP: " + testClient1ZIP + ", Country Code: " + testClient1CC + ").");
//        }

        // Validating added Client object

        Client testClient1Check = cDB.findClientByCVR(testClient1CVR, false, Client.class);
        Assertions.assertEquals(testClient1CVR, testClient1Check.getCvr(), "CVR invalid");
        Assertions.assertEquals(testClient1Name, testClient1Check.getName(), "Name invalid");
        Assertions.assertEquals(testClient1Email, testClient1Check.getEmail(), "Email invalid");
        Assertions.assertEquals(testClient1PhoneNum, testClient1Check.getPhoneNum(), "PhoneNum invalid");
        Assertions.assertEquals(testClient1StreetName, testClient1Check.getStreetName(), "StreetName invalid");
        Assertions.assertEquals(testClient1StreetNum, testClient1Check.getStreetNum(), "StreetNum invalid");
        Assertions.assertEquals(testClient1ZIP, testClient1Check.getZip().trim(), "ZIP invalid");
        Assertions.assertEquals(testClient1CC, testClient1Check.getCountryCode().trim(), "CountryCode invalid");
        Assertions.assertEquals(test1ClientCountry, testClient1Check.getCountry(), "Country invalid");
        Assertions.assertEquals(testClient1start, testClient1Check.getDateStart(), "Date start invalid");
        Assertions.assertEquals(testClient1end, testClient1Check.getDateEnd(), "Date end invalid");

        // Adding WorkSite test article

        String testWorkSite1Name = "IntTest1";
        String testWorkSite1Description = "description of test work site integrationTest1234";
        String testWorkSite1StreetName = "streetNameTest";
        String testWorkSite1StreetNum = "streetNumT";
        String testWorkSite1Zip = "9230";
        String testWorkSite1Country = "Denmark";
        String testWorkSite1CC = "DK";
        String testWorkSite1TypeOfJob = "typeOfJob of test work site integrationTest1234";
        Double testWorkSite1PricePerWorker = 2459.0;

        WorkSite testWorkSite1 = new WorkSite(testWorkSite1Name, testWorkSite1Description, testWorkSite1StreetName,
                testWorkSite1StreetNum, testWorkSite1Zip, testWorkSite1Country, testWorkSite1CC, testWorkSite1TypeOfJob,
                testWorkSite1PricePerWorker);

        affectedRows += wsDB.insertWorkSite(testClient1Check.getCvr(), testWorkSite1);

        // Validating added WorkSite object
        List<WorkSite> workSitesOfTestClient= wsDB.findWorkSitesOfClient(testClient1Check.getCvr(),
                false);
        WorkSite testWorkSite1Check = workSitesOfTestClient.get(0);
        Assertions.assertEquals(testWorkSite1Name, testWorkSite1Check.getName(), "Name invalid");
        Assertions.assertEquals(testWorkSite1Description, testWorkSite1Check.getDescription(),
                "Description invalid");
        Assertions.assertEquals(testWorkSite1StreetName, testWorkSite1Check.getStreetName(),
                "Street name invalid");
        Assertions.assertEquals(testWorkSite1StreetNum, testWorkSite1Check.getStreetNum(),
                "Street num invalid");
        Assertions.assertEquals(testWorkSite1Zip, testWorkSite1Check.getZip().trim(), "Zip invalid");
        Assertions.assertEquals(testWorkSite1CC, testWorkSite1Check.getCountryCode().trim(), "Country code invalid");
        Assertions.assertEquals(testWorkSite1Country, testWorkSite1Check.getCountry(), "Country invalid");
        Assertions.assertEquals(testWorkSite1TypeOfJob, testWorkSite1Check.getTypeOfJob(), "TypeOfJob invalid");
        Assertions.assertEquals(testWorkSite1PricePerWorker, testWorkSite1Check.getPricePerWorker(),
                "Price per worker invalid");

        // Adding WorkType test article
        String testWorkType1DescOfJob = "Collecting grapes";
        String testWorkType1TypeOfProduce = "IntTest1";
        String testWorkType1SalaryType = "Hourly";
        Double testWorkType1Pay = 150.0;

        WorkType testWorkType1 = new WorkType(testWorkType1DescOfJob, testWorkType1TypeOfProduce, testWorkType1SalaryType,
                testWorkType1Pay);
        affectedRows += wtDB.insertWorkType(testWorkSite1Check.getWorkSiteID(), testWorkType1);

        List<WorkType> workTypesOfTestWorkSite = wtDB.findAllWorkTypesOfWorkSite(testWorkSite1Check.getWorkSiteID());
        WorkType testWorkType1Check = workTypesOfTestWorkSite.get(0);
        Assertions.assertEquals(testWorkType1DescOfJob, testWorkType1Check.getDescOfJob(), "Desc of job invalid");
        Assertions.assertEquals(testWorkType1TypeOfProduce, testWorkType1Check.getTypeOfProduce(), "Type of produce invalid");
        Assertions.assertEquals(testWorkType1SalaryType, testWorkType1Check.getSalaryType(), "Salary type invalid");
        Assertions.assertEquals(testWorkType1Pay, testWorkType1Check.getPay(), "Pay invalid invalid");


        System.out.println("Affected rows after integration test finished: " + affectedRows);


//        String testClient1NewCVR;
//        cDB.updateClient()

    }

    // TODO
    // Test database on UPDATE, on DELETE
    // what happens with depending entities

    @AfterAll
    static void cleanUp() throws DataAccessException {
        System.out.println("Cleaning up: ");
        PreparedStatement psDeleteWorkType = null;
        PreparedStatement psDeleteWorkSite = null;
        PreparedStatement psDeleteClient = null;
        Integer affectedRows = 0;
        Connection con = dbConnection.getConnection();

        try {
            psDeleteWorkType = con.prepareStatement(deleteWorkType);
            psDeleteWorkSite = con.prepareStatement(deleteWorkSite);
            psDeleteClient = con.prepareStatement(deleteClient);
        } catch (SQLException e) {
            throw new DataAccessException("Issue cleaning up after the tests (preparing statement).", e);
        }

        try {
            affectedRows += psDeleteWorkType.executeUpdate();
            Assertions.assertEquals(1, affectedRows);
            affectedRows += psDeleteWorkSite.executeUpdate();
            Assertions.assertEquals(2, affectedRows);
            affectedRows += psDeleteClient.executeUpdate();
            Assertions.assertEquals(3, affectedRows);
        } catch (SQLException e) {
            throw new DataAccessException("Issue cleaning up after the tests (executing update).", e);
        }

        System.out.println("cleanUp() affected rows: " + affectedRows);
        System.out.println("Finished cleaning up");
    }
}
