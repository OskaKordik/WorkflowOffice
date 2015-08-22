package controller;

/**
 * Класс отвечающий за работу всех сотрудников
 */
public class WorkController {
    public static final WorkController INSTANCE = new WorkController();

    public void runPersonController() {
        try {
            //workMonth(); //запуск моделирования работы компании в течении месяца
            //workWeek(); //запуск моделирования работы компании в течении недели
            workDay(); //запуск моделирования работы компании в течении дня
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод, запускающий работу сотрудников в течении дня
     * @throws InterruptedException
     */
    private static void workDay() throws InterruptedException {

    }

    /**
     * Метод, запускающий работу сотрудников в течении недели
     * @throws InterruptedException
     */
    private static void workWeek() throws InterruptedException {
        for (int i = 0; i < 5; i++) workDay();
        //каждую неделю бухгалтер платит з/п - реализовать
    }

    /**
     * Метод, запускающий работу сотрудников в течении месяца
     * @throws InterruptedException
     */
    private static void workMonth() throws InterruptedException {
        for (int i = 0; i < 4; i++) workWeek();
        //формирование суммарного отчета + сохранение в файл - реализовать
    }
}
