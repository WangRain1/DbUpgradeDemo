/*
 * Copyright (c) 2019. Parrot Faurecia Automotive S.A.S. All rights reserved.
 */

package com.example.ts.dbupgradedemo;

import android.graphics.PointF;

/**
 * Entity of custom color.
 */
public class CustomColor {

    /**
     * Require this empty construct method.
     */
    public CustomColor() {

    }

    /**
     * Construct method.
     *
     * @param color       color
     * @param cursorPoint cursorPoint
     */
    public CustomColor(int color, PointF cursorPoint) {
        this.color = color;
        this.locationX = cursorPoint.x;
        this.locationY = cursorPoint.y;
    }

    /**
     * Construct method.
     *
     * @param id          id
     * @param color       color
     * @param cursorPoint cursorPoint
     */
    public CustomColor(int id, int color, PointF cursorPoint) {
        this(color, cursorPoint);
        this.id = id;
    }

    /** CustomColor id. */
    private int id;

    /** Color code. */
    private int color;

    /** X-Axis in the palette. */
    private float locationX;

    /** Y-Axis in the palette. */
    private float locationY;

    String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Get id.
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Set id.
     *
     * @param id id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get color.
     *
     * @return color
     */
    public int getColor() {
        return color;
    }

    /**
     * Set color.
     *
     * @param color color
     */
    public void setColor(int color) {
        this.color = color;
    }

    /**
     * Get X-Axis location.
     *
     * @return X-Axis location
     */
    public float getLocationX() {
        return locationX;
    }

    /**
     * Set X-Axis location.
     *
     * @param locationX X-Axis location
     */
    public void setLocationX(float locationX) {
        this.locationX = locationX;
    }

    /**
     * Get Y-Axis location.
     *
     * @return Y-Axis location
     */
    public float getLocationY() {
        return locationY;
    }

    /**
     * Set Y-Axis location.
     *
     * @param locationY Y-Axis location
     */
    public void setLocationY(float locationY) {
        this.locationY = locationY;
    }
}
