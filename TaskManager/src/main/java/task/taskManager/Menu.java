package task.taskManager;

import java.io.IOException;
import java.util.*;

/**
 * Класс меню
 */
public class Menu {
    /**
     * менеджер задач
     */
    private TaskManager taskManager;
    /**
     * сканнер
     */
    private final Scanner scanner = new Scanner(System.in);
    /**
     * флаг выхода из меню
     */
    private boolean flag = false;

    /**
     * конструктор класса, инициализация менджера задач
     */
    public Menu() throws IOException {
        taskManager = new TaskManager();
    }

    /**
     * start запускат меню
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void start() throws IOException, ClassNotFoundException {
        do {
            System.out.println(Titles.strMenu.getDesc() + "\n" + Titles.strChoose.getDesc());

            switch (checkIntInput()) {
                case 1: {
                    int numOfSubject = 0;
                    System.out.println("Введите наименование задачи:");
                    String name = scanner.nextLine();
                    System.out.println("Введите описание задачи:");
                    String desc = scanner.nextLine();
                    System.out.println("Выберите предмет:");
                    if(taskManager.getSubjects().isEmpty()){
                        System.out.println("Введите новый предмет:");
                        String subject = scanner.nextLine();
                        taskManager.newSubject(subject);
                    }else {
                        System.out.println("Выберите предмет:");
                        taskManager.soutSubjects();
                        numOfSubject = checkIntInput();
                    }
                    System.out.println("Выберите исполнителя к задаче:");
                    taskManager.newTask(name, desc, taskManager.getSubjects().get(numOfSubject));
                    continue;
                }
                case 2: {
                    if (!taskManager.getTasks().isEmpty()) {
                        taskManager.getSoutTasks();
                        System.out.println("Введите задачу, которую нужно изменить:");
                        int numOfChangeTask = checkIntInput();
                        //выводится список изменений, то есть что именно в задаче нужно поменять
                        System.out.println(Titles.strChange.getDesc());
                        //выбираем нужное изменение
                        int numOfChangeVariant = checkIntInput();
                        switch (numOfChangeVariant) {
                            case 1: {
                                System.out.println("Введите новое наименование:");
                                String title = scanner.nextLine();
                                taskManager.changeTaskTitle(numOfChangeTask, title);
                                break;
                            }
                            case 2: {
                                System.out.println("Введите новое описание:");
                                String desc = scanner.nextLine();
                                taskManager.changeTaskDesc(numOfChangeTask, desc);
                                break;
                            }
                            //изменение студента
                            case 3: {
                                if (!taskManager.getStudents().isEmpty()) {
                                    System.out.println("Выберите студента из списка:");
                                    taskManager.getSoutStudents();
                                    int numOfStudent = checkIntInput();
                                    System.out.println(Titles.strChangeEmp.getDesc());
                                    //что именно менять: назначить нового сотрудника или снять старого?
                                    int createNewOrDeleteOld = checkIntInput();
                                    switch (createNewOrDeleteOld) {
                                        case 1: {
                                            taskManager.changeTaskNewStudent(numOfChangeTask, numOfStudent);
                                            break;
                                        }

                                    }
                                } else {
                                    System.out.println("Список сотрудников пуст");
                                }
                                break;
                            }
                            case 4: {
                                System.out.println("Введите новый статус из списка:");
                                taskManager.getStatus();
                                int status = checkIntInput();
                                taskManager.changeTaskStatus(numOfChangeTask, status);
                                break;
                            }
                        }
                    } else {
                        System.out.println("Список задач пуст");
                    }
                    continue;
                }
                case 3: {
                    if (!taskManager.getTasks().isEmpty()) {
                        taskManager.getSoutTasks();
                    } else {
                        System.out.println("Список задач пуст");
                    }
                    continue;
                }
                case 4: {
                    if (!taskManager.getTasks().isEmpty()) {
                        System.out.println("Выберите задачу, которую следует cохранить:");
                        taskManager.getSoutTasks();
                        int chooseSaveTask = checkIntInput();
                        taskManager.saveTask(chooseSaveTask);
                        System.out.println("Задача успешно сохранена");
                    } else {
                        System.out.println("Список задач пуст");
                    }
                    continue;
                }
                case 5: {
                    if (!taskManager.getTasks().isEmpty()) {
                        System.out.println("Выберите задачу, которую следует удалить:");
                        taskManager.getSoutTasks();
                        int chooseDeleteTask = checkIntInput();
                        taskManager.deleteTask(chooseDeleteTask);
                        System.out.println("Задача успешно удалена");
                    } else {
                        System.out.println("Список задач пуст");
                    }
                    continue;
                }
                case 6: {
                    taskManager.recoverTask();
                    System.out.println("Задачи успешно восстановлены");
                    continue;
                }
                case 7: {
                    System.out.println("Введите имя студента:");
                    String name = scanner.nextLine();
                    System.out.println("Введите фамилию студента:");
                    String lastName = scanner.nextLine();
                    taskManager.newStudent(name, lastName);
                    continue;
                }
                //изменение студента
                case 8: {
                    if (!taskManager.getStudents().isEmpty()) {
                        System.out.println("Выберите студента:");
                        taskManager.getSoutStudents();
                        int chooseChangeStudent = checkIntInput();
                        System.out.println("Изменить:\n1. Имя\n2. Фамилию");
                        switch (checkIntInput()) {
                            case 1: {
                                System.out.println("Введите имя:");
                                String name = scanner.nextLine();
                                taskManager.changeNameOfStudent(chooseChangeStudent, name);
                                break;
                            }
                            case 2: {
                                System.out.println("Введите фамилию:");
                                String lastName = scanner.nextLine();
                                taskManager.changeLastNameOfStudent(chooseChangeStudent, lastName);
                                break;
                            }
                        }
                    } else {
                        System.out.println("Список студентов пуст");
                    }
                    continue;
                }
                case 9: {
                    if (!taskManager.getStudents().isEmpty()) {
                        taskManager.getSoutStudents();
                    } else {
                        System.out.println("Список студентов пуст");
                    }
                    continue;
                }
                case 10: {
                    if (!taskManager.getStudents().isEmpty()) {
                        System.out.println("Выберите студента, которого следует удалить:");
                        taskManager.getSoutStudents();
                        int chooseChangeStudent = checkIntInput();
                        taskManager.deleteStudent(chooseChangeStudent);
                    } else {
                        System.out.println("Список студентов пуст");
                    }
                    continue;
                }
                case 11: {
                    taskManager.soutSubjects();
                    continue;
                }
                case 12: {
                    System.out.println("Введите название нового предмета");
                    String subject = scanner.nextLine();
                    taskManager.newSubject(subject);
                    continue;
                }
                case 13:{
                    taskManager.soutSubjects();
                    System.out.println("Выберите предмет для удаления");
                    int numOfSubject = checkIntInput();
                    taskManager.deleteSubject(numOfSubject);
                    continue;
                }
                case 0: {
                    scanner.close();
                    taskManager.closeThread();
                    flag = true;
                    break;
                }
            }
        } while (!flag);
    }

    /**
     * checkIntInput - статические метод, проверяющий ввода целого значения
     *
     * @return
     */
    public static int checkIntInput() {
        int read;
        Scanner sca = new Scanner(System.in);
        while (!sca.hasNextInt()) {
            System.out.println(Titles.strChoose.getDesc());
            sca.next();
        }
        read = sca.nextInt();
        return read;
    }

}
