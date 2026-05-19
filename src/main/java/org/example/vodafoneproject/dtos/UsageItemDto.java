package org.example.vodafoneproject.dtos;

public class UsageItemDto {

    private String type;
    private int currentSpent;
    private int total;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCurrentSpent() {
        return currentSpent;
    }

    public void setCurrentSpent(int currentSpent) {
        this.currentSpent = currentSpent;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}