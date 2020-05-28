package Model;

/**
 * Used to represent produce collected at specific work sites
 *
 * @author Daniel Zoltan Ban
 * @author Mikuláš Dobrodej
 * @author Adrian Mihai Dohot
 * @author Damian Hrabąszcz
 * @author Toms Vanders
 * @version 1.0
 *
 * Date: 29.05.2020
 */
public class WorkSiteProduce {

    private int workSiteID;
    private String produceName;

    /**
     * Constructor of WorkSiteProduce
     */
    public WorkSiteProduce() {

    }

    /**
     * Constructor of WorkSiteProduce.
     *
     * @param workSiteID ID number of work site
     * @param produceName name of produce
     */
    public WorkSiteProduce(int workSiteID, String produceName) {
        this.workSiteID = workSiteID;
        this.produceName = produceName;
    }

    public int getWorkSiteID() {
        return workSiteID;
    }

    public void setWorkSiteID(int workSiteID) {
        this.workSiteID = workSiteID;
    }

    public String getProduceName() {
        return produceName;
    }

    public void setProduceName(String produceName) {
        this.produceName = produceName;
    }
}
