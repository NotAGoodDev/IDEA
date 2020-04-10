package ucm.gps.idea.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

public class ModelIdea implements Serializable{

    private static final long serialVersionUID = 1L;

    @JsonProperty
    private Integer id;

    @JsonProperty
    private Integer deal;

    @JsonProperty
    private String creatorUsername;

    @JsonProperty
    private String categoryName;

    @JsonProperty
    private String enterpriseUsername;

    @JsonProperty
    private String title;

    @JsonProperty
    private Date createdAt;

    @JsonProperty
    private String description;

    @JsonProperty
    private String summary;

    @JsonProperty
    private Boolean active;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getDeal() {
        return deal;
    }

    public void setDeal(Integer deal) {
        this.deal = deal;
    }

    public String getCreatorUsername() {
        return creatorUsername;
    }

    public void setCreatorUsername(String creatorUsername) {
        this.creatorUsername = creatorUsername;
    }

    public String getEnterpriseUsername() {
        return enterpriseUsername;
    }

    public void setEnterpriseUsername(String enterpriseUsername) {
        this.enterpriseUsername = enterpriseUsername;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "RegisterIdea{" +
                "id='" + id + '\'' +
                ", deal='" + deal + '\'' +
                ", creator='" + creatorUsername + '\'' +
                ", enterprise=" + enterpriseUsername +
                ", category='" + categoryName + '\'' +
                ", title='" + title + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", desciption='" + description + '\'' +
                ", summary='" + summary + '\'' +
                ", active='" + active + '\'' +
                '}';
    }
}