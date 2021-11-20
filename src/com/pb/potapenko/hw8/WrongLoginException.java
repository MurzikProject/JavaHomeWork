package com.pb.potapenko.hw8;

/**
 * Пользовательское исключение, срабатывающее при некорректном заполнении поля "логин".
 */
public class WrongLoginException extends Exception{
    private int symbolCount;

    public WrongLoginException() {

    }

    /**
     * Исключение, которое срабатывает при условии, что введены недопустимые символы.
     * @param clientMsg - сообщение от отработчика.
     */
    public WrongLoginException(String clientMsg) {
        System.out.println(clientMsg);
    }

    /**
     * Исключение, которое срабатывает при условии, что введено недопустимое количество символов.
     * @param symbolCount - количество введенных символов.
     * @param clientMsg - сообщение от отработчика.
     */
    public WrongLoginException(int symbolCount, String clientMsg) {
        this.symbolCount = symbolCount;
        System.out.println(clientMsg + " - " + getSymbolCount() + ".");
        System.out.println("Длина логина находится в интервале 5-20 символов.");
    }

    public int getSymbolCount() {
        return symbolCount;
    }
}
