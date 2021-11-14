package com.pb.potapenko.hw7;

public abstract class Tshirt extends Clothes implements ManClothes, WomenClothes{

    public Tshirt(Size clothesSize, int clothesPrice, String clothesColour) {
        super(clothesSize, clothesPrice, clothesColour);
    }
}
