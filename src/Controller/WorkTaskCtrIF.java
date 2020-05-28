package Controller;

import Model.*;

import java.util.ArrayList;
import java.util.List;

public interface WorkTaskCtrIF {
    List<WorkTask> findAllWorkTasks() throws DataAccessException;
    List<WorkTask> findAllWorkTasksOfWorker(boolean fullAssociation,String workerCpr) throws DataAccessException;
    List<WorkTask> findAllPendingTasks(boolean fullAssociation) throws DataAccessException;
    WorkTask findWorkTasks(Integer id) throws DataAccessException;
    boolean insertWorkTask(WorkTask newWorkTask, String workerCpr) throws DataAccessException;
    boolean updateWorkTask(WorkTask newWorkTask, Integer workTaskID) throws DataAccessException;
    boolean approveWorkTasks(ArrayList<Integer> idList) throws DataAccessException;
    boolean deleteWorkTask(Integer id) throws DataAccessException;
}
