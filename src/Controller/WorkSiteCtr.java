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
    public List<WorkSite> listAllWorkSites(boolean fullAssociation) throws DataAccessException {
        try {
            return workSiteDB.findAll(fullAssociation);
        } catch (DataAccessException e) {
            throw new DataAccessException("WorkSiteCtr error", e);
        }
    }

    @Override
    public int insertWorkSite(String cvr, WorkSite newWorkSite) throws DataAccessException {
        try {
            return workSiteDB.insertWorkSite(cvr, newWorkSite);
        } catch (DataAccessException e) {
            throw new DataAccessException("WorkSiteCtr error", e);
        }
    }

    @Override
    public int updateWorkSite(String workSiteID, WorkSite newWorkSite) throws DataAccessException {
        return 0;
    }
}
