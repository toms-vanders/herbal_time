package Controller;

import Model.*;

import java.util.List;

public interface WorkTaskCtrIF {
    List<WorkTask> findAllWorkTasks() throws DataAccessException;
    List<WorkTask> findAllWorkTasksOfWorker(String workerCpr) throws DataAccessException;
    WorkTask findWorkTasks(Integer id) throws DataAccessException;
    int insertWorkTask(WorkTask newWorkTask, String workerCpr) throws DataAccessException;
    int updateWorkTask(WorkTask newWorkTask, Integer workTaskID) throws DataAccessException;
    void deleteWorkTask(Integer id) throws DataAccessException;
}