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
     * Метод проверяет корректно ли задается имя person при создании
     */
    @Test
    public void testGetPersonName() {
        assertEquals("TestPerson", person.getPersonName());
    }

    /**
     * Метод проверяет, что сотрудник сразу после создания не занят
     */
    @Test
    public void testIsBusy() {
        person = new Person("TestPerson");
        assertFalse(person.isBusy());
    }


    @Test
    public void testSetWorkHoursPerDay() {
        float hours = 6.1f;
        person.setWorkHoursPerDay(hours);
        assertEquals(hours, person.getWorkHoursPerDay(), 0.0f);
    }


    @Test
    public void testSetAmountHoursOneInstructions() {
        float hours = 1.1f;
        person.setAmountHoursOneInstructions(hours);
        assertEquals(hours, person.getAmountHoursOneInstructions(), 0.0f);
    }

}