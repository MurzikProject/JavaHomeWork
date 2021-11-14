package com.pb.potapenko.hw7;

public abstract class Pants extends Clothes implements ManClothes, WomenClothes{

    public Pants(Size clothesSize, int clothesPrice, String clothesColour) {
        super(clothesSize, clothesPrice, clothesColour);
    }
}
