package controller;

import model.Person;
import model.Position;
import model.Positions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Класс контролирующий работу сотрудников компании
 */
public final class PersonController {
    public static final PersonController INSTANCE = new PersonController();
    private static final SecureRandom random = new SecureRandom(); // for random enums

    private Map<Person, Set<Position>> personList; //список сотрудников
    private Set<Position> necessaryPositions; //список обязательных должностей
    private int countDirectorsPositions;


    /**
     * Метод запускающий работу контроллера
     */
    public void runPersonController() {
        countDirectorsPositions = Company.MAX_AMOUNT_DIRECTORS_POSITIONS; //задаем максимальное кол-во директоров
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
        int countPersons = random.nextInt(Company.MAX_AMOUNT_PERSONS) + Company.MIN_AMOUNT_PERSONS;

        for (int i = 1; i <= countPersons; i++) {
            Person person = createPerson("Сотрудник №" + i);
            Set<Position> positionList = setRandomPositions();
            person.setListPositions(createPositionsForPerson(positionList)); //задаем список должностей

            list.put(person, positionList); //добавляем сотрудника в список
        }

        return list;
    }

    /**
     * Метод создает эекземпляры классов соответстующих должностей для сотрудника
     * @param posList список должностей определенного сотрудника
     * @return список экземпляров с должностями
     */
    protected Map<Position, APosition> createPositionsForPerson(Set<Position> posList) {
        Map<Position, APosition> positionMap = new HashMap<>();
        for (Position pos : posList) {
            int countPos = pos.ordinal();
            switch (countPos) {
                case 0 : positionMap.put(pos, new Programmer("Programmer"));
                    break;
                case 1 : positionMap.put(pos, new Designer("Designer"));
                    break;
                case 2 : positionMap.put(pos, new Tester("Tester"));
                    break;
                case 3 : positionMap.put(pos, new Manager("Manager"));
                    break;
                case 4 : positionMap.put(pos, new Director("Director"));
                    break;
                case 5 : positionMap.put(pos, new Accountant("Accountant"));
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
        person.setAmountHoursOneInstructions(new BigDecimal(random.nextFloat() + Company.MIN_WORKING_HOURS)
                                                .setScale(2, RoundingMode.UP)
                                                .floatValue());
        //кол-во рабочих часов в день
        person.setWorkHoursPerDay(random.nextInt(Company.MAX_WORKING_HOURS) + Company.MIN_WORKING_HOURS);
        //добавляет сотрудника в список
        return person;
    }

    /**
     * Метод задает сотруднику список должностей случайным образом
     */
    protected Set<Position> setRandomPositions() {
        Set<Position> list = new HashSet<>();
        int amountPositions = random.nextInt(Company.MAX_AMOUNT_POSITIONS) + Company.MIN_AMOUNT_POSITIONS; //количество должностей

        for (int i = 0; i <= amountPositions; i++) {
            int x = random.nextInt(Position.values().length); //выбор случайной должности
            if (Position.values()[x] == Position.Director) { //если выпала должность директора
                //проверяем, что директора еще требуются
                if (countDirectorsPositions > 0) {
                    list.clear(); //удаляем все предыдущие должности
                    list.add(Position.values()[x]);
                    countDirectorsPositions--;
                }
            } else list.add(Position.values()[x]); //добавление в список должности
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
