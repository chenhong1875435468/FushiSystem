package com.chenhong;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The type Exam paper.
 *
 * @author chenhong
 * @version 1.0
 * @description an exampaper for student
 * @date 2021 /10/31 9:09
 */
public class ExamPaper implements Iterable<TestItem> {
    private List<TestItem> testItems = new ArrayList();

    /**
     * Instantiates a new Exam paper.
     */
    public ExamPaper() {
    }

    /**
     * Add test item.
     *
     * @param var1 the var 1
     */
    public void addTestItem(TestItem var1) {
        if (this.testItems.size() < 10) {
            this.testItems.add(var1);
        }

    }

    /**
     * Remove test item.
     *
     * @param var1 the var 1
     */
    public void removeTestItem(TestItem var1) {
        this.testItems.remove(var1);
    }

    @Override
    public Iterator<TestItem> iterator() {
        return this.testItems.iterator();
    }

    /**
     * Gets number of items.
     *
     * @return the number of items
     */
    public int getNumberOfItems() {
        return this.testItems.size();
    }

    /**
     * Gets total score.
     *
     * @return the total score
     */
    public double getTotalScore() {
        double var1 = 0.0D;

        TestItem var4;
        for(Iterator var3 = this.testItems.iterator(); var3.hasNext(); var1 += var4.getScore()) {
            var4 = (TestItem)var3.next();
        }

        return var1;
    }

    /**
     * Gets test item.
     *
     * @param var1 the var 1
     * @return the test item
     */
    public TestItem getTestItem(int var1) {
        return (TestItem)this.testItems.get(var1);
    }

}

