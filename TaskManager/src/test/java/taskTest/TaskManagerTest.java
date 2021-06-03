package taskTest;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import task.structure.Student;
import task.structure.Status;
import task.structure.Task;
import task.taskManager.TaskManager;

import java.io.IOException;


public class TaskManagerTest {
    /**Инициализация менеджера задач*/
    TaskManager taskManager = new TaskManager();
    Student student = new Student("Arthur","Gasparyan");
    Task task = new Task(1,"Heyyou","FirstTask", student, "Мат.анализ", Status.New);

    public TaskManagerTest() throws IOException {
    }

    @DisplayName("Тест создания задачи")
    @Test
    public void newTaskTest(){
        /**
         * ДЛЯ СОЗДАНИЯ НОВОЙ ЗАДАЧИ СЛУЖИТ МЕТОД newTask() в классе TaskManager, но в процесс выполнения метода,
         * он просит пользователя ввести либо фамилию и имя нового сотрудника, если список сотрудников пуст,
         * иначе выбрать из списка сотрудников одного, поэтому я не могу проверить тестом данный метод.
         */
        taskManager.getTasks().put(1,task);
        //Проверка наименования задачи
        Assertions.assertEquals("Heyyou",taskManager.getTasks().get(1).getTitle());
        //Проверка описания задачи
        Assertions.assertEquals("FirstTask",taskManager.getTasks().get(1).getDescription());
        //Проверка сотрудника, назначенного на данную задачу
        Assertions.assertEquals(student,taskManager.getTasks().get(1).getStudent());
        //Проверка предмета, по которому назначена задача
        Assertions.assertEquals("Мат.анализ",taskManager.getTasks().get(1).getSubject());
        //Проверка статуса задачи
        Assertions.assertEquals("Новая",taskManager.getTasks().get(1).getStatus().getTitle());
    }
    @DisplayName("Тест проверки измененных данных задачи")
    @Test
    public void editedTaskTest(){
        taskManager.getTasks().put(1,task);
        //Изменение задачи
        taskManager.changeTaskTitle(1,"Task1");
        //Проверка измененного наименования задачи
        Assertions.assertEquals("Task1", taskManager.getTasks().get(1).getTitle());
        taskManager.changeTaskDesc(1,"ChangedTask");
        //Проверка измененного описания задачи
        Assertions.assertEquals("ChangedTask",taskManager.getTasks().get(1).getDescription());
        taskManager.changeTaskStatus(1,5);
        //Проверка измененного статуса задачи
        Assertions.assertEquals(Status.getStatuses().get(5).getTitle(),
                taskManager.getTasks().get(1).getStatus().getTitle());
        //Проверка удаления задачи
        taskManager.deleteTask(1);
        Assertions.assertFalse(taskManager.getTasks().containsKey(1));
    }
    @DisplayName("Тест на проверку сотрудника")
    @Test
    public void newEmployeeTest(){
        //Проверка создания нового сотрудника
        Student newStudent = new Student("Roman","Gasparyan");
        taskManager.newStudent("Roman","Gasparyan");
        Assertions.assertTrue(newStudent.equals(taskManager.getStudents().get(1)));
        //Проверка изменения созданного сотрудника
        taskManager.changeNameOfStudent(1,"Robert");
        Assertions.assertEquals("Robert",taskManager.getStudents().get(1).getName());
        taskManager.changeLastNameOfStudent(1,"Petrov");
        Assertions.assertEquals("Petrov",taskManager.getStudents().get(1).getLastName());
        //Проверка удаления сотрудника
        taskManager.deleteStudent(1);
        Assertions.assertFalse(taskManager.getStudents().containsKey(1));
    }
    @DisplayName("Тест на проверку сериализации")
    @Test
    public void saveRecoverTest() throws IOException, ClassNotFoundException {
        taskManager.saveTask(1);
        taskManager.closeThread();
        taskManager.deleteTask(1);
        Assertions.assertTrue(taskManager.getTasks().isEmpty());
    }
}
