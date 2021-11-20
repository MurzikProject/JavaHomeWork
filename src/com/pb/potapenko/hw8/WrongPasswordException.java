package com.pb.potapenko.hw8;

/**
 * Пользовательское исключение, срабатывающее при некорректном заполнении поля "пароль".
 */
public class WrongPasswordException extends Exception{
    private int symbolCount;

    public WrongPasswordException() {

    }

    /**
     * Исключение, которое срабатывает при условии, что введены недопустимые символы.
     * @param clientMsg - сообщение от отработчика.
     */
    public WrongPasswordException(String clientMsg) {
        System.out.println(clientMsg);
    }

    /**
     * Исключение, которое срабатывает при условии, что введено недопустимое количество символов.
     * @param symbolCount - количество введенных символов.
     * @param clientMsg - сообщение от отработчика.
     */
    public WrongPasswordException(int symbolCount, String clientMsg) {
        this.symbolCount = symbolCount;
        System.out.println(clientMsg + " - " + getSymbolCount() + ".");
        System.out.println("Длина пароля должна превышать 5 символов.");
    }

    public int getSymbolCount() {
        return symbolCount;
    }
}
