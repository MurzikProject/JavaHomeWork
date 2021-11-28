package com.pb.potapenko.hw10;

public class Main {

    /**
     * Метод демонстрирует работу методов генерика NumBox
     * @param box - ссылка на генерик NumBox
     * @param index - номер необходимого индекса элемента для метода get
     */
    private static void getInfo(NumBox box, int index) {
        System.out.println("Значение элемента с индексом " + index + " = "+box.get(index));
        System.out.println("Количество элементов в массиве = "+box.length());
        System.out.println("Cреднее арифметическое среди элементов массива = "+box.average());
        System.out.println("Cумма всех элементов массива = "+box.sum());
        System.out.println("Mаксимальный элемент массива = "+box.max());
        System.out.println("---------------------------------------------------------------");
    }

    public static void main(String[] args) {
        // Создадим массив отбъектов типа Integer и проверим работу всех его методов
        NumBox<Integer> myInt = new NumBox<>(4);
        myInt.add(21);
        myInt.add(3);
        myInt.add(43);
        myInt.add(5);
        myInt.add(54);
        myInt.add(101);
        getInfo(myInt,3);

        // Создадим массив объектов типа Float и проверим работу всех его методов
        NumBox<Float> myFloat = new NumBox<>(5);
        myFloat.add(13.4F);
        myFloat.add(21F);
        myFloat.add(0.001F);
        getInfo(myFloat,1);
    }
}
