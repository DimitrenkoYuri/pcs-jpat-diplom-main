package ru.netology.javacore;
import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class Task implements Serializable {
    @Expose
    private String type;
    @Expose
    private String task;

    public Task(String type, String task) {
        this.type = type;
        this.task = task;
    }

    public Task(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getTask() {
        return task;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Task{" +
                "type='" + type + '\'' +
                ", task='" + task + '\'' +
                '}';
    }
}
