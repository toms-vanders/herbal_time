package Model;

public class Produce {
    private String produceName;
    private Boolean isCollectedOnWorksite;

    public Produce() {
        isCollectedOnWorksite = null;
    }

    public Produce(String produceName) {
        this.produceName = produceName;
        isCollectedOnWorksite = null;
    }

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
