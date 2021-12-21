package com.pb.potapenko.hw12;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static com.pb.potapenko.hw12.Contact.getCountContacts;

public class PhoneBook {

    /**
     * Метод выводит меню приложения по требованию пользователя.
     */
    private static void mainMenu() {
        Stream.concat(
                Stream.of("-----------------------------------------",
                        "1 - Добавить контакт вручную","21 - Удалить контакт вручную",
                        "22 - Удалить все контакты","31 - Осуществить поиск в телефонной книге по имени",
                        "32 - Осуществить поиск в телефонной книге по номеру телефона",
                        "4 - Показать телефонную книгу","41 - Показать телефонную книгу отсортированную по имени",
                        "51 - Редактировать имя контакта","52 - Редактировать дату рождения контакта"),
                Stream.of("6 - Записать телефонную книгу в файл","7 - Загрузить телефонную книгу из файла",
                        "0 - Показать меню приложения","-1 - Выйти из приложения","ВЫБЕРИ НЕОБХОДИМЫЙ ПУНКТ МЕНЮ",
                        "-----------------------------------------"))
                .forEach(System.out::println);
    }

    /**
     * Метод добавляет новый контакт в книгу
     * @param tHashMap ссылка на телефонную книгу в формате HashMap
     */
    private static void contactInputManual(HashMap tHashMap) {
        String[] contact1Phones = {"+380662577094"};
        String[] contact2Phones = {"+380508187856"};
        String[] contact3Phones = {"+380508187804","+380508187839"};

        Contact contact1 = new Contact("Яна", LocalDate.parse("1993-02-02"),
                contact1Phones,"Полтава",LocalDateTime.now());
        Contact.Sayable sayable = contact1::contactCreateInform;
        sayable.say();
        Contact contact2 = new Contact("Варвара",LocalDate.parse("2017-11-09"),
                contact2Phones,"Днепр",LocalDateTime.now());
        sayable = contact2::contactCreateInform;
        sayable.say();
        Contact contact3 = new Contact("Антон",LocalDate.parse("1981-04-21"),
                contact3Phones,"Симферополь", LocalDateTime.now());
        sayable = contact3::contactCreateInform;
        sayable.say();

        tHashMap.put("contact1",contact1);
        tHashMap.put("contact2",contact2);
        tHashMap.put("contact3",contact3);

        System.out.println("Добавлено контактов - "+ getCountContacts());
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
        ArrayList<String> deleteContactKeys = new ArrayList<>();

        for(Object key : tHashMap.keySet()) {
            deleteContactKeys.add((String) key);
        }
        deleteContactKeys.forEach(tHashMap::remove);
        System.out.println("Телефонная книга полностью очищена.");
    }

    /**
     * Метод осуществляет поиск контакта по имени
     * @param tHashMap ссылка на телефонную книгу в формате HashMap
     * @param contactName имя контакта
     */
    private static void contactSearchName(HashMap tHashMap, String contactName){
        int isHasName = 0;
        Predicate<Integer> intZero = i -> i == 0;
        Predicate<String> containsContactName = t -> t.contains(contactName);
        for(Object key : tHashMap.keySet()) {
            Contact commonContact = (Contact) tHashMap.get(key);
            if(containsContactName.test(commonContact.getContactFIO())) {
                System.out.println("Контакт найден.");
                Contact.Sayable sayable = commonContact::getContactInfo;
                sayable.say();
                isHasName++;
            }
        }
        if(intZero.test(isHasName)) {
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
        Predicate<Integer> intZero = i -> i == 0;
        for(Object key : tHashMap.keySet()) {
            Contact commonContact = (Contact) tHashMap.get(key);
            for(String phoneNumber : commonContact.getContactPHONES()) {
                if(phoneNumber.equals(contactPhone)) {
                    System.out.println("Контакт найден.");
                    Contact.Sayable sayable = commonContact::getContactInfo;
                    sayable.say();
                    isHasPhone++;
                }
            }
        }
        if(intZero.test(isHasPhone)){
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
            Contact.Sayable sayable = commonContact::getContactInfo;
            sayable.say();
        }
    }

    /**
     * Метод выводит на экран всю телефонную книгу отсортированную по имени
     * @param tHashMap  ссылка на телефонную книгу в формате HashMap
     */
    private static void contactShowSortByName(HashMap tHashMap) {
        HashMap<String,String> sortName = new HashMap<>();
        ArrayList<String> names = new ArrayList<>();

        for(Object key : tHashMap.keySet()) {
            Contact commonContact = (Contact) tHashMap.get(key);
            sortName.put((String) key,commonContact.getContactFIO());
            names.add(commonContact.getContactFIO());
        }
        Collections.sort(names);
        //System.out.println(names);

        for(String name : names) {
            for(Object key : tHashMap.keySet()) {
                Contact commonContact = (Contact) tHashMap.get(key);
                if(commonContact.getContactFIO().equals(name)) {
                    Contact.Sayable sayable = commonContact::getContactInfo;
                    sayable.say();
                }
            }
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
        Predicate<Integer> intZero = i -> i == 0;
        Predicate<String> containsOldName = t -> t.contains(oldName);
        for(Object key : tHashMap.keySet()) {
            Contact commonContact = (Contact) tHashMap.get(key);
            if(containsOldName.test(commonContact.getContactFIO())) {
                System.out.println("Контакт найден.");
                commonContact.setContactFIO(newName);
                commonContact.setContactMODIFYDT(LocalDateTime.now());
                System.out.println("Контакт обновлен.");
                Contact.Sayable sayable = commonContact::getContactInfo;
                sayable.say();
                isHasName++;
            }
        }
        if(intZero.test(isHasName)) {
            System.out.println("Контакт с именем "+oldName+" не найден. Замена невозможна.");
        }
    }

    /**
     * Метод меняет дату рождения контакта
     * @param tHashMap ссылка на телефонную книгу в формате HashMap
     * @param contactName имя контакта
     * @param newBDay новая дата рождения
     */
    private static void contactEditBday(HashMap tHashMap, String contactName, LocalDate newBDay) {
        int isHasName = 0;
        Predicate<Integer> intZero = i -> i == 0;
        Predicate<String> containsContactName = t -> t.contains(contactName);
        for(Object key : tHashMap.keySet()) {
            Contact commonContact = (Contact) tHashMap.get(key);
            if (containsContactName.test(commonContact.getContactFIO())) {
                System.out.println("Контакт найден.");
                commonContact.setContactBDAY(newBDay);
                commonContact.setContactMODIFYDT(LocalDateTime.now());
                System.out.println("Контакт обновлен.");
                Contact.Sayable sayable = commonContact::getContactInfo;
                sayable.say();
                isHasName++;
            }
        }
        if(intZero.test(isHasName)) {
            System.out.println("Контакт с именем "+contactName+" не найден. Замена невозможна.");
        }
    }

    /**
     * Метод генерирует json объект, сохраняет туда данные телефонной книги и записывает в файл на диске
     * @param tHashMap ссылка на телефонную книгу в формате HashMap
     * @param pathName путь к каталогу, где будет лежать файл с данными телефонной книги
     * @param fileName имя файла с данными телефонной книги
     * @param contactObjectMapper ссылка на объект для корректной работы с форматами LocalDate и LocalDateTime
     * @throws Exception
     */
    private static void contactSaveToFile(HashMap tHashMap, String pathName, String fileName, ObjectMapper contactObjectMapper) throws Exception{

        String contactJson = "";

        for(Object key : tHashMap.keySet()) {
            contactJson = contactObjectMapper.writeValueAsString(tHashMap);
        }
        try (BufferedWriter myWriter = Files.newBufferedWriter(Paths.get(pathName + fileName))) {
            myWriter.write(contactJson);
            myWriter.flush();
            System.out.println("Данные телефонной книги успешно записаны в файл "+pathName+fileName);
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка записи в файл: " + e);
        }
    }

    /**
     * Метод считывает данные из файла и генерирует json объект, сохраняет туда данные телефонной книги
     * @param pathName путь к каталогу, где будет лежать файл с данными телефонной книги
     * @param fileName имя файла с данными телефонной книги
     * @param restoreContacts ссылка на восстановленную телефонную книгу в формате HashMap
     * @param contactObjectMapper ссылка на объект для корректной работы с форматами LocalDate и LocalDateTime
     */
    private static void contactReadFromFile(String pathName, String fileName, HashMap restoreContacts, ObjectMapper contactObjectMapper) {

        String contactJson = "";
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(pathName + fileName))) {
            String line;
            while((line = reader.readLine()) != null) {
                contactJson += line+'\n';
            }
        }
        catch (Exception e) {
            System.out.println("Ошибка чтения файла: " + e);
        }
        System.out.println(contactJson);
    }

    public static void main(String[] args) throws Exception {
        ObjectMapper contactObjectMapper = new ObjectMapper();
        contactObjectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        SimpleModule module = new SimpleModule();
        module.addSerializer(LocalDate.class, new LocalDateSerializer());
        module.addDeserializer(LocalDate.class, new LocalDateDeserializer());
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        contactObjectMapper.registerModule(module);

        int intCustomerChoice = -1;
        String jsonPath = "/home/varvara/IdeaProjects/JavaHomeWork/src/com/pb/potapenko/hw12/"; // каталог
        String jsonFileName = "PhoneBook.json"; // имя файла
        HashMap<String, Contact> myContacts = new HashMap<>(); // Коллекция с данными телефонной книги
        HashMap<String, Contact> myRestoreContacts = new HashMap<>(); // Коллекция с востановленными из файла данными телефонной книги

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
                        contactSearchName(myContacts,"Василина");
                        break;
                    case(32): System.out.println("Ищу контакт по номеру телефона");
                        contactSearchPhone(myContacts,"+380508187832");
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
                        contactEditBday(myContacts,"Варвара",LocalDate.parse("2017-12-31"));
                        break;
                    case(6): System.out.println("Сохраняю телефонную книгу в файл json");
                        contactSaveToFile(myContacts,jsonPath,jsonFileName,contactObjectMapper);
                        break;
                    case(7): System.out.println("В книгу загружены контакты из файла");
                        contactReadFromFile(jsonPath,jsonFileName,myRestoreContacts,contactObjectMapper);
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
