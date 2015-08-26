package controller;

import model.Positions.Accountant;

/**
 * Класс отвечающий за формирование отчета
 */
public class ReportController {
    public static final ReportController INSTANCE = new ReportController();
    private static Accountant generalAccountant; //главный бухгалтер

    /**
     * Доступ к контроллеру осуществляется через INSTANCE
     */
    private ReportController() {}

    public void runReportController() {
        generalAccountant = WorkController.INSTANCE.getGeneralAccountant();
        System.out.println("Всего сотрудников : " + PersonController.INSTANCE.getPersonList().size());
        System.out.println("Всего фрилансеров : " + PersonController.INSTANCE.getFreelancers().size());
        System.out.println("-------------------------------------");
        System.out.println("Всего выплачено сотрудникам : " + generalAccountant.getAllSalary());
        System.out.println("Всего выплачено фрилансерам : " + generalAccountant.getAllSalaryFreelancers());
    }
}
