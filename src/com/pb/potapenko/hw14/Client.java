package com.pb.potapenko.hw14;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    /**
     * Клиент подключается к серверу, отправляет и получает от него сообщения.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        int clientPort = 2104; // Номер порта. У сервера и клиентов он должен совпадать

        // Создаем объект Scan для ввода текста из консоли
        Scanner scan = new Scanner(System.in);

        // Создаем сокет для общения с сервером
        Socket mainSocket = new Socket("127.0.0.1", clientPort);

        // Пишем, что стартовали клиент
        System.out.println("Клиент запущен");

        // Создаём потоки для чтения и записи сообщений из/в сокета для общения с сервером
        BufferedReader msgIn = new BufferedReader(new InputStreamReader(mainSocket.getInputStream()));
        PrintWriter msgOut = new PrintWriter(mainSocket.getOutputStream(), true);

        // Считываем с консоли вводимый клиентом текст. Если ввести exit то сервер разорвет соедиение
        System.out.println("Введите ссобщение");
        msgOut.println(scan.nextLine());

        // Читаем сообщения от сервера
        String msgText;

        // Входим в цикл чтения, что нам ответил сервер
        while ((msgText = msgIn.readLine()) != null) {
            // Если пришел ответ “exit”, то заканчиваем цикл
            if (msgText.equals("exit")) {
                break;
            }
            // Печатаем ответ от сервера на консоль для проверки
            System.out.println(msgText);

            // Считываем с консоли вводимый клиентом текст. Если ввести exit то сервер разорвет соедиение
            msgOut.println(scan.nextLine());
        }

        msgIn.close();
        msgOut.close();
        mainSocket.close();
        scan.close();
    }
}
