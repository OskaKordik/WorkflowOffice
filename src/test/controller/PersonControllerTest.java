package controller;

import model.Person;
import model.Position;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Тестирует работу контроллера класса Person
 */
public class PersonControllerTest {

    /**
     * Тест проверяет наличие списка сотрудников после запуска
     * класса контроллера
     */
    @Test
    public void testRunPersonController() {
        PersonController.INSTANCE.runPersonController();
        assertNotNull(PersonController.INSTANCE.getPersonList());
    }

    @Test
    public void testSetNecessaryPositions() {
        Set<Position> positions = new HashSet<>();
        positions.add(Position.Accountant);
        positions.add(Position.Designer);
        PersonController.INSTANCE.setNecessaryPositions(positions);

        assertNotNull(PersonController.INSTANCE.getNecessaryPositions());
    }

    @Test
    public void testSetRandomPositions() {
        assertNotNull(PersonController.INSTANCE.setRandomPositions());
    }

    @Test
    public void testCreatePerson() {
        assertNotNull(PersonController.INSTANCE.createPerson("TestPerson"));
    }


    @Test
    public void testEditingPersonPositions() {
        PersonController.INSTANCE.runPersonController();
        Map<Person, Set<Position>> testList = PersonController.INSTANCE.getPersonList();

        PersonController.INSTANCE.editingPersonPositions(testList, Position.Director);

        assertNotNull(PersonController.INSTANCE.getPersonList());
    }

    @Test
    public void testSizeListPersonsAfterEditingPersonPositions() {
        Map<Person, Set<Position>> testList = new HashMap<>();
        Set<Position> positions = new HashSet<>();
        positions.add(Position.Accountant);
        positions.add(Position.Designer);

        testList.put(PersonController.INSTANCE.createPerson("TestPerson"), positions);

        int testListCount = testList.size();
        PersonController.INSTANCE.editingPersonPositions(testList, Position.Director);
        int testListCountAfterEditing = testList.size();

        assertEquals(testListCount + 1, testListCountAfterEditing);
    }


    @Test
    public void testCreateRandomPerson() {
        assertNotNull(PersonController.INSTANCE.createRandomPerson());
    }

    @Test
    public void testIsContainsPosition() {
        Map<Person, Set<Position>> testList = PersonController.INSTANCE.createRandomPerson();
        Person testPerson = PersonController.INSTANCE.createPerson("TestPerson");
        Set<Position> positionList = new HashSet<>();
        positionList.add(Position.Director);
        testList.put(testPerson, positionList); //добавляем в список сотрудника с определенной должностью

        assertTrue(PersonController.INSTANCE.isContainsPosition(testList, Position.Director));
    }
}