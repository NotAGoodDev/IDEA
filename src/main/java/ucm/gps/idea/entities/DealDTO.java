package ucm.gps.idea.entities;

public class DealDTO {

    DealDTO()
    {

    }

    DealDTO(Integer id, Integer ideaId, String creator, String enterprise, String title, String terms, float percentage, boolean cAgree, boolean eAgree)
    {
        this.id = id;
        this.ideaId = id;
        this.creator = creator;
        this.enterprise = enterprise;
        this.title = title;
        this.terms = terms;
        this.percentage = percentage;
        this.cAgree = cAgree;
        this.eAgree = eAgree;
    }

    private Integer id;

    private Integer ideaId;

    private String creator;

    private String enterprise;

    private String title;

    private String terms;

    private float percentage;

    private boolean cAgree;

    private boolean eAgree;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(Integer ideaId) {
        this.ideaId = ideaId;
    }

    public String getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public boolean isCAgree() {
        return cAgree;
    }

    public void setCAgree(boolean cAgree) {
        this.cAgree = cAgree;
    }

    public boolean isEAgree() {
        return eAgree;
    }

    public void setEAgree(boolean eAgree) {
        this.eAgree = eAgree;
    }
}
