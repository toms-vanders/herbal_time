package Controller;

import Model.*;

import java.lang.reflect.Type;
import java.util.List;

public interface WorkSiteCtrIF {
    List<WorkSite> listAllWorkSites(boolean fullAssociation) throws DataAccessException;
    WorkSite findByID(int workSiteID,boolean fullAssociation) throws DataAccessException;
    WorkSite findByName(String siteName, boolean fullAssociation) throws DataAccessException;
    WorkSite findByWorkerCPR(String cpr, boolean fullAssociation) throws DataAccessException;
    Boolean insertWorkSite(String cvr, WorkSite newWorkSite) throws DataAccessException;
    int updateWorkSite(int workSiteID, WorkSite newWorkSite) throws DataAccessException;

}
