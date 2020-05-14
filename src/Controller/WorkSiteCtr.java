package Controller;

import DB.*;
import Model.*;

import java.util.List;

public class WorkSiteCtr implements WorkSiteCtrIF{

    WorkSiteDB workSiteDB;

    @Override
    public List<WorkSite> listAllWorkSites() throws DataAccessException {
        return null;
    }

    @Override
    public int insertWorkSite(WorkSite newWorkSite) throws DataAccessException {
        return 0;
    }

    @Override
    public int updateWorkSite(String workSiteID, WorkSite newWorkSite) throws DataAccessException {
        return 0;
    }
}
