package Model;

public class WorkSiteProduce {

    private int workSiteID;
    private String produceName;

    public WorkSiteProduce() {
    }

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
