package com.pb.potapenko.hw4;

import java.util.Scanner;
import java.util.StringTokenizer;
import java.lang.StringBuilder;

public class CapitalLetter {

    //Метод ввода строки пользователем
    private static String inputText(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите свой текст.");
        String txt = scan.nextLine();
        scan.close();

        return txt;
    }

    //Метод разбора пользовательской строки и сохранения токенов в массив
    private static String[] inputTextToArray(String inputTxt){
        StringTokenizer strTok = new StringTokenizer(inputTxt," ,.?!:;-", true);
        int cntTokens = strTok.countTokens();
        String[] tokensArray = new String[cntTokens];
        for(int x=0;x<cntTokens;x++) {
            tokensArray[x] = strTok.nextToken();
        }

        return tokensArray;
    }

    //Метод перевода первой литеры слова в верхний регистр
    private static String letterUpper(String inputWord){
        String firstLetter = inputWord.substring(0,1);
        firstLetter = firstLetter.toUpperCase();

        return firstLetter + inputWord.substring(1);
    }

    public static void main(String[] args) {
        StringBuilder strBldr = new StringBuilder();

        String clientTxt = inputText();               //Вводим клиентское занчение
        String[] myArr = inputTextToArray(clientTxt); //Разбираем значение на токены
        for(int x=0;x<myArr.length;x++) {
            strBldr.append(letterUpper(myArr[x]));    //Переводим в верхний регистр и делаем контакенацию
        }
        System.out.println(strBldr.toString());
    }
}
