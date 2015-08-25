package controller;

import model.Person;
import model.Position;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Класс отвечающий за работу всех сотрудников
 */
public class WorkController {
    public static final WorkController INSTANCE = new WorkController();


    private Map<Person, Set<Position>> personList; //список сотрудников
    private static Set<Position> necessaryPositions; //список обязательных должностей

    public void runWorkController() {

        necessaryPositions = createNecessaryPositions();
        PersonController.INSTANCE.setNecessaryPositions(necessaryPositions);
        PersonController.INSTANCE.runPersonController(); //создаем сотрудников

        personList = PersonController.INSTANCE.getPersonList(); //получаем список работников

        try {
            //workMonth(); //запуск моделирования работы компании в течении месяца
            //workWeek(); //запуск моделирования работы компании в течении недели
            workDay(); //запуск моделирования работы компании в течении дня
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод, запускающий работу сотрудников в течении дня
     * @throws InterruptedException
     */
    private void workDay() throws InterruptedException {
        //запуск всех сотрудников
        for (Map.Entry<Person, Set<Position>> person : personList.entrySet()) person.getKey().start();
        DirectorsController.INSTANCE.runDirectorsController();
        //остановка всех сотрудниковв конце рабочего дня
        Thread.sleep(Company.MAX_WORKING_HOURS);
        for (Map.Entry<Person, Set<Position>> person : personList.entrySet()) person.getKey().setStopWork(true);
    }

    /**
     * Метод, запускающий работу сотрудников в течении недели
     * @throws InterruptedException
     */
    private void workWeek() throws InterruptedException {
        for (int i = 0; i < Company.MAX_WORKING_DAYS; i++) workDay();
        //каждую неделю бухгалтер платит з/п - реализовать
    }

    /**
     * Метод, запускающий работу сотрудников в течении месяца
     * @throws InterruptedException
     */
    private void workMonth() throws InterruptedException {
        for (int i = 0; i < Company.MAX_WORKING_WEEKS; i++) workWeek();
        //формирование суммарного отчета + сохранение в файл - реализовать
    }

    /**
     * Метод создает список обязательных должностей
     * @return список должностей
     */
    public Set<Position> createNecessaryPositions() {
        Set<Position> list = new HashSet<>(); //создаем список обязательных должностей
        list.add(Position.Director);
        list.add(Position.Manager);
        list.add(Position.Accountant);
        return list;
    }
}
