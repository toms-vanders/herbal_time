package Controller;

import Model.*;

import java.util.List;

public interface WorkSiteCtrIF {
    List<WorkSite> listAllWorkSites() throws DataAccessException;
    int insertWorkSite(WorkSite newWorkSite) throws DataAccessException;
    int updateWorkSite(String workSiteID, WorkSite newWorkSite) throws DataAccessException;
}
