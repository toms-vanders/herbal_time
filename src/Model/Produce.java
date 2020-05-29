package Model;

/**
 * Used to represent employees of CS Works
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
public class Produce {
    private String produceName;
    private Boolean isCollectedOnWorksite;

    /**
     * Constructor of Produce
     * assigns isCollectedOnWorksite a null value
     */
    public Produce() {
        isCollectedOnWorksite = null;
    }

    /**
     * Constructor of Produce
     *
     * assigns isCollectedOnWorksite a null value
     * @param produceName name of the produce
     */
    public Produce(String produceName) {
        this.produceName = produceName;
        isCollectedOnWorksite = null;
    }

    /**
     * Constructor of Produce
     * @param produceName name of produce
     * @param collectedOnWorksite boolean signaling whether produce is collected on the worksite
     */
    public Produce(String produceName, Boolean collectedOnWorksite) {
        this.produceName = produceName;
        isCollectedOnWorksite = collectedOnWorksite;
    }

    public String getProduceName() {
        return produceName;
    }

    public void setProduceName(String produceName) {
        this.produceName = produceName;
    }

    public Boolean getCollectedOnWorksite() { return isCollectedOnWorksite; }
    public void setCollectedOnWorksite(Boolean collectedOnWorksite) { isCollectedOnWorksite = collectedOnWorksite; }
}
