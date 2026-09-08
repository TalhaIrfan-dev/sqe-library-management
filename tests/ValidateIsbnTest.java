package tests;

import LibrarySystem.*;

public class ValidateIsbnTest {

    public static void main(String[] args) {

        testValidIsbn();
        testEmptyIsbn();
        testShortIsbn();
        testLettersAndSymbols();

        System.out.println("\nISBN EP tests completed!");
    }

    static void testValidIsbn() {

        boolean result = BookManager.validateIsbn("1234567890123");

        if (result) {
            System.out.println(
                "TC-ISBN-01 PASS - Valid 13-digit ISBN accepted"
            );
        } else {
            System.out.println(
                "TC-ISBN-01 FAIL - Valid ISBN rejected"
            );
        }
    }

    static void testEmptyIsbn() {

        boolean result = BookManager.validateIsbn("");

        if (!result) {
            System.out.println(
                "TC-ISBN-02 PASS - Empty ISBN rejected"
            );
        } else {
            System.out.println(
                "TC-ISBN-02 FAIL - Empty ISBN accepted"
            );
        }
    }

    static void testShortIsbn() {

        boolean result = BookManager.validateIsbn("1234567890");

        if (!result) {
            System.out.println(
                "TC-ISBN-03 PASS - Short ISBN rejected"
            );
        } else {
            System.out.println(
                "TC-ISBN-03 FAIL - Short ISBN accepted"
            );
        }
    }

    static void testLettersAndSymbols() {

        boolean result = BookManager.validateIsbn("1234567890AB!");

        if (!result) {
            System.out.println(
                "TC-ISBN-04 PASS - Letters/symbols rejected"
            );
        } else {
            System.out.println(
                "TC-ISBN-04 FAIL - Letters/symbols accepted"
            );
        }
    }
}