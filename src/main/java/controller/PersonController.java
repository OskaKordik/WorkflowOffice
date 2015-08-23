package controller;

import model.Person;
import model.Position;
import model.Positions.APosition;
import model.Positions.Programmer;

import java.security.SecureRandom;
import java.util.*;

/**
 * Класс контролирующий работу сотрудников компании
 */
public final class PersonController {
    public static final PersonController INSTANCE = new PersonController();
    private static final SecureRandom random = new SecureRandom(); // for random enums

    private Map<Person, Set<Position>> personList; //список сотрудников
    private Set<Position> necessaryPositions; //список обязательных должностей


    /**
     * Метод запускающий работу контроллера
     */
    public void runPersonController() {
        personList = createRandomPerson(); //создаем список сотрудников

        //проверка, что в фирме есть необходимые должности
        if (necessaryPositions != null)
            for (Position position : necessaryPositions)
                personList = editingPersonPositions(personList, position);
    }

    /**
     * Метод создает список сотрудников
     */
    protected Map<Person, Set<Position>> createRandomPerson() {
        Map<Person, Set<Position>> list = new HashMap<>();
        int countPersons = random.nextInt(90) + 10; //кол-во сотрудников (от 10 до 100)

        for (int i = 1; i <= countPersons; i++) {
            Person person = createPerson("Сотрудник №" + i);
            Set<Position> position = setRandomPositions();

            Map<Position, APosition> positionMap = createPositionForPerson(position); //протестить!
            person.setListPositions(positionMap); //задаем список должностей

            list.put(person, position);
        }

        return list;
    }

    /**
     * Метод создает эекземпляры классов соответстующих должностей для сотрудника
     * @param posList список должностей определенного сотрудника
     * @return список экземпляров с должностями
     */
    protected Map<Position, APosition> createPositionForPerson(Set<Position> posList) {
        Map<Position, APosition> positionMap = new HashMap<>();
        for (Position pos : posList) {
            int countPos = pos.ordinal();
            switch (countPos) {
                case 0 : positionMap.put(pos, new Programmer("Programmer"));
                    break;
                case 1 : positionMap.put(pos, new Programmer("Designer"));
                    break;
                case 2 : positionMap.put(pos, new Programmer("Tester"));
                    break;
                case 3 : positionMap.put(pos, new Programmer("Manager"));
                    break;
                case 4 : positionMap.put(pos, new Programmer("Director"));
                    break;
                case 5 : positionMap.put(pos, new Programmer("Accountant"));
                    break;
                default: break;
            }
        }
        return positionMap;
    }

    /**
     * Метод создает сотрудника и задает ему необходимые параметры
     * (время выполнения одного задания и ко-во рабочих часов в день)
     * @param name имя сотрудника
     * @return сотрудника
     */
    protected Person createPerson(String name) {
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
    protected Set<Position> setRandomPositions() {
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
     * @param list     список сотрудников
     * @param position должность
     */
    protected Map<Person, Set<Position>> editingPersonPositions(Map<Person, Set<Position>> list, Position position) {
        if (!isContainsPosition(list, position)) {
            Set<Position> positionList = new HashSet<>();
            positionList.add(position);
            list.put(createPerson("Сотрудник №" + (list.size() + 1)), positionList);
        }
        return list;
    }

    /**
     * Метод проверяет наличие определенной должности у сотрудников
     *
     * @param list     список сотрудников
     * @param position должность
     */
    protected boolean isContainsPosition(Map<Person, Set<Position>> list, Position position) {
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
    protected void setNecessaryPositions(Set<Position> necessaryPositions) {
        this.necessaryPositions = necessaryPositions;
    }

    /**
     * Метод возвращает список обязательных должностей
     * @return список должностей
     */
    protected Set<Position> getNecessaryPositions() {
        return necessaryPositions;
    }
}
