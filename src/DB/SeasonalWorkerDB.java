package DB;

import Controller.DataAccessException;
import Model.SeasonalWorker;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Used to access data about seasonal workers from the database.
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
public class SeasonalWorkerDB implements SeasonalWorkerDBIF {
    /**
     * Pre-made queries for the program
     */
    private static final String findAll = "SELECT ps.cpr, ps.fname, ps.lname, ps.dateOfBirth, ps.sex, ps.email, " +
            "ps.phoneNum, ps.streetName, ps.streetNum, ps.zip, ps.countryCode, ps.country, sw.cpr, sw.passportNum, " +
            "sw.swift, sw.iban, sw.ssn, sw.workedBefore, sw.leadBy, sw.wSiteID " +
            "FROM SeasonalWorker sw JOIN PERSON ps ON sw.cpr = ps.cpr";

    private static final String findWorkSiteIDOfSeasonalWorker = "SELECT wSiteID FROM SeasonalWorker WHERE cpr = ?";
    private static final String findSeasonalWorkerByCPR = "SELECT * FROM SeasonalWorker " +
            "JOIN Person ON SeasonalWorker.cpr = Person.cpr " +
            "WHERE SeasonalWorker.cpr = ?";
    private static final String insertSeasonalWorker = "INSERT INTO Person VALUES(?,?,?,?,?,?,?,?,?,?,?,?); INSERT INTO SeasonalWorker VALUES(?,?,?,?,?,?,?,?)";
    private static final String updateSeasonalWorker = "UPDATE Person SET "
            + "cpr = ?,"
            + "fname = ?,"
            + "lname = ?,"
            + "dateOfBirth = ?,"
            + "sex = ?,"
            + "email = ?,"
            + "phoneNum = ?,"
            + "streetName = ?,"
            + "streetNum = ?,"
            + "zip = ?,"
            + "countryCode = ?,"
            + "country = ?"
            + " WHERE cpr = ?"
            + "; UPDATE SeasonalWorker SET "
            + "cpr = ?,"
            + "passportNum = ?,"
            + "swift = ?,"
            + "iban = ?,"
            + "ssn = ?,"
            + "workedBefore = ?,"
            + "leadBy = ?,"
            + "wSiteID = ?"
            + " WHERE cpr = ?";
    private static final String deleteSeasonalWorkerByCPR = "DELETE FROM SeasonalWorker WHERE cpr = ?; "
            + "DELETE FROM Person WHERE cpr = ?";
    private static final String findSeasonalWorkerByWorkTask = "SELECT * FROM SeasonalWorker sw " +
            "JOIN Person ps ON sw.cpr = ps.cpr " +
            "WHERE sw.cpr = (SELECT workerCpr FROM WorkTask " +
            "WHERE workTaskID = ?)";
    private static final String attachWorkSiteToSeasonalWorker = "Update SeasonalWorker SET "
            +"wSiteID = ?"
            +" WHERE cpr = ?";

    /**
     * Prepared statement declaration for the above queries
     */
    private PreparedStatement PSfindAll;
    private PreparedStatement PSfindSeasonalWorkerByCPR;
    private PreparedStatement PSinsertSeasonalWorker;
    private PreparedStatement PSupdateSeasonalWorker;
    private PreparedStatement PSdeleteSeasonalWorkerByCPR;
    private PreparedStatement PSfindSeasonalWorkerByTask;
    private PreparedStatement PSfindWorkSiteIDOfSeasonalWorker;
    private PreparedStatement PSattachWorkSiteToSeasonalWorker;

    /**
     * Constructor of SeasonalWorkerDB
     */
    public SeasonalWorkerDB() {

    }

    /**
     * Initialize DB connection and prepare SQL statements
     *
     * @throws DataAccessException Throw an exception on statements that cannot be prepared
     */
    private void connectToDB() throws DataAccessException{
        DBConnection.connect();
        if (DBConnection.instanceIsNull()) {
            throw new DataAccessException("Couldn't connect and read from database; throwing to GUI", new Exception());
        }
    }

    /**
     * Returns list of all seasonal workers stored in the database.
     *
     * @param fullAssociation if true builds also worker's WorkTask objects and leadBy SeasonalWorker object
     *                        and associates them with returned object
     * @param type requires to be of proper type
     * @return list of all seasonal workers stored in the database if found, otherwise an emptylist
     * @throws DataAccessException
     */
    @Override
    public List<SeasonalWorker> findAll(boolean fullAssociation, Type type) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSfindAll = con.prepareStatement(findAll);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue preparing statement", e);
        }

        ResultSet rs;
        try {
            rs = this.PSfindAll.executeQuery();
            return buildObjects(rs,fullAssociation,type);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Error with fetching all Seasonal Workers from DB.", e);
        }
    }

    /**
     * Returns SeasonalWorker object with a specific CPR number stored in the database.
     *
     * @param seasonalWorkerCPR CPR number of seasonal worker to be searched for
     * @param fullAssociation if true builds also worker's WorkTask objects and leadBy SeasonalWorker object
     *                        and associates them with returned object
     * @param type requires to be of proper type
     * @return built SeasonalWorker object if database query found a match, otherwise null
     * @throws DataAccessException
     */
    @Override
    public SeasonalWorker findSeasonalWorkerByCPR(String seasonalWorkerCPR, boolean fullAssociation, Type type) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSfindSeasonalWorkerByCPR = con.prepareStatement(findSeasonalWorkerByCPR);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue preparing statement", e);
        }

        try {
            PSfindSeasonalWorkerByCPR.setString(1, seasonalWorkerCPR);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with setting up query parameters when loading seasonal worker.", e);
        }

        ResultSet rs;
        try {
            rs = this.PSfindSeasonalWorkerByCPR.executeQuery();
            if (rs.next()) {
                SeasonalWorker res = buildObject(rs, fullAssociation, type);
                DBConnection.disconnect();
                return res;
            } else {
                DBConnection.disconnect();
                return null;
            }
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Error with fetching a specific SeasonalWorker from DB.", e);
        }
    }

    /**
     * Returns SeasonalWorker object who in the database is refrained to by a specific work task.
     *
     * @param workTaskID ID of workTask to search by in database
     * @param fullAssociation if true builds also worker's WorkTask objects and leadBy SeasonalWorker object
     *                        and associates them with returned object
     * @param type requires to be of proper type
     * @return built SeasonalWorker object if database query found a match, otherwise null
     * @throws DataAccessException
     */
    @Override
    public SeasonalWorker findSeasonalWorkerByWorkTask(int workTaskID, boolean fullAssociation, Type type) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSfindSeasonalWorkerByTask = con.prepareStatement(findSeasonalWorkerByWorkTask);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue preparing statement", e);
        }
        try {
            PSfindSeasonalWorkerByTask.setInt(1, workTaskID);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with setting up query parameters when loading seasonal worker.", e);
        }

        ResultSet rs;
        try {
            rs = PSfindSeasonalWorkerByTask.executeQuery();
            rs.next();
            SeasonalWorker res = buildObject(rs, fullAssociation, type);
            DBConnection.disconnect();
            return res;
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Error with fetching a specific SeasonalWorker from DB.", e);
        }
    }

    /**
     * Returns the ID of the worksite the seasonal worker is employed at.
     *
     * @param seasonalWorkerCPR CPR number of seasonal worker to search by
     * @return ID of worksite
     * @throws DataAccessException
     */
    @Override
    public int findWorkSiteIDOfSeasonalWorker(String seasonalWorkerCPR) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSfindWorkSiteIDOfSeasonalWorker = con.prepareStatement(findWorkSiteIDOfSeasonalWorker);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue preparing statement", e);
        }
        try {
            PSfindWorkSiteIDOfSeasonalWorker.setString(1, seasonalWorkerCPR);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with setting up query parameters when getting SW wSiteID.", e);
        }

        ResultSet rs;
        int wSiteIDToReturn = 1;
        try {
            rs = PSfindWorkSiteIDOfSeasonalWorker.executeQuery();
            if (rs.next()) {
                wSiteIDToReturn = rs.getInt("wSiteID");
                System.out.println("WSITE ID IS: "+rs.getInt("wSiteID"));
            }
            DBConnection.disconnect();
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Error with fetching a specific wSiteID of SW from DB.", e);
        }
        return wSiteIDToReturn;
    }

    /**
     * Inserts seasonal worker into database.
     *
     * @param newSeasonalWorker instance of new seasonal worker to be inserted into database
     * @param workSiteID ID of worksite the seasonal worker is employed at
     * @param type requires to be of proper type
     * @return count of affected rows in database after executing operation
     * @throws DataAccessException
     */
    @Override
    public int insertSeasonalWorker(SeasonalWorker newSeasonalWorker, Integer workSiteID, Type type) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSinsertSeasonalWorker = con.prepareStatement(insertSeasonalWorker);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue preparing statement", e);
        }

        try {
            //Person table insert
            PSinsertSeasonalWorker.setString(1, newSeasonalWorker.getCpr());
            PSinsertSeasonalWorker.setString(2, newSeasonalWorker.getFname());
            PSinsertSeasonalWorker.setString(3, newSeasonalWorker.getLname());
            PSinsertSeasonalWorker.setDate(4, newSeasonalWorker.getDob());
            PSinsertSeasonalWorker.setString(5, newSeasonalWorker.getSex().toString());
            PSinsertSeasonalWorker.setString(6, newSeasonalWorker.getEmail());
            PSinsertSeasonalWorker.setString(7, newSeasonalWorker.getPhoneNum());
            PSinsertSeasonalWorker.setString(8, newSeasonalWorker.getStreetName());
            PSinsertSeasonalWorker.setString(9, newSeasonalWorker.getStreetNum());
            PSinsertSeasonalWorker.setString(10, newSeasonalWorker.getZip());
            PSinsertSeasonalWorker.setString(11, newSeasonalWorker.getCountryCode());
            PSinsertSeasonalWorker.setString(12, newSeasonalWorker.getCountry());
            //SeasonalWorker table insert
            PSinsertSeasonalWorker.setString(13, newSeasonalWorker.getCpr());
            PSinsertSeasonalWorker.setString(14, newSeasonalWorker.getPassportNum());
            PSinsertSeasonalWorker.setString(15, newSeasonalWorker.getSwift());
            PSinsertSeasonalWorker.setString(16, newSeasonalWorker.getIban());
            PSinsertSeasonalWorker.setString(17, newSeasonalWorker.getSsn());
            PSinsertSeasonalWorker.setBoolean(18, newSeasonalWorker.isWorkedBefore());
            if (newSeasonalWorker.getLeadBy() == null) {
                PSinsertSeasonalWorker.setString(19, null);
            } else {
                PSinsertSeasonalWorker.setString(19, newSeasonalWorker.getLeadBy().getCpr());
            }
            PSinsertSeasonalWorker.setInt(20, workSiteID);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("There was a problem with the Seasonal Worker being inserted into DB.", e);
        }
        int affectedRows = 0;
        try {
            affectedRows = PSinsertSeasonalWorker.executeUpdate();
            DBConnection.disconnect();
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("There was a problem with inserting Seasonal Worker into DB.", e);
        }
        return affectedRows;
    }

    /**
     * Updates record of seasonal worker with a specific CPR number stored in database.
     *
     * @param seasonalWorkerCPR CPR number of the seasonal worker that is going to be updated
     * @param newSeasonalWorker a new instance of SeasonalWorker to be updated into database where the old record was
     * @param type requires to be of proper type
     * @return count of affected rows in database after executing operation
     * @throws DataAccessException
     */
    @Override
    public int updateSeasonalWorker(String seasonalWorkerCPR, SeasonalWorker newSeasonalWorker, Type type) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSupdateSeasonalWorker = con.prepareStatement(updateSeasonalWorker);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue preparing statement", e);
        }
        int affectedRows;
        try {
            //Person table update
            PSupdateSeasonalWorker.setString(1, newSeasonalWorker.getCpr());
            PSupdateSeasonalWorker.setString(2, newSeasonalWorker.getFname());
            PSupdateSeasonalWorker.setString(3, newSeasonalWorker.getLname());
            PSupdateSeasonalWorker.setDate(4, newSeasonalWorker.getDob());
            PSupdateSeasonalWorker.setString(5, newSeasonalWorker.getSex().toString());
            PSupdateSeasonalWorker.setString(6, newSeasonalWorker.getEmail());
            PSupdateSeasonalWorker.setString(7, newSeasonalWorker.getPhoneNum());
            PSupdateSeasonalWorker.setString(8, newSeasonalWorker.getStreetName());
            PSupdateSeasonalWorker.setString(9, newSeasonalWorker.getStreetNum());
            PSupdateSeasonalWorker.setString(10, newSeasonalWorker.getZip());
            PSupdateSeasonalWorker.setString(11, newSeasonalWorker.getCountryCode());
            PSupdateSeasonalWorker.setString(12, newSeasonalWorker.getCountry());
            PSupdateSeasonalWorker.setString(13, seasonalWorkerCPR);
            //SeasonalWorker table update
            PSupdateSeasonalWorker.setString(14, newSeasonalWorker.getCpr());
            PSupdateSeasonalWorker.setString(15, newSeasonalWorker.getPassportNum());
            PSupdateSeasonalWorker.setString(16, newSeasonalWorker.getSwift());
            PSupdateSeasonalWorker.setString(17, newSeasonalWorker.getIban());
            PSupdateSeasonalWorker.setString(18, newSeasonalWorker.getSsn());
            PSupdateSeasonalWorker.setBoolean(19, newSeasonalWorker.isWorkedBefore());
            if (newSeasonalWorker.getLeadBy() == null) {
                PSupdateSeasonalWorker.setString(20, null);
            } else {
                PSupdateSeasonalWorker.setString(20, newSeasonalWorker.getLeadBy().getCpr());
            }
            PSupdateSeasonalWorker.setInt(21, 1);
            PSupdateSeasonalWorker.setString(22,seasonalWorkerCPR);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("There was a problem with the Seasonal Worker being inserted into DB.", e);
        }

        try {
            affectedRows = PSupdateSeasonalWorker.executeUpdate();
            System.out.println("Affected rows:" + affectedRows);
            DBConnection.disconnect();
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("There was a problem with inserting Seasonal Worker into DB.", e);
        }
        return affectedRows;
    }

    /**
     * Removes seasonal worker of specific CPR number from database.
     *
     * @param seasonalWorkerCPR CPR number of seasonal worker that is going to be removed
     * @param type requires to be of proper type
     * @return count of affected rows in database after executing operation
     * @throws DataAccessException
     */
    @Override
    public int deleteSeasonalWorker(String seasonalWorkerCPR, Type type) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSdeleteSeasonalWorkerByCPR = con.prepareStatement(deleteSeasonalWorkerByCPR);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue preparing statement", e);
        }
        int affectedRows;
        try {
            PSdeleteSeasonalWorkerByCPR.setString(1, seasonalWorkerCPR);
            PSdeleteSeasonalWorkerByCPR.setString(2, seasonalWorkerCPR);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("", e);
        }
        try {
            affectedRows = PSdeleteSeasonalWorkerByCPR.executeUpdate();
            DBConnection.disconnect();
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("There was a problem with deleting SeasonalWorker from DB.", e);
        }
        return affectedRows;
    }

    /**
     * Modifies the wSiteID attribute of an existing SeasonalWorker in the database
     *
     * @param seasonalWorkerCPR CPR number of the SeasonalWorker to be modified
     * @param wSiteID new wSiteID to insert
     * @return count of affected rows in database after executing operation
     * @throws DataAccessException
     */
    @Override
    public int attachWorkSiteToSeasonalWorker(String seasonalWorkerCPR, int wSiteID) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSattachWorkSiteToSeasonalWorker = con.prepareStatement(attachWorkSiteToSeasonalWorker);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue preparing statement", e);
        }
        int affectedRows;
        try {
            PSattachWorkSiteToSeasonalWorker.setInt(1, wSiteID);
            PSattachWorkSiteToSeasonalWorker.setString(2, seasonalWorkerCPR);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("There was a problem with attaching wSiteID to SW.", e);
        }

        try {
            affectedRows = PSattachWorkSiteToSeasonalWorker.executeUpdate();
            DBConnection.disconnect();
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("There was a problem with attaching wSiteID to SW.", e);
        }
        return affectedRows;
    }

    /**
     * Returns list of SeasonalWorker objects after finding matching cases in database.
     *
     * @param rs ResultSet object returned after executing query
     * @param fullAssociation if true builds also worker's WorkTask objects and leadBy SeasonalWorker object
     *                        and associates them with returned object
     * @param type requires to be of proper type
     * @return list of SeasonalWorker objects
     * @throws DataAccessException
     */
    private List<SeasonalWorker> buildObjects(ResultSet rs, boolean fullAssociation, Type type) throws DataAccessException {
        List<SeasonalWorker> res = new ArrayList<>();
        try {
            while(rs.next()) {
                SeasonalWorker currentSeasonalWorker = buildObject(rs,fullAssociation,type);
                res.add(currentSeasonalWorker);
            }
            DBConnection.disconnect();
            return res;
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("buildObjects: There was an Error with building the List.", e);
        }
    }

    /**
     * Gets data from the DB and builds a SeasonalWorker object.
     *
     * @param rs The ResultSet from which a SeasonalWorker object is to be assembled
     * @param fullAssociation if true builds also worker's WorkTask objects and leadBy SeasonalWorker object
     *                        and associates them with returned object
     * @param type requires to be of proper type
     * @return an assembled SeasonalWorker object
     * @throws DataAccessException
     */
    private SeasonalWorker buildObject(ResultSet rs, boolean fullAssociation, Type type) throws DataAccessException {
        SeasonalWorker currentSeasonalWorker = null;
//        try {
//            rs.next();
//        } catch (SQLException e) {
//            throw new DataAccessException("buildObject: There was an Error with the ResultSet.", e);
//        }
        try {
            if (type.equals(SeasonalWorker.class)) {
                currentSeasonalWorker = new SeasonalWorker(rs.getString("cpr"),
                        rs.getString("fname"),
                        rs.getString("lname"), rs.getDate("dateOfBirth"),
                        rs.getString("sex").charAt(0), rs.getString("email"),
                        rs.getString("phoneNum"), rs.getString("streetName"),
                        rs.getString("streetNum"), rs.getString("zip"),
                        rs.getString("countryCode"), rs.getString("country"));

                //SeasonalWorker
//                currentSeasonalWorker.setCpr(rs.getString("cpr"));
                currentSeasonalWorker.setPassportNum(rs.getString("passportNum"));
                currentSeasonalWorker.setSwift(rs.getString("swift"));
                currentSeasonalWorker.setIban(rs.getString("iban"));
                currentSeasonalWorker.setSsn(rs.getString("ssn"));
                currentSeasonalWorker.setWorkedBefore(rs.getBoolean("workedBefore"));
                currentSeasonalWorker.setLeadBy(new SeasonalWorker(rs.getString("leadBy")));

                if (fullAssociation) {
                    currentSeasonalWorker.setLeadBy(findSeasonalWorkerByCPR(rs.getString("leadBy"),
                            false, SeasonalWorker.class));
                }
//                currentSeasonalWorker.setWorkSiteID(rs.getInt("wSiteID"));
            } else {
                throw new DataAccessException("Could not determine type.", new Exception());
            }
            return currentSeasonalWorker;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("buildObject: There was an Error with building the SeasonalWorker object.", e);
        }
    }
}
