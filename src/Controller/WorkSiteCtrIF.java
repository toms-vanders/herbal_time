package Controller;

import Model.*;

import java.lang.reflect.Type;
import java.util.List;

public interface WorkSiteCtrIF {
    List<WorkSite> listAllWorkSites(boolean fullAssociation, Type type) throws DataAccessException;
    int insertWorkSite(String cvr, WorkSite newWorkSite) throws DataAccessException;
    int updateWorkSite(String workSiteID, WorkSite newWorkSite) throws DataAccessException;
}
