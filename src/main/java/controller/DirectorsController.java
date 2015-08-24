package controller;

import model.Person;
import model.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Класс управляющий директорами
 */
public class DirectorsController {
    public static final DirectorsController INSTANCE = new DirectorsController();
    private Map<Person, Set<Position>> personList; //список всех сотрудников
    private List<Person> directorsList; //список директоров

    public void runDirectorsController() {
        personList = PersonController.INSTANCE.getPersonList(); //получаем список работников
        directorsList = selectionOfDirectors(personList);

        for (Person director : directorsList)
            director.performTask(Position.Director, "раздать распоряжения сотрудникам");
    }

    /**
     * Метод выбирает из списка сотрудников только с должностью директора
     * @param personList список всех сотрудников
     * @return list список сотрудников с должностью директора
     */
    protected List<Person> selectionOfDirectors(Map<Person, Set<Position>> personList) {
        List<Person> list = new ArrayList<>();
        for (Map.Entry<Person, Set<Position>> person : personList.entrySet())
            if (person.getValue().contains(Position.Director)) {
                person.getKey().setAmountHoursOneInstructions(1); //задаем время выполнение задания 1 час
                list.add(person.getKey());
            }
        return list;
    }
}
