package DB;

import DB.Exception.DataAccessException;
import Model.WorkTask;
import Model.WorkType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Used to access data from the WorkTask table in the database.
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
    private static final String approveWorkTask = "UPDATE WorkTask SET taskStatus = 'APPROVED' WHERE workTaskID = ?";
    private static final String findAllPending = "SELECT * FROM WorkTask WHERE taskStatus = 'PENDING APPROVAL' OR  taskStatus = 'PENDING'";
    /**
     * Prepared statement declaration for the above queries
     */
    private PreparedStatement PSfindAll;
    private PreparedStatement PSfindAllWorkTasksOfWorker;
    private PreparedStatement PSfindByID;
    private PreparedStatement PSinsertWorkTask;
    private PreparedStatement PSupdateWorkTask;
    private PreparedStatement PSdeleteWorkTask;
    private PreparedStatement PSapproveWorkTask;
    private PreparedStatement PSfindAllPending;

    /**
     * Constructor of WorkTaskDB
     */
    public WorkTaskDB() {

    }

    /**
     * Initializes DB connection and prepares SQL statements.
     *
     * @throws DataAccessException On statements that cannot be prepared
     */
    private void connectToDB() throws DataAccessException{
        DBConnection.connect();
        if (DBConnection.instanceIsNull()) {
            throw new DataAccessException("Couldn't connect and read from database; throwing to GUI", new Exception());
        }
    }

    /**
     * Returns list of all work tasks of a seasonal worker.
     *
     * @param fullAssociation if True, build also work task's WorkType object,
     *                        and associates it with the returned object
     * @param workerCpr CPR number of seasonal worker
     * @return list of work tasks a seasonal worker has if found, otherwise an empty list
     * @throws DataAccessException
     */
    @Override
    public List<WorkTask> findAllWorkTasksOfWorker(boolean fullAssociation, String workerCpr) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSfindAllWorkTasksOfWorker = con.prepareStatement(findAllWorkTasksOfWorker);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue preparing statement", e);
        }

        ResultSet rs;
        try {
            PSfindAllWorkTasksOfWorker.setString(1, workerCpr);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with setting up query parameters when loading work types.", e);
        }

        try {
            rs = PSfindAllWorkTasksOfWorker.executeQuery();
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with retrieving work types from the database (executeQuery)", e);
        }
        return buildObjects(rs, fullAssociation);
    }

    /**
     * Returns list of all work tasks with a pending status.
     *
     * @param fullAssociation if True, build also work task's WorkType object,
     *                        and associates it with the returned object
     * @return list of work tasks with a pending status if found, otherwise an empty list
     * @throws DataAccessException
     */
    @Override
    public List<WorkTask> findAllPendingTasks(boolean fullAssociation) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSfindAllPending = con.prepareStatement(findAllPending);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue preparing statement", e);
        }
        ResultSet rs;
        try {
            rs = PSfindAllPending.executeQuery();
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with retrieving work types from the database (executeQuery)", e);
        }
        return buildObjects(rs, fullAssociation);
    }

    /**
     * Returns list of all work tasks stored in the database.
     *
     * @param fullAssociation if True, build also work task's WorkType object,
     *                        and associates it with the returned object
     * @return list of all work tasks stored in the database if found, otherwise empty list
     * @throws DataAccessException
     */
    @Override
    public List<WorkTask> findAll(boolean fullAssociation) throws DataAccessException {
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
            rs = PSfindAll.executeQuery();
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("resultset error", e);
        }
        return buildObjects(rs,fullAssociation);
    }

    /**
     * Returns WorkTask object with a specific ID stored in the database.
     *
     * @param id ID of work task to be searched for
     * @param fullAssociation if True, build also work task's WorkType object,
     *                        and associates it with the returned object
     * @return built WorkTask object if database query found a match, otherwise null
     * @throws DataAccessException
     */
    @Override
    public WorkTask findByID(int id, boolean fullAssociation) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSfindByID = con.prepareStatement(findByID);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue preparing statement", e);
        }

        try {
            PSfindByID.setInt(1, id);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with setting up query parameters when loading work types.", e);
        }

        ResultSet rs;
        try {
            rs = PSfindByID.executeQuery();
            if (rs.next()) {
                WorkTask res = buildObject(rs, fullAssociation);
                DBConnection.disconnect();
                return res;
            } else {
                DBConnection.disconnect();
                return null;
            }
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with retrieving work types from the database (executeQuery)", e);
        }
    }

    /**
     * Inserts work task into database and associates it with seasonal worker.
     *
     * @param workTask WorkSite object to be added to database
     * @param workerCpr CPR of seasonal worker
     * @return count of affected rows in database after executing operation
     * @throws DataAccessException
     */
    @Override
    public int insertWorkTask(WorkTask workTask, String workerCpr) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSinsertWorkTask = con.prepareStatement(insertWorkTask);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue preparing statement", e);
        }

        try {
            PSinsertWorkTask.setDouble(1, workTask.getHoursWorked());
            PSinsertWorkTask.setDouble(2, workTask.getQuantity());
            PSinsertWorkTask.setDate(3, workTask.getDateStart());
            PSinsertWorkTask.setDate(4, workTask.getDateEnd());
            PSinsertWorkTask.setString(5, workTask.getStatus());
            PSinsertWorkTask.setInt(6, workTask.getWorkType().getWorkTypeID());
            PSinsertWorkTask.setString(7, workerCpr);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("There was a problem with the workTask being inserted into DB.", e);
        }

        int affectedRows;
        try {
            affectedRows = PSinsertWorkTask.executeUpdate();
            DBConnection.disconnect();
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("There was a problem with inserting workTask into DB.", e);
        }
        return affectedRows;
    }

    /**
     * Updates record of work task with a specific ID number stored in database.
     *
     * @param workTask a new instance of WorkTask to be updated into database where the old record was
     * @param workTaskID ID number of the work task that is going to be updated
     * @return count of affected rows in database after executing operation
     * @throws DataAccessException
     */
    @Override
    public int updateWorkTask(WorkTask workTask, int workTaskID) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSupdateWorkTask = con.prepareStatement(updateWorkTask);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue preparing statement", e);
        }

        try {
            PSupdateWorkTask.setDouble(1,workTask.getHoursWorked());
            PSupdateWorkTask.setDouble(2,workTask.getQuantity());
            PSupdateWorkTask.setDate(3,workTask.getDateStart());
            PSupdateWorkTask.setDate(4,workTask.getDateEnd());
            PSupdateWorkTask.setString(5,workTask.getStatus());
            PSupdateWorkTask.setInt(6, workTask.getWorkType().getWorkTypeID());
//            PSupdateWorkTask.setString(7,workerCpr);
            PSupdateWorkTask.setInt(7, workTaskID);
        }
        catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("There was a problem with the workTask being updated in DB.",e);
        }

        int affectedRows;
        try {
            affectedRows = PSupdateWorkTask.executeUpdate();
            DBConnection.disconnect();
        }
        catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("There was a problem with updating workTask in DB.",e);
        }
        return affectedRows;
    }

    /**
     * Approves multiple tasks at once. Changes tasks' state to Approved in database.
     *
     * @param idList list work ID numbers of work tasks that are to be updated
     * @return true if operation succeeded, false otherwise
     * @throws DataAccessException
     */
    @Override
    public boolean approveWorkTasks(ArrayList<Integer> idList) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            con.setAutoCommit(false);
            PSapproveWorkTask = con.prepareStatement(approveWorkTask);
            for (Integer id : idList) {
                PSapproveWorkTask.setInt(1, id);
                PSapproveWorkTask.executeUpdate();
            }
            con.commit();
            con.setAutoCommit(true);
            DBConnection.disconnect();
            return true;

        } catch (Exception ex) {
            try {
                con.rollback();
            } catch (SQLException e) {
                throw new DataAccessException("There was an error making a rollback", e);
            }
            DBConnection.disconnect();
            return false;
        }
    }

    /**
     * Removes work task of specific ID number from database.
     *
     * @param id ID number of work task that is going to be removed
     * @return count of affected rows in database after executing operation
     * @throws DataAccessException
     */
    @Override
    public int deleteWorkTask(int id) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSdeleteWorkTask = con.prepareStatement(deleteWorkTask);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue preparing statement", e);
        }

        try {
            PSdeleteWorkTask.setInt(1, id);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("There was a problem with the id of the workTask being deleted from DB.",e);
        }

        int affectedRows;
        try {
            affectedRows = PSdeleteWorkTask.executeUpdate();
            DBConnection.disconnect();
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("There was a problem with deleting workTask from DB.",e);
        }
        return affectedRows;
    }

    /**
     * Generates and returns WorkTask objects based on the ResultSet returned by the query
     * @param rs ResultSet object filled with results of a query
     * @param fullAssociation if True, build also work task's WorkType object,
     *                        and associates it with the returned object
     * @return list of built WorkTask objects if found, otherwise empty list
     * @throws DataAccessException
     */
    private List<WorkTask> buildObjects(ResultSet rs, boolean fullAssociation) throws DataAccessException {
        List<WorkTask> res = new ArrayList<>();
        try {
            while(rs.next()) {
                WorkTask currentWorkTask = buildObject(rs, fullAssociation);
                res.add(currentWorkTask);
            }
            if(!DBConnection.instanceIsNull()) {
                DBConnection.disconnect();
            }
            return res;
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("buildObjects: problem with result set", e);
        }
    }

    /**
     * Gets data from the DB and builds a Produce object.
     *
     * @param rs The ResultSet from which a WorkTask object is to be assembled
     * @param fullAssociation if True, build also work task's WorkType object,
     *                        and associates it with the returned object
     * @return an assembled WorkTask object
     * @throws DataAccessException
     */
    private WorkTask buildObject(ResultSet rs, boolean fullAssociation) throws DataAccessException {
        WorkTask currentWorkTask = null;

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
                WorkTypeDB wtDB = new WorkTypeDB();
                WorkType workType = wtDB.findWorkTypeByID(rs.getInt("workTypeID"));
                currentWorkTask.setWorkType(workType);
            }
        }
        catch (SQLException e) {
            throw new DataAccessException("buildObject: Error.", e);
        }
        return currentWorkTask;
    }
}