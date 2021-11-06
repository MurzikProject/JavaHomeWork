package com.pb.potapenko.hw6;

public class Animal {

    private String food;
    private String location;
    private String noise;

    /**
     * Полный конструктор
     * @param food - что животное ест.
     * @param location - где обитает животное.
     * @param noise - какие звуки животное издает.
     */
    public Animal(String food, String location, String noise) {
        this.food = food;
        this.location = location;
        this.noise = noise;
    }

    /**
     * Метод выводит звук конкретного животного.
     */
    public void makeNoise() {
        System.out.println("Животное издает такие звуки: " + noise + ".");
    }

    /**
     * Метод оповещает о том, что животное ест.
     */
    public void eat() {
        System.out.println("Животное ест " + food + ".");
    }

    /**
     * Метод оповещает о том, что животное спит.
     */
    public void sleep(){
        System.out.println("Животное спит.");
    }

    /**
     * Выводим комплексную информацию о животном
     */
    public void getInfo() {
        System.out.println("Любит есть " + getFood()
                         + ", живет в " + getLocation()
                         + ", издает звуки " + getNoise()
                         + ".");
    }

    /**
     * Сеттеры создать всегда правильно, информация о состоянии объекта нужна.
     * А вот геттеры мне не нравятся: состояния должны изменяться только методами.
     */

    public String getFood() {
        return food;
    }

    public String getLocation() {
        return location;
    }

    public String getNoise() {
        return noise;
    }
}