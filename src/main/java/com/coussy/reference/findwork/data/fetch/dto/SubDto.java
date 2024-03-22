package com.coussy.reference.findwork.data.fetch.dto;


import java.time.LocalDateTime;
import java.util.List;

class SubDto {

        String id;

        List<String> keywords;

        LocalDateTime date_posted;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public LocalDateTime getDate_posted() {
        return date_posted;
    }

    public void setDate_posted(LocalDateTime date_posted) {
        this.date_posted = date_posted;
    }


    @Override
    public String toString() {
        return "SubDto{" +
                "id='" + id + '\'' +
                ", keywords=" + keywords +
                ", date_posted=" + date_posted +
                '}';
    }
}




