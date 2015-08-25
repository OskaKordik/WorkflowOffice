package model.Positions;

import model.Employee;
import model.Person;
import model.Position;

import java.security.SecureRandom;
import java.util.Map;
import java.util.Set;

/**
 * Класс должности Директор
 */
public class Director extends APosition implements Employee {
    private float fixedRate; //фиксированная ставка
    private Map<Position, String> taskList; //список распоряжений для сотрудников
    private Map<Person, Set<Position>> personList; //список сотрудников под руководством данного директора
    private static final SecureRandom random = new SecureRandom();

    public Director(String name) {
        super(name);
    }

    /**
     * Метод, устанавливающий фиксированную ставку
     *
     * @param fixedRate
     */
    @Override
    public void setFixedRate(float fixedRate) {
        this.fixedRate = fixedRate;
    }

    /**
     * Метод в котором выполняется работа должности
     */
    @Override
    public void getToWork() {
        if ((taskList != null) && (personList != null)) {
            //раздает всем задания
            int amountTasks = random.nextInt(taskList.size()) + 1; //случайное количество распоряжений

            for (int i = 0; i < amountTasks; i++) {
                int x = random.nextInt(Position.values().length); //выбор случайной должности
                if (Position.values()[x] == Position.Director) continue; //если должность директора идем дальше
                Position currentPosition = Position.values()[x];

                for (Map.Entry<Person, Set<Position>> person : personList.entrySet()) {
                    Person currentPerson = person.getKey(); //текущий сотрудник

                    if (!currentPerson.isBusy() && currentPerson.isWork() && person.getValue().contains(currentPosition))
                        currentPerson.performTask(currentPosition, taskList.get(currentPosition)); //даем задание
                }
            }
        }
    }

    /**
     * Метод для получения зарплаты
     *
     * @return сумму зарплаты
     */
    @Override
    public double paySalary() {
        //отчитывается о получении зарплаты
        System.out.println("Я получил зарплату! ");
        return getFixedRate();
    }

    /**
     * Метод возвращает фиксированную ставку
     *
     * @return fixedRate
     */
    @Override
    public float getFixedRate() {
        return fixedRate;
    }

    public void setPersonList(Map<Person, Set<Position>> personList) {
        this.personList = personList;
    }

    public Map<Person, Set<Position>> getPersonList() {
        return personList;
    }

    public void setTaskList(Map<Position, String> taskList) {
        this.taskList = taskList;
    }
}
