package com.chenhong;

/**
 * The type Student.
 *
 * @author chenhong
 * @version 1.0
 * @description student
 * @date 2021 /10/31 9:12
 */
public class Student {
    private String id;
    private String name;
    private ExamPaper examPaper;

    /**
     * Instantiates a new Student.
     *
     * @param var1 the var 1
     * @param var2 the var 2
     */
    public Student(String var1, String var2) {
        this.id = var1;
        this.name = var2;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets exam paper.
     *
     * @return the exam paper
     */
    public ExamPaper getExamPaper() {
        return this.examPaper;
    }

    @Override
    public String toString() {
        return this.getId() + "_" + this.getName();
    }

    /**
     * Sets exam paper.
     *
     * @param var1 the var 1
     */
    public void setExamPaper(ExamPaper var1) {
        this.examPaper = var1;
    }

    @Override
    public boolean equals(Object var1) {
        if (this == var1) {
            return true;
        } else if (var1 == null) {
            return false;
        } else if (this.getClass() != var1.getClass()) {
            return false;
        } else {
            Student var2 = (Student)var1;
            if (this.id == null) {
                if (var2.id != null) {
                    return false;
                }
            } else if (!this.id.equals(var2.id)) {
                return false;
            }

            return true;
        }
    }
}

