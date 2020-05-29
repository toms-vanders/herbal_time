package Controller;

import DB.DataAccessException;
import DB.WorkTaskDB;import DB.WorkTaskDBIF;import Model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Used to interface between the GUI layer and WorkTaskDB
 *
 * @author Daniel Zoltan Ban
 * @author Mikuláš Dobrodej
 * @author Adrian Mihai Dohot
 * @author Damian Hrabąszcz
 * @author Toms Vanders
 * @version 1.0 (29.05.2020)
 *
 * Date: 29.05.2020
 */
public class WorkTaskCtr implements WorkTaskCtrIF {

    final WorkTaskDBIF workTaskDB;

    public WorkTaskCtr() {
        workTaskDB = new WorkTaskDB();
    }

    /**
     * Returns a list of all WorkTasks of a Worker
     * @param fullAssociation If true, returns also the WorkTask's associated WorkType
     * @param workerCpr CPR of Worker whose tasks to return
     * @return List of WorkTasks of a specific Worker
     * @throws DataAccessException
     */
    @Override
    public List<WorkTask> findAllWorkTasksOfWorker(boolean fullAssociation, String workerCpr) throws DataAccessException {
        try {
            return workTaskDB.findAllWorkTasksOfWorker(fullAssociation, workerCpr);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new DataAccessException("WorkTaskCtr error.", e);
        }
    }

    /**
     * Returns a list of all WorkTasks that have the Pending status
     * @param fullAssociation If true, return also the WorkTasks' WorkTypes
     * @return List of WorkTasks with Pending status
     * @throws DataAccessException
     */
    @Override
    public List<WorkTask> findAllPendingTasks(boolean fullAssociation) throws DataAccessException {
        try{
            return workTaskDB.findAllPendingTasks(fullAssociation);
        }catch (DataAccessException e){
            throw new DataAccessException("Unable to execute query to retrieve pending tasks.",e);
        }
    }

    /**
     * Return a list of all WorkTasks in the database
     * @return a list of all WorkTasks in the database
     * @throws DataAccessException
     */
    @Override
    public List<WorkTask> findAllWorkTasks() throws DataAccessException {
        try {
            return workTaskDB.findAll(false);
        }
        catch (DataAccessException e) {
            throw new DataAccessException("WorkTaskCtr error.", e);
        }
    }

    /**
     * Return a WorkTask with the appropriate ID
     * @param id ID of WorkTask to return
     * @return WorkTask with appropriate ID
     * @throws DataAccessException
     */
    @Override
    public WorkTask findWorkTasks(Integer id) throws DataAccessException {
        try {
            return workTaskDB.findByID(id, false);
        } catch (DataAccessException e) {
            throw new DataAccessException("WorkTaskCtr error.", e);
        }
    }

    /**
     * Inserts a new WorkTask into the database
     * @param newWorkTask The new WorkTask to be inserted
     * @param workerCpr Worker to associate the WorkTask with
     * @return True if insert was successful (1 line changed)
     * @throws DataAccessException
     */
    @Override
    public boolean insertWorkTask(WorkTask newWorkTask, String workerCpr) throws DataAccessException {
        try {
            return (workTaskDB.insertWorkTask(newWorkTask, workerCpr)==1);
        } catch(DataAccessException e) {
            throw new DataAccessException("WorkTaskCtr error.", e);
        }
    }

    /**
     * Make changes to WorkTasks already in the database
     * @param newWorkTask new WorkTask object to replace the original one
     * @param workTaskID ID of the WorkTask wished to be changed
     * @return Number of rows affected
     * @throws DataAccessException
     */
    @Override
    public boolean updateWorkTask(WorkTask newWorkTask, Integer workTaskID) throws DataAccessException {
        try {
            return workTaskDB.updateWorkTask(newWorkTask, workTaskID) == 1;
        } catch(DataAccessException e) {
            throw new DataAccessException("WorkTaskCtr error.", e);
        }
    }

    @Override
    public boolean approveWorkTasks(ArrayList<Integer> idList) throws DataAccessException {
        try{
            return workTaskDB.approveWorkTasks(idList);
        }catch(DataAccessException e){
            throw new DataAccessException("Unable to execute approval of work tasks. (Control layer)",e);
        }
    }

    /**
     * Deletes a WorkTask from the database
     * @param id ID of the WorkTask wished to be deleted
     * @return Number of rows affected
     * @throws DataAccessException
     */
    @Override
    public boolean deleteWorkTask(Integer id) throws DataAccessException {
        try {
            return workTaskDB.deleteWorkTask(id) == 1;
        } catch(DataAccessException e) {
            throw new DataAccessException("WorkTaskCtr error.", e);
        }
    }
}
