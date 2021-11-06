package com.pb.potapenko.hw6;

public class Veterinarian {

    public Veterinarian() {

    }

    public void treatAnimal(Animal animal) {
        System.out.println("Это животное ест " + animal.getFood() + " и живет в " + animal.getLocation() + ".");
    }
}
