package com.chenhong;

/**
 * The type Professional test.
 *
 * @author chenhong
 * @version 1.0
 * @description a kind of test
 * @date 2021 /10/31 9:11
 */
public class ProfessionalTest extends Test {
    private String programInstruction;
    private String programming;
    private String photoURL;

    /**
     * Instantiates a new Professional test.
     *
     * @param var1 the var 1
     * @param var2 the var 2
     * @param var3 the var 3
     * @param var4 the var 4
     * @param var5 the var 5
     * @param var6 the var 6
     * @param var7 the var 7
     */
    public ProfessionalTest(String var1, String var2, int var3, String var4, String var5, String var6, String var7) {
        super(var1, var2, var3, var4);
        this.programInstruction = var5;
        this.programming = var6;
        this.photoURL = var7;
    }

    /**
     * Gets program instruction.
     *
     * @return the program instruction
     */
    public String getProgramInstruction() {
        return this.programInstruction;
    }

    /**
     * Gets programming.
     *
     * @return the programming
     */
    public String getProgramming() {
        return this.programming;
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
     * Sets program instruction.
     *
     * @param var1 the var 1
     */
    public void setProgramInstruction(String var1) {
        this.programInstruction = var1;
    }

    /**
     * Sets programming.
     *
     * @param var1 the var 1
     */
    public void setProgramming(String var1) {
        this.programming = var1;
    }

    /**
     * Sets photo url.
     *
     * @param var1 the var 1
     */
    public void setPhotoURL(String var1) {
        this.photoURL = var1;
    }

    @Override
    public String toString() {
        return super.toString() + "|" + this.getProgramInstruction() + "|" + this.getProgramming() + "|" + this.getPhotoURL();
    }
}

