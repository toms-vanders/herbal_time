package Controller;

import DB.*;
import Model.*;

import java.util.List;

public class WorkTaskCtr implements WorkTaskCtrIF {

    WorkTaskDBIF workTaskDB;

    public WorkTaskCtr() throws DataAccessException
    {
        try {
            workTaskDB = new WorkTaskDB();
        } catch(DataAccessException e) {
            throw new DataAccessException("WorkTaskCtr error.", e);
        }
    }

    @Override
    public List<WorkTask> findAllWorkTasksOfWorker(boolean fullAssociation, String workerCpr) throws DataAccessException {
        try {
            return workTaskDB.findAllWorkTasksOfWorker(fullAssociation, workerCpr);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new DataAccessException("WorkTaskCtr error.", e);
        }
    }

    @Override
    public List<WorkTask> findAllPendingTasks(boolean fullAssociation) throws DataAccessException {
        try{
            return workTaskDB.findAllPendingTasks(fullAssociation);
        }catch (DataAccessException e){
            throw new DataAccessException("Unable to execute query to retrieve pending tasks.",e);
        }
    }

    @Override
    public List<WorkTask> findAllWorkTasks() throws DataAccessException {
        try {
            return workTaskDB.findAll(false);
        }
        catch (DataAccessException e) {
            throw new DataAccessException("WorkTaskCtr error.", e);
        }
    }

    @Override
    public WorkTask findWorkTasks(Integer id) throws DataAccessException {
        try {
            return workTaskDB.findByID(id, false);
        } catch (DataAccessException e) {
            throw new DataAccessException("WorkTaskCtr error.", e);
        }
    }

    @Override
    public boolean insertWorkTask(WorkTask newWorkTask, String workerCpr) throws DataAccessException {
        try {
            return workTaskDB.insertWorkTask(newWorkTask, workerCpr) == 1;
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
