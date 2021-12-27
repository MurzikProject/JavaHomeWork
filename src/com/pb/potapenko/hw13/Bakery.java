package com.pb.potapenko.hw13;

import java.util.ArrayList;

/**
 * В маленькой пекарне есть только один лоток (tray) на traySize булочек, куда пекарь выкладывает булки по номеру номенклатуры от 1 до 10.
 * Пекарь выпекает булочки в количестве не более, чем количество свободных мест в лотке. Каждая операция добавляет минимум 1 булку в лоток.
 * Покупатели покупают булочки в количестве не более, чем количество булочек в лотке. Каждая операция забирает из лотка минимум 1 булку.
 */
public class Bakery {

    /**
     * Метод генерирует случайное число в интервале (1,maxNumber)
     * @param maxNumber - верхний предел случайного числа
     * @return num - возвращаемое значение
     */
    private static int getNum(int maxNumber) {
        int minValue = 1;
        int maxValue = maxNumber;
        int num = (int) (Math.random()*(maxValue-minValue+1)+minValue);
        return num;
    }

    /**
     * Метод вычисляет количество пустых элементов в лотке
     * @param arrList - коллекция - лоток с булками
     * @param size - максимальное количество мест в лотке
     * @return iNullElements - количество пустых элементов в лотке
     */
    private static int getNullElements(ArrayList arrList, int size) {
        int iNullElements = 0; // Количество пустых элементов в лотке
        int iNotNullElements = 0; // Колтичество непустых элементов в лотке
        if(!arrList.isEmpty()) {
            for(Object buns : arrList) {
                iNotNullElements++;
            }
            iNullElements = size-iNotNullElements;
        }
        else {
            iNullElements = size;
        }
        return iNullElements;
    }

    /**
     * Метод возвращает текущее количество элементов - булочек в лотке
     * @param arrList - коллекция - лоток с булками
     * @param size - максимальное количество мест в лотке
     * @return
     */
    private static int getBunsCount(ArrayList arrList, int size) {
        return (size - getNullElements(arrList, size));
    }

    /**
     * Метод информирует о текущем количестве булок в лотке
     * @param arrList - коллекция - лоток с булками
     * @param size - максимальное количество мест в лотке
     */
    private static void infoBunsCount(ArrayList arrList, int size) {
        System.out.println("------------------------------------------------------");
        System.out.println("Текущее количество булок в лотке - " + getBunsCount(arrList, size));
        System.out.println("------------------------------------------------------");
    }

    /**
     * Метод заполняем лоток рандомным количеством булок, но не более пустого доступного количества
     * @param arrList - коллекция - лоток с булками
     * @param size - максимальное количество мест в лотке
     */
    private static synchronized void setBuns(ArrayList arrList, int size) {
        int notNullSize = getNullElements(arrList,size); // максимальное количество булок, которое можно положить в лоток при текущем состоянии
        if(notNullSize>0) {
            int bunsCnt = getNum(notNullSize);
            for(int i = 0;i<bunsCnt;i++) {
                int bunNum = getNum(10);
                arrList.add(bunNum);
                System.out.println("Положил в лоток булку № "+bunNum);
            }
            infoBunsCount(arrList, size);
        }
        else {
            System.out.println("Лоток заполнен. Жду, пока покупатели купят немного булок.");
        }
    }

    /**
     * Метод забирает булки из лотка в произвольном количестве, но не более доступного количества.
     * @param arrList - коллекция - лоток с булками
     * @param size - максимальное количество мест в лотке
     */
    private static synchronized void getBuns(ArrayList arrList, int size) {
        int notNullSize = size - getNullElements(arrList,size); // максимальное количество булок, которое можно забрать из лотка при текущем состоянии
        if(notNullSize>0) {
            int bunsCnt = getNum(notNullSize); // рандомное число булок, которое хотим забрать из лотка, но не больше notNullSize

            // Создаем массив булок, которые хотим забрать из лотка
            int[] miniTray = new int[bunsCnt];
            for(int i = 0;i<miniTray.length;i++) {
                miniTray[i] = (Integer) arrList.get(i);
            }

            // Забираем булки из лотка
            for(int i : miniTray) {
                System.out.println("Забрал из лотка булку № " + i);
                arrList.remove(new Integer(i));
            }
            infoBunsCount(arrList, size);
        }
        else {
            System.out.println("Лоток пустой. Жду, пока пекарь выложит булки на лоток.");
        }
    }

    public static void main(String[] args) throws IllegalThreadStateException{
        int traySize = 5; // Максимальное количество мест в лотке
        ArrayList<Integer> tray = new ArrayList<Integer>(traySize); // лоток - коллекция с булками
        int stepCount = 0;

        class ProducerThread extends Thread {

            private boolean isActive;

            ProducerThread() {
                isActive = true;
            }

            void disable() {
                isActive = false;
            }

            @Override
            public void run() {
                while(isActive) {
                    try {
                        setBuns(tray, traySize);
                        ProducerThread.sleep(2000);
                    }
                    catch (IllegalThreadStateException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

        class ConsumerThread extends Thread {

            private boolean isActive;

            ConsumerThread() {
                isActive = true;
            }

            void disable(){
                isActive = false;
            }

            @Override
            public void run() {
                while (isActive) {
                    try {
                        getBuns(tray,traySize);
                        ConsumerThread.sleep(2000);
                    }
                    catch (IllegalThreadStateException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        ProducerThread producer = new ProducerThread();
        ConsumerThread consumer = new ConsumerThread();

        try {
            producer.start();
            consumer.start();
            stepCount++;

            if(getBunsCount(tray, traySize)==0) {
                System.out.println("Лоток пустой. Жду, пока пекарь выложит булки на лоток.");
                producer.join();
            }

            if(getBunsCount(tray, traySize)==traySize) {
                System.out.println("Лоток заполнен. Жду, пока покупатели купят немного булок.");
                consumer.join();
            }

            if(stepCount>10) {
                producer.disable();
                consumer.disable();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
