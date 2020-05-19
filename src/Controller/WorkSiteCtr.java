package Controller;

import DB.*;
import Model.*;

import java.lang.reflect.Type;
import java.util.List;

public class WorkSiteCtr implements WorkSiteCtrIF{

    WorkSiteDB workSiteDB;

    @Override
    public List<WorkSite> listAllWorkSites(boolean fullAssociation, Type type) throws DataAccessException {
        try {
            return workSiteDB.findAll(fullAssociation, type);
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
