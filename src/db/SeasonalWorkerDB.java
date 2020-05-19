package DB;

import Controller.*;
import Model.*;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    private static final String findSeasonalWorkerByCVR = "SELECT * FROM SeasonalWorker WHERE cpr = ?";
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
    private static final String deleteSeasonalWorkerByCVR = "DELETE FROM SeasonalWorker WHERE cpr = ?";

    /**
     * Prepared statement declaration for the above queries
     */
    private PreparedStatement PSfindAll;
    private PreparedStatement PSfindSeasonalWorkerByCVR;
    private PreparedStatement PSinsertSeasonalWorker;
    private PreparedStatement PSupdateSeasonalWorker;
    private PreparedStatement PSdeleteSeasonalWorkerByCVR;


    public SeasonalWorkerDB() throws DataAccessException{
        init();
    }

    /**
     * Initialize DB connection and prepare SQL statements
     *
     * @throws DataAccessException Throw an exception on statements that cannot be prepared
     */
    private void init() throws DataAccessException {
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSfindAll = con.prepareStatement(findAll);
            PSfindSeasonalWorkerByCVR = con.prepareStatement(findSeasonalWorkerByCVR);
            PSinsertSeasonalWorker = con.prepareStatement(insertSeasonalWorker);
            PSupdateSeasonalWorker = con.prepareStatement(updateSeasonalWorker);
            PSdeleteSeasonalWorkerByCVR = con.prepareStatement(deleteSeasonalWorkerByCVR);
        } catch (SQLException e) {
            throw new DataAccessException("SeasonalWorkerDB could not initialize.", e);
        }
    }

    @Override
    public List<SeasonalWorker> findAll(boolean fullAssociation, Type type) throws DataAccessException {
        ResultSet rs;
        try {
            rs = this.PSfindAll.executeQuery();
            return buildObjects(rs,fullAssociation,type);
        } catch (SQLException e) {
            throw new DataAccessException("Error with fetching all Seasonal Workers from DB.", e);
        }
    }

    @Override
    public SeasonalWorker findSeasonalWorkerByCPR(String seasonalWorkerCPR, boolean fullAssociation, Type type) throws DataAccessException {
        ResultSet rs;
        try {
            rs = this.PSfindSeasonalWorkerByCVR.executeQuery();
            return buildObject(rs, fullAssociation, type);
        } catch (SQLException e) {
            throw new DataAccessException("Error with fetching a specific SeasonalWorker from DB.", e);
        }
    }


    @Override
    public int insertSeasonalWorker(SeasonalWorker newSeasonalWorker, Integer workSiteID, Type type) throws DataAccessException {
        Integer affectedRows;
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
            throw new DataAccessException("There was a problem with the client being inserted into DB.", e);
        }

        try {
            affectedRows = PSinsertSeasonalWorker.executeUpdate();
        } catch (SQLException e) {
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
            return res;
        } catch (SQLException e) {
            throw new DataAccessException("buildObjects: There was an Error with building the List.", e);
        }
    }


    private SeasonalWorker buildObject(ResultSet rs, boolean fullAssociation, Type type) throws DataAccessException {
        SeasonalWorker currentSeasonalWorker = null;
//        try {
//            rs.next();
//        } catch (SQLException e) {
//            throw new DataAccessException("buildObject: There was an Error with the ResultSet.", e);
//        }
        try {
            if (type.equals(SeasonalWorker.class)) {
                currentSeasonalWorker = new SeasonalWorker(rs.getString("cpr"), rs.getString("fname"),
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
