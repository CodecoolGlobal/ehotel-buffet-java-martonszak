package com.codecool.ehotel.service.ui;

import java.util.Scanner;

public class Input {

    private final Scanner scanner = new Scanner(System.in);

    public String getTextInput() {
        return scanner.nextLine();
    }

    public int getNumberInput() {
        return scanner.nextInt();
    }


}
