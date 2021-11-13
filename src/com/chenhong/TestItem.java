package com.chenhong;

/**
 * The type Test item.
 *
 * @author chenhong
 * @version 1.0
 * @description each testItem contains a test and its score
 * @date 2021 /10/31 9:14
 */
public class TestItem {
    private double score;
    private Test test;

    /**
     * Instantiates a new Test item.
     *
     * @param var1 the var 1
     * @param var2 the var 2
     */
    public TestItem(Test var1, double var2) {
        this.test = var1;
        this.score = var2;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public double getScore() {
        return this.score;
    }

    /**
     * Sets score.
     *
     * @param var1 the var 1
     */
    public void setScore(double var1) {
        this.score = var1;
    }

    @Override
    public String toString() {
        return this.test.toString() + "|" + this.getScore();
    }

    /**
     * Gets test.
     *
     * @return the test
     */
    public Test getTest() {
        return this.test;
    }
}

