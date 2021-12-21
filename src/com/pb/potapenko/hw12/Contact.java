package com.pb.potapenko.hw12;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Contact {

    /**
     * Функциональный интерфейс для реализации lambda - функций
     */
    @FunctionalInterface
    interface Sayable{
        void say();
    }

    /**
     * Метод информирует о создании контакта
     */
    public void contactCreateInform(){
        System.out.println("Контакт с именем " + contactFIO + " создан.");
    }

    /**
     * Метод предоставляет полнаю информацию о контакте
     */
    public void getContactInfo() {
        System.out.println("ФИО контакта: "+getContactFIO());
        System.out.println("Дата рождения: "+getContactBDAY());
        System.out.print("Телефоны контакта: ");
        for(String phones : getContactPHONES()) {
            System.out.print(phones+", ");
        }
        System.out.println();
        System.out.println("Адрес: "+getContactADRESS());
        System.out.println("Последняя модификация данных: "+getContactMODIFYDT());
        System.out.println("-----------------------------------------");
    }

    private static int countContacts = 0; // счетчик созданных контактов

    private String contactFIO; // имя контакта
    private LocalDate contactBDAY; // дата рождения контакта
    private String[] contactPHONES; // массив телефонов контакта
    private String contactADRESS; // адрес контакта
    private LocalDateTime contactMODIFYDT; // датавремя создания/модификации контакта

    public Contact(String contactFIO, LocalDate contactBDAY, String[] contactPHONES, String contactADRESS, LocalDateTime contactMODIFYDT){
        this.contactFIO = contactFIO;
        this.contactBDAY = contactBDAY;
        this.contactPHONES = contactPHONES;
        this.contactADRESS = contactADRESS;
        this.contactMODIFYDT = contactMODIFYDT;
        countContacts++;
    }

    public String getContactFIO() {
        return contactFIO;
    }

    public LocalDate getContactBDAY() {
        return contactBDAY;
    }

    public String[] getContactPHONES() {
        return contactPHONES;
    }

    public String getContactADRESS() {
        return contactADRESS;
    }

    public LocalDateTime getContactMODIFYDT() {
        return contactMODIFYDT;
    }

    public void setContactFIO(String contactFIO) {
        this.contactFIO = contactFIO;
    }

    public void setContactBDAY(LocalDate contactBDAY) {
        this.contactBDAY = contactBDAY;
    }

    public void setContactMODIFYDT(LocalDateTime contactMODIFYDT) {
        this.contactMODIFYDT = contactMODIFYDT;
    }

    public static int getCountContacts(){
        return countContacts;
    }
}
