package controller;

import model.Positions.Accountant;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс отвечающий за формирование отчета
 */
public class ReportController {
    public static final ReportController INSTANCE = new ReportController();
    private static Accountant generalAccountant; //главный бухгалтер
    private String fileName;
    private List<String> dataFile;

    /**
     * Доступ к контроллеру осуществляется через INSTANCE
     */
    private ReportController() {}

    public void runReportController() {
        fileName = "report.txt";
        dataFile = new ArrayList<>();

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
        dataFile.add("Сотрудников :            " + PersonController.INSTANCE.getPersonList().size());
        dataFile.add("   Из них :");
        dataFile.add("      Директоров :       ");
        dataFile.add("      Программистов :    ");
        dataFile.add("      Дизайнеров :       ");
        dataFile.add("      Тестировщиков :    ");
        dataFile.add("      Менеджеров :       ");
        dataFile.add("      Бухгалтеров :      ");
        dataFile.add("Фрилансеров :            " + PersonController.INSTANCE.getFreelancers().size());
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
