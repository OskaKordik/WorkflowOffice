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

    /**
     * Доступ к контроллеру осуществляется через INSTANCE
     */
    private WorkController() {}

    public void runWorkController() {
        necessaryPositions = createNecessaryPositions();
        //задаем список обязательных должностей
        PersonController.INSTANCE.setNecessaryPositions(necessaryPositions);
        //задаем максимальное кол-во директоров
        PersonController.INSTANCE.setCountDirectorsPositions(Company.MAX_AMOUNT_DIRECTORS_POSITIONS);
        //создаем сотрудников
        PersonController.INSTANCE.runPersonController();

        personList = PersonController.INSTANCE.getPersonList(); //получаем список работников
        //получаем главного бухгалтера
        Person personGeneralAccountant = PersonController.INSTANCE.selectionRandomAccountant(personList);
        Accountant generalAccountant = (Accountant) personGeneralAccountant.getListPositions().get(Position.Accountant);

        try {
            workMonth(generalAccountant); //запуск моделирования работы компании в течении месяца
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод, запускающий работу сотрудников в течении месяца
     * @throws InterruptedException
     */
    private void workMonth(Accountant generalAccountant) throws InterruptedException {
        //запуск всех сотрудников
        for (Map.Entry<Person, Set<Position>> person : personList.entrySet()) person.getKey().start();

        for (int i = 0; i < Company.MAX_WORKING_WEEKS; i++) {
            for (int j = 0; j < Company.MAX_WORKING_DAYS; j++) {
                DirectorsController.INSTANCE.runDirectorsController();
                Thread.sleep(Company.MAX_WORKING_HOURS);
            }
            generalAccountant.payWeekSalary(personList, PersonController.INSTANCE.getFreelancers());
        }
        //формирование суммарного отчета + сохранение в файл - реализовать

        //остановка всех сотрудников в конце месяца
        for (Map.Entry<Person, Set<Position>> person : personList.entrySet()) person.getKey().setStopWork(true);
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
