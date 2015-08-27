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
    private ReportController() {
    }

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
                    case "Director":
                        directors++;
                        break;
                    case "Programmer":
                        programmers++;
                        break;
                    case "Designer":
                        designers++;
                        break;
                    case "Tester":
                        testers++;
                        break;
                    case "Manager":
                        managers++;
                        break;
                    case "Accountant":
                        accountants++;
                        break;
                    default:
                        break;
                }
            }
        }
        dataFile.add("Сотрудников :             " + persons);
        dataFile.add("   Из них с должностью :");
        dataFile.add("      Директора :         " + directors);
        dataFile.add("      Программиста :      " + programmers);
        dataFile.add("      Дизайнера :         " + designers);
        dataFile.add("      Тестировщика :      " + testers);
        dataFile.add("      Менеджера :         " + managers);
        dataFile.add("      Бухгалтера :        " + accountants);
        dataFile.add("Фрилансеров :             " + freelancers);
    }

    protected void amountPersonsWork() {
        int personsTasks = 0;
        int freelancersTasks = freelancersList.size();

        int directorsTasks = 0;
        int programmersTasks = 0;
        int designersTasks = 0;
        int testersTasks = 0;
        int managersTasks = 0;
        int accountantsTasks = 0;

        double personsHours = 0;
        double freelancersHours = 0;

        double directorsHours = 0;
        double programmersHours = 0;
        double designersHours = 0;
        double testersHours = 0;
        double managersHours = 0;
        double accountantsHours = 0;

        for (Freelancer freelancer : freelancersList) {
            freelancersHours += freelancer.getAllHoursWorked();
        }

        for (Map.Entry<Person, Set<Position>> person : personList.entrySet()) {
            personsTasks += person.getKey().getTaskList().size();
            personsHours += person.getKey().getAllHoursWorked();
            Map<Position, APosition> listPositions = person.getKey().getListPositions();
            for (Map.Entry<Position, APosition> personAPositionEntry : listPositions.entrySet()) {
                switch (personAPositionEntry.getKey().toString()) {
                    case "Director":
                        Director director = (Director) personAPositionEntry.getValue();
                        directorsTasks += director.getCountTasks();
                        directorsHours += director.getAllHoursWorked();
                        break;
                    case "Programmer":
                        Programmer programmer = (Programmer) personAPositionEntry.getValue();
                        programmersTasks += programmer.getCountTasks();
                        programmersHours += programmer.getAllHoursWorked();
                        break;
                    case "Designer":
                        Designer designer = (Designer) personAPositionEntry.getValue();
                        designersTasks += designer.getCountTasks();
                        designersHours += designer.getAllHoursWorked();
                        break;
                    case "Tester":
                        Tester tester = (Tester) personAPositionEntry.getValue();
                        testersTasks += tester.getCountTasks();
                        testersHours += tester.getAllHoursWorked();
                        break;
                    case "Manager":
                        Manager manager = (Manager) personAPositionEntry.getValue();
                        managersTasks += manager.getCountTasks();
                        managersHours += manager.getAllHoursWorked();
                        break;
                    case "Accountant":
                        Accountant accountant = (Accountant) personAPositionEntry.getValue();
                        accountantsTasks += accountant.getCountTasks();
                        accountantsHours += accountant.getAllHoursWorked();
                        break;
                    default:
                        break;
                }
            }
        }

        dataFile.add("                   Отработано часов   Выполнено заданий");
        dataFile.add(String
                .format("Сотрудниками :            %.2f            %d", personsHours, personsTasks));
        dataFile.add("   Из них :");
        dataFile.add(String
                .format("      Директорами :       %.2f            %d", directorsHours, directorsTasks));
        dataFile.add(String
                .format("      Программистами :    %.2f            %d", programmersHours, programmersTasks));
        dataFile.add(String
                .format("      Дизайнерами :       %.2f            %d", designersHours, designersTasks));
        dataFile.add(String
                .format("      Тестировщиками :    %.2f            %d", testersHours, testersTasks));
        dataFile.add(String
                .format("      Менеджерами :       %.2f            %d", managersHours, managersTasks));
        dataFile.add(String
                .format("      Бухгалтерами :      %.2f            %d", accountantsHours, accountantsTasks));
        dataFile.add(String
                .format("Фрилансерами :            %.2f            %d", freelancersHours, freelancersTasks));
    }

    protected void amountPersonsSalary() {
        generalAccountant = WorkController.INSTANCE.getGeneralAccountant();
        dataFile.add("Сотрудникам :             " + generalAccountant.getAllSalary());
        dataFile.add("   Из них :");
        dataFile.add("      Директорам :        ");
        dataFile.add("      Программистам :     ");
        dataFile.add("      Дизайнерам :        ");
        dataFile.add("      Тестировщикам :     ");
        dataFile.add("      Менеджерам :        ");
        dataFile.add("      Бухгалтерам :       ");
        dataFile.add("Фрилансерам :             " + generalAccountant.getAllSalaryFreelancers());
    }

}
