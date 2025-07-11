import controllers.TaskManager;
import model.*;

public class Main {

    public static void main(String[] args) {

        TaskManager taskMeneger = new TaskManager();


        System.out.println("Поехали!");

        System.out.println("");
        System.out.println("ХХХХХХХХХХХХХХХХХХХХХХ");
        System.out.println("");

        Task task1 = new Task("Задача 1", "Описание задачи 1", StatusList.IN_PROGRESS);
        Task task2 = new Task("Задача 2", "Описание задачи 2", StatusList.IN_PROGRESS);
        Epic epic1 = new Epic("Эпик 1", "Описание эпик задачи 1", StatusList.DONE);
        Subtask subtask1 = new Subtask("Подзадача 1", "Описание подзадачи 11",
                StatusList.NEW, "Эпик 1");
        Subtask subtask2 = new Subtask("Подзадача 2", "Описание подзадачи 21",
                StatusList.DONE, "Эпик 1");
        Epic epic2 = new Epic("Эпик 2", "Описание эпик задачи 2", StatusList.IN_PROGRESS);
        Subtask subtask3 = new Subtask("Подзадача 3", "Описание подзадачи 12",
                StatusList.IN_PROGRESS, "Эпик 2");


        System.out.println("Создание 2 обычныйх задач, 1 эпика с 2 позадачами, 1 эпика с 1 подзадачей");
        System.out.println("");
        taskMeneger.addTask(task1);
        taskMeneger.addTask(task2);

        taskMeneger.addEpic(epic1);
        taskMeneger.addEpic(epic2);

        taskMeneger.addSubtask(subtask1);
        taskMeneger.addSubtask(subtask2);
        taskMeneger.addSubtask(subtask3);

        System.out.println("ХХХХХХХХХХХХХХХХХХХХХХ");
        System.out.println("");

        System.out.println("Список всех задач:");
        for (Task task : taskMeneger.getTasks()) {
            System.out.println(task);
        }
        System.out.println("");
        System.out.println("Список всех эпиков:");
        for (Task task : taskMeneger.getEpics()) {
            System.out.println(task);
            System.out.println("");
        }
        System.out.println("Список всех подзадач:");
        for (Task task : taskMeneger.getSubtasks()) {
            System.out.println(task);
        }

        System.out.println("");
        System.out.println("ХХХХХХХХХХХХХХХХХХХХХХ");
        System.out.println("");

        System.out.println("Изменение задачи:");
        System.out.println("Было");
        System.out.println(taskMeneger.getTaskById(1));
        System.out.println("Стало");
        task1 = new Task("Задача 4", "Описание задачи 4", StatusList.NEW);
        taskMeneger.updateTask(task1, 1);
        System.out.println(taskMeneger.getTaskById(1));

        System.out.println("");
        System.out.println("ХХХХХХХХХХХХХХХХХХХХХХ");
        System.out.println("");

        System.out.println("Изменение эпика:");
        System.out.println("Было");
        System.out.println(taskMeneger.getEpicById(3));
        System.out.println("Стало");
        epic1 = new Epic("Эпик 3", "Описание эпик задачи 3", StatusList.NEW);
        taskMeneger.updateEpic(epic1, 3);
        System.out.println(taskMeneger.getEpicById(3));

        System.out.println("");
        System.out.println("ХХХХХХХХХХХХХХХХХХХХХХ");
        System.out.println("");

        System.out.println("Изменение подзадачи:");
        System.out.println("Было");
        System.out.println(taskMeneger.getEpicById(3));
        System.out.println(taskMeneger.getEpicById(4));
        System.out.println("");
        System.out.println("Стало");
        subtask2 = new Subtask("Подзадача 4", "Описание подзадачи 21",
                StatusList.NEW, "Эпик 2");
        taskMeneger.updateSubtask(subtask2, 5);
        System.out.println(taskMeneger.getEpicById(3));
        System.out.println(taskMeneger.getEpicById(4));

        System.out.println("");
        System.out.println("ХХХХХХХХХХХХХХХХХХХХХХ");
        System.out.println("");

        System.out.println("Удаление задачи:");
        taskMeneger.removeTaskById(2);
        System.out.println("Список всех задач:");
        for (Task task : taskMeneger.getTasks()) {
            System.out.println(task);
        }
        System.out.println("");

        System.out.println("");
        System.out.println("ХХХХХХХХХХХХХХХХХХХХХХ");
        System.out.println("");

        System.out.println("Удаление подзадачи:");
        taskMeneger.removeSubtaskById(6);
        System.out.println("Список всех подзадач:");
        for (Task task : taskMeneger.getSubtasks()) {
            System.out.println(task);
        }

        System.out.println("");
        System.out.println("ХХХХХХХХХХХХХХХХХХХХХХ");
        System.out.println("");

        System.out.println("Удаление эпика:");
        taskMeneger.removeEpicById(4);
        System.out.println("Список всех эпиков:");
        for (Task task : taskMeneger.getEpics()) {
            System.out.println(task);
            System.out.println("");
        }
        System.out.println("Список всех подзадач:");
        for (Task task : taskMeneger.getSubtasks()) {
            System.out.println(task);
        }

    }
}
