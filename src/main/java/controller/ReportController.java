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
        generalAccountant = WorkController.INSTANCE.getGeneralAccountant();
        dataFile.add("Всего сотрудников : " + PersonController.INSTANCE.getPersonList().size());
        dataFile.add("Всего фрилансеров : " + PersonController.INSTANCE.getFreelancers().size());
        dataFile.add("-------------------------------------");
        dataFile.add("Всего выплачено сотрудникам : " + generalAccountant.getAllSalary());
        dataFile.add("Всего выплачено фрилансерам : " + generalAccountant.getAllSalaryFreelancers());

        try (FileWriter fileWriter = new FileWriter(fileName, false)) {
            for (String writeStr : dataFile) {
                fileWriter.write(writeStr);
                fileWriter.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
