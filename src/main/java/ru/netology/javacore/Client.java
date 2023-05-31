package ru.netology.javacore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception {
        try (Socket clientSocket = new Socket("localhost", 8989);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(
                     new InputStreamReader(clientSocket.getInputStream()));
             BufferedReader reader = new BufferedReader(
                     new InputStreamReader(System.in))
        ) {
            while (true) {
                System.out.println("Менеджер Ваших задач. Выберите операцию или 'end':" +
                        "\n1. Добавить задачу" +
                        "\n2. Удалить задачу" +
                        "\n3. Отменить");

                String input = reader.readLine();

                if (input.equals("end"))
                {System.out.println("До скорых встреч!");
                    break;
                }

                GsonBuilder builder = new GsonBuilder();
                builder.excludeFieldsWithoutExposeAnnotation();
                Gson gson = builder.create();
                Task task;

                if (input.equals("1")) {
                    String input1 = reader.readLine();
                    task = new Task("ADD", input1);

                    String sendTask = gson.toJson(task);
                    out.println(sendTask);


                    String answer = in.readLine();
                    System.out.println(answer);
                }

                if (input.equals("2")) {
                    String input1 = reader.readLine();
                    task = new Task("REMOVE", input1);

                    String sendTask = gson.toJson(task);
                    out.println(sendTask);

                   String answer = in.readLine();
                    System.out.println(answer);
                }

                if (input.equals("3")) {
                    task = new Task("RESTORE");

                    String sendTask = gson.toJson(task);
                    out.println(sendTask);

                    String answer = in.readLine();
                    System.out.println(answer);
                }
            }
        }
    }
}
