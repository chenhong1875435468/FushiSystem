package com.chenhong;

/**
 * The type Xml students formatter.
 *
 * @author chenhong
 * @version 1.1.0
 * @see StudentsFormatter
 */
public class XMLStudentsFormatter implements StudentsFormatter {


    private static XMLStudentsFormatter singletonInstance = new XMLStudentsFormatter();

    /**
     * Gets singleton instance.
     *
     * @return the singleton instance
     */
    public static XMLStudentsFormatter getSingletonInstance() {
        return singletonInstance;
    }

    @Override
    public String formatStudents(StudentCatalog studentCatalog) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<StudentCatalog>\n");
        for(Student student : studentCatalog) {
            if (student.getExamPaper() == null) {
                stringBuffer.append("<hr/>");
                stringBuffer.append("           " + student.getName() + "_" + student.getId() +":该学生还未生成试卷\n");
            } else {
                stringBuffer.append("  <student id=\"" + student.getId() + "\" name=\"" + student.getName() + "\">\n");
                stringBuffer.append("    <ExamPaper>\n");
                for(TestItem testItem : student.getExamPaper()) {
                    stringBuffer.append("      <Test code=\""+ testItem.getTest().getCode() +"\">" + testItem.getTest().getTitle() + "</Test>\n");
                }
                stringBuffer.append("    </ExamPaper>\n");
                stringBuffer.append("  </student>\n");
            }
        }
        stringBuffer.append("</StudentCatalog>\n");
        return stringBuffer.toString();
    }
}
