package ru.netology.javacore;

import java.util.*;
import java.util.stream.Collectors;

public class Todos {
    private Set<Task> taskSet;
    private Deque<Task> taskDeque;
    private int selector = 0;
    private int maxTasks = 7;

    public Todos() {
        taskSet = new TreeSet<>(Comparator.comparing(Task::getTask));
        taskDeque = new ArrayDeque<>();
    }

    public Set<Task> getTaskSet() {
        return taskSet;
    }

    public Deque<Task> getTaskDeque() {
        return taskDeque;
    }

    public void setMaxTasks(int maxTasks) {
        this.maxTasks = maxTasks;
    }

    public void addTask(Task taskAdd) {
        if (taskSet.size() < maxTasks) {
            if (selector == 0) {
                taskDeque.offer(taskAdd);
            }
            taskSet.add(taskAdd);
        }
    }

    public void removeTask(Task taskRemove) {
        if (taskSet.size() >= 1) {
            if (selector == 0) {
                taskDeque.offer(taskRemove);
            }
            taskSet.remove(taskRemove);
        }
    }

    public String getAllTasks() {
        String collect = taskSet.stream()
                .map(Task::getTask)
                .collect(Collectors.joining(" "));
        return collect;
    }

    public void restoreTask() {
        if (!taskDeque.isEmpty()) {
            selector++;
            Task restoreTask = taskDeque.pollLast();

            switch (restoreTask.getType()) {
                case "ADD":
                    removeTask(restoreTask);
                    break;
                case "REMOVE":
                    addTask(restoreTask);
                    break;
            }
            selector--;
        }
    }

    @Override
    public String toString() {
        return "Todos{" +
                "stringSet=" + taskSet +
                '}';
    }
}
