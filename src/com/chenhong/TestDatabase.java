package com.chenhong;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The type Test database.
 *
 * @author chenhong
 * @version 1.0
 * @description store tests
 * @date 2021 /10/31 9:13
 */
public class TestDatabase implements Iterable<Test> {
    private List<Test> tests = new ArrayList();

    /**
     * Instantiates a new Test database.
     */
    public TestDatabase() {
    }

    /**
     * Add test.
     *
     * @param var1 the var 1
     */
    public void addTest(Test var1) {
        this.tests.add(var1);
    }

    /**
     * Remove test.
     *
     * @param var1 the var 1
     */
    public void removeTest(Test var1) {
        this.tests.remove(var1);
    }

    @Override
    public Iterator<Test> iterator() {
        return this.tests.iterator();
    }

    /**
     * Gets number of tests.
     *
     * @return the number of tests
     */
    public int getNumberOfTests() {
        return this.tests.size();
    }

    /**
     * Gets test.
     *
     * @param var1 the var 1
     * @return the test
     */
    public Test getTest(int var1) {
        return (Test)this.tests.get(var1);
    }
}

