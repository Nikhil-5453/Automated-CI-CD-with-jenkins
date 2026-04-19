package com.example;

import com.service.CalculatorService;

public class App {
    public static void main(String[] args) {
        CalculatorService calc = new CalculatorService();
        System.out.println("Sum: " + calc.add(10, 20));
        System.out.println("Product: " + calc.multiply(4, 5));
        System.out.println("Factorial(5): " + calc.factorial(5));
    }
}
