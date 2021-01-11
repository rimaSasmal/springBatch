package com.example.springBatch.repo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cartoon {
    @Id
    Integer id;
    @Column(name = "show")
    String showName;
    String channel;

    @Override
    public String toString() {
        return "Cartoon{" +
                "id=" + id +
                ", showName='" + showName + '\'' +
                ", channel='" + channel + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
