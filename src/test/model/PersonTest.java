package model;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Тестирует работу класса Person
 */
public class PersonTest {

    /**
     * Тест проверяет корректное заполнение списка должностей при создании сотрудника
     */
    @Test
    public void test_getPositionList() {
        Person person = new Person("Vasya");

        assertTrue((person.getPositionList().size() > 0) && (person.getPositionList().size() <= 7));
    }
}