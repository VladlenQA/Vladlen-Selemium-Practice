package org.brit;

import com.google.common.base.Objects;

public class Pencil {
    private double length;
    private double weight;
    private String brand;

    public Pencil(double length, double weight, String brand) {
        this.length = length;
        this.weight = weight;
        this.brand = brand;
    }

    public double getLength() {
        return length;
    }

    public double getWeight() {
        return weight;
    }

    public String getBrand() {
        return brand;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pencil pencil = (Pencil) o;
        return this.length == pencil.getLength()
                && this.weight == pencil.getLength()
                && this.brand == pencil.getBrand();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(length, weight, brand);
    }

    @Override
    public String toString() {
        return "Pencil{" +
                "length=" + length +
                ", weight=" + weight +
                ", brand='" + brand + '\'' +
                '}';
    }
}
