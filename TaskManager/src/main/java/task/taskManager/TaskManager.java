package task.taskManager;

import task.structure.Student;
import task.structure.Status;
import task.structure.Task;

import java.io.*;
import java.util.*;

/**
 * Класс менеджер задач, служит для управления системой ведения задач
 */
public class TaskManager {
    /**
     * Предметы
     */
    private Map<Integer, String> subjects = new HashMap<>();
    /**
     * Номер предмета в map
     */
    private int idSubject = 1;
    /**
     * номер сотрудника в map
     */
    private int idStudent = 0;
    /**
     * вспомогательная переменная для инкремента
     */
    private int idTask = 0;
    /**
     * Номер задачи в map и id задачи
     */
    private int id = 0;
    /**
     * файл, в котором хранятся сохраненные задачи
     */
    private File file = new File("tasks/tasks.txt");
    /**
     * открывается поток записи байтов в файл
     */
    private FileOutputStream outputStream = new FileOutputStream(file);
    /**
     * для сериализации объектов в поток
     */
    private ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
    /**
     * список задач
     */
    private Map<Integer, Task> tasks = new HashMap<Integer, Task>();
    /**
     * список сотрудников
     */
    private Map<Integer, Student> students = new HashMap();
    /**
     * сканнер
     */
    private Scanner scanner = new Scanner(System.in);

    public TaskManager() throws IOException {
    }

    /**
     * newTask создает новую задачу
     *
     * @param name - наименование задачи
     * @param desc - описание задачи
     */
    public void newTask(String name, String desc, String subject) {
        // если список сотруников не пуст, то предлагаем пользователю выбрать сотрудника для новой задачи из списка
        if (!students.isEmpty()) {
            //вывод списка сотрудников
            getSoutStudents();
            //выбор сотрудника(номер)
            int chooseStudent = Menu.checkIntInput();
            //id задачи
            id = ++idTask;
            //создаем новую задачу с данным id и записываем ее в наш список всех задач
            tasks.put(id, new Task(id, name, desc, students.get(chooseStudent), subject, Status.New));
        } else {
            //если ни одного сотрудника не было создано, то создаем
            System.out.println(Titles.strEmpEmpty.getDesc());
            id = ++idTask;
            String nameSec = scanner.nextLine();
            System.out.println("Введите фамилию:");
            String nameLastSec = scanner.nextLine();
            //добавляем нового сотрудника и добавляем в список всех сотрудников
            students.put(++idStudent, new Student(nameSec, nameLastSec));
            //создаем задачу с данным сотрудников и добавляем в список всех задач
            tasks.put(id, new Task(id, name, desc, students.get(idStudent), subject, Status.New));
        }
    }

    /**
     * changeTaskTitle изменяет наименование, выбранной задачи
     *
     * @param numOfChangeTask - задача, выбранная пользователем
     * @param title           - наименование задачи
     */
    public void changeTaskTitle(int numOfChangeTask, String title) {
        tasks.get(numOfChangeTask).setTitle(title);
    }

    /**
     * changeTaskDesc изменяет описание, выбранной задачи
     *
     * @param numOfChangeTask - задача, выбранная пользователем
     * @param desc            - описание задачи
     */
    public void changeTaskDesc(int numOfChangeTask, String desc) {
        tasks.get(numOfChangeTask).setDescription(desc);
    }

    /**
     * changeTaskNewStudent переназначает сотрудника к определенной задаче
     *
     * @param numOfChangeTask - задача, выбранная пользователем
     * @param numOfStudent   - новый (выбранный) сотрудник
     */
    public void changeTaskNewStudent(int numOfChangeTask, int numOfStudent) {
        tasks.get(numOfChangeTask).setStudent(students.get(numOfStudent));
    }



    /**
     * changeTaskStatus изменяет статус задачи
     *
     * @param numOfChangeTask - задача, выбранная пользователем
     * @param status          - статус
     */
    public void changeTaskStatus(int numOfChangeTask, int status) {
        tasks.get(numOfChangeTask).setStatus(Status.getStatuses().get(status));
    }

    /**
     * new создает нового сотрудника
     *
     * @param name     - имя
     * @param lastName - фамилия
     */
    public void newStudent(String name, String lastName) {
        students.put(++idStudent, new Student(name, lastName));
    }

    /**
     * changeNameOfStudent изменяет мя сотрудника
     *
     * @param chooseChangeStudent - сотрудник, выбранный пользователем
     * @param name                 - имя
     */
    public void changeNameOfStudent(int chooseChangeStudent, String name) {
        students.get(chooseChangeStudent).setName(name);
    }

    /**
     * changeLastNameOfStudent изменяет фамилию сотрудника
     *
     * @param chooseChangeStudent - сотрудник, выбранный пользователем
     * @param lastName             - фамилия
     */
    public void changeLastNameOfStudent(int chooseChangeStudent, String lastName) {
        students.get(chooseChangeStudent).setLastName(lastName);
    }

    /**
     * deleteStudent удаляет сотрудника
     *
     * @param chooseChangeStudent - сотрудник, выбранный пользователем
     */
    public void deleteStudent(int chooseChangeStudent) {
        students.remove(chooseChangeStudent);
    }

    /**
     * Метод по добавлению нового предмета
     * @param subject - предмет
     */
    public void newSubject(String subject) {
        int id = idSubject++;
        subjects.put(id,subject);
    }

    /**
     * Метод по удалению предмета
     * @param k - номер предмета
     */
    public void deleteSubject(Integer k){
        subjects.remove(k);
    }
    /**
     * deleteTask удаляет задачу
     *
     * @param chooseDeleteTask - задача, выбранная пользователем
     */

    public void deleteTask(int chooseDeleteTask) {
        tasks.remove(chooseDeleteTask);
    }

    /**
     * saveTask сохраняет, выбранную задачу
     *
     * @param chooseSaveTask - задача, выбранная пользователем
     * @throws IOException
     */
    public void saveTask(int chooseSaveTask) throws IOException {
        objectOutputStream.writeObject(tasks.get(chooseSaveTask));
    }

    /**
     * closeThread() закрывает поток записи в файл
     *
     * @throws IOException
     */
    public void closeThread() throws IOException {
        outputStream.close();
        objectOutputStream.close();
    }


    /**
     * Выводит список всех предметов
     */
    public void soutSubjects(){
        for (Integer k : subjects.keySet()){
            System.out.println(k + ". " + subjects.get(k));
        }
    }

    /**
     * восстанавливает все задачи
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void recoverTask() {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            while (true) {
                try {
                    Task savedTask = (Task) objectInputStream.readObject();
                    tasks.put(savedTask.getId(), savedTask);
                } catch (EOFException e) {
                    fileInputStream.close();
                    objectInputStream.close();

                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл отсуствует");
        } catch (EOFException e) {
            System.out.println("Файл пуст");
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * getSoutStudents выводит список всех сотрудников
     */
    public void getSoutStudents() {
        Set<Integer> keys = students.keySet();
        for (int set : keys) {
            System.out.println(set + ". " + students.get(set));
        }
    }

    /**
     * getSoutTasks выводит список всех задач
     */
    public void getSoutTasks() {
        Set<Integer> keys = tasks.keySet();
        for (int set : keys) {
            System.out.println(set + ". " + tasks.get(set));
        }
    }

    public void getStatus() {
        Set<Integer> keys = Status.getStatuses().keySet();
        for (int status : keys) {
            System.out.println(status + ". " + Status.getStatuses().get(status).getTitle());
        }
    }
    public Map<Integer, String> getSubjects() {
        return subjects;
    }
    public Map<Integer, Task> getTasks() {
        return tasks;
    }

    public Map<Integer, Student> getStudents() {
        return students;
    }

}
