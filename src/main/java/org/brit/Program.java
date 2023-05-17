package org.brit;

public class Program {
    public static void main(String[] args) {
        Pencil pencil1 = new Pencil(0.1, 0.010, "HB");
        Pencil pencil2 = new Pencil(0.1, 0.010, "HB");

        if (pencil1.equals(pencil2)) {
            System.out.println("Pencils are equal!!!");
        }
    }
}
