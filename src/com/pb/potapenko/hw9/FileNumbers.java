package com.pb.potapenko.hw9;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

public class FileNumbers {

    /**
     * Метод создает массив случайных чисел и записывает их в файл "numbers.txt"
     * @param catPath - путь к каталогу, где будут лежать файлы с данными
     * @param srcFileName - имя файла с источником данных
     * @param elemCount - количество элементов, которое нужно записать в файл
     * @param countInRow - количество элементов в одной строке
     */
    private static void createNumbersFile(String catPath, String srcFileName, int elemCount, int countInRow) {
        // создадим массив размерностью elemCount случайных элементов от 1 до 99
        int myArr[] = new int[elemCount];
        for(int i=0;i<elemCount;i++) {
            int minValue = 0;
            int maxValue = 99;
            myArr[i] = (int)(Math.random()*(maxValue-minValue+1)+minValue);
        }

        // запишем данные массива в файл "numbers.txt"
        try (BufferedWriter myWriter = Files.newBufferedWriter(Paths.get(catPath + srcFileName))) {
            String delimiter = " "; // разделитель - пробел

            for(int j=0;j<elemCount;j+=countInRow) {
                for(int i=j;i<j+countInRow;i++) {
                    if (i<elemCount) {
                        myWriter.write(myArr[i]+delimiter);
                    }
                }
                myWriter.newLine();
            }
            myWriter.flush();
            System.out.println("createNumbersFile: Данные успешно сгенерированы и записаны в файл!");
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error with file write: " + e);
        }
    }

    /**
     * Метод считывает массив чисел из файла "numbers.txt", заменяет четные числа 0 и записывает измененный массив в файл "odd-numbers.txt"
     * @param catPath - путь к каталогу, где будут лежать файлы с данными
     * @param srcFileName - имя файла с источником данных
     * @param destFileName - имя файла с измененными данными
     * @param elemCount - количество элементов, которое нужно записать в файл
     * @param countInRow - количество элементов в одной строке
     */
    private static void createOddNumbersFile(String catPath, String srcFileName, String destFileName, int elemCount, int countInRow) {
        // Считаем данные из файла и запишем их в массив
        List<Integer> myList = new ArrayList<Integer>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(catPath + srcFileName))) {
            String line;
            while((line = reader.readLine()) != null) {
                String[] array = line.split(" ");
                for(int i=0;i< array.length;i++) {
                    myList.add(Integer.parseInt(array[i]));
                }
            }
        }
        catch (Exception e) {
            System.out.println("Error with file read: " + e);
        }
        int[] myArr = myList.stream().mapToInt(Integer::intValue).toArray();

        // Заменим все четные числа на 0
        for(int i=0;i<myArr.length;i++) {
            if(myArr[i]%2==0) {
                myArr[i] = 0;
            }
            else {
                myArr[i] = myArr[i];
            }
        }

        // запишем данные измененного массива в файл "odd-numbers.txt"
        try (BufferedWriter myWriter = Files.newBufferedWriter(Paths.get(catPath + destFileName))) {
            String delimiter = " "; // разделитель - пробел

            for(int j=0;j<elemCount;j+=countInRow) {
                for(int i=j;i<j+countInRow;i++) {
                    if (i<elemCount) {
                        myWriter.write(myArr[i]+delimiter);
                    }
                }
                myWriter.newLine();
            }
            myWriter.flush();
            System.out.println("createOddNumbersFile: Данные успешно считаны, изменены и записаны в файл!");
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error with file write: " + e);
        }
    }

    public static void main(String[] args) {
        String catPath = "/home/varvara/IdeaProjects/JavaHomeWork/src/com/pb/potapenko/hw9/"; // каталог
        String srcFileName = "numbers.txt"; // имя файла с первичными данными
        String destFileName = "odd-numbers.txt"; // имя файла с измененными данными
        int elemCount = 100; // количество элементов для записи
        int countInRow = 10; // количество элементов в строке

        // Сгенерируем данные и запишем их в файл
        createNumbersFile(catPath,srcFileName,elemCount,countInRow);

        // Считаем данные из файла, изменим четные числа и запишем в другой файл
        createOddNumbersFile(catPath,srcFileName,destFileName,elemCount,countInRow);
    }
}
