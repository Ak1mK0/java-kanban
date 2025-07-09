public class Main {

    public static void main(String[] args) {

        TaskManager taskMeneger = new TaskManager();


        System.out.println("Поехали!");

        System.out.println("");
        System.out.println("ХХХХХХХХХХХХХХХХХХХХХХ");
        System.out.println("");

        Task task1 = new Task("Задача 1", "Описание задачи 1", StatusList.IN_PROGRESS);
        Task task2 = new Task("Задача 2", "Описание задачи 2", StatusList.IN_PROGRESS);
        Task epic1 = new Epic("Эпик 1", "Описание эпик задачи 1", StatusList.NEW);
        Task subtask1 = new Subtask("Подзадача 1", "Описание подзадачи 11",
                StatusList.IN_PROGRESS, "Эпик 1");
        Task subtask2 = new Subtask("Подзадача 2", "Описание подзадачи 21",
                StatusList.NEW, "Эпик 1");
        Task epic2 = new Epic("Эпик 2", "Описание эпик задачи 2", StatusList.IN_PROGRESS);
        Task subtask3 = new Subtask("Подзадача 3", "Описание подзадачи 12",
                StatusList.DONE, "Эпик 2");


        System.out.println("Создание 2 обычныйх задач, 1 эпика с 2 позадачами, 1 эпика с 1 подзадачей");
        System.out.println("");
        taskMeneger.addTask(task1, 1);
        taskMeneger.addTask(task2, 2);

        taskMeneger.addTask(epic1, 3);
        taskMeneger.addTask(epic2, 4);

        taskMeneger.addTask(subtask1, 5);
        taskMeneger.addTask(subtask2, 6);
        taskMeneger.addTask(subtask3, 7);
        taskMeneger.printAll();

        System.out.println("");
        System.out.println("ХХХХХХХХХХХХХХХХХХХХХХ");
        System.out.println("");

        System.out.println("Меняем обычную задачу");
        task1 = new Task("Задача 3", "Описание задачи 3", StatusList.DONE);
        System.out.println("Было");
        taskMeneger.printById(1);
        taskMeneger.updateTask(task1, 1);
        System.out.println("Стало");
        taskMeneger.printById(1);

        System.out.println("");
        System.out.println("ХХХХХХХХХХХХХХХХХХХХХХ");
        System.out.println("");

        System.out.println("Меняем эпик задачу");
        epic1 = new Epic("Эпик 3", "Описание эпик задачи 3", StatusList.NEW);
        System.out.println("Было");
        taskMeneger.printById(3);
        taskMeneger.updateTask(epic1, 3);
        System.out.println("Стало");
        taskMeneger.printById(3);

        System.out.println("");
        System.out.println("ХХХХХХХХХХХХХХХХХХХХХХ");
        System.out.println("");

        System.out.println("Меняем подзадачу");
        subtask1 = new Subtask("Подзадача 4", "Описание подзадачи 42",
                StatusList.IN_PROGRESS, "Эпик 2");
        System.out.println("Было");
        taskMeneger.printById(6);
        System.out.println("");
        taskMeneger.printById(3);
        System.out.println("");
        taskMeneger.printById(4);
        System.out.println("");

        taskMeneger.updateTask(subtask1, 6);
        System.out.println("Стало");
        taskMeneger.printById(6);
        System.out.println("");
        taskMeneger.printById(3);
        System.out.println("");
        taskMeneger.printById(4);

        System.out.println("");
        System.out.println("ХХХХХХХХХХХХХХХХХХХХХХ");
        System.out.println("");

        System.out.println("Удаление обычной задачи");
        System.out.println("Было");
        taskMeneger.printAll();
        taskMeneger.removeById(1);
        System.out.println("Стало");
        taskMeneger.printAll();

        System.out.println("");
        System.out.println("ХХХХХХХХХХХХХХХХХХХХХХ");
        System.out.println("");

        System.out.println("Удаление подзадачи");
        System.out.println("Было");
        taskMeneger.printAll();
        taskMeneger.removeById(5);
        System.out.println("Стало");
        taskMeneger.printAll();

        System.out.println("");
        System.out.println("ХХХХХХХХХХХХХХХХХХХХХХ");
        System.out.println("");

        System.out.println("Удаление эпик задачи");
        System.out.println("Было");
        taskMeneger.printAll();
        taskMeneger.removeById(4);
        System.out.println("Стало");
        taskMeneger.printAll();

        System.out.println("");
        System.out.println("ХХХХХХХХХХХХХХХХХХХХХХ");
        System.out.println("");
    }
}
