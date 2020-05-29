package DB;

import Model.WorkTask;

import java.util.ArrayList;
import java.util.List;

/**
 * User of this interface implements access to data from the WorkTask table in the database.
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
public interface WorkTaskDBIF {
    List<WorkTask> findAll(boolean fullAssociation) throws DataAccessException;
    List<WorkTask> findAllWorkTasksOfWorker(boolean fullAssociation, String workerCpr) throws DataAccessException;
    List<WorkTask> findAllPendingTasks(boolean fullAssociation) throws DataAccessException;
    WorkTask findByID(int id, boolean fullAssociation) throws DataAccessException;
    int insertWorkTask(WorkTask workTask, String workerCpr) throws DataAccessException;
    int updateWorkTask(WorkTask workTask, int workTaskID) throws DataAccessException;
    boolean approveWorkTasks(ArrayList<Integer> idList) throws DataAccessException;
    int deleteWorkTask(int id) throws DataAccessException;
}

