package com.pb.potapenko.hw8;

import java.util.Scanner;

public class OnlineShop {

    /**
     * Метод возвращает введенное пользователем значение.
     * @param clientMsg - ссобщение для клинта, какая информация от него требуется.
     * @return - собственно введенное пользователем значение.
     */
    private static String getInputStream(String clientMsg) {
        System.out.println(clientMsg);
        Scanner scan = new Scanner(System.in);
        String inputValue = scan.nextLine();

        return inputValue;
    }

    public static void main(String[] args) throws WrongLoginException {

        try {
            // Регистрация на сайте
            System.out.println("Пожалуйста, пройдите регистрацию на сайте.");
            Auth newAuth = new Auth();
            newAuth.signUp(getInputStream("Введите логин"),
                    getInputStream("Введите пароль"),
                    getInputStream("Подтвердите пароль"));
            System.out.println(newAuth.getLogin());
            System.out.println(newAuth.getPassword());

            // Вход на сайт
            System.out.println("--------------------------------------------------------");
            System.out.println("Пожалуйста, зайдите на сайт под своим логином и паролем.");
            newAuth.signIn(getInputStream("Введите логин"),
                    getInputStream("Введите пароль"));

        } catch (WrongLoginException | WrongPasswordException e) {

        }
    }
}
