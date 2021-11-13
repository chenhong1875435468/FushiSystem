package com.chenhong;

/**
 * The type English test.
 *
 * @author chenhong
 * @version 1.0
 * @description a kind of test
 * @date 2021 /10/31 9:01
 */
public class EnglishTest extends Test {
    private String type;

    /**
     * Instantiates a new English test.
     *
     * @param var1 the var 1
     * @param var2 the var 2
     * @param var3 the var 3
     * @param var4 the var 4
     * @param var5 the var 5
     */
    public EnglishTest(String var1, String var2, int var3, String var4, String var5) {
        super(var1, var2, var3, var4);
        this.type = var5;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return this.type;
    }

    /**
     * Sets type.
     *
     * @param var1 the var 1
     */
    public void setType(String var1) {
        this.type = var1;
    }

    @Override
    public String toString() {
        return super.toString() + "|" + this.getType();
    }
}
