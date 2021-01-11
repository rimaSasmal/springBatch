package com.example.springBatch.repo;

public class CartoonDTO {
    Integer id;
    String showName;
    String channel;

    @Override
    public String toString() {
        return "CartoonDTO{" +
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
