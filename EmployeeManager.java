

import java.io.*;
import java.util.*;

public class EmployeeManager {

    // Method for reading employee data
    public static String readEmployeeData() throws IOException {
        BufferedReader reader = new BufferedReader(
            new FileReader(Constants.EMPLOYEE_FILE));

        String employeeData = reader.readLine();
        reader.close();

        return employeeData;
    }

    // Method for writing employee data
    public static void writeEmployeeData(String employeeData)
            throws IOException {

        BufferedWriter writer = new BufferedWriter(
            new FileWriter(Constants.EMPLOYEE_FILE));

        writer.write(employeeData);
        writer.close();
    }

    public static void main(String[] args) {

        // Check number of arguments
        if (args.length != 1) {
            System.out.println("Please provide exactly one argument.");
            return;
        }

        // Load employee data
        if (args[0].equals("l")) {

            System.out.println("Loading data ...");

            try {
                for (String employee : readEmployeeData().split(",")) {
                    System.out.println(employee);
                }

            } catch (IOException exception) {
                System.out.println("Error reading employee data.");
            }

            System.out.println("Data Loaded.");

        } else if (args[0].equals("s")) {

            // Select random employee
            System.out.println("Loading data ...");

            try {
                String[] employees = readEmployeeData().split(",");

                Random random = new Random();
                int randomIndex = random.nextInt(employees.length);

                System.out.println(employees[randomIndex]);

            } catch (IOException exception) {
                System.out.println("Error reading employee data.");
            }

            System.out.println("Data Loaded.");

        } else if (args[0].startsWith("+")) {

            // Add employee
            System.out.println("Loading data ...");

            try {
                writeEmployeeData(
                    readEmployeeData() + ", " + args[0].substring(1));

            } catch (IOException exception) {
                System.out.println("Error adding employee.");
            }

            System.out.println("Data Loaded.");

        } else if (args[0].startsWith("?")) {

            // Search employee
            System.out.println("Loading data ...");

            try {
                String[] employees = readEmployeeData().split(",");
                String searchName = args[0].substring(1);

                int index = 0;

                while (index < employees.length &&
                       !employees[index].trim().equals(searchName)) {
                    index++;
                }

                if (index < employees.length) {
                    System.out.println("Employee found!");
                } else {
                    System.out.println("Employee not found!");
                }

            } catch (IOException exception) {
                System.out.println("Error searching employee.");
            }

            System.out.println("Data Loaded.");

        } else if (args[0].startsWith("c")) {

            // Count words
            System.out.println("Loading data ...");

            try {
                char[] characters =
                    readEmployeeData().toCharArray();

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

                System.out.println(wordCount + " word(s) found "
                    + characters.length);

            } catch (IOException exception) {
                System.out.println("Error counting words.");
            }

            System.out.println("Data Loaded.");

        } else if (args[0].startsWith("u")) {

            // Update employee
            System.out.println("Loading data ...");

            try {
                String[] employees = readEmployeeData().split(",");
                String employeeName = args[0].substring(1);

                for (int index = 0; index < employees.length; index++) {

                    if (employees[index].trim().equals(employeeName)) {
                        employees[index] = "Updated";
                    }
                }

                writeEmployeeData(String.join(",", employees));

            } catch (IOException exception) {
                System.out.println("Error updating employee.");
            }

            System.out.println("Data Updated.");

        } else if (args[0].startsWith("d")) {

            // Delete employee
            System.out.println("Loading data ...");

            try {
                String[] employees = readEmployeeData().split(",");
                String employeeName = args[0].substring(1);

                List<String> employeeList =
                    new ArrayList<>(Arrays.asList(employees));

                employeeList.remove(employeeName);

                writeEmployeeData(String.join(",", employeeList));

            } catch (IOException exception) {
                System.out.println("Error deleting employee.");
            }

            System.out.println("Data Deleted.");
        }
    }
}
