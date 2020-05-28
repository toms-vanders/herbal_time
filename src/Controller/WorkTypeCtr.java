package Controller;

import Model.*;
import DB.*;

import javax.xml.crypto.Data;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;

/**
 * Used to interface between the database and the GUI
 */
public class WorkTypeCtr implements WorkTypeCtrIF{

    WorkTypeDB workTypeDB;

    public WorkTypeCtr() throws DataAccessException {
        try {
            workTypeDB = new WorkTypeDB();
        } catch(Exception e) {
            throw new DataAccessException("WorkTaskCtr error.", e);
        }
    }

    /**
     * Returns a list of all WorkTypes loaded in the database
     * @return a list of all WorkTypes loaded in the database
     * @throws DataAccessException
     */
    @Override
    public List<WorkType> findAll() throws DataAccessException {
        try {
            return workTypeDB.findAll();
        } catch (DataAccessException e) {
            throw new DataAccessException("Error with WorkTypeCtr.", e);
        }
    }

    /**
     * Returns a list of all WorkTypes at a WorkSite
     * @param workSiteID ID of WorkSite whose WorkTypes to return
     * @return a list of all WorkTypes at a WorkSite
     * @throws DataAccessException
     */
    @Override
    public List<WorkType> findAllWorkTypesOfWorkSite(int workSiteID) throws DataAccessException {
        try {
            return workTypeDB.findAllWorkTypesOfWorkSite(workSiteID);
        } catch (DataAccessException e) {
            throw new DataAccessException("Error with WorkTypeCtr.", e);
        }
    }

    /**
     * Returns a WorkType with the appropriate ID
     * @param worktypeID ID of the WorkType to return
     * @return a WorkType with the appropriate ID
     * @throws DataAccessException
     */
    @Override
    public WorkType findWorkTypeByID(int worktypeID) throws DataAccessException {
        try {
            return workTypeDB.findWorkTypeByID(worktypeID);
        } catch (DataAccessException e) {
            throw new DataAccessException("Error with WorkTypeCtr.", e);
        }
    }

    /**
     * Inserts a new WorkType into the database
     * @param workSiteID ID of WorkSite to which to assign the WorkType
     * @param newWorkType The new WorkType object to be inserted
     * @return Returns 1 if insert was successful
     * @throws DataAccessException
     */
    @Override
    public boolean insertWorkType(int workSiteID, WorkType newWorkType) throws DataAccessException {
        try {
            return (workTypeDB.insertWorkType(workSiteID, newWorkType)==1);
        } catch (DataAccessException e) {
            throw new DataAccessException("Error with WorkTypeCtr.", e);
        }
    }

    /**
     * Make changes to WorkTypes already in the database
     * @param workTypeID ID of the WorkType wished to be changed
     * @param newWorkType The new WorkType to replace the original one
     * @return Number of rows affected
     * @throws DataAccessException
     */
    @Override
    public int updateWorkType(String workTypeID, WorkType newWorkType) throws DataAccessException {
        try {
            return updateWorkType(workTypeID, newWorkType);
        } catch (DataAccessException e) {
            throw new DataAccessException("Error with WorkTypeCtr.", e);
        }
    }

    /**
     * Deletes a WorkType from the database
     * @param workTypeID ID of the WorkType to be deleted
     * @return Number of rows affected
     * @throws DataAccessException
     */
    @Override
    public int deleteWorkType(int workTypeID) throws DataAccessException {
        try {
            return workTypeDB.deleteWorkType(workTypeID);
        } catch (DataAccessException e) {
            throw new DataAccessException("Error with WorkTypeCtr.", e);
        }
    }


}
