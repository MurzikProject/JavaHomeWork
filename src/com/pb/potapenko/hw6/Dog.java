package com.pb.potapenko.hw6;

import java.util.Objects;

public class Dog extends Animal{

    private boolean isHunterDog = false;
    private String dogCharacter = "нордический";

    /**
     * Наследуем стандартный конструктор
     */
    public Dog() {
        super("мясо","двор","гав");
    }

    /**
     * Переопределяем конструктор если хотим четко указать собака охотничья или нет, а также ее характер.
     * @param isHunterDog - является собака охотничьей или нет
     * @param dogCharacter - описание характера собаки
     */
    public Dog(boolean isHunterDog, String dogCharacter) {
        super("мясо","двор","гав");
        this.isHunterDog = isHunterDog;
        this.dogCharacter = dogCharacter;
    }

    /**
     * Переопределяем метод путем его расширения
     */
    @Override
    public void makeNoise() {
        super.makeNoise();
        System.out.println("А еще наша собака виляет хвостом когда гавкает.");
    }

    /**
     * Полностью переопределяем метод
     */
    @Override
    public void eat() {
        System.out.println("Ты же знаешь характер этой собаки, он - " + dogCharacter +
                "Поделись еще едой немедленно!.");
    }

    /**
     * Полностью переопределяем метод
     */
    @Override
    public void getInfo() {
        String hunterDog = "";
        if(isHunterDog) {
            hunterDog = hunterDog + "охотничья";
        }
        else {
            hunterDog = hunterDog + "не охотничья";
        }
        System.out.println("Эта " + hunterDog + " собака любит кушать " + getFood()
                         + ", живет у нас во " + getLocation()
                         + ", издает звонкий " + getNoise() + "-" + getNoise()
                         + " и вообще имеет " + getDogCharacter() + " характер.");
    }

    @Override
    public String toString() {
        return "Dog{" +
                "isHunterDog=" + isHunterDog +
                ", dogCharacter='" + dogCharacter + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dog dog = (Dog) o;
        return isHunterDog == dog.isHunterDog && Objects.equals(dogCharacter, dog.dogCharacter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isHunterDog, dogCharacter);
    }

    /**
     * Добавим только сеттер для информации о состоянии isHunterDog и характере собаки.
     * Остальные сеттеры достались по наследству от Animal.
     */
    public boolean getIsHunterDog() {
        return isHunterDog;
    }

    public String getDogCharacter() {
        return dogCharacter;
    }
}
