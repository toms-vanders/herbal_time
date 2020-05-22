package Controller;

import Model.*;
import DB.*;

import javax.xml.crypto.Data;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;

public class WorkTypeCtr implements WorkTypeCtrIF{

    WorkTypeDB workTypeDB;

    @Override
    public List<WorkType> findAllWorkTypesOfWorkSite(int workSiteID) throws DataAccessException {
        try {
            return workTypeDB.findAllWorkTypesOfWorkSite(workSiteID, false);
        } catch (DataAccessException e) {
            throw new DataAccessException("Error with WorkTypeCtr.", e);
        }
    }

    @Override
    public WorkType findWorkTypeByID(int worktypeID, boolean fullAssociation) throws DataAccessException {
        try {
            return workTypeDB.findWorkTypeByID(worktypeID, fullAssociation);
        } catch (DataAccessException e) {
            throw new DataAccessException("Error with WorkTypeCtr.", e);
        }
    }

    @Override
    public int insertWorkType(int workSiteID, WorkType newWorkType) throws DataAccessException {
        try {
            return workTypeDB.insertWorkType(workSiteID, newWorkType);
        } catch (DataAccessException e) {
            throw new DataAccessException("Error with WorkTypeCtr.", e);
        }
    }

    @Override
    public int updateWorkType(String workTypeID, WorkType newWorkType) throws DataAccessException {
        try {
            return updateWorkType(workTypeID, newWorkType);
        } catch (DataAccessException e) {
            throw new DataAccessException("Error with WorkTypeCtr.", e);
        }
    }

    @Override
    public int deleteWorkType(int workTypeID) throws DataAccessException {
        try {
            return workTypeDB.deleteWorkType(workTypeID);
        } catch (DataAccessException e) {
            throw new DataAccessException("Error with WorkTypeCtr.", e);
        }
    }


}
