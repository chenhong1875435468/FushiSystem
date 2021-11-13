package com.chenhong;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;


/**
 * The type File fushi test database loader.
 *
 * @author chenhong
 * @version 1.0
 * @description to load a testdatabase from file
 * @date 2021 /10/31 9:27
 */
public class FileFushiTestDatabaseLoader implements FushiTestDatabaseLoader {

    @Override
    public TestDatabase loadTestDatabase(String fileName) throws FileNotFoundException, IOException, DataFormatException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String line = null;
        TestDatabase testDatabase = new TestDatabase();
        Test test = null;
        while((line = bufferedReader.readLine()) != null) {
            if(line.startsWith("EnglishTest")) {
                test = readEnglishTest(line);
            } else if(line.startsWith("MathTest")) {
                test = readMathTest(line);
            } else if(line.startsWith("ProfessionalTest")) {
                test = readProfessionalTest(line);
            }
            testDatabase.addTest(test);
        }
        bufferedReader.close();
        return testDatabase;
    }

    /**
     * Read english test english test.
     *
     * @param line the line
     * @return the english test
     * @throws DataFormatException the data format exception
     */
    public EnglishTest readEnglishTest(String line) throws DataFormatException {

        //EnglishTest_code_title_difficultyDegree_scoreCriteria_type

        ArrayList<String> list = new ArrayList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(line, "_");
        while(stringTokenizer.hasMoreTokens()) {
            list.add(stringTokenizer.nextToken());
        }
        if(list.size() != 6) {
            throw new DataFormatException("该行没有预期数量的字段");
        }
        if(isNumeric(list.get(3))) {
            throw new DataFormatException("应包含数字的字段没有包含数字");
        }
        return new EnglishTest(list.get(1), list.get(2), Integer.parseInt(list.get(3)), list.get(4), list.get(5));
    }

    /**
     * Read math test math test.
     *
     * @param line the line
     * @return the math test
     * @throws DataFormatException the data format exception
     */
    public MathTest readMathTest(String line) throws DataFormatException {
        //MathTest_code_title_difficultyDegree_scoreCriteria_photoURL_calculationProcess
        ArrayList<String> list = new ArrayList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(line, "_");
        while(stringTokenizer.hasMoreTokens()) {
            list.add(stringTokenizer.nextToken());
        }
        if(list.size() != 7) {
            throw new DataFormatException("该行没有预期数量的字段");
        }
        if(isNumeric(list.get(3))) {
            throw new DataFormatException("应包含数字的字段没有包含数字");
        }
        return new MathTest(list.get(1), list.get(2), Integer.parseInt(list.get(3)), list.get(4), list.get(5),list.get(6));
    }

    /**
     * Read professional test professional test.
     *
     * @param line the line
     * @return the professional test
     * @throws DataFormatException the data format exception
     */
    public ProfessionalTest readProfessionalTest(String line) throws DataFormatException {
        //ProfessionalTest_code_title_difficultyDegree_scoreCriteria_programInstruction_programming_photoURL
        ArrayList<String> list = new ArrayList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(line, "_");
        while(stringTokenizer.hasMoreTokens()) {
            list.add(stringTokenizer.nextToken());
        }
        if(list.size() != 8) {
            throw new DataFormatException("该行没有预期数量的字段");
        }
        if(isNumeric(list.get(3))) {
            throw new DataFormatException("应包含数字的字段没有包含数字");
        }
        return new ProfessionalTest(list.get(1), list.get(2), Integer.parseInt(list.get(3)), list.get(4), list.get(5),list.get(6), list.get(7));
    }

    /**
     * Is numeric boolean.
     *
     * @param str the str
     * @return the boolean
     */
    public static boolean isNumeric(String str) {
        for (int i = str.length();--i>=0;) {
            if (!Character.isDigit(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}
