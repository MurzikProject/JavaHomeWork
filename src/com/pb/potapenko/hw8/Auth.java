package com.pb.potapenko.hw8;

import java.util.Locale;

public class Auth {
    private String login;
    private String password;

    /**
     * Пустой конструктор, так как поля заполним лишь после успешной проверки.
     */
    public Auth() {

    }

    /**
     * Метод осуществляющий регистрацию на сайте.
     * @param login - логин пользователя.
     * @param password - пароль пользователя.
     * @param confirmPassword - подтверждение пароля пользователя.
     */
    public void signUp(String login, String password, String confirmPassword) throws WrongLoginException, WrongPasswordException{
        int loginTrue = 0; // Каждая проверка добавляет 1. Записываем логин в объект, если = 2.
        int passwordTrue = 0; // Каждая проверка добавляет 1. Записываем пароль в объект, если = 3.

        // Проверяем логин на количество символов
        if(login.length()>=5&&login.length()<=20) {
            loginTrue ++;
        }
        else {
            throw new WrongLoginException(login.length(),"ОШИБКА! Введено недопустимое количество символов в логин");
        }

        // Проверяем символы логина на соответсвие шаблону [A-Z0-9]
        if(login.toUpperCase().matches("[A-Z0-9]+")) {
            loginTrue ++;
        }
        else {
            throw new WrongLoginException("ОШИБКА! Введены недопустимые символы в логин (допускаются только цифры или английские литеры).");
        }

        // Проверяем пароль на количество символов
        if(password.length()>5) {
            passwordTrue++;
        }
        else {
            throw new WrongPasswordException(password.length(),"ОШИБКА! Введено недопустимое количество символов в пароль.");
        }

        // Проверяем символы пароля на соответсвие шаблону [A-Z0-9_]
        if(password.toUpperCase().matches("[A-Z0-9_]+")) {
            passwordTrue++;
        }
        else {
            throw new WrongPasswordException("ОШИБКА! Введены недопустимые символы в пароль (допускаются только цифры, английские литеры и знак подчерка).");
        }

        // Проверяем пароль и его подтверждение на подобие
        if(password.equals(confirmPassword)) {
            passwordTrue++;
        }
        else {
            throw new WrongPasswordException("ОШИБКА! Пароль и его подтверждение не совпадают.");
        }

        // Проверяем все требования к логину и сохраняем его
        if(loginTrue==2&&passwordTrue==3) {
            System.out.println("Количество и допустимость символов вашего логина и пароля соответсвует требованиям.");
            System.out.println("Ваш логин и пароль успешно сохранены!");
            this.login = login;
            this.password = password;
        }
        else {
            System.out.println("Введите корректный логин и пароль!");
        }
    }


    /**
     * Метод осуществляющий проверку логина/пароля при входе на сайт.
     * @param login - логин пользователя.
     * @param password - пароль пользователя.
     */
    public void signIn(String login, String password) throws WrongLoginException{

        if(login.equals(this.login)&&password.equals(this.password)) {
            System.out.println("Поздравляем! Вы зашли на сайт под своим профилем.");
        }
        else {
            throw new WrongLoginException("ОШИБКА! Логин и пароль не совпадают.");
        }
    }

    /**
     * Сеттеры создать всегда правильно, информация о состоянии объекта нужна.
     * А вот геттеры мне не нравятся: состояния должны изменяться только методами.
     */
    public String getLogin() {
        return "Ваш логин: " + login;
    }

    public String getPassword() {
        return "Ваш пароль: " + password;
    }
}
