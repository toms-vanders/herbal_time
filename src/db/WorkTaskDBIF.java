package DB;

import Controller.DataAccessException;
import Model.WorkTask;

import java.lang.reflect.Type;
import java.util.List;

public interface WorkTaskDBIF {
    List<WorkTask> findAll(boolean fullAssociation) throws DataAccessException;
    List<WorkTask> findAllWorkTasksOfWorker(boolean fullAssociation, String workerCpr) throws DataAccessException;
    List<WorkTask> findAllPendingTasks(boolean fullAssociation) throws DataAccessException;
    WorkTask findByID(Integer id, boolean fullAssociation) throws DataAccessException;
    Integer insertWorkTask(WorkTask workTask, String workerCpr) throws DataAccessException;
    Integer updateWorkTask(WorkTask workTask, Integer workTaskID) throws DataAccessException;
    Integer deleteWorkTask(Integer id) throws DataAccessException;
}

