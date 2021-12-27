package com.pb.potapenko.hw13;

import java.util.ArrayList;

/**
 * В маленькой пекарне есть только один лоток (tray) на traySize булочек, куда выкладываются булки по номеру номенклатуры от 1 до 10
 * Пекарь выпекает булочки
 * Покупатели покупают булочки
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
     * Метод информирует о текущем количестве булок в лотке
     * @param arrList - коллекция - лоток с булками
     * @param size - максимальное количество мест в лотке
     */
    private static void infoBunsCount(ArrayList arrList, int size) {
        System.out.println("------------------------------------------------------");
        System.out.println("Текущее количество булок в лотке - " + (size - getNullElements(arrList, size)));
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
                System.out.println("Положили в лоток булку № "+bunNum);
            }
            System.out.println("Всего положили в лоток "+bunsCnt+" булок.");
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
            int bunsCnt = getNum(notNullSize); // рандомное число буок, которое хотим забрать из лотка, но не больше notNullSize
            //System.out.println("Всего хотим забрать "+bunsCnt+" булок.");

            // Создаем массив булок, которые хотим забрать из лотка
            int[] miniTray = new int[bunsCnt];
            for(int i = 0;i<miniTray.length;i++) {
                miniTray[i] = (Integer) arrList.get(i);
            }

            // Забираем булки из лотка
            for(int i : miniTray) {
                System.out.println("Забрали из лотка булку № " + i);
                arrList.remove(new Integer(i));
            }
            System.out.println("Всего забрали из лотка "+bunsCnt+" булок.");
        }
        else {
            System.out.println("Лоток пустой. Жду, пока пекарь выложит булки на лоток.");
        }
    }

    public static void main(String[] args) throws IllegalThreadStateException{
        int traySize = 5; // Максимальное количество мест в лотке
        ArrayList<Integer> tray = new ArrayList<Integer>(traySize); // лоток - коллекция с булками

        class ProducerThread extends Thread {

            private boolean isActive;
            void disable(){
                isActive=false;
            }

            ProducerThread() {
                isActive=true;
            }

            @Override
            public void run() {
                while(isActive) {
                    try {
                        infoBunsCount(tray,traySize);
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
            void disable(){
                isActive=false;
            }

            ConsumerThread() {
                isActive=true;
            }

            @Override
            public void run() {
                while (isActive) {
                    try {
                        infoBunsCount(tray,traySize);
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
            producer.join();
            consumer.join();
            producer.disable();
            consumer.disable();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //producer.disable();
        //consumer.start();
        //consumer.disable();
    }
}
