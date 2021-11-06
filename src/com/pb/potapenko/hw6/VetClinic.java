package com.pb.potapenko.hw6;

import java.lang.ClassNotFoundException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class VetClinic {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        int encounter = 1;

        Dog myDog1 = new Dog();
        Dog myDog2 = new Dog(true,"добрый");
        Cat myCat1 = new Cat();
        Cat myCat2 = new Cat("рыжий");
        Horse myHorse1 = new Horse(true);
        Horse myHorse2 = new Horse();
        Animal tRex = new Animal("человеки","парк юрского периода","рррррррррррр");

        Animal[] myAnimals = new Animal[] {myDog1,myDog2,myCat1,myCat2,myHorse1,myHorse2,tRex};

        // Выведем общую информацию об этом дивном питомнике.
        System.out.println("Позвольте мне рассказать об этом дивном питомнике: ");
        for(Animal anims : myAnimals) {
            System.out.print(encounter + ". ");
            anims.getInfo();
            encounter++;
        }

        // Проверим состояния некоторых животных.
        System.out.println("------------------------------------");
        System.out.println("myDog1");
        myDog1.makeNoise();
        System.out.println("------------------------------------");
        System.out.println("myCat2");
        myCat2.eat();
        System.out.println("------------------------------------");
        System.out.println("myHorse1");
        myHorse1.sleep();
        System.out.println("------------------------------------");

        // Создадим объект myVeterinarian класса Veterinarian с помощью рефлексии
        Class myVetClass = Class.forName("com.pb.potapenko.hw6.Veterinarian");
        Object myVeterinarian = myVetClass.newInstance();
        if(myVeterinarian instanceof Veterinarian) {
            System.out.println("Объект myVeterinarian создан.");
            for (Animal anims : myAnimals) {
                ((Veterinarian) myVeterinarian).treatAnimal(anims);
            }
        }
        else {
            System.out.println("Создать объект myVeterinarian не удалось.");
        }
    }
}
