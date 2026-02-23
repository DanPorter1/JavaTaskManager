package taskManager.service;

import taskManager.util.Priority;

import java.util.Scanner;

public class InputService {

    private final Scanner scan;

    public InputService(){
        this.scan = new Scanner(System.in);
    }

    public String readString(String prompt) {
        System.out.print(prompt + " (or type 'q' to cancel): ");
        String input = scan.nextLine();
        if (input.equalsIgnoreCase("q")) {
            return null;
        }
        while (input.trim().isEmpty()) {
            System.out.print("Input cannot be empty. " + prompt + ": ");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("q")) return null;
        }
        return input;
    }

    public int readInt(String prompt){
        while (true) {
            System.out.print(prompt + " (Leave blank to cancel): ");
            String input = scan.nextLine().trim();
            if (input.isEmpty()) {
                return -1;
            }
            try {
                int value = Integer.parseInt(input);
                if (value < 0) {
                    System.out.println("Number must be positive.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    public Priority readPriority() {
        while (true) {
            System.out.println("Select Priority:\n1 - HIGH\n2 - MEDIUM\n3 - LOW");
            String input = scan.nextLine().toLowerCase().trim();
            switch (input) {
                case "high", "1" -> {return Priority.HIGH;}
                case "medium", "2" -> {return Priority.MEDIUM;}
                case "low", "3" -> {return Priority.LOW;}
                default -> System.out.println("Invalid choice. Please enter 1, 2, 3 or the name.");
            }
        }
    }
}
