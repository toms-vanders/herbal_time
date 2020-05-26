package DB;

import Controller.DataAccessException;
import Model.WorkTask;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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

