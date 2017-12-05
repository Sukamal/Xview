package com.everi.xview.customviews.piechart;

/**
 * Created by sukamal on 17/2/17.
 */

public class PieChartHelper {
    private float startDegree;
    private float endDegree;
    private float targetStartDegree;
    private float targetEndDegree;
    private String title;
    private int color;
    private float sweepDegree;

    int velocity = 5;

    /**
     *
     * @param percent
     * from 0 to 100
     */
    public PieChartHelper(float percent) {
        this(percent, null, 0);
    }

    public PieChartHelper(float percent, int color) {
        this(percent, null, color);
    }

    /**
     *
     * @param percent
     * from 0 to 100
     * @param title
     */
    PieChartHelper(float percent, String title) {
        this(percent, title, 0);
    }

    /**
     *
     * @param percent
     * from 0 to 100
     * @param title
     * @param color
     */
    public PieChartHelper(float percent, String title, int color) {
        this.sweepDegree = percent * 360 / 100;
        this.title = title;
        this.color = color;
    }

    PieChartHelper(float startDegree, float endDegree, PieChartHelper targetPie) {
        this.startDegree = startDegree;
        this.endDegree = endDegree;
        targetStartDegree = targetPie.getStartDegree();
        targetEndDegree = targetPie.getEndDegree();
        this.sweepDegree = targetPie.getSweep();
        this.title = targetPie.getTitle();
        this.color = targetPie.getColor();
    }

    PieChartHelper setTarget(PieChartHelper targetPie) {
        this.targetStartDegree = targetPie.getStartDegree();
        this.targetEndDegree = targetPie.getEndDegree();
        this.title = targetPie.getTitle();
        this.color = targetPie.getColor();
        this.sweepDegree = targetPie.getSweep();
        return this;
    }

    void setDegree(float startDegree, float endDegree) {
        this.startDegree = startDegree;
        this.endDegree = endDegree;
    }

    boolean isColorSetted() {
        return color != 0;
    }

    boolean isAtRest() {
        return (startDegree == targetStartDegree)
                && (endDegree == targetEndDegree);
    }

    void update() {
        this.startDegree = updateSelf(startDegree, targetStartDegree, velocity);
        this.endDegree = updateSelf(endDegree, targetEndDegree, velocity);
        this.sweepDegree = endDegree - startDegree;
    }

    String getPercentStr() {
        float percent = sweepDegree / 360 * 100;
        return String.valueOf((int) percent) + "%";
    }

    public int getColor() {
        return color;
    }

    public String getTitle() {
        return title;
    }

    public float getSweep() {
        return sweepDegree;
    }

    public float getStartDegree() {
        return startDegree;
    }

    public float getEndDegree() {
        return endDegree;
    }

    private float updateSelf(float origin, float target, int velocity) {
        if (origin < target) {
            origin += velocity;
        } else if (origin > target) {
            origin -= velocity;
        }
        if (Math.abs(target - origin) < velocity) {
            origin = target;
        }
        return origin;
    }
}
