package Controller;

import DB.*;
import Model.*;

import java.util.List;

public class WorkTaskCtr implements WorkTaskCtrIF{

    WorkTaskDB workTaskDB;

    @Override
    public List<WorkTask> findallWorkTasks() throws DataAccessException {
        return null;
    }

    @Override
    public int insertWorkTask(WorkTask newWorkTask) throws DataAccessException {
        return 0;
    }

    @Override
    public int updateWorkTask(String workTaskID, WorkTask newWorkTask) throws DataAccessException {
        return 0;
    }
}
