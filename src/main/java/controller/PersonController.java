package controller;

import model.Person;
import model.Position;

import java.security.SecureRandom;
import java.util.*;

/**
 * Класс контролирующий работу сотрудников компании
 */
public final class PersonController {
    public static final PersonController INSTANCE = new PersonController();
    private static final SecureRandom random = new SecureRandom(); // for random enums

    private static Map<Person, Set<Position>> personList; //список сотрудников
    private static Set<Position> necessaryPositions; //список обязательных должностей


    public void runPersonController() {
        personList = createRandomPerson(); //создаем список сотрудников

        //проверка, что в фирме есть необходимые должности
        if (necessaryPositions != null)
            for (Position position : necessaryPositions)
                editingPersonPositions(personList, position);
    }

    /**
     * Метод создает список сотрудников
     */
    private Map<Person, Set<Position>> createRandomPerson() {
        Map<Person, Set<Position>> list = new HashMap<>();
        int countPersons = random.nextInt(90) + 10; //кол-во сотрудников (от 10 до 100)

        for (int i = 1; i <= countPersons; i++)
            personList.put(createPerson("Сотрудник №" + i), setRandomPositions());

        return list;
    }

    /**
     * Метод создает сотрудника и задает ему необходимые параметры
     * (время выполнения одного задания и ко-во рабочих часов в день)
     * @param name имя сотрудника
     * @return сотрудника
     */
    private Person createPerson(String name) {
        Person person = new Person(name);
        //кол-во времени на выполнение одного задания
        person.setAmountHoursOneInstructions(random.nextFloat() + 1);
        //кол-во рабочих часов в день
        person.setWorkHoursPerDay(random.nextInt(8) + 1);
        //добавляет сотрудника в список
        return person;
    }

    /**
     * Метод задает сотруднику список должностей случайным образом
     */
    private Set<Position> setRandomPositions() {
        Set<Position> list = new HashSet<>();
        int amountPositions = random.nextInt(6) + 1; //количество должностей

        for (int i = 0; i <= amountPositions; i++) {
            int x = random.nextInt(Position.values().length); //выбор случайной должности
            if (Position.values()[x] == Position.Director) { //если выпала должность директора
                list.clear(); //удаляем все предыдущие должности
                list.add(Position.values()[x]);
                break;
            }
            list.add(Position.values()[x]); //добавление в список должности
        }
        return list;
    }

    /**
     * Метод добавляет сотрудника с необходимой должностью
     * в случае если такого нет в штате
     *
     * @param list     список сотрудников
     * @param position должность
     */
    private void editingPersonPositions(Map<Person, Set<Position>> list, Position position) {
        if (!isContainsPosition(personList, position)) {
            Set<Position> positionList = new HashSet<>();
            positionList.add(position);
            personList.put(createPerson("Сотрудник №" + (personList.size() + 1)), positionList);
        }
    }

    /**
     * Метод проверяет наличие определенной должности у сотрудников
     *
     * @param list     список сотрудников
     * @param position должность
     */
    private boolean isContainsPosition(Map<Person, Set<Position>> list, Position position) {
        for (Map.Entry<Person, Set<Position>> person : list.entrySet())
            if (person.getValue().contains(position)) return true;
        return false;
    }

    /**
     * Метод возвращает список сотрудников
     */
    public Map<Person, Set<Position>> getPersonList() {
        return personList;
    }

    /**
     * Метод задающий список обязательных должностей
     * @param necessaryPositions список должностей
     */
    public void setNecessaryPositions(Set<Position> necessaryPositions) {
        PersonController.necessaryPositions = necessaryPositions;
    }
}
