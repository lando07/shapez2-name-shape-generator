package com.shapez2;

import com.shapez2.shapeData.Shape;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
     static void main() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter resident's first name: ");
        String firstName;
        System.out.print("Enter first name: ");
        firstName = sc.nextLine().trim();
        System.out.print("Enter last name: ");
        String lastName = sc.nextLine().trim();
        if (firstName.isEmpty() && lastName.isEmpty()) {
            System.err.println("Error: At least one name (first or last) must be provided.");
            System.exit(1);
        }
        Shape s = NameEncoder.generateShape(firstName, lastName);
        System.out.println(s);
    }
}
