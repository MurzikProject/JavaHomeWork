package com.pb.potapenko.hw5;

import static com.pb.potapenko.hw5.Book.getCountBooks;
import static com.pb.potapenko.hw5.Reader.getCountReaders;

public class Library {
    public static void main(String[] args) {
        Book book1 = new Book();
        Book book2 = new Book("Хребты безумия","Лавкрафт Г.В.");
        Book book3 = new Book("Приключения","Иванов И.И.",2000);
        Book book4 = new Book("Словарь","Сидоров А.В.",1980);
        Book book5 = new Book("Энциклопедия","Гусев К.В.",2010);
        Book book6 = new Book("Библия","Бог",1);

        Reader reader1 = new Reader("Петров В.В.","AB1024");
        Reader reader2 = new Reader("Потапенко А.В.","E985","эконом","21041981","103");
        Reader reader3 = new Reader("Биленко Я.Ю.","О111","юр","02021993","101");

        System.out.println("Всего в нашей библиотеке "+getCountBooks()+" книг:");
        book1.getInfo();
        book2.getInfo();
        book3.getInfo();
        book4.getInfo();
        book5.getInfo();
        book6.getInfo();
        System.out.println("--**************************************************--");
        System.out.println("Всего в нашей библиотеке зарегистрировано "+getCountReaders()+" читателей:");
        reader1.getInfo();
        reader2.getInfo();
        reader3.getInfo();

        System.out.println("--**************************************************--");
        reader1.takeBook(3);
        reader1.takeBook(book3.getBookName(), book4.getBookName(), book5.getBookName());
        reader1.takeBook(book3,book4,book5);
        reader2.takeBook(book2);
        reader3.takeBook(book6.getBookName());

        System.out.println("--**************************************************--");
        reader1.returnBook(3);
        reader1.returnBook(book3.getBookName(), book4.getBookName(), book5.getBookName());
        reader1.returnBook(book3,book4,book5);
        reader2.returnBook(book2);
        reader3.returnBook(book6);
    }
}
