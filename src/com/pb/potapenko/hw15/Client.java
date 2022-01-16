package com.pb.potapenko.hw15;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    /**
     * Клиент подключается к серверу, отправляет и получает от него сообщения c через графическую форму
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        // Создаем и настраиваем фрейм клиента
        JFrame clientFrame = new JFrame("Client");
        clientFrame.setLayout(new BorderLayout());
        clientFrame.setSize(400,400);
        //clientFrame.setResizable(false);
        clientFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создаем панели
        JPanel infoPanel = new JPanel();
        JPanel textPanel = new JPanel();
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new GridLayout(3,1));

        // Создаем статические ярлыки
        JLabel infoLabel = new JLabel("Клиент запущен");
        infoLabel.setForeground(Color.GREEN);
        JLabel messageLabel = new JLabel("Введите сообщение:");
        messageLabel.setForeground(Color.BLUE);

        // Создаем текстовое поле, куда буду выводиться сообщения клиента и устанавливаем опции переноса строки
        JTextArea inputMessages = new JTextArea(30,35);
        inputMessages.setLineWrap(true);
        inputMessages.setWrapStyleWord(true);

        // Создаем поле для ввода текста клиентом
        JTextField outputMessages = new JTextField();

        // Создаем кнопку, отправляющее сообщение на сервер
        JButton sendMessage = new JButton("Send");

        // Размещаем элементы на панелях
        infoPanel.add(infoLabel);
        textPanel.add(inputMessages);
        messagePanel.add(messageLabel);
        messagePanel.add(outputMessages);
        messagePanel.add(sendMessage);

        clientFrame.add(infoPanel,BorderLayout.NORTH);
        clientFrame.add(textPanel,BorderLayout.CENTER);
        clientFrame.add(messagePanel,BorderLayout.SOUTH);

        clientFrame.setVisible(true);

        int clientPort = 2104; // Номер порта. У сервера и клиентов он должен совпадать

        // Создаем сокет для общения с сервером
        Socket mainSocket = new Socket("127.0.0.1", clientPort);

        // Создаём потоки для чтения и записи сообщений из/в сокета для общения с сервером
        BufferedReader msgIn = new BufferedReader(new InputStreamReader(mainSocket.getInputStream()));
        PrintWriter msgOut = new PrintWriter(mainSocket.getOutputStream(), true);

        // Считываем вводимый клиентом текст. Если ввести exit то сервер разорвет соедиение
        if(outputMessages.getText().equals("")) {
            msgOut.println("Всем привет. Я в сети!");
            outputMessages.setText("");
        }

        // Читаем сообщения от сервера
        String msgText;

        // Входим в цикл чтения, что нам ответил сервер
        while ((msgText = msgIn.readLine()) != null) {
            // Если пришел ответ “exit”, то заканчиваем цикл и закрываем форму
            if (msgText.equals("exit")) {
                clientFrame.dispose();
                break;

            }
            // Печатаем ответ от сервера в поле входящих сообщений
            //System.out.println(msgText);
            if(inputMessages.getText().length()>0) {
                inputMessages.setText(inputMessages.getText()+"\n"+msgText);
            }
            else {
                inputMessages.setText(msgText);
            }


            // Прописываем действия при нажатии кнопки Send
            sendMessage.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    // Считываем с текстового поля вводимый клиентом текст. Если ввести exit то сервер разорвет соедиение
                    if(!outputMessages.getText().equals("")) {
                        msgOut.println(outputMessages.getText());
                        outputMessages.setText("");
                    }
                }
            });
        }
        msgIn.close();
        msgOut.close();
        mainSocket.close();
    }
}
