package com.chenhong;

/**
 * @author chenhong
 * @version 1.0
 * @description store students
 * @date 2021/10/31 9:12
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The type Student catalog.
 */
public class StudentCatalog implements Iterable<Student> {
    private List<Student> students = new ArrayList();

    /**
     * Instantiates a new Student catalog.
     */
    public StudentCatalog() {
    }

    /**
     * Add student.
     *
     * @param var1 the var 1
     */
    public void addStudent(Student var1) {
        this.students.add(var1);
    }

    /**
     * Remove student.
     *
     * @param var1 the var 1
     */
    public void removeStudent(Student var1) {
        this.students.remove(var1);
    }

    @Override
    public Iterator<Student> iterator() {
        return this.students.iterator();
    }

    /**
     * Gets student.
     *
     * @param var1 the var 1
     * @return the student
     */
    public Student getStudent(String var1) {
        Iterator var2 = this.students.iterator();

        Student var3;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            var3 = (Student)var2.next();
        } while(!var3.getId().equals(var1));

        return var3;
    }

    /**
     * Gets number of students.
     *
     * @return the number of students
     */
    public int getNumberOfStudents() {
        return this.students.size();
    }
}

