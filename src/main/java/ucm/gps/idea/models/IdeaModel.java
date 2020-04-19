package ucm.gps.idea.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ucm.gps.idea.entities.Category;
import ucm.gps.idea.entities.Creator;
import ucm.gps.idea.entities.Deal;
import ucm.gps.idea.entities.Enterprise;

import javax.persistence.*;
import java.util.Date;

public class IdeaModel {

    public IdeaModel(){

    }

    public IdeaModel(Integer deal, String creator, String creatorUser, String enterprise, String category, String title, String description, String summary, Date createdAt, String state, boolean active) {
        this.deal = deal;   //No necesitamos asignar un deal de primeras
        this.creator = creator;
        this.creatorUser = creatorUser;
        this.category = category;
        this.enterprise = enterprise;
        this.title = title;
        this.description = description;
        this.summary = summary;
        this.createdAt = createdAt;
        this.state = state;
        this.active = active;
    }

    private Integer id;

    private Integer deal;

    private String creator;

    private String creatorUser;

    private String category;

    private String enterprise;

    private String title;

    private Date createdAt;

    private String description;

    private String summary;

    private String state;

    private Boolean active;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeal() {
        return deal;
    }

    public void setDeal(Integer deal) {
        this.deal = deal;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatorUser() {
        return creatorUser;
    }

    public void setCreatorUser(String creatorUser) {
        this.creatorUser = creatorUser;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
