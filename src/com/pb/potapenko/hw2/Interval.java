package com.pb.potapenko.hw2;

import java.util.Scanner;

public class Interval {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int yourNumber;
        String txtMessage = "Введенное вами число попадет в интервал ";

        System.out.println("Введите произвольное число:");
        yourNumber = scan.nextInt();
        scan.close();

        if(0<=yourNumber&&yourNumber<=14) {
            txtMessage = txtMessage + "[0 - 14].";
        }
        else if(15<=yourNumber&&yourNumber<=35) {
            txtMessage = txtMessage + "[15 - 35].";
        }
        else if(36<=yourNumber&&yourNumber<=50) {
            txtMessage = txtMessage + "[36 - 50].";
        }
        else if(51<=yourNumber&&yourNumber<=100) {
            txtMessage = txtMessage + "[51 - 100].";
        }
        else {
            txtMessage = "Ваше число не попадает ни в один интервал.";
        }
        System.out.println(txtMessage);
    }
}
