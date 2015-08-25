package controller;

import model.Person;
import model.Position;
import model.Positions.Accountant;

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
        //получаем главного бухгалтера
        Person personGeneralAccountant = PersonController.INSTANCE.selectionRandomAccountant(personList);
        Accountant generalAccountant = (Accountant) personGeneralAccountant.getListPositions().get(Position.Accountant);

        try {
            //workMonth(generalAccountant); //запуск моделирования работы компании в течении месяца
            //workWeek(generalAccountant); //запуск моделирования работы компании в течении недели
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
    private void workWeek(Accountant generalAccountant) throws InterruptedException {
        for (int i = 0; i < Company.MAX_WORKING_DAYS; i++) workDay();
        generalAccountant.payWeekSalary();
    }

    /**
     * Метод, запускающий работу сотрудников в течении месяца
     * @throws InterruptedException
     */
    private void workMonth(Accountant generalAccountant) throws InterruptedException {
        for (int i = 0; i < Company.MAX_WORKING_WEEKS; i++) workWeek(generalAccountant);
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
