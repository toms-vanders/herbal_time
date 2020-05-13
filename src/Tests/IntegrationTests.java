package Tests;

import DB.DBConnection;
import org.junit.jupiter.api.BeforeEach;

public class IntegrationTests {
    private DBConnection dbConnection;

    @BeforeEach
    public void testDBConnection() {
        System.out.println("Initiaing DB Connection");
        dbConnection = DBConnection.getInstance();
        System.out.println(dbConnection.toString());

    }

    // workSite + workType integration test
    //    @Test
//    public void testGetAllWorkTypeFromWorkSite() throws DataAccessException {
//        WorkTypeDB wtDB = new WorkTypeDB();
//        List<WorkType> res1 = wtDB.findAll(3, false, WorkType.class);
//        Assertions.assertFalse(res1.isEmpty(), "There no work types associated with the work site 3");
//        for (WorkType wt : res1) {
//            System.out.println(wt.toString());
//        }
//        List<WorkType> res2 = wtDB.findAll(9999, false, WorkType.class);
//        Assertions.assertTrue(res2.isEmpty(), "There are some work types associated with the work site 9999");
//    }

}
