package com.chenhong;

/**
 * The type Html students formatter.
 * @author chenhong
 * @version 1.1.0
 * @see StudentsFormatter
 */
public class HTMLStudentsFormatter implements StudentsFormatter {

    private static HTMLStudentsFormatter singletonInstance = new HTMLStudentsFormatter();

    /**
     * Gets singleton instance.
     *
     * @return the singleton instance
     */
    public static HTMLStudentsFormatter getSingletonInstance() {
        return singletonInstance;
    }
    @Override
    public String formatStudents(StudentCatalog studentCatalog) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<html>\n  <body>\n    <center><h2>Student Catalog</h2></center>\n");

        for(Student student : studentCatalog) {
            if (student.getExamPaper() == null) {
                stringBuffer.append("<hr/>");
                stringBuffer.append("           " + student.getName() + "_" + student.getId() +":该学生还未生成试卷<br/>\n");
                stringBuffer.append("<hr/>");
            } else {
                stringBuffer.append("    <hr/>\n    <h4>" + student.getId() + " "  + student.getName() + "</h4>\n    <blockquote>\n");
                for(TestItem testItem : student.getExamPaper()) {
                    stringBuffer.append("           " + testItem.getTest().toString() + "<br/>" + "\n");
                }
                stringBuffer.append("      </blockquote>\n");
            }
        }
        stringBuffer.append("  </body>\n");
        stringBuffer.append("</html>");
        return stringBuffer.toString();
    }
}
