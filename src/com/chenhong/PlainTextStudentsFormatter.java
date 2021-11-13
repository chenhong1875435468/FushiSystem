package com.chenhong;

/**
 * The type Plain text students formatter.
 * @author chenhong
 * @version 1.1.0
 * @see StudentsFormatter
 */
public class PlainTextStudentsFormatter implements StudentsFormatter {


    private static PlainTextStudentsFormatter singletonInstance = new PlainTextStudentsFormatter();


    /**
     * Gets singleton instance.
     *
     * @return the singleton instance
     */
    public static PlainTextStudentsFormatter getSingletonInstance() {
        return singletonInstance;
    }
    @Override
    public String formatStudents(StudentCatalog studentCatalog) {
        StringBuffer stringBuffer = new StringBuffer();
        for(Student student : studentCatalog) {
            if (student.getExamPaper() == null) {
                stringBuffer.append("           " + student.getName() + "_" + student.getId() +":该学生还未生成试卷\n");
            } else {
                stringBuffer.append(student.getId() + "_" +  student.getName());
                for(TestItem testItem : student.getExamPaper()) {
                    stringBuffer.append("_" + testItem.getTest().getCode());
                }
                stringBuffer.append("\n");
            }
        }
        return stringBuffer.toString();
    }
}
