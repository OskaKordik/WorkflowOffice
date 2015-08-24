package controller;

import model.Person;
import model.Position;

import java.util.*;

/**
 * Класс управляющий директорами
 */
public class DirectorsController {
    public static final DirectorsController INSTANCE = new DirectorsController();
    private static Map<Person, Set<Position>> personList; //список всех сотрудников
    private static List<Person> directorsList; //список директоров
    private static final Map<Position, String> taskList = new HashMap<>();
    private static Map<Person, Set<Position>> personListOtherDirectors; //список всех сотрудников кроме директоров

    static { //заполнение списка распоряжений для сотрудников
        taskList.put(Position.Director, "раздать распоряжения сотрудникам");
        taskList.put(Position.Programmer, "писать код");
        taskList.put(Position.Designer, "рисовать макет");
        taskList.put(Position.Tester, "тестировать программу");
        taskList.put(Position.Manager, "продавать услуги");
        taskList.put(Position.Accountant, "составить отчетность");
    }

    public void runDirectorsController() {
        personList = PersonController.INSTANCE.getPersonList(); //получаем список работников
        personListOtherDirectors = selectionOfPersonsOtherDirectors(personList);
        directorsList = selectionOfDirectors(personList);

        for (int i = 0; i < Company.MAX_WORKING_HOURS; i++) {
            workDirectors(directorsList);
            try {
                Thread.sleep(Company.ONE_HOUR);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Метод раздает всем директорам задание
     * @param list список директоров
     */
    protected void workDirectors(List<Person> list) {
        for (Person director : list) {
            if (!director.isBusy() && director.isWork())
                director.performTask(Position.Director, taskList.get(Position.Director));
        }
    }

    /**
     * Метод выбирает из списка сотрудников только с должностью директора
     *
     * @param personList список всех сотрудников
     * @return list список сотрудников с должностью директора
     */
    protected List<Person> selectionOfDirectors(Map<Person, Set<Position>> personList) {
        List<Person> list = new ArrayList<>();
        for (Map.Entry<Person, Set<Position>> person : personList.entrySet())
            if (person.getValue().contains(Position.Director)) {
                person.getKey().setAmountHoursOneInstructions(Company.ONE_HOUR); //задаем время выполнение задания 1 час

                list.add(person.getKey());
            }
        return list;
    }

    /**
     * Метод выбирает из списка всех сотрудников кроме директоров
     *
     * @param personList список всех сотрудников
     * @return list список сотрудников не с должностью директора
     */
    protected Map<Person, Set<Position>> selectionOfPersonsOtherDirectors(Map<Person, Set<Position>> personList) {
        Map<Person, Set<Position>> list = new HashMap<>();
        for (Map.Entry<Person, Set<Position>> person : personList.entrySet())
            if (!person.getValue().contains(Position.Director)) {
                list.put(person.getKey(), person.getValue());
            }
        return list;
    }

    public static Map<Position, String> getTaskList() {
        return taskList;
    }

    public static Map<Person, Set<Position>> getPersonListOtherDirectors() {
        return personListOtherDirectors;
    }
}
