package com.pb.potapenko.hw5;

import java.lang.StringBuilder;

/**
 * Класс, описывающий читателя.
 * @param countReaders - Количество читателей в библиотеке.
 * @param readerName - ФИО читателя.
 * @param readerBilNum - Номер читательского билета.
 * @param readerDep - Факультет читателя.
 * @param readerBirth - Дата рождения читателя.
 * @param readerPhone - Телефон читателя.
 */
public class Reader {

    public static int countReaders = 0;

    /**
     * Метод информирования о количестве созданных объектах - читателях.
     * @return
     */
    public static int getCountReaders() {
        return countReaders;
    }

    private String readerName;
    private String readerBilNum;
    private String readerDep;
    private String readerBirth;
    private String readerPhone;

    public Reader(String readerName,String readerBilNum,String readerDep,String readerBirth,String readerPhone) {
        this(readerName,readerBilNum);
        this.readerDep = readerDep;
        this.readerBirth = readerBirth;
        this.readerPhone = readerPhone;
    }

    public Reader(String readerName,String readerBilNum) {
        this.readerName = readerName;
        this.readerBilNum = readerBilNum;
        this.readerDep = "общий";
        this.readerBirth = "01012000";
        this.readerPhone = "104";
        countReaders++;
    }

    public String getReaderName() {
        return readerName;
    }

    public String getReaderBilNum() {
        return readerBilNum;
    }

    public String getReaderDep() {
        return readerDep;
    }

    public String getReaderBirth() {
        return readerBirth;
    }

    public String getReaderPhone() {
        return readerPhone;
    }

    /**
     * Метод информированияо состоянии обекта - читателя.
     */
    public void getInfo() {
        System.out.println("--**************************************************--"+'\n'
                           +"ФИО читателя: "+readerName+'\n'
                           +"№ читательского билета: "+readerBilNum+'\n'
                           +"Факультет читателя: "+readerDep+'\n'
                           +"Дата рождения читателя: "+readerBirth+'\n'
                           +"Номер телефона читателя: "+readerPhone+'\n');
    }

    /**
     * Метод информирует о количестве взятых студентом книг.
     * @param countBook - количество взятых книг
     */
    public void takeBook(int countBook) {
        System.out.println("Студент "+this.readerName+" взял "+countBook+" книги.");
    }

    /**
     * Метод информирует о наименованиях взятых студентом книг.
     * @param booksName - список наименований книг
     */
    public void takeBook(String... booksName) {
        StringBuilder strBldr = new StringBuilder();
        strBldr.append("Студент "+this.readerName+" взял книги: ");
        for(String bookname : booksName) {
            strBldr.append(bookname);
            strBldr.append(", ");
        }
        String txt = strBldr.substring(0,strBldr.length()-2);
        System.out.println(txt+".");
    }

    /**
     * Метод информирует о метаданных взятых студентом книг.
     * @param books - список объектов-книг
     */
    public void takeBook(Book... books) {
        StringBuilder strBldr = new StringBuilder();
        strBldr.append("Студент "+this.readerName+" взял книги: ");
        for(Book bookObject : books) {
            strBldr.append(bookObject.getBookName());
            strBldr.append(" (");
            strBldr.append(bookObject.getBookAuthor());
            strBldr.append(" ");
            strBldr.append(bookObject.getBookYear());
            strBldr.append(" г.), ");
        }
        String txt = strBldr.substring(0,strBldr.length()-2);
        System.out.println(txt+".");
    }

    /**
     * Метод информирует о количестве возвращенных студентом книг.
     * @param countBook - количество возвращенных книг
     */
    public void returnBook(int countBook) {
        System.out.println("Студент "+this.readerName+" вернул "+countBook+" книг.");
    }

    /**
     * Метод информирует о наименованиях возвращенных студентом книг.
     * @param booksName - список наименований книг
     */
    public void returnBook(String... booksName) {
        StringBuilder strBldr = new StringBuilder();
        strBldr.append("Студент "+this.readerName+" вернул книги: ");
        for(String bookname : booksName) {
            strBldr.append(bookname);
            strBldr.append(", ");
        }
        String txt = strBldr.substring(0,strBldr.length()-2);
        System.out.println(txt+".");
    }

    /**
     * Метод информирует о метаданных возвращенных студентом книг.
     * @param books список объектов-книг
     */
    public void returnBook(Book... books) {
        StringBuilder strBldr = new StringBuilder();
        strBldr.append("Студент "+this.readerName+" вернул книги: ");
        for(Book bookObject : books) {
            strBldr.append(bookObject.getBookName());
            strBldr.append(" (");
            strBldr.append(bookObject.getBookAuthor());
            strBldr.append(" ");
            strBldr.append(bookObject.getBookYear());
            strBldr.append(" г.), ");
        }
        String txt = strBldr.substring(0,strBldr.length()-2);
        System.out.println(txt+".");
    }

}
