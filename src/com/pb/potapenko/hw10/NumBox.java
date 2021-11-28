package com.pb.potapenko.hw10;

import java.util.Arrays;

public class NumBox<T extends Number> {
    private final T[] numbers;

    public NumBox(int size) {
        numbers = (T[]) new Number[size];
    }

    /**
     * Метод добавляет объект в массив. В случае если массив полон - выбросить исключение
     * @param num - новый объект
     */
    void add(T num) {
        int index = 0;

        // Вычисляем ближайший от начала незаполненный индекс
        for(Number nums : numbers) {
            if(nums == null) {
                index = index;
            }
            else {
                index++;
            }
        }

        // Проверяем заполнен ли массив уже полностью. Если нет, то добавляем новый объект.
        try {
            get(index);
            this.numbers[index] = num;
            System.out.println("Элемент с индексом "+index+" успешно добавлен в массив.");
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Массив заполнен полностью! Добавить новый объект с индексом "+e.getMessage()+" невозможно.");
        }
    }

    /**
     * Метод возвращает число по индексу
     * @param index - индекс элемента в массиве
     * @return
     */
    T get(int index) {
        return numbers[index];
    }

    /**
     * Метод возвращает текущее количество элементов в массиве
     * @return
     */
    int length() {
        int iNotEmpty = 0;
        for(Number nums : numbers) {
            if(nums != null) {
                iNotEmpty++;
            }
        }
        return iNotEmpty;
    }

    /**
     * Метод возвращает среднее арифметическое среди элементов массива
     * @return
     */
    double average() {
        double dSum = 0D;
        int iCount = 0;
        for(Number nums : numbers) {
            if(nums != null) {
                dSum += nums.doubleValue();
                iCount++;
            }
        }
        return dSum/iCount;
    }

    /**
     * Метод возвращает сумму всех элементов массива
     * @return
     */
    double sum() {
        double dSum = 0D;
        for(Number nums : numbers) {
            if(nums != null) {
                dSum += nums.doubleValue();
            }
        }
        return dSum;
    }

    /**
     * Метод возвращает максимальный элемент массива
     * @return
     */
    T max() {
        T tMax = null;
        double dMax = 0D;
        for(Number nums : numbers) {
            if(nums != null) {
                if(dMax < nums.doubleValue()) {
                    dMax = nums.doubleValue();
                    tMax = (T) nums;
                }
            }
        }
        return tMax;
    }

}
