package controller;

import model.Person;
import model.Position;
import model.Positions.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Класс отвечающий за формирование отчета
 */
public class ReportController {
    public static final ReportController INSTANCE = new ReportController();
    private static Accountant generalAccountant; //главный бухгалтер
    private Map<Person, Set<Position>> personList; //список сотрудников
    private Set<Freelancer> freelancersList; //список фрилансеров
    private String fileName;
    private List<String> dataFile;

    /**
     * Доступ к контроллеру осуществляется через INSTANCE
     */
    private ReportController() {}

    public void runReportController() {
        fileName = "report.txt";
        dataFile = new ArrayList<>();
        personList = PersonController.INSTANCE.getPersonList();
        freelancersList = PersonController.INSTANCE.getFreelancers();

        dataFile.add("---------------- КОЛИЧЕСТВО РАБОТНИКОВ ----------------");
        amountPersons();
        dataFile.add("-------------------------------------------------------");
        dataFile.add("---------------- ВЫПОЛНЕННАЯ РАБОТА -------------------");
        amountPersonsWork();
        dataFile.add("-------------------------------------------------------");
        dataFile.add("---------------- ВСЕГО ВЫПЛАЧЕНО ----------------------");
        amountPersonsSalary();
        dataFile.add("-------------------------------------------------------");

        try (FileWriter fileWriter = new FileWriter(fileName, false)) {
            for (String writeStr : dataFile) {
                fileWriter.write(writeStr);
                fileWriter.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void amountPersons() {
        int persons = personList.size();
        int directors = 0;
        int programmers = 0;
        int designers = 0;
        int testers = 0;
        int managers = 0;
        int accountants = 0;
        int freelancers = freelancersList.size();

        for (Map.Entry<Person, Set<Position>> person : personList.entrySet()) {
            Set<Position> positionSet = person.getValue();
            for (Position position : positionSet) {
                switch (position.toString()) {
                    case "Director" : directors++;
                        break;
                    case "Programmer" : programmers++;
                        break;
                    case "Designer" : designers++;
                        break;
                    case "Tester" : testers++;
                        break;
                    case "Manager" : managers++;
                        break;
                    case "Accountant" : accountants++;
                        break;
                    default: break;
                }
            }
        }
        dataFile.add("Сотрудников :            " + persons);
        dataFile.add("   Из них с должностью :");
        dataFile.add("      Директора :        " + directors);
        dataFile.add("      Программиста :     " + programmers);
        dataFile.add("      Дизайнера :        " + designers);
        dataFile.add("      Тестировщика :     " + testers);
        dataFile.add("      Менеджера :        " + managers);
        dataFile.add("      Бухгалтера :       " + accountants);
        dataFile.add("Фрилансеров :            " + freelancers);
    }

    protected void amountPersonsWork() {
        dataFile.add("                   Отработано часов   Выполнено заданий");
        dataFile.add("Сотрудниками :           ");
        dataFile.add("   Из них :");
        dataFile.add("      Директорами :      ");
        dataFile.add("      Программистами :   ");
        dataFile.add("      Дизайнерами :      ");
        dataFile.add("      Тестировщиками :   ");
        dataFile.add("      Менеджерами :      ");
        dataFile.add("      Бухгалтерами :     ");
        dataFile.add("Фрилансерами :           ");
    }

    protected void amountPersonsSalary() {
        generalAccountant = WorkController.INSTANCE.getGeneralAccountant();
        dataFile.add("Сотрудникам :            " + generalAccountant.getAllSalary());
        dataFile.add("   Из них :");
        dataFile.add("      Директорам :       ");
        dataFile.add("      Программистам :    ");
        dataFile.add("      Дизайнерам :       ");
        dataFile.add("      Тестировщикам :    ");
        dataFile.add("      Менеджерам :       ");
        dataFile.add("      Бухгалтерам :      ");
        dataFile.add("Фрилансерам :            " + generalAccountant.getAllSalaryFreelancers());
    }

}
