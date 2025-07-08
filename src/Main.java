public class Main {

    public static void main(String[] args) {

        TaskManager taskMeneger = new TaskManager();
        System.out.println("Поехали!");


        Task task = new Task("Задача 1", "Описание задачи 1", StatusList.IN_PROGRESS, 1);
        taskMeneger.addTask(task);

        task = new Task("Задача 2", "Описание задачи 2", StatusList.IN_PROGRESS, 2);
        taskMeneger.addTask(task);

        Task epic = new Epic("Эпик 1", "Описание эпик задачи 1", StatusList.NEW, 3);
        taskMeneger.addTask(epic);

        epic = new Epic("Эпик 2", "Описание эпик задачи 2", StatusList.IN_PROGRESS, 4);
        taskMeneger.addTask(epic);

        Task subtask = new Subtask("Подзадача 1 для Эпик 1", "Описание подзадачи 1",
                StatusList.IN_PROGRESS,5, "Эпик 1");
        taskMeneger.addTask(subtask);

        subtask = new Subtask("Подзадача 2 для Эпик 1", "Описание подзадачи 2",
                StatusList.DONE,6, "Эпик 1");
        taskMeneger.addTask(subtask);

        subtask = new Subtask("Подзадача 3 для Эпик 2", "Описание подзадачи 3",
                StatusList.DONE,7, "Эпик 2");
        taskMeneger.addTask(subtask);

        System.out.println("-----------------------");
        System.out.println("Список всех задач");
        taskMeneger.printAll();

        System.out.println("-----------------------");
        System.out.println("Текущая `Задача 1`");
        taskMeneger.printById(1);
        System.out.println("Обновление `Задача 1` в `Задача 4`");
        task = new Task("Задача 4", "Бывшая задача 1", StatusList.DONE, 8);
        taskMeneger.updateTask(1, task);
        taskMeneger.printById(8);

        System.out.println("-----------------------");
        System.out.println("Текущая `Эпик 1`");
        taskMeneger.printById(3);
        System.out.println("Обновление `Эпик 1` в `Эпик 4`");
        epic = new Task("Эпик 4", "Бывшая эпик 1", StatusList.DONE, 9);
        taskMeneger.updateTask(3, epic);
        taskMeneger.printById(9);

          System.out.println("-----------------------");
//        System.out.println("Удаление `Задача 4`");
//        taskMeneger.removeById(1673999774);
          taskMeneger.printAll();

//        System.out.println("-----------------------");
//        System.out.println("Удаление `Подзадача 1 для Эпик 1`");
//        taskMeneger.removeById(1932409137);
//        taskMeneger.printAll();
//
//        System.out.println("-----------------------");
//        System.out.println("Удаление `Эпик 1`");
//        taskMeneger.removeById(362184061);
//        taskMeneger.printAll();
//
//        System.out.println("-----------------------");
//        System.out.println("Очистить список задач");
//        taskMeneger.removeAll();
//        taskMeneger.printAll();
//
//        System.out.println("-----------------------");
//        System.out.println("Выход");
    }
}
