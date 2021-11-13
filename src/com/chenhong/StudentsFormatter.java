package com.chenhong;

/**
 * The interface Students formatter.
 * @author chenhong
 * @version 1.1.0
 */
public interface StudentsFormatter {

     /**
      * Format students string.
      *
      * @param studentCatalog the student catalog
      * @return the string
      */
     String formatStudents(StudentCatalog studentCatalog);
}
