package DB;

import Controller.*;
import Model.*;

import javax.xml.crypto.Data;
import java.lang.reflect.Type;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SeasonalWorkerDB implements SeasonalWorkerIF {
    /**
     * Pre-made queries for the program
     */
    private static final String findAll = "SELECT ps.cpr, ps.fname, ps.lname, ps.dateOfBirth, ps.sex, ps.email, " +
            "ps.phoneNum, ps.streetName, ps.streetNum, ps.zip, ps.countryCode, ps.country, sw.cpr, sw.passportNum, " +
            "sw.swift, sw.iban, sw.ssn, sw.workedBefore, sw.leadBy, sw.wSiteID " +
            "FROM SeasonalWorker sw JOIN PERSON ps ON sw.cpr = ps.cpr";

    private static final String findSeasonalWorkerByCPR = "SELECT * FROM SeasonalWorker " +
            "JOIN Person ON SeasonalWorker.cpr = Person.cpr " +
            "WHERE SeasonalWorker.cpr = ?";
    private static final String insertSeasonalWorker = "INSERT INTO SeasonalWorker VALUES(?,?,?,?,?,?,?,?,?,?,?)";
    private static final String updateSeasonalWorker = "UPDATE SeasonalWorker SET "
            + "cpr = ?,"
            + "passportNum = ?,"
            + "swift = ?,"
            + "iban = ?,"
            + "ssn = ?,"
            + "workedBefore = ?,"
            + "leadBy = ?,"
            + "wSiteID = ?"
            + " WHERE cpr = ?";
    private static final String deleteSeasonalWorkerByCPR = "DELETE FROM SeasonalWorker WHERE cpr = ?";

    /**
     * Prepared statement declaration for the above queries
     */
    private PreparedStatement PSfindAll;
    private PreparedStatement PSfindSeasonalWorkerByCPR;
    private PreparedStatement PSinsertSeasonalWorker;
    private PreparedStatement PSupdateSeasonalWorker;
    private PreparedStatement PSdeleteSeasonalWorkerByCPR;


    public SeasonalWorkerDB() {
//        init();
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

//    // This should be made obsolete ASAP
//    // In order to do that, find methods where preparing statements wasn't yet moved into corresponding bodies,
//    // and move it there
//    private void init() throws DataAccessException {
//        connectToDB();
//        Connection con = DBConnection.getInstance().getConnection();
//        try {
//            PSfindAll = con.prepareStatement(findAll);
//            PSfindSeasonalWorkerByCPR = con.prepareStatement(findSeasonalWorkerByCPR);
//            PSinsertSeasonalWorker = con.prepareStatement(insertSeasonalWorker);
//            PSupdateSeasonalWorker = con.prepareStatement(updateSeasonalWorker);
//            PSdeleteSeasonalWorkerByCPR = con.prepareStatement(deleteSeasonalWorkerByCPR);
//            DBConnection.disconnect();
//        } catch (SQLException e) {
//            DBConnection.disconnect();
//            throw new DataAccessException("SeasonalWorkerDB could not initialize.", e);
//        }
//    }

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
            rs.next();
            SeasonalWorker res = buildObject(rs, fullAssociation, type);
            DBConnection.disconnect();
            return res;
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Error with fetching a specific SeasonalWorker from DB.", e);
        }
    }


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

        int affectedRows;
        try {
            PSinsertSeasonalWorker.setString(1, newSeasonalWorker.getCpr());
            PSinsertSeasonalWorker.setString(2, newSeasonalWorker.getPassportNum());
            PSinsertSeasonalWorker.setString(3, newSeasonalWorker.getSwift());
            PSinsertSeasonalWorker.setString(4, newSeasonalWorker.getIban());
            PSinsertSeasonalWorker.setString(5, newSeasonalWorker.getSsn());
            PSinsertSeasonalWorker.setBoolean(6, newSeasonalWorker.isWorkedBefore());
            PSinsertSeasonalWorker.setString(7, newSeasonalWorker.getLeadBy().getCpr());
            PSinsertSeasonalWorker.setInt(8, workSiteID);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("There was a problem with the client being inserted into DB.", e);
        }

        try {
            affectedRows = PSinsertSeasonalWorker.executeUpdate();
            DBConnection.disconnect();
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("There was a problem with inserting client into DB.", e);
        }
        return affectedRows;
    }

    @Override
    public int updateSeasonalWorker(String seasonalWorkerCPR, SeasonalWorker newSeasonalWorker, Type type) throws DataAccessException {
        return 0;
    }

    @Override
    public int deleteSeasonalWorker(String seasonalWorkerCPR, Type type) throws DataAccessException {
        return 0;
    }


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


    private SeasonalWorker buildObject(ResultSet rs, boolean fullAssociation, Type type) throws DataAccessException, SQLException {
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
