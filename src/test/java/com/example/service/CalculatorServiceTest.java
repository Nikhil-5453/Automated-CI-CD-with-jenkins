package com.example.service;

import com.service.CalculatorService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CalculatorService Tests")
class CalculatorServiceTest {

    private CalculatorService calculator;

    @BeforeEach
    void setUp() {
        calculator = new CalculatorService();
    }

    @Test
    @DisplayName("Add two positive integers")
    void testAdd() {
        assertEquals(30, calculator.add(10, 20));
        assertEquals(0,  calculator.add(-5, 5));
    }

    @Test
    @DisplayName("Subtract integers")
    void testSubtract() {
        assertEquals(5, calculator.subtract(10, 5));
        assertEquals(-5, calculator.subtract(0, 5));
    }

    @Test
    @DisplayName("Multiply integers")
    void testMultiply() {
        assertEquals(20, calculator.multiply(4, 5));
        assertEquals(0,  calculator.multiply(0, 100));
    }

    @Test
    @DisplayName("Divide integers correctly")
    void testDivide() {
        assertEquals(2.5, calculator.divide(5, 2));
    }

    @Test
    @DisplayName("Divide by zero throws exception")
    void testDivideByZero() {
        assertThrows(IllegalArgumentException.class,
            () -> calculator.divide(10, 0));
    }

    @ParameterizedTest
    @CsvSource({"0,1", "1,1", "5,120", "6,720"})
    @DisplayName("Factorial parameterized")
    void testFactorial(int input, long expected) {
        assertEquals(expected, calculator.factorial(input));
    }

    @Test
    @DisplayName("Factorial of negative throws exception")
    void testFactorialNegative() {
        assertThrows(IllegalArgumentException.class,
            () -> calculator.factorial(-1));
    }

    @ParameterizedTest
    @CsvSource({"2,true", "3,true", "4,false", "17,true", "20,false"})
    @DisplayName("Prime number check")
    void testIsPrime(int number, boolean expected) {
        assertEquals(expected, calculator.isPrime(number));
    }
}
