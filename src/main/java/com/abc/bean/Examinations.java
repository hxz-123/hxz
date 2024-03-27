package com.abc.bean;

import lombok.Data;

@Data
public class Examinations {
    private int id;
    private int total;
    private int rigth;
    private int error;
    private double score;

    public Examinations(int id, int total, int rigth, int error, double score) {
        this.id = id;
        this.total = total;
        this.rigth = rigth;
        this.error = error;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getRigth() {
        return rigth;
    }

    public void setRigth(int rigth) {
        this.rigth = rigth;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
