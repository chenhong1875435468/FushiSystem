package com.chenhong;

/**
 * The type Math test.
 *
 * @author chenhong
 * @version 1.0
 * @description a kind of test
 * @date 2021 /10/31 9:10
 */
public class MathTest extends Test {
    private String photoURL;
    private String calculationProcess;

    /**
     * Instantiates a new Math test.
     *
     * @param var1 the var 1
     * @param var2 the var 2
     * @param var3 the var 3
     * @param var4 the var 4
     * @param var5 the var 5
     * @param var6 the var 6
     */
    public MathTest(String var1, String var2, int var3, String var4, String var5, String var6) {
        super(var1, var2, var3, var4);
        this.photoURL = var5;
        this.calculationProcess = var6;
    }

    /**
     * Gets photo url.
     *
     * @return the photo url
     */
    public String getPhotoURL() {
        return this.photoURL;
    }

    /**
     * Gets calculation process.
     *
     * @return the calculation process
     */
    public String getCalculationProcess() {
        return this.calculationProcess;
    }

    /**
     * Sets photo url.
     *
     * @param var1 the var 1
     */
    public void setPhotoURL(String var1) {
        this.photoURL = var1;
    }

    /**
     * Sets calculation process.
     *
     * @param var1 the var 1
     */
    public void setCalculationProcess(String var1) {
        this.calculationProcess = var1;
    }

    @Override
    public String toString() {
        return super.toString() + "|" + this.getPhotoURL() + "|" + this.getCalculationProcess();
    }
}

