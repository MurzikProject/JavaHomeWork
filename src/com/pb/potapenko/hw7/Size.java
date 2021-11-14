package com.pb.potapenko.hw7;

public enum Size {
    XXS("Детский размер",32),
    XS("Взрослый размер",34),
    S("Взрослый размер",36),
    M("Взрослый размер",38),
    L("Взрослый размер",40);

    private String description;
    private int euroSize;

    /**
     * Полный конструктор создания перечисления.
     * @param description - описание размера.
     * @param euroSize - численная характеристика размера.
     */
    Size(String description, int euroSize) {
        this.description = description;
        this.euroSize = euroSize;
    }

    /**
     * Метод, возвращающий описание размера.
     */
    public void getDescription(){
        System.out.println("Описание размера - " + description + ".");
    }

    /**
     * Метод, возвращающий числовую характеристику размера.
     */
    public void getEuroSize() {
        System.out.println("Европейский размер - " + euroSize + ".");
    }

}
