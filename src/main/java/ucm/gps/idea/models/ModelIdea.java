package ucm.gps.idea.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

public class ModelIdea implements Serializable{

        private static final long serialVersionUID = 0;

        @JsonProperty
        private Integer id;

        @JsonProperty
        private Integer deal;

        @JsonProperty
        private Integer creator;

        @JsonProperty
        private Integer category;

        @JsonProperty
        private Integer enterprise;

        @JsonProperty
        private String title;

        @JsonProperty
        private Date createdAt;

        @JsonProperty
        private String text;

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

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
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

        public Integer getCreator() {
            return creator;
        }

        public void setCreator(Integer creator) {
            this.creator = creator;
        }

        public Integer getCategory() {
            return category;
        }

        public void setCategory(Integer category) {
            this.category = category;
        }

        public Integer getEnterprise()
        {
            return enterprise;
        }

        public void setEnterprise(Integer enterprise)
        {
            this.enterprise = enterprise;
        }

    @Override
    public String toString() {
        return "RegisterIdea{" +
                "id='" + id + '\'' +
                ", deal='" + deal + '\'' +
                ", creator='" + creator + '\'' +
                ", category='" + category + '\'' +
                ", enterprise=" + enterprise +
                ", title='" + title + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", text='" + text + '\'' +
                ", active='" + active + '\'' +
                '}';
    }
}
