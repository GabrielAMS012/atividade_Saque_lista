package Atividade1;

import java.util.Scanner;

public class ClearBuffer {
    static void clearBuffer(Scanner scanner) {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }
}

