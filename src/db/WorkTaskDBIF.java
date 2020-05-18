package DB;

import Controller.DataAccessException;
import Model.WorkTask;

import java.lang.reflect.Type;
import java.util.List;

public interface WorkTaskDBIF {
    List<WorkTask> findAll(boolean fullAssociation, Type type) throws DataAccessException;
    List<WorkTask> findAllWorkTasksOfWorker(boolean fullAssociation, String workerCpr, Type type) throws DataAccessException;
    WorkTask findByID(Integer id, boolean fullAssociation, Type type) throws DataAccessException;
    Integer insertWorkTask(WorkTask workTask, String workerCpr) throws DataAccessException;
    Integer updateWorkTask(WorkTask workTask, Integer workTaskID) throws DataAccessException;
    void deleteWorkTask(Integer id) throws DataAccessException;
}

