package DB;

import Controller.DataAccessException;
import Model.*;
import DB.WorkTypeDB;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkTaskDB implements WorkTaskDBIF {
    /**
     * Pre-made queries for the program
     */
    private static final String findAll = "SELECT * FROM WorkTask";
    private static final String findAllWorkTasksOfWorker = "SELECT * FROM WorkTask WHERE workerCpr = ?";
    private static final String findByID = "SELECT * FROM WorkTask WHERE workTaskID = ?";
    private static final String insertWorkTask = "INSERT INTO WorkTask VALUES(?,?,?,?,?,?,?)";
    private static final String deleteWorkTask = "DELETE FROM WorkTask WHERE workTaskID = ?";
    private static final String updateWorkTask = "UPDATE WorkTask SET "
            + "hoursWorked = ?,"
            + "quantity = ?,"
            + "dateStart = ?,"
            + "dateEnd = ?,"
            + "taskStatus = ?,"
            + "workTypeID = ? "
//            + "workerCPR = ? "
            + "WHERE workTaskID = ? ";

    /**
     * Prepared statement declaration for the above queries
     */
    private final PreparedStatement PSfindAll;
    private final PreparedStatement PSfindAllWorkTasksOfWorker;
    private final PreparedStatement PSfindByID;
    private final PreparedStatement PSinsertWorkTask;
    private final PreparedStatement PSupdateWorkTask;
    private final PreparedStatement PSdeleteWorkTask;

    private WorkTypeDB wtDB;

    // TODO
    // This should be made obsolete ASAP
    // In order to do that, find methods where preparing statements wasn't yet moved into corresponding bodies,
    // and move it there
    public WorkTaskDB() throws DataAccessException{
        Connection con = DBConnection.getInstance().getConnection();
        wtDB = new WorkTypeDB();
        try {
            PSfindAll = con.prepareStatement(findAll);
            PSfindAllWorkTasksOfWorker = con.prepareStatement(findAllWorkTasksOfWorker);
            PSfindByID = con.prepareStatement(findByID);
            PSinsertWorkTask = con.prepareStatement(insertWorkTask);
            PSupdateWorkTask = con.prepareStatement(updateWorkTask);
            PSdeleteWorkTask = con.prepareStatement(deleteWorkTask);
        } catch (SQLException e) {
            throw new DataAccessException("WorkTaskDB error.", e);
        }
    }

    @Override
    public List<WorkTask> findAllWorkTasksOfWorker(boolean fullAssociation, String workerCpr) throws DataAccessException {
        ResultSet rs;
        try {
            PSfindAllWorkTasksOfWorker.setString(1, workerCpr);
        } catch (SQLException e) {
            throw new DataAccessException("Issue with setting up query parameters when loading work types.", e);
        }

        try {
            rs = PSfindAllWorkTasksOfWorker.executeQuery();
        } catch (SQLException e) {
            throw new DataAccessException("Issue with retrieving work types from the database (executeQuery)", e);
        }
        return buildObjects(rs,fullAssociation);
    }

    @Override
    public List<WorkTask> findAll(boolean fullAssociation) throws DataAccessException {
        ResultSet rs;
        try {
            rs = PSfindAll.executeQuery();
        } catch (SQLException e) {
            throw new DataAccessException("resultset error", e);
        }
        return buildObjects(rs,fullAssociation);
    }

    @Override
    public WorkTask findByID(Integer id, boolean fullAssociation) throws DataAccessException {
        ResultSet rs;
        WorkTask workTask = null;
        try {
            PSfindByID.setInt(1, id);
        } catch (SQLException e) {
            throw new DataAccessException("Issue with setting up query parameters when loading work types.", e);
        }

        try {
            rs = PSfindByID.executeQuery();
            if (rs.next()) {
                workTask = buildObject(rs, fullAssociation);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Issue with retrieving work types from the database (executeQuery)", e);
        }
        return workTask;
    }

    @Override
    public Integer insertWorkTask(WorkTask workTask, String workerCpr) throws DataAccessException {
        Integer affectedRows;
//        Integer genKey = null;
        try {
            PSinsertWorkTask.setDouble(1, workTask.getHoursWorked());
            PSinsertWorkTask.setDouble(2, workTask.getQuantity());
            PSinsertWorkTask.setDate(3, workTask.getDateStart());
            PSinsertWorkTask.setDate(4, workTask.getDateEnd());
            PSinsertWorkTask.setString(5, workTask.getStatus());
            PSinsertWorkTask.setInt(6, workTask.getWorkType().getWorkTypeID());
            PSinsertWorkTask.setString(7, workerCpr);
        } catch (SQLException e) {
            throw new DataAccessException("There was a problem with the workTask being inserted into DB.", e);
        }
        try {
            affectedRows = PSinsertWorkTask.executeUpdate();
//            genKey = PSinsertWorkTask.getGeneratedKeys().getInt(1);
        } catch (SQLException e) {
            throw new DataAccessException("There was a problem with inserting workTask into DB.", e);
        }
        return affectedRows;
    }

    @Override
    public Integer updateWorkTask(WorkTask workTask, Integer workTaskID) throws DataAccessException {
        Integer genKey = null;
        try {
            PSupdateWorkTask.setDouble(1,workTask.getHoursWorked());
            PSupdateWorkTask.setDouble(2,workTask.getQuantity());
            PSupdateWorkTask.setDate(3,workTask.getDateStart());
            PSupdateWorkTask.setDate(4,workTask.getDateEnd());
            PSupdateWorkTask.setString(5,workTask.getStatus());
            PSinsertWorkTask.setInt(6, workTask.getWorkType().getWorkTypeID());
//            PSupdateWorkTask.setString(7,workerCpr);
            PSupdateWorkTask.setInt(7, workTaskID);
        }
        catch (SQLException e) {
            throw new DataAccessException("There was a problem with the workTask being updated in DB.",e);
        }
        try
        {
            PSupdateWorkTask.executeQuery();
            genKey = PSupdateWorkTask.getGeneratedKeys().getInt(1);
        }
        catch (SQLException e){
            throw new DataAccessException("There was a problem with updating workTask in DB.",e);
        }

        return genKey;
    }

    @Override
    public void deleteWorkTask(Integer id) throws DataAccessException {
        try {
            PSdeleteWorkTask.setInt(1, id);
        }
        catch (SQLException e) {
            throw new DataAccessException("There was a problem with the id of the workTask being deleted from DB.",e);
        }
        try
        {
            PSdeleteWorkTask.executeQuery();
        }
        catch (SQLException e){
            throw new DataAccessException("There was a problem with deleting workTask from DB.",e);
        }
    }

    private List<WorkTask> buildObjects(ResultSet rs, boolean fullAssociation) throws DataAccessException {
        List<WorkTask> res = new ArrayList<>();
        try {
            while(rs.next()) {
                WorkTask currentWorkTask = buildObject(rs, fullAssociation);
                res.add(currentWorkTask);
            }
        } catch (SQLException e) {
            throw new DataAccessException("buildObjects: problem with result set", e);

        }
        return res;
    }

    private WorkTask buildObject(ResultSet rs, boolean fullAssociation) throws DataAccessException {
        WorkTask currentWorkTask = null;
        WorkTypeDB wTypeDB = new WorkTypeDB();

        try {
                currentWorkTask = new WorkTask();
                currentWorkTask.setWorkTaskID(rs.getInt("workTaskID"));
                currentWorkTask.setHoursWorked(rs.getDouble("hoursWorked"));
                currentWorkTask.setQuantity(rs.getDouble("quantity"));
                currentWorkTask.setDateStart(rs.getDate("dateStart"));
                currentWorkTask.setDateEnd(rs.getDate("dateEnd"));
                currentWorkTask.setStatus(rs.getString("taskStatus"));
                currentWorkTask.setWorkType(new WorkType(rs.getInt("workTypeID")));

            if (fullAssociation) {
                WorkType workType = wtDB.findWorkTypeByID(rs.getInt("workTypeID"),false);
                currentWorkTask.setWorkType(workType);
            }
        }
        catch (SQLException e) {
            throw new DataAccessException("buildObject: Error.", e);
        }

        return currentWorkTask;
    }
}