package org.hw4;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Web UI Java. Homework 4
 *
 * @author Vitalii Luzhnov
 * @version 15.04.2022
 */
public class TriangleTest {

    static Integer testInt = 0;

    private static final Logger logger = LoggerFactory.getLogger(TriangleTest.class);

    @BeforeAll
    static void beforeAll() {
        logger.info("Начало тестирования метода расчёта площади треугольника по трём сторонам");
    }

    @BeforeEach
    void beforeEach() {
        testInt++;
        logger.info("Тест #" + testInt);
    }

    @ParameterizedTest
    @Tag("Negative")
    @DisplayName("Передадим в метод граничное отрицательное значение стороны")
    @CsvSource({
            "-1,1,1",
            "1,-1,1",
            "1,1,-1",
            "-2147483648,2147483647,2147483647",
            "2147483647,-2147483648,2147483647",
            "2147483647,2147483647,-2147483648"})
    void triangleOutOfBoundsTest(int a, int b, int c) {
        logger.info("a = " + a + "; b = " + b + "; с = " + c );
        Triangle triangle = new Triangle();
        Assertions.assertThrows(TriangleException.class, () -> triangle.areaOfTriangle(a, b, c));
    }

    @ParameterizedTest
    @Tag("Negative")
    @DisplayName("Передадим в метод нулевое значение стороны")
    @CsvSource({
            "0,1,1",
            "1,0,1",
            "1,1,0"})
    void triangleSideZeroTest(int a, int b, int c) {
        logger.info("a = " + a + "; b = " + b + "; с = " + c );
        Triangle triangle = new Triangle();
        Assertions.assertThrows(TriangleException.class, () -> triangle.areaOfTriangle(a, b, c));
    }

    @ParameterizedTest
    @Tag("Negative")
    @DisplayName("Передадим в метод сторону, длина которой больше суммы двух других сторон")
    @CsvSource({
            "3,1,1",
            "1,3,1",
            "1,1,3",
            "2147483647,1073741823,1073741823",
            "1073741823,2147483647,1073741823",
            "1073741823,1073741823,2147483647"})
    void triangleLongSideTest(int a, int b, int c) {
        logger.info("a = " + a + "; b = " + b + "; с = " + c );
        Triangle triangle = new Triangle();
        Assertions.assertThrows(TriangleException.class, () -> triangle.areaOfTriangle(a, b, c));
    }

    @ParameterizedTest
    @Tag("Negative")
    @DisplayName("Передадим в метод сторону, длина которой равна сумме двух других сторон")
    @CsvSource({
            "2,1,1",
            "1,2,1",
            "1,1,2",
            "2147483647,1073741824,1073741823",
            "1073741824,2147483647,1073741823",
            "1073741824,1073741823,2147483647"})
    void triangleSideEqualOtherSidesTest(int a, int b, int c) {
        logger.info("a = " + a + "; b = " + b + "; с = " + c );
        Triangle triangle = new Triangle();
        Assertions.assertThrows(TriangleException.class, () -> triangle.areaOfTriangle(a, b, c));
    }

    @ParameterizedTest
    @Tag("Positive")
    @DisplayName("Передадим в метод равносторонний треугольник")
    @CsvSource({
            "1,1,1,0.4330127018922193",
            "2147483647,2147483647,2147483647,1.99691862125803904E18"})
    void triangleEquilateralTest(int a, int b, int c, double s) throws TriangleException {
        logger.info("a = " + a + "; b = " + b + "; с = " + c + "; s = " + s );
        Triangle triangle = new Triangle();
        Assertions.assertEquals(s,triangle.areaOfTriangle(a, b, c));
    }

    @ParameterizedTest
    @Tag("Positive")
    @DisplayName("Передадим в метод равнобедренный треугольник")
    @CsvSource({
            "1,2,2,0.9682458365518543",
            "2,1,2,0.9682458365518543",
            "2,2,1,0.9682458365518543",
            "2147483646,2147483647,2147483647,1.99691862063811379E18",
            "2147483647,2147483646,2147483647,1.99691862063811379E18",
            "2147483647,2147483647,2147483646,1.99691862063811379E18"})
    void triangleBaseSideTest(int a, int b, int c, double s) throws TriangleException {
        logger.info("a = " + a + "; b = " + b + "; с = " + c + "; s = " + s );
        Triangle triangle = new Triangle();
        Assertions.assertEquals(s,triangle.areaOfTriangle(a, b, c));
    }

    @ParameterizedTest
    @Tag("Positive")
    @DisplayName("Передадим в метод треугольник с разными сторонами")
    @CsvSource({
            "2,3,4,2.9047375096555625",
            "4,2,3,2.9047375096555625",
            "3,4,2,2.9047375096555625",
            "2147483645,2147483646,2147483647,1.99691861939826355E18",
            "2147483647,2147483645,2147483646,1.99691861939826355E18",
            "2147483646,2147483647,2147483645,1.99691861939826355E18"})
    void triangleDifferentSidesTest(int a, int b, int c, double s) throws TriangleException {
        logger.info("a = " + a + "; b = " + b + "; с = " + c + "; s = " + s );
        Triangle triangle = new Triangle();
        Assertions.assertEquals(s,triangle.areaOfTriangle(a, b, c));
    }

    @AfterAll
    static void afterAll() {
        logger.info("Завершение теста метода расчёта площади треугольника по трём сторонам");
    }
}