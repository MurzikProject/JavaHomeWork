package com.pb.potapenko.hw3;

import java.util.Scanner;

public class Array {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] myArr = new int[10];
        int myArrSum = 0;      //Сумма всех элементов массива
        int myArrPosCnt = 0;   //Количество положительных элементов массива

        System.out.println("НЕОБХОДИМО ВВОДИТЬ ТОЛЬКО ЦЕЛЫЕ ЧИСЛА!");

        // Цикл ввода всех элементов массива
        for(int i=0;i<myArr.length;i++) {
            System.out.println("Введите "+(i+1)+"-й элемент массива.");
            myArr[i] = scan.nextInt();
        }
        scan.close();

        //Цикл вывода в строку всех элементов массива
        System.out.println("Вы ввели следующие элементы массива:");
        for(int i=0;i<myArr.length;i++) {
            System.out.print(""+myArr[i]+" ");
        }
        System.out.println("");

        //Цикл подсчета суммы всех элементов массива
        System.out.println("Сумма всех элементов массива равна:");
        for(int i=0;i<myArr.length;i++) {
            myArrSum = myArrSum + myArr[i];
        }
        System.out.println(myArrSum);

        //Цикл подсчета всех положительных элементов массива
        System.out.println("Количество положительных (>0) элементов массива равно: ");
        for(int i=0;i<myArr.length;i++) {
            if(myArr[i]>0){
                myArrPosCnt++;
            }
        }
        System.out.println(myArrPosCnt);

        //Цикл пузырьковой сортировки от меньшего к большему
        for(int j=0;j<myArr.length;j++) {
            for(int i=0;i<myArr.length-1;i++){
                if(myArr[i]>myArr[i+1]) {
                    int buffer = myArr[i+1];
                    myArr[i+1] = myArr[i];
                    myArr[i] = buffer;
                }
            }
        }
        System.out.println("Упорядоченный массив:");
        for(int i=0;i<myArr.length;i++) {
            System.out.print(""+myArr[i]+" ");
        }
    }
}
