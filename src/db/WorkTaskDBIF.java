package DB;

import Controller.DataAccessException;
import Model.WorkTask;

import java.util.List;

public interface WorkTaskDBIF {
    List<WorkTask> findAll(boolean fullAssociation) throws DataAccessException;
    WorkTask findByID(Integer wTaskID, boolean fullAssociation) throws DataAccessException;
    Integer insertWorkTask(WorkTask workTask) throws DataAccessException;
    Integer updateWorkTask(Integer wTaskID, WorkTask workTask) throws DataAccessException;
}
