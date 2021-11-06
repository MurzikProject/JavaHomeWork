package com.pb.potapenko.hw6;

import java.util.Objects;

public class Horse extends Animal{
    private boolean isShod = false;

    /**
     * Наследуем стандартный конструктор
     */
    public Horse() {
        super("трава","луг","иго-го");
    }

    /**
     * Переопределяем конструктор если хотим четко указать подкована лошадь или нет.
     * @param isShod является ли лошадь подкована.
     */
    public Horse(boolean isShod) {
        super("трава","луг","иго-го");
        this.isShod = isShod;

    }

    /**
     * Переопределяем метод путем его расширения
     */
    @Override
    public void makeNoise() {
        super.makeNoise();
        System.out.println("А еще копытом лязгну!");
    }

    /**
     * Полностью переопределяем метод
     */
    @Override
    public void eat() {
        System.out.println("Дай еще овса!");
    }

    /**
     * Полностью переопределяем метод
     */
    @Override
    public void getInfo() {
        String shod = "";
        if(isShod) {
            shod = shod + "подкованый на все 4 копыта";
        }
        else {
            shod = shod + "не подкованый";
        }
        System.out.println("Красивый " + shod + " конь"
                         + ", пасется на " + getLocation()
                         + ", ест только свежую " + getFood()
                         + " и высказывает свое мнение с помощью " + getNoise() + ".");
    }

    @Override
    public String toString() {
        return "Horse{" +
                "isShod=" + isShod +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Horse horse = (Horse) o;
        return isShod == horse.isShod;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isShod);
    }

    /**
     * Добаим сеттер только для вывода информации о том, подкована лошадь или нет.
     * Остальные сеттеры достались по наследству от Animal.
     */
    public boolean getIsShod() {
        return isShod;
    }
}
