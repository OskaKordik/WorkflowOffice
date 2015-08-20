package controller;

import model.Person;
import model.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс Компания - контроллирует работу компании
 */
public class Company {
    private static List<Person> personList; //список сотрудников (количество задается случайно)

    public static void main(String[] args) {
        setRandomPerson();

        //проверка, что в фирме есть директор
        if (!isContainsPosition(Position.Director)) {
            Person person = new Person("Director");
            person.setPositionList(Position.Director);
            personList.add(person);
        }
        //проверка, что в фирме есть менеджер
        if (!isContainsPosition(Position.Manager)) {
            Person person = new Person("Manager");
            person.setPositionList(Position.Manager);
            personList.add(person);
        }
        //проверка, что в фирме есть бухгалтер
        if (!isContainsPosition(Position.Accountant)) {
            Person person = new Person("Accountant");
            person.setPositionList(Position.Accountant);
            personList.add(person);
        }
        try {
            workMonth(); //запуск моделирования работы компании в течении месяца
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод, запускающий работу сотрудников в течении дня
     * @throws InterruptedException
     */
    private static void workDay() throws InterruptedException {
        for (Person person : personList) {
            Thread threadPerson = new Thread(person);
            threadPerson.start();
        }
        Thread.sleep(8);
    }

    /**
     * Метод, запускающий работу сотрудников в течении недели
     * @throws InterruptedException
     */
    private static void workWeek() throws InterruptedException {
        for (int i = 0; i < 5; i++) workDay();
        //каждую неделю бухгалтер платит з/п - реализовать
    }

    /**
     * Метод, запускающий работу сотрудников в течении месяца
     * @throws InterruptedException
     */
    private static void workMonth() throws InterruptedException {
        for (int i = 0; i < 4; i++) workWeek();
        //формирование суммарного отчета + сохранение в файл - реализовать
    }

    /**
     * Метод проверяет есть ли указанная должность в списке сотрудников
     * @param position
     * @return true or false
     */
    private static boolean isContainsPosition(Position position) {
        for (Person person : personList) {
            if (person.getPositionList().contains(position)) return true;
        }
        return false;
    }

    /**
     * Метод задает случайное кол-во сотрудников (от 10 до 100)
     */
    private static void setRandomPerson() {
        personList = new ArrayList<>();
        int countPersons = (int) (Math.random() * 90 + 10);
        for (int i = 1; i <= countPersons; i++) {
            personList.add(new Person("Сотрудник №" + i)); //добавляет сотрудника в список
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
