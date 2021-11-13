package com.chenhong;

/**
 * The type Test.
 *
 * @author chenhong
 * @version 1.0
 * @description test
 * @date 2021 /10/31 9:13
 */
public class Test {
    private String code;
    private String title;
    private int difficultyDegree;
    private String scoreCriteria;

    /**
     * Instantiates a new Test.
     */
    public Test() {
    }

    /**
     * Instantiates a new Test.
     *
     * @param var1 the var 1
     * @param var2 the var 2
     * @param var3 the var 3
     * @param var4 the var 4
     */
    public Test(String var1, String var2, int var3, String var4) {
        this.code = var1;
        this.title = var2;
        this.difficultyDegree = var3;
        this.scoreCriteria = var4;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Gets difficulty degree.
     *
     * @return the difficulty degree
     */
    public int getDifficultyDegree() {
        return this.difficultyDegree;
    }

    /**
     * Gets score criteria.
     *
     * @return the score criteria
     */
    public String getScoreCriteria() {
        return this.scoreCriteria;
    }

    @Override
    public String toString() {
        return this.getCode() + "|" + this.getTitle() + "|" + this.getDifficultyDegree() + "|" + this.getScoreCriteria();
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
            Test var2 = (Test)var1;
            if (this.code == null) {
                if (var2.code != null) {
                    return false;
                }
            } else if (!this.code.equals(var2.code)) {
                return false;
            }

            return true;
        }
    }
}

