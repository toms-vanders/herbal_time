package Controller;

import Model.*;
import DB.*;

import java.util.List;

public class WorkTypeCtr implements WorkTypeCtrIF{

    WorkTypeDB workTypeDB;

    @Override
    public List<WorkType> findallWorkTypes() throws DataAccessException {
        return null;
    }

    @Override
    public int insertWorkType(WorkType newWorkType) throws DataAccessException {
        return 0;
    }

    @Override
    public int updateWorkType(String workTypeID, WorkType newWorkType) throws DataAccessException {
        return 0;
    }
}
