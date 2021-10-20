package com.pb.potapenko.hw3;

import java.lang.Math;
import java.util.Scanner;

public class Bingo {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int cntIterations = 0; // Счетчик итераций
        int playerValue = -1;  // Число, загаданное игроком. Изначально -1 для инициализации переменной.

        //Генерируем случайное число в интервале 0-100
        int minValue = 0;
        int maxValue = 100;
        int compValue = (int)(Math.random()*(maxValue-minValue+1)+minValue);
        System.out.println("Привет. Я твой компьютер. Давай сыграем в игру.");
        System.out.println("Я загадал целое число в интервале 0-100. Попробуй угадать, какое?");
        System.out.println("После каждой неудачной попытки я буду давать тебе подсказки.");
        System.out.println("Если по какой-то причине ты решил прекратить игру, введи -1.");
        System.out.println("--*************************************************************--");
        System.out.println(compValue);

        //Цикл угадывания.
        System.out.println("Введи целое число в интервале 0-100.");
        while(compValue!=playerValue) {
            playerValue = scan.nextInt();
            cntIterations++;
            if(playerValue == -1) {
                System.out.println("Очень жаль, что ты решил сдаться после "+cntIterations+"-й попытки.");
                break;
            }
            if(playerValue<compValue) {
                System.out.println("Твое число меньше моего. Попробуй снова.");
            }
            else if(playerValue>compValue) {
                System.out.println("Твое число больше моего. Попробуй снова.");
            }
            else{
                System.out.println("Поздравляю! Ты угадал число "+compValue+" c "+cntIterations+"-й попытки.");
            }
        }
        scan.close();
    }
}
