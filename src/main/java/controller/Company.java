package controller;

import model.Position;

import java.util.HashSet;
import java.util.Set;

/**
 * Класс Компания - контроллирует работу компании
 */
public class Company {

    private static Set<Position> necessaryPositions; //список обязательных должностей

    public static void main(String[] args) {

        necessaryPositions = new HashSet<>(); //создаем список обязательных должностей
        necessaryPositions.add(Position.Director);
        necessaryPositions.add(Position.Manager);
        necessaryPositions.add(Position.Accountant);
        PersonController.INSTANCE.setNecessaryPositions(necessaryPositions);

        PersonController.INSTANCE.runPersonController(); //создаем сотрудников

        WorkController.INSTANCE.runPersonController(); //запускаем работу
    }

}
