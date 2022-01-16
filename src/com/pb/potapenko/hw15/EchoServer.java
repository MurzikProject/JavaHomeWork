package com.pb.potapenko.hw15;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoServer {
    /**
     * Многопоточный сервер. Подключиться могут до 10 клиентов
     * Клиент разрывает соедиение если скажет exit
     */
    static class Handler implements Runnable {

        private final Socket mySocket; // объект типа Socket
        private int clientNumber; // номер подключаемого клиента
        private JTextArea inputMessagesArea; // Ссылка на поле, куда буду выводиьтся сообщения от клиентов

        public Handler (Socket mySocket, int clientNumber, JTextArea inputMessagesArea) {
            this.mySocket = mySocket;
            this.clientNumber = clientNumber;
            this.inputMessagesArea = inputMessagesArea;
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
                        if(inputMessagesArea.getText().length()>0) {
                            inputMessagesArea.setText(inputMessagesArea.getText() + "\n" + clientName + " прислал сообщение: "  +  msgText + " и разорвал соедиенние.");
                        }
                        else {
                            inputMessagesArea.setText(clientName + " прислал сообщение: "  +  msgText + " и разорвал соедиенние.");
                        }
                        break;
                    } else {
                        // Посылаем клиенту ответ
                        if(inputMessagesArea.getText().length()>0) {
                            inputMessagesArea.setText(inputMessagesArea.getText() + "\n" + clientName + " прислал сообщение: "  +  msgText);
                        }
                        else {
                            inputMessagesArea.setText(clientName + " прислал сообщение: "  +  msgText);
                        }

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

    /**
     * Метод запускает сервер и содержит его настройки
     * @throws Exception
     */
    private static void createServer() throws Exception{

        int serverPort = 2104; // Номер порта. У сервера и клиентов он должен совпадать
        int clientCounter = 1; // счетчик клиентов
        int clientMaxCount = 5; // максимальное количество клиентов, которые могут подключиться к серверу одновременно

        ServerSocket serverSocket = new ServerSocket(serverPort);
        ExecutorService threadPool = Executors.newFixedThreadPool(clientMaxCount);

        // Создаем и настраиваем фрейм сервера
        JFrame serverFrame = new JFrame("EchoServer");
        serverFrame.setLayout(new BorderLayout());
        serverFrame.setSize(700,500);
        serverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Добавляем лейбл, информирующий о состоянии сервера
        JLabel serverState = new JLabel();
        serverState.setText("Сервер запущен. Порт: " + serverPort);
        serverState.setForeground(Color.GREEN);
        serverState.setVisible(true);

        // Создаем текстовое поле, куда буду выводиться сообщения клиентов и устанавливаем опции переноса строки
        JTextArea inputMessages = new JTextArea(30,35);
        inputMessages.setLineWrap(true);
        inputMessages.setWrapStyleWord(true);

        // Размещаем элементы на фрейме
        serverFrame.add(serverState,BorderLayout.NORTH);
        serverFrame.add(inputMessages,BorderLayout.SOUTH);

        // Делаем окно сервера видимым
        serverFrame.setVisible(true);

        // В цикле ждем запроса клиента или нескольких клиентов
        while (true) {
            Socket clientSocket = serverSocket.accept();
            threadPool.submit(new Handler(clientSocket,clientCounter,inputMessages));
            clientCounter++;
        }
    }

    public static void main(String[] args) throws Exception{
        createServer();
    }
}
