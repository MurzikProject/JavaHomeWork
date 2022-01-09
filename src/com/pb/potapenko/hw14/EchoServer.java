package com.pb.potapenko.hw14;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class EchoServer {

    /**
     * Многопоточный сервер. Подключиться могут до 10 клиентов
     * Клиент разрывает соедиение если скажет exit
     */
    static class Handler implements Runnable {

        private final Socket mySocket; // объект типа Socket
        private int clientNumber; // номер подключаемого клиента

        public Handler (Socket mySocket, int clientNumber) {
            this.mySocket = mySocket;
            this.clientNumber = clientNumber;
        }

        @Override
        public void run() {
            try {
                // Присваивам клиенту имя с номером и информируем, что клиент подключился
                Thread.currentThread().setName("Клиент №"+clientNumber);
                String clientName = Thread.currentThread().getName();
                System.out.println(clientName + " в сети.");

                // Создаём потоки для чтения и записи сообщений из/в сокета
                BufferedReader msgIn = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
                PrintWriter msgOut = new PrintWriter(mySocket.getOutputStream(), true);

                // Читаем сообщения от клиента до тех пор пока он не скажет "exit"
                String msgText;
                while ((msgText = msgIn.readLine()) != null) {

                    // Сравниваем с "exit" и если это так - выходим из цикла
                    if (msgText.equals("exit")) {
                        // Тоже говорим клиенту "exit" и выходим из цикла
                        msgOut.println("exit");
                        System.out.println(clientName + " прислал сообщение: "  +  msgText + " и разорвал соедиенние.");
                        break;
                    } else {
                        // Посылаем клиенту ответ
                        System.out.println(clientName + " прислал сообщение: "  +  msgText);
                        msgText = "СООБЩЕНИЕ ОТ СЕРВЕРА: " + LocalDateTime.now().toString() + ": " + msgText;
                        msgOut.println(msgText);
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                try {
                    mySocket.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        int serverPort = 2104; // Номер порта. У сервера и клиентов он должен совпадать
        int clientCounter = 1; // счетчик клиентов
        int clientMaxCount = 5; // максимальное количество клиентов, которые могут подключиться к серверу одновременно

        ServerSocket serverSocket = new ServerSocket(serverPort);
        System.out.println("Сервер запущен. Порт: " + serverPort);
        ExecutorService threadPool = Executors.newFixedThreadPool(clientMaxCount);

        // В цикле ждем запроса клиента или нескольких клиентов
        while (true) {
            Socket clientSocket = serverSocket.accept();
            threadPool.submit(new Handler(clientSocket,clientCounter));
            clientCounter++;
        }
    }
}
