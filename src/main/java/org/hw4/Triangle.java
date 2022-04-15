package org.hw4;

/**
 * Web UI Java. Homework 4
 *
 * @author Vitalii Luzhnov
 * @version 15.04.2022
 */
public class Triangle {
    public double areaOfTriangle(int a, int b, int c) throws TriangleException {

        if ((double) a <= 0 || (double) b <= 0 || (double) c <= 0 ) throw new TriangleException ("Стороны заданы неверно");

        if ((double) a + (double) b <= (double) c || (double) a + (double) c <= (double) b || (double) b + (double) c <= (double) a) throw new TriangleException ("Стороны не образуют треугольник");

        double p = ((double) a + (double) b + (double) c) / 2.0;

        return Math.sqrt(p * (p - (double) a) * (p - (double) b) * (p - (double) c));
    }
}
