package Controller;

import DB.DataAccessException;
import DB.WorkSiteDB;
import Model.WorkSite;

import java.util.List;

/**
 * Used to interface between the GUI layer and WorkSiteDB
 *
 * @author Daniel Zoltan Ban
 * @author Mikuláš Dobrodej
 * @author Adrian Mihai Dohot
 * @author Damian Hrabąszcz
 * @author Toms Vanders
 * @version 1.0 (29.05.2020)
 *
 * Date: 29.05.2020
 */
public class WorkSiteCtr implements WorkSiteCtrIF{

    final WorkSiteDB workSiteDB;

    public WorkSiteCtr() {
        workSiteDB = new WorkSiteDB();
    }

    /**
     * Returns a WorkSite with the appropriate ID
     * @param workSiteID ID of the WorkSite to return
     * @param fullAssociation If true, returns also the WorkSite's WorkTypes
     * @return a WorkSite with the appropriate ID
     * @throws DataAccessException
     */
    @Override
    public WorkSite findByID(int workSiteID,boolean fullAssociation) throws DataAccessException {
        try{
            return workSiteDB.findByID(workSiteID,fullAssociation);
        }catch (DataAccessException e){
            throw new DataAccessException("Unable to retrive worksite by ID, controller level.",e);
        }
    }

    /**
     * Returns a WorkSite with the appropriate name
     * @param siteName Name of the WorkSite to return
     * @param fullAssociation If true, returns also the WorkSite's WorkTypes
     * @return a WorkSite with the appropriate name
     * @throws DataAccessException
     */
    @Override
    public WorkSite findByName(String siteName, boolean fullAssociation) throws DataAccessException {
        try{
            return workSiteDB.findByName(siteName, fullAssociation);
        }catch (DataAccessException e){
            throw new DataAccessException("Unable to retrive worksite by name, controller level.",e);
        }
    }

    /**
     * Returns a WorkSite of a SeasonalWorker
     * @param cpr CPR of the SeasonalWorker whose WorkSite to return
     * @param fullAssociation If true, returns also the WorkSite's WorkType
     * @return Number of rows affected
     * @throws DataAccessException
     */
    @Override
    public WorkSite findByWorkerCPR(String cpr, boolean fullAssociation) throws DataAccessException {
        try {
            return workSiteDB.findByWorkerCPR(cpr,fullAssociation);
        } catch (DataAccessException e) {
            throw new DataAccessException("Unable to retrieve worksite based on seasonal worker CPR.", e);
        }
    }

    /**
     * Returns a list of all WorkSites loaded in the database
     * @param fullAssociation If true, returns also the WorkSites' WorkTypes
     * @return a list of all WorkSites loaded in the database
     * @throws DataAccessException
     */
    @Override
    public List<WorkSite> listAllWorkSites(boolean fullAssociation) throws DataAccessException {
        try {
            return workSiteDB.findAll(fullAssociation);
        } catch (DataAccessException e) {
            throw new DataAccessException("WorkSiteCtr error", e);
        }
    }

    /**
     * Inserts a new WorkSite into the database
     * @param cvr CVR of Client to attach the WorkSite to
     * @param newWorkSite The new WorkSite object to be inserted
     * @return True if insert was successful (1 line changed)
     * @throws DataAccessException
     */
    @Override
    public Boolean insertWorkSite(String cvr, WorkSite newWorkSite) throws DataAccessException {
        try {
            if (workSiteDB.insertWorkSite(cvr, newWorkSite) == 1) {
                return true;
            }
        } catch (DataAccessException e) {
            throw new DataAccessException("WorkSiteCtr error", e);
        }
        return false;
    }

    /**
     * Make changes to Clients already in the database
     * @param workSiteID ID of the WorkSite wished to be changed
     * @param newWorkSite new WorkSite object to replace the original one
     * @return Number of rows affected
     */
    @Override
    public int updateWorkSite(int workSiteID, WorkSite newWorkSite) {
        return 0;
    }

}
