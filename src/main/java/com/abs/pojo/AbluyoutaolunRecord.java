package com.abs.pojo;

public class AbluyoutaolunRecord {
    private String userId;
    private int topicId;
    private String time;

    @Override
    public String toString() {
        return "AbluyoutaolunRecord{" +
                "userId='" + userId + '\'' +
                ", topicId=" + topicId +
                ", time='" + time + '\'' +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
