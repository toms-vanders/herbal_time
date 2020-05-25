package Controller;

import DB.*;
import Model.*;

import java.lang.reflect.Type;
import java.util.List;

public class WorkSiteCtr implements WorkSiteCtrIF{

    WorkSiteDB workSiteDB;

    public WorkSiteCtr() throws DataAccessException {
        try {
            workSiteDB = new WorkSiteDB();
        } catch (DataAccessException e) {
            throw new DataAccessException("Unable to obtain seasonal worker database instance.", e);
        }
    }

    @Override
    public WorkSite findByID(int workSiteID,boolean fullAssociation) throws DataAccessException {
        try{
            return workSiteDB.findByID(workSiteID,fullAssociation);
        }catch (DataAccessException e){
            throw new DataAccessException("Unable to retrive worksite by ID, controller level.",e);
        }
    }

    @Override
    public List<WorkSite> listAllWorkSites(boolean fullAssociation) throws DataAccessException {
        try {
            return workSiteDB.findAll(fullAssociation);
        } catch (DataAccessException e) {
            throw new DataAccessException("WorkSiteCtr error", e);
        }
    }

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

    @Override
    public int updateWorkSite(int workSiteID, WorkSite newWorkSite) throws DataAccessException {
        return 0;
    }

    @Override
    public WorkSite findByWorkerCPR(String cpr, boolean fullAssociation) throws DataAccessException {
        try{
            return workSiteDB.findByWorkerCPR(cpr,fullAssociation);
        }catch (DataAccessException e){
            throw new DataAccessException("Unable to retrieve worksite based on seasonal worker CPR.", e);
        }
    }
}
