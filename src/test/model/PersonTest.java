package model;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Тестирует работу класса Person
 */
public class PersonTest {
    private static Person person;

    /**
     * Метод инициализирующий объект класса Field
     * вызывается перед тестами
     */
    @BeforeClass
    public static void setUpBeforeClass() {
        person = new Person("TestPerson");
    }

    /**
     * Тест проверяет корректно ли задается имя person при создании
     */
    @Test
    public void testGetPersonName() {
        assertEquals("TestPerson", person.getPersonName());
    }

    /**
     * Тест проверяет, что сотрудник сразу после создания не занят
     */
    @Test
    public void testIsBusy() {
        person = new Person("TestPerson");
        assertFalse(person.isBusy());
    }


    /**
     * Тест проверяет корректно ли задается кол-во рабочих часов в день
     */
    @Test
    public void testSetWorkHoursPerDay() {
        float hours = 6.1f;
        person.setWorkHoursPerDay(hours);
        assertEquals(hours, person.getWorkHoursPerDay(), 0.0f);
    }


    /**
     * Тест проверяет корректно ли задается кол-во времени на выполнение одного задания
     */
    @Test
    public void testSetAmountHoursOneInstructions() {
        float hours = 1.1f;
        person.setAmountHoursOneInstructions(hours);
        assertEquals(hours, person.getAmountHoursOneInstructions(), 0.0f);
    }

}