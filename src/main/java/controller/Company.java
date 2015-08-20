package controller;

import model.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс Компания - контроллирует работу компании
 */
public class Company {
    private static List<Person> personList; //список сотрудников (количество задается случайно)

    public static void main(String[] args) {
        setRandomPerson();
        //проверить, что в фирме есть хотя бы один директор, менеджер и бухгалтер
        //или задать новых

        //смоделировать работу сотрудников в течении месяца
        //каждую неделю бухгалтер платит з/п

        //формирование суммарного отчета + сохранение в файл
    }

    /**
     * Метод задает случайное кол-во сотрудников (от 10 до 100)
     */
    private static void setRandomPerson() {
        personList = new ArrayList<>();
        int countPersons = (int) (Math.random() * 90 + 10);
        for (int i = 0; i < countPersons; i++) {
            double workHoursPerDay = Math.random() * 7 + 1; //генерирует количество рабочих часов в день
            personList.add(new Person("Сотрудник №" + i, workHoursPerDay)); //добавляет сотрудника в список
        }
    }

    /**
     * Метод возвращает список сотрудников
     * @return personList
     */
    public static List<Person> getPersonList() {
        return personList;
    }
}
