package com.pb.potapenko.hw4;

import java.util.Arrays;
import java.util.Scanner;

public class Anagram {
    //Использую банальный подход: сравнение упорядоченных массивов
    //Можно было решить через количество вхождений каждой литеры и HashMap, но мы это еще не изучали
    
    //Метод ввода фраз
    private static String inputText(int isAnagram) {
        switch(isAnagram){
            case(0): System.out.println("Введите свою первоначальную слово(фразу).");
            break;
            case(1): System.out.println("Введите анаграмму.");
            break;
        }
        Scanner scan = new Scanner(System.in);
        //для дальнейшего сравнения сводим все символы в верхний регистр
        String txtFromUser = scan.nextLine().toUpperCase();

        return txtFromUser;
    }

    //Метод упорядочивания элементов массива по алфавиту и поэлементного сравнения
    private static String compareArrays(String original, String anagram){
        String txtMessage = "";

        char[] originalChars = original.toCharArray();
        char[] anagramChars = anagram.toCharArray();
        Arrays.sort(originalChars);
        Arrays.sort(anagramChars);

        if(Arrays.equals(originalChars,anagramChars)==true){
            txtMessage = txtMessage+"Поздравляю, Вам удалось сделать анаграмму!";
        }
        else{
            txtMessage = txtMessage+"Увы, Ваши строки не являются анаграммами!";
        }

        return txtMessage;
    }

    //Метод проверки анаграммы
    private static void checkAnagram(String original, String anagram){
        String txtMessage = "";

        if(original.equals(anagram)){
            txtMessage = txtMessage+"Вы ввели идентичные фразы. Это не анаграмма!";
        }
        else{
            original = original.replaceAll("[ ,.?!:;-]","");
            anagram = anagram.replaceAll("[ ,.?!:;-]","");

            txtMessage = txtMessage+compareArrays(original,anagram);
        }
        System.out.println(txtMessage);
    }

    public static void main(String[] args) {
        String originalText = inputText(0);
        String anagramText = inputText(1);

        checkAnagram(originalText,anagramText);
    }
}
