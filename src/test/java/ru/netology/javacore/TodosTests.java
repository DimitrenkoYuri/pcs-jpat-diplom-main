package ru.netology.javacore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;


public class TodosTests {
    private Set<Task> taskSet;
    private Deque<Task> taskDeque;
    private Todos todos;

    @BeforeEach
    void initSet() {
        taskSet = new TreeSet<>(Comparator.comparing(Task::getTask));
        taskDeque = new ArrayDeque<>();
        todos = new Todos();
    }


    @Test
    void addTest() {
        taskSet.add(new Task("ADD", "Завтрак"));
        taskSet.add(new Task("ADD", "Обед"));
        taskSet.add(new Task("ADD", "Ужин"));
        Set<Task> expected = taskSet;

        todos.addTask(new Task("ADD", "Завтрак"));
        todos.addTask(new Task("ADD", "Обед"));
        todos.addTask(new Task("ADD", "Ужин"));

        Set<Task> actual = todos.getTaskSet();
        Assertions.assertEquals(expected, actual);
    }


    @Test
    void removeTest() {
        taskSet.add(new Task("ADD", "Завтрак"));
        taskSet.add(new Task("ADD", "Ужин"));
        Set<Task> expected = taskSet;
        todos.addTask(new Task("ADD", "Завтрак"));
        todos.addTask(new Task("ADD", "Обед"));
        todos.addTask(new Task("ADD", "Ужин"));

        todos.removeTask(new Task("ADD", "Обед"));

        Set<Task> actual = todos.getTaskSet();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void restoreTest() {
        String expected = "Вторая Первая";
        todos.addTask(new Task("ADD", "Первая"));
        todos.addTask(new Task("ADD", "Вторая"));
        todos.removeTask(new Task("REMOVE", "Первая"));
        todos.addTask(new Task("ADD", "Третья"));
        todos.restoreTask();
        todos.restoreTask();

        String actual = todos.getAllTasks();
        Assertions.assertEquals(expected, actual);
    }

}
