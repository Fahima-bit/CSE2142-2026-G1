
import java.io.*;
import java.util.*;

public class EmployeeManager {
    public static void main(String[] args) {

        // Check the number of arguments
        if (args.length != 1) {
            System.out.println("Please provide exactly one argument.");
            return;
        }

        // Check arguments
        if (args[0].equals("l")) {

            System.out.println("Loading data ...");

            try {
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")));

                String employeeData = reader.readLine();
                String[] employees = employeeData.split(",");

                for (String employee : employees) {
                    System.out.println(employee);
                }

            } catch (Exception exception) {
            }

            System.out.println("Data Loaded.");

        } else if (args[0].equals("s")) {

            System.out.println("Loading data ...");

            try {
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")));

                String employeeData = reader.readLine();
                System.out.println(employeeData);

                String[] employees = employeeData.split(",");

                Random random = new Random();
                int randomIndex = random.nextInt(employees.length);

                System.out.println(employees[randomIndex]);

            } catch (Exception exception) {
            }

            System.out.println("Data Loaded.");

        } else if (args[0].contains("+")) {

            System.out.println("Loading data ...");

            try {
                BufferedWriter writer = new BufferedWriter(
                    new FileWriter("employees.txt", true));

                String employeeName = args[0].substring(1);

                writer.write(", " + employeeName);
                writer.close();

            } catch (Exception exception) {
            }

            System.out.println("Data Loaded.");

        } else if (args[0].contains("?")) {

            System.out.println("Loading data ...");

            try {
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")));

                String employeeData = reader.readLine();
                String[] employees = employeeData.split(",");

                boolean employeeFound = false;
                String searchName = args[0].substring(1);

                for (int index = 0;
                     index < employees.length && !employeeFound;
                     index++) {

                    if (employees[index].equals(searchName)) {
                        System.out.println("Employee found!");
                        employeeFound = true;
                    }
                }

            } catch (Exception exception) {
            }

            System.out.println("Data Loaded.");

        } else if (args[0].contains("c")) {

            System.out.println("Loading data ...");

            try {
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")));

                String employeeData = reader.readLine();
                char[] characters = employeeData.toCharArray();

                boolean insideWord = false;
                int wordCount = 0;

                for (char character : characters) {

                    if (character == ' ') {

                        if (!insideWord) {
                            wordCount++;
                            insideWord = true;

                        } else {
                            insideWord = false;
                        }
                    }
                }

                System.out.println(wordCount + " word(s) found " + characters.length);

            } catch (Exception exception) {
            }

            System.out.println("Data Loaded.");

        } else if (args[0].contains("u")) {

            System.out.println("Loading data ...");

            try {
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")));

                String employeeData = reader.readLine();
                String[] employees = employeeData.split(",");
                String employeeName = args[0].substring(1);

                for (int index = 0; index < employees.length; index++) {

                    if (employees[index].equals(employeeName)) {
                        employees[index] = "Updated";
                    }
                }

                BufferedWriter writer = new BufferedWriter(
                    new FileWriter("employees.txt"));

                writer.write(String.join(",", employees));
                writer.close();

            } catch (Exception exception) {
            }

            System.out.println("Data Updated.");

        } else if (args[0].contains("d")) {

            System.out.println("Loading data ...");

            try {
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")));

                String employeeData = reader.readLine();
                String[] employees = employeeData.split(",");
                String employeeName = args[0].substring(1);

                List<String> employeeList =
                    new ArrayList<>(Arrays.asList(employees));

                employeeList.remove(employeeName);

                BufferedWriter writer = new BufferedWriter(
                    new FileWriter("employees.txt"));

                writer.write(String.join(",", employeeList));
                writer.close();

            } catch (Exception exception) {
            }

            System.out.println("Data Deleted.");
        }
    }
}
