package com.pb.potapenko.hw2;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int operand1;
        int operand2;
        String sign;
        float resultValue = 0.0F;
        String txtMessage = "";

        System.out.println("Введите число 1:");
        operand1 = scan.nextInt();
        System.out.println("Введите число 2:");
        operand2 = scan.nextInt();
        System.out.println("Введите символ операции (+,-,*,/):");
        sign = scan.next();
        scan.close();

        if(operand2==0&&sign.equals("/")) {
            txtMessage = "Деление на 0 запрещено!";
            //System.out.println("Деление на 0 запрещено!");
        }
        else {
            switch (sign){
                case("+"): resultValue = operand1 + operand2;
                    txtMessage = (operand1 + " " + sign + " " + operand2 + " = " + resultValue);
                    break;
                case("-"): resultValue = operand1 - operand2;
                    txtMessage = (operand1 + " " + sign + " " + operand2 + " = " + resultValue);
                    break;
                case("*"): resultValue = operand1 * operand2;
                    txtMessage = (operand1 + " " + sign + " " + operand2 + " = " + resultValue);
                    break;
                case("/"): resultValue = operand1 / operand2;
                    txtMessage = (operand1 + " " + sign + " " + operand2 + " = " + resultValue);
                    break;
                default: txtMessage = "Вы ввели недопустимую арифметическую операцию: "+sign;
            }
        }
        System.out.println("РЕЗУЛЬТАТ АРИФМЕТИЧЕСКОЙ ОПЕРАЦИИ:");
        System.out.println(txtMessage);
    }
}
