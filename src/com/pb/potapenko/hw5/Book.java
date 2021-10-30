package com.pb.potapenko.hw5;

/**
 * Класс, описывающий книгу.
 * @param countBooks - Количество созданных объектов - книг.
 * @param bookName - Название книги.
 * @param bookAuthor - Автор книги.
 * @param bookYear - Год издания книги.
 */
public class Book {
    private static int countBooks = 0;

    /**
     * Метод информирования о количестве созданных объектах - книгах.
     * @return
     */
    public static int getCountBooks(){
        return countBooks;
    }

    private String bookName;
    private String bookAuthor;
    private int bookYear;

    public Book(String bookName, String bookAuthor, int bookYear) {
        this(bookName,bookAuthor);
        this.bookYear = bookYear;
    }

    public Book(String bookName, String bookAuthor){
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookYear = 1900;
        countBooks++;
    }

    public Book() {
        this.bookName = "Noname Book №"+countBooks;
        this.bookAuthor = "Noname Author №"+countBooks;
        this.bookYear = 1900;
        countBooks++;
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public int getBookYear() {
        return bookYear;
    }

    /**
     * Метод информирования о состоянии объекта - книги.
     */
    public void getInfo(){
        System.out.println("Книгу \""+bookName+"\" написал "+bookAuthor+" в "+bookYear+" году.");
    }
}
