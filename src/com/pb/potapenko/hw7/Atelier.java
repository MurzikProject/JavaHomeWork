package com.pb.potapenko.hw7;

public class Atelier {

    /**
     * Метод выводит общую информацию об элементие одежды.
     * @param clothesSize - размер одежды.
     * @param clothesColour - цвет одежды.
     * @param clothesPrice - цена одежды.
     */
    private static void getClothesInfo(Size clothesSize, String clothesColour, int clothesPrice) {
        System.out.println("Размер производителя - " + clothesSize + ".");
        System.out.println("Цвет - " + clothesColour + ".");
        System.out.println("Цена - " + clothesPrice + ".");
        System.out.println("--------------------------------------------");
    }

    /**
     * Метод выводит всю информацию о мужской одежде.
     * @param clothes - массив объектов - элементов одежды.
     */
    private static void dressMan(Clothes[] clothes) {
        System.out.println("Ассортимент мужской одежды: ");
        for(Clothes clo:clothes){
            if(clo instanceof ManClothes){
                ((ManClothes) clo).dressMan();
                getClothesInfo(clo.getClothesSize(),clo.getClothesColour(),clo.getClothesPrice());
            }
        }
    }

    /**
     * Метод выводит всю информацию о женской одежде.
     * @param clothes - массив объектов - элементов одежды.
     */
    private static void dressWomen(Clothes[] clothes) {
        System.out.println("Ассортимент женской одежды: ");
        for(Clothes clo:clothes){
            if(clo instanceof WomenClothes) {
                ((WomenClothes) clo).dressWomen();
                getClothesInfo(clo.getClothesSize(),clo.getClothesColour(),clo.getClothesPrice());
            }
        }
    }

    public static void main(String[] args) {

        // Создаем объекты одежды:
        Clothes pawPatrolTshirt = new Tshirt(Size.XS,250,"Белый") {
            @Override
            public void dressMan() {
                System.out.println("Футболка из серии Щенячий патруль.");
                Size.XS.getEuroSize();
                Size.XS.getDescription();
            }

            @Override
            public void dressWomen() {
                System.out.println("Футболка из серии Щенячий патруль.");
                Size.XS.getEuroSize();
                Size.XS.getDescription();
            }
        };

        Clothes threeCatsTshirt = new Tshirt(Size.XXS,220,"Желтый") {

            @Override
            public void dressMan() {
                System.out.println("Футболка из серии Три кота.");
                Size.XXS.getEuroSize();
                Size.XXS.getDescription();
            }

            @Override
            public void dressWomen() {
                System.out.println("Футболка из серии Три кота.");
                Size.XXS.getEuroSize();
                Size.XXS.getDescription();
            }
        };

        Clothes classicPants = new Pants(Size.M,500,"Черный") {
            @Override
            public void dressMan() {
                System.out.println("Брюки классического кроя.");
                Size.M.getEuroSize();
                Size.M.getDescription();
            }

            @Override
            public void dressWomen() {
                System.out.println("Брюки женские, деловые .");
                Size.M.getEuroSize();
                Size.M.getDescription();
            }
        };

        Clothes jeansPants = new Pants(Size.M,450,"Синий") {
            @Override
            public void dressMan() {
                System.out.println("Джинсы.");
                Size.M.getEuroSize();
                Size.M.getDescription();
            }

            @Override
            public void dressWomen() {
                System.out.println("Джинсы.");
                Size.M.getEuroSize();
                Size.M.getDescription();
            }
        };

        Clothes shortSkirt = new Skirt(Size.S,385,"Фиолетовый") {
            @Override
            public void dressWomen() {
                System.out.println("Юбка короткая.");
                Size.S.getEuroSize();
                Size.S.getDescription();
            }
        };

        Clothes longSkirt = new Skirt(Size.L,615,"Бежевый") {
            @Override
            public void dressWomen() {
                System.out.println("Юбка длинная.");
                Size.L.getEuroSize();
                Size.L.getDescription();
            }
        };

        Clothes rainbowTie = new Tie(Size.S,275,"Разноцветный") {
            @Override
            public void dressMan() {
                System.out.println("Галстук клубный.");
                Size.S.getEuroSize();
                Size.S.getDescription();
            }
        };

        Clothes businessTie = new Tie(Size.S,299,"Темно-синий") {
            @Override
            public void dressMan() {
                System.out.println("Галстук деловой.");
                Size.S.getEuroSize();
                Size.S.getDescription();
            }
        };


        Clothes[] myClothes = {pawPatrolTshirt,threeCatsTshirt,classicPants,jeansPants,
                shortSkirt,longSkirt,rainbowTie,businessTie};
        dressMan(myClothes);
        dressWomen(myClothes);
    }
}
