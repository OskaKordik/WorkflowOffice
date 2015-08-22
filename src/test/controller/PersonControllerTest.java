package controller;

import model.Person;
import model.Position;
import org.junit.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertNotNull;

/**
 * Тестирует работу контроллера класса Person
 */
public class PersonControllerTest {

    /**
     * Тест проверяет наличие списка сотрудников после запуска
     * класса контроллера
     * @throws Exception
     */
    @Test
    public void testRunPersonController() throws Exception {
        PersonController.INSTANCE.runPersonController();
        assertNotNull(PersonController.INSTANCE.getPersonList());
    }

    @Test
    public void testSetNecessaryPositions() throws Exception {

    }

    @Test
    public void testSetRandomPositions() throws Exception {
        assertNotNull(PersonController.INSTANCE.setRandomPositions());
    }

    @Test
    public void testCreatePerson() throws Exception {
        assertNotNull(PersonController.INSTANCE.createPerson("TestPerson"));
    }

    @Test
    public void testEditingPersonPositions() throws Exception {
        Map<Person, Set<Position>> testList = PersonController.INSTANCE.createRandomPerson();
        PersonController.INSTANCE.editingPersonPositions(testList, Position.Director);

        assertNotNull(PersonController.INSTANCE.getPersonList());
    }


    @Test
    public void testCreateRandomPerson() throws Exception {
        assertNotNull(PersonController.INSTANCE.createRandomPerson());
    }
}