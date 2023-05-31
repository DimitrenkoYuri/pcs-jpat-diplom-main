package ru.netology.javacore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TodoServer {
    private int port = 8989;
    private Todos todos;

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    public void start() {
        System.out.println("Starting server at " + port + "...");
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        try (ServerSocket serverSocket = new ServerSocket(port)) {

            while (true) {
                Socket fromClientSocket = serverSocket.accept();
                try (
                        Socket socket = fromClientSocket;
                        // Прием входящих данных от клиента
                        BufferedReader in = new BufferedReader(
                                new InputStreamReader(socket.getInputStream()));
                        // Отправка данных - ответ сервера
                        PrintWriter out = new PrintWriter(
                                socket.getOutputStream(), true);
                ) {
                    String inputText;
                    while ((inputText = in.readLine()) != null) {
                        System.out.println("Получено сервером: " + inputText);

                        Task task = gson.fromJson(inputText, Task.class);

                        if (task.getType().equals("ADD")) {
                            todos.addTask(task);
                            String allTasks = todos.getAllTasks();
                            out.println(allTasks);
                        }
                        if (task.getType().equals("REMOVE")) {
                            todos.removeTask(task);
                            String allTasks = todos.getAllTasks();
                            out.println(allTasks);
                        }
                        if (task.getType().equals("RESTORE")) {
                            todos.restoreTask();
                            String allTasks = todos.getAllTasks();
                            out.println(allTasks);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}
