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
        containsPosition(Position.Director);

        //проверка, что в фирме есть менеджер
        containsPosition(Position.Manager);

        //проверка, что в фирме есть бухгалтер
        containsPosition(Position.Accountant);

        try {
            //workMonth(); //запуск моделирования работы компании в течении месяца
            //workWeek(); //запуск моделирования работы компании в течении недели
            workDay(); //запуск моделирования работы компании в течении дня
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    /**
     * Метод, запускающий работу сотрудников с должностью Директора в течении дня
     * @throws InterruptedException
     */
    private static void workDay() throws InterruptedException {
        Thread threadDirector;
        for (Person person : personList) {
            if (person.getPositionList().contains(Position.Director)) {
                threadDirector = new Thread(person);
                threadDirector.start();
                threadDirector.join(); //запускает каждого директора поочередно
            }
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
     * Метод проверяет наличие определенной должности у сотрудников
     * если нет - создает нового и назначает ему должность
     * @param position должность
     */
    private static void containsPosition(Position position) {
        if (!isContainsPosition(position)) {
            Person person = new Person("Сотрудник №" + personList.size() + 1);
            person.setPositionList(position);
            personList.add(person);
        }
    }
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
