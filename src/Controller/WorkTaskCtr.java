package Controller;

import DB.*;
import Model.*;

import java.util.List;

public class WorkTaskCtr implements WorkTaskCtrIF {

    WorkTaskDB workTaskDB;

    public WorkTaskCtr() throws DataAccessException
    {
        try {
            workTaskDB = new WorkTaskDB();
        } catch(DataAccessException e) {
            throw new DataAccessException("WorkTaskCtr error.", e);
        }
    }

    @Override
    public List<WorkTask> findAllWorkTasksOfWorker(String workerCpr) throws DataAccessException {
        try {
            return workTaskDB.findAllWorkTasksOfWorker(true, workerCpr, WorkTask.class);
        } catch (DataAccessException e) {
            throw new DataAccessException("WorkTaskCtr error.", e);
        }
    }

    @Override
    public List<WorkTask> findAllWorkTasks() throws DataAccessException {
        try {
            return workTaskDB.findAll(false, WorkTask.class);
        }
        catch (DataAccessException e) {
            throw new DataAccessException("WorkTaskCtr error.", e);
        }
    }

    @Override
    public WorkTask findWorkTasks(Integer id) throws DataAccessException {
        try {
            return workTaskDB.findByID(id, false, WorkTask.class);
        } catch (DataAccessException e) {
            throw new DataAccessException("WorkTaskCtr error.", e);
        }
    }

    @Override
    public int insertWorkTask(WorkTask newWorkTask, String workerCpr) throws DataAccessException {
        try {
            return workTaskDB.insertWorkTask(newWorkTask, workerCpr);
        } catch(DataAccessException e) {
            throw new DataAccessException("WorkTaskCtr error.", e);
        }
    }

    @Override
    public int updateWorkTask(WorkTask newWorkTask, Integer workTaskID) throws DataAccessException {
        try {
            return workTaskDB.updateWorkTask(newWorkTask, workTaskID);
        } catch(DataAccessException e) {
            throw new DataAccessException("WorkTaskCtr error.", e);
        }
    }

    @Override
    public void deleteWorkTask(Integer id) throws DataAccessException {
        try {
            workTaskDB.deleteWorkTask(id);
        } catch(DataAccessException e) {
            throw new DataAccessException("WorkTaskCtr error.", e);
        }
    }
}
