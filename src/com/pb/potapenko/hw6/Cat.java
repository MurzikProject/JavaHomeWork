package com.pb.potapenko.hw6;

import java.util.Objects;

public class Cat extends Animal{

    private String catColour = "не определен";

    /**
     * Наследуем стандартный конструктор
     */
    public Cat() {
        super("рыба","дом","мяу");
    }

    /**
     * Переопределяем конструктор если хотим уточнить окрас кота.
     * @param catColour - окрас кота
     */
    public Cat(String catColour) {
        super("рыба","дом","мяу");
        this.catColour = catColour;
    }

    /**
     * Переопределяем метод путем его расширения
     */
    @Override
    public void makeNoise() {
        super.makeNoise();
        System.out.println("A еще котик мурчит!");
    }

    /**
     * Переопределяем метод путем его расширения
     */
    @Override
    public void eat() {
        super.eat();
        System.out.println("Благодаря этой еде его " + catColour + " окрас стал еще насыщеннее.");
    }

    /**
     * Полностью переопределяем метод
     */
    @Override
    public void getInfo() {
        System.out.println("Этот милый котик цвета " + catColour
                         + ", очень уважает " + getFood() + " как еду"
                         + ", решил, что его " + getLocation() + " это наш дом"
                         + " и радует нас своим хриплым " + getNoise() + ".");
    }

    @Override
    public String toString() {
        return "Cat{" +
                "catColour='" + catColour + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return Objects.equals(catColour, cat.catColour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(catColour);
    }

    /**
     * Добаим сеттер только для вывода описания окраса кота.
     * Остальные сеттеры достались по наследству от Animal.
     */
    public String getCatColour() {
        return catColour;
    }
}
