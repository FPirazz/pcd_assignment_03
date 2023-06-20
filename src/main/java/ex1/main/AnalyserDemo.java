package ex1.main;

import ex1.actors.ActorsAnalyser;

import java.util.Scanner;

public class AnalyserDemo {

    private static SourceAnalyser analyser = null;
    private static int selectedAnalyser = -1;
    private static int selectedMethod = -1;

    public static void main(String[] args) throws InterruptedException {



        Scanner scanner = new Scanner(System.in);
        analyser = new ActorsAnalyser();

        while (selectedMethod < 1 || selectedMethod > 2) {
            System.out.println("Choose method to test (1 to 2): ");
            System.out.println("1. getReport()");
            System.out.println("2. analyzeSources()");
            if (scanner.hasNextInt()) {
                selectedMethod = scanner.nextInt();
                scanner.nextLine();
            } else {
                scanner.nextLine(); // Discard invalid input
            }
        }


        switch (selectedMethod) {
            case 1 -> {
                scanner = new Scanner(System.in);
                String input = null;
                System.out.println("Enter parameters directory path, number of intervals, max lines and number of top files separated by spaces: ");
                if (scanner.hasNextLine()) {
                    input = scanner.nextLine();
                }
                assert input != null;
                String[] params = input.split(" ");
                scanner.close();
                if (params.length == 4) {
                    String directory = params[0];
                    int ranges = Integer.parseInt(params[1]);
                    int maxL = Integer.parseInt(params[2]);
                    int numTopFiles = Integer.parseInt(params[3]);
                    analyser.getReport(directory, ranges, maxL, numTopFiles);

                } else {
                    System.out.println("Invalid input. Please enter four parameters separated by spaces.");
                }
            }
            case 2 -> analyser.analyzeSources();
        }
        scanner.close();

    }

}
