package task.structure;

import java.io.Serializable;

/**
 * Класс сотрудника
 */
public class Student implements Serializable {
    /**
     * имя сотрудника
     */
    private String name;
    /**
     * фамилия сотрудника
     */
    private String lastName;

    /**
     * конструктор класса
     *
     * @param name     - имя сотрудника
     * @param lastName - фамилия сотрудника
     */
    public Student(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Student" +
                "{Имя=" + name + "," +
                " Фамилия=" + lastName + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student) || o == null) return false;
        Student newStudent = (Student) o;
        return (newStudent.name == this.name) && (newStudent.lastName == this.lastName);
    }

}
