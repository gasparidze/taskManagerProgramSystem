package task.structure;

import java.io.Serializable;

/**
 * Класс задачи
 */
public class Task implements Serializable {
    /**
     * id задачи
     */
    private final int id;
    /**
     * наименование задачи
     */
    private String title;
    /**
     * описание задачи
     */
    private String description;
    /**
     * сотрудник, назначенный на данную задачу
     */
    private Student student;
    /**
     * предмет, по которому назначена задача
     */
    private String subject;
    /**
     * статус задачи
     */
    private Status status;

    /**
     * конструктор задачи
     *
     * @param id          - id задачи
     * @param title       - наименование задачи
     * @param description - описание задачи
     * @param student    - сотрудник, назначенный на данную задачу
     * @param subject     - предмет, по которому назначена задача
     * @param status      - статус задачи
     */
    public Task(int id, String title, String description, Student student, String subject, Status status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.student = student;
        this.subject = subject;
        this.status = status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Student getStudent() {
        return student;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Task" +
                "{id = " + id +
                ", Наименование = '" + title + "'," +
                " Описание = '" + description + "'," +
                " Сотрудник = " + student + ',' +
                " Предмет = " + subject + ',' +
                " Статус = " + status.getTitle() +
                '}';
    }
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
