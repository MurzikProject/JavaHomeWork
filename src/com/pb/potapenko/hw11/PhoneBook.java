package com.pb.potapenko.hw11;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.*;

import static com.pb.potapenko.hw11.Contact.getCountContacts;

public class PhoneBook {

    /**
     * Метод выводит меню приложения по требованию пользователя.
     */
    private static void mainMenu() {
        System.out.println("-----------------------------------------");
        System.out.println("1 - Добавить контакт вручную");
        System.out.println("21 - Удалить контакт вручную");
        System.out.println("22 - Удалить все контакты");
        System.out.println("31 - Осуществить поиск в телефонной книге по имени");
        System.out.println("32 - Осуществить поиск в телефонной книге по номеру телефона");
        System.out.println("4 - Показать телефонную книгу");
        System.out.println("41 - Показать телефонную книгу отсортированную по имени");
        System.out.println("51 - Редактировать имя контакта");
        System.out.println("52 - Редактировать дату рождения контакта");
        System.out.println("6 - Записать телефонную книгу в файл");
        System.out.println("7 - Загрузить телефонную книгу из файла");
        System.out.println("0 - Показать меню приложения");
        System.out.println("-1 - Выйти из приложения");
        System.out.println("ВЫБЕРИ НЕОБХОДИМЫЙ ПУНКТ МЕНЮ");
        System.out.println("-----------------------------------------");
    }

    /**
     * Метод добавляет новый контакт в книгу
     * @param tHashMap ссылка на телефонную книгу в формате HashMap
     */
    private static void contactInputManual(HashMap tHashMap) {
        String[] contact1Phones = {"+380662577094"};
        String[] contact2Phones = {"+380508187856"};
        String[] contact3Phones = {"+380508187804","+380508187839"};

        Contact contact1 = new Contact("Яна",Date.valueOf("1993-02-02"),
                contact1Phones,"Полтава",LocalDateTime.now());
        Contact contact2 = new Contact("Варвара",Date.valueOf("2017-11-09"),
                contact2Phones,"Днепр",LocalDateTime.now());
        Contact contact3 = new Contact("Антон",Date.valueOf("1981-04-21"),
                contact3Phones,"Симферополь", LocalDateTime.now());

        tHashMap.put("contact1",contact1);
        tHashMap.put("contact2",contact2);
        tHashMap.put("contact3",contact3);

        System.out.println("Добавлено контактов - "+getCountContacts());
    }

    /**
     * Метод удаляет контакт из телефонной книги
     * @param tHashMap ссылка на телефонную книгу в формате HashMap
     * @param contactKey ключ конкретного контакта
     */
    private static void contactDeleteManual(HashMap tHashMap, String contactKey) {
        tHashMap.remove(contactKey);
        System.out.println("Контакт удален.");
    }

    /**
     * Метод удаляет все контакты
     * @param tHashMap ссылка на телефонную книгу в формате HashMap
     */
    private static void contactDeleteAll(HashMap tHashMap) {
        for(Object key : tHashMap.keySet()) {
            tHashMap.remove(key);
        }
        System.out.println("Телефонная книга полностью очищена.");
    }

    /**
     * Метод осуществляет поиск контакта по имени
     * @param tHashMap ссылка на телефонную книгу в формате HashMap
     * @param contactName имя контакта
     */
    private static void contactSearchName(HashMap tHashMap, String contactName){
        int isHasName = 0;
        for(Object key : tHashMap.keySet()) {
            Contact commonContact = (Contact) tHashMap.get(key);
            if(commonContact.getContactFIO().equals(contactName)) {
                System.out.println("Контакт найден.");
                System.out.println("ФИО контакта: "+commonContact.getContactFIO());
                System.out.println("Дата рождения: "+commonContact.getContactBDAY());
                System.out.print("Телефоны контакта: ");
                for(String phones : commonContact.getContactPHONES()) {
                    System.out.print(phones+", ");
                }
                System.out.println();
                System.out.println("Адрес: "+commonContact.getContactADRESS());
                System.out.println("Последняя модификация данных: "+commonContact.getContactMODIFYDT());
                System.out.println("-----------------------------------------");
                isHasName++;
            }
        }
        if(isHasName==0){
            System.out.println("Контакт с именем "+contactName+" не найден!");
        }
    }

    /**
     * Метод осуществляет поиск контакта по номеру телефона
     * @param tHashMap ссылка на телефонную книгу в формате HashMap
     * @param contactPhone телефон контакта
     */
    private static void contactSearchPhone(HashMap tHashMap, String contactPhone) {
        int isHasPhone = 0;
        for(Object key : tHashMap.keySet()) {
            Contact commonContact = (Contact) tHashMap.get(key);
            for(String phoneNumber : commonContact.getContactPHONES()) {
                if(phoneNumber.equals(contactPhone)) {
                    System.out.println("Контакт найден.");
                    System.out.println("ФИО контакта: "+commonContact.getContactFIO());
                    System.out.println("Дата рождения: "+commonContact.getContactBDAY());
                    System.out.print("Телефоны контакта: ");
                    for(String phones : commonContact.getContactPHONES()) {
                        System.out.print(phones+", ");
                    }
                    System.out.println();
                    System.out.println("Адрес: "+commonContact.getContactADRESS());
                    System.out.println("Последняя модификация данных: "+commonContact.getContactMODIFYDT());
                    System.out.println("-----------------------------------------");
                    isHasPhone++;
                }
            }
        }
        if(isHasPhone==0){
            System.out.println("Контакт с номером "+contactPhone+" не найден!");
        }
    }

    /**
     * Метод выводит на экран всю телефонную книгу без сортировки
     * @param tHashMap ссылка на телефонную книгу в формате HashMap
     */
    private static void contactShow(HashMap tHashMap) {
        for(Object key : tHashMap.keySet()) {
            Contact commonContact = (Contact) tHashMap.get(key);
            System.out.println(key.toString());
            System.out.println("ФИО контакта: "+commonContact.getContactFIO());
            System.out.println("Дата рождения: "+commonContact.getContactBDAY());
            System.out.print("Телефоны контакта: ");
            for(String phones : commonContact.getContactPHONES()) {
                System.out.print(phones+", ");
            }
            System.out.println();
            System.out.println("Адрес: "+commonContact.getContactADRESS());
            System.out.println("Последняя модификация данных: "+commonContact.getContactMODIFYDT());
            System.out.println("-----------------------------------------");
        }
    }

    /**
     * Метод выводит на экран всю телефонную книгу отсортированную по имени
     * @param tHashMap  ссылка на телефонную книгу в формате HashMap
     */
    private static void contactShowSortByName(HashMap tHashMap) {
        HashMap<String,String> sortName = new HashMap<>();
        ArrayList<String> names = new ArrayList<>();
        LinkedHashSet<String> keys = new LinkedHashSet<>();

        for(Object key : tHashMap.keySet()) {
            Contact commonContact = (Contact) tHashMap.get(key);
            sortName.put((String) key,commonContact.getContactFIO());
            names.add(commonContact.getContactFIO());
        }
        Collections.sort(names);
        System.out.println(names);

        for(String name: names) {
            for(String key : sortName.keySet()) {
                if(sortName.containsValue(name)) {
                    keys.add(key);
                }
            }
        }

        for(Object key : keys) {
            System.out.println(key.toString());
        }

        for(Object key : keys) {
            Contact commonContact = (Contact) tHashMap.get(key);
            System.out.println(key.toString());
            System.out.println("ФИО контакта: "+commonContact.getContactFIO());
            System.out.println("Дата рождения: "+commonContact.getContactBDAY());
            System.out.print("Телефоны контакта: ");
            for(String phones : commonContact.getContactPHONES()) {
                System.out.print(phones+", ");
            }
            System.out.println();
            System.out.println("Адрес: "+commonContact.getContactADRESS());
            System.out.println("Последняя модификация данных: "+commonContact.getContactMODIFYDT());
            System.out.println("-----------------------------------------");
        }

    }

    /**
     * Метод изменяет имя контакта
     * @param tHashMap ссылка на телефонную книгу в формате HashMap
     * @param oldName старое имя
     * @param newName новое имя
     */
    private static void contactEditName(HashMap tHashMap, String oldName, String newName) {
        int isHasName = 0;
        for(Object key : tHashMap.keySet()) {
            Contact commonContact = (Contact) tHashMap.get(key);
            if(commonContact.getContactFIO().equals(oldName)) {
                System.out.println("Контакт найден.");
                commonContact.setContactFIO(newName);
                commonContact.setContactMODIFYDT(LocalDateTime.now());
                System.out.println("Контакт обновлен.");
                System.out.println("ФИО контакта: "+commonContact.getContactFIO());
                System.out.println("Дата рождения: "+commonContact.getContactBDAY());
                System.out.print("Телефоны контакта: ");
                for(String phones : commonContact.getContactPHONES()) {
                    System.out.print(phones+", ");
                }
                System.out.println();
                System.out.println("Адрес: "+commonContact.getContactADRESS());
                System.out.println("Последняя модификация данных: "+commonContact.getContactMODIFYDT());
                System.out.println("-----------------------------------------");
                isHasName++;
            }
        }
        if(isHasName==0) {
            System.out.println("Контакт с именем "+oldName+" не найден. Замена невозможна.");
        }
    }

    /**
     * Метод меняет дату рождения контакта
     * @param tHashMap ссылка на телефонную книгу в формате HashMap
     * @param contactName имя контакта
     * @param newBDay новая дата рождения
     */
    private static void contactEditBday(HashMap tHashMap, String contactName, Date newBDay) {
        int isHasName = 0;
        for(Object key : tHashMap.keySet()) {
            Contact commonContact = (Contact) tHashMap.get(key);
            if (commonContact.getContactFIO().equals(contactName)) {
                System.out.println("Контакт найден.");
                commonContact.setContactBDAY(newBDay);
                commonContact.setContactMODIFYDT(LocalDateTime.now());
                System.out.println("Контакт обновлен.");
                System.out.println("ФИО контакта: " + commonContact.getContactFIO());
                System.out.println("Дата рождения: " + commonContact.getContactBDAY());
                System.out.print("Телефоны контакта: ");
                for (String phones : commonContact.getContactPHONES()) {
                    System.out.print(phones + ", ");
                }
                System.out.println();
                System.out.println("Адрес: " + commonContact.getContactADRESS());
                System.out.println("Последняя модификация данных: " + commonContact.getContactMODIFYDT());
                System.out.println("-----------------------------------------");
                isHasName++;
            }
        }
        if(isHasName==0) {
            System.out.println("Контакт с именем "+contactName+" не найден. Замена невозможна.");
        }
    }

    private static void contactSaveToFile() {
        ObjectMapper contactObjectMapper = new ObjectMapper();
    }

    public static void main(String[] args) {
        int intCustomerChoice = -1;
        HashMap<String,Contact> myContacts = new HashMap<>();

        System.out.println("Привет. Я Телефонная книга.");
        mainMenu();

        Scanner mainScan = new Scanner(System.in);

        try {
            do {
                intCustomerChoice = mainScan.nextInt();

                switch (intCustomerChoice){
                    case(0): mainMenu();
                        break;
                    case(1): System.out.println("Добавляю новый контакт");
                        contactInputManual(myContacts);
                        break;
                    case(21): System.out.println("Удаляю контакт");
                        contactDeleteManual(myContacts,"contact3");
                        break;
                    case(22): System.out.println("Удаляю контакт");
                        contactDeleteAll(myContacts);
                        break;
                    case(31): System.out.println("Ищу контакт по имени");
                        contactSearchName(myContacts,"Варвара");
                        break;
                    case(32): System.out.println("Ищу контакт по номеру телефона");
                        contactSearchPhone(myContacts,"+380508187839");
                        break;
                    case(4): System.out.println("Показываю телефонную книгу");
                        contactShow(myContacts);
                        break;
                    case(41): System.out.println("Показываю телефонную книгу отсортированную по имени");
                        contactShowSortByName(myContacts);
                        break;
                    case(51): System.out.println("Редактирую имя контакта");
                        contactEditName(myContacts,"Антон","Платон");
                        break;
                    case(52): System.out.println("Редактирую дату рождения контакта");
                        contactEditBday(myContacts,"Варвара",Date.valueOf("2017-12-31"));
                        break;
                    case(6): System.out.println("Сохраняю телефонную книгу в файл");
                        contactSaveToFile();
                        break;
                    case(7): System.out.println("В книгу загружены контакты из файла");
                        break;
                    case(-1): break;
                    default: System.out.println("Вы ввели несуществующий пункт меню: "+intCustomerChoice);
                        mainMenu();
                }
            }
            while (intCustomerChoice!=-1);
            System.out.println("Приложение закрыто.");
            mainScan.close();
        }
        catch(InputMismatchException e) {
            System.out.println("Недопустимый символ ввода: "+e.getMessage()+" Приложение закрыто.");
        }
    }
}
