

import java.io.*;
import java.util.*;

// Manages employee records and employee-related operations
public class EmployeeManager {

    // Reads employee information from the employee file
    public static String loadEmployeeData() throws IOException {

        BufferedReader fileReader = new BufferedReader(
            new FileReader(Constants.EMPLOYEE_FILE));

        String employeeData = fileReader.readLine();

        fileReader.close();

        return employeeData;
    }

    // Saves updated employee information into the file
    public static void saveEmployeeData(String employeeData)
            throws IOException {

        BufferedWriter fileWriter = new BufferedWriter(
            new FileWriter(Constants.EMPLOYEE_FILE));

        fileWriter.write(employeeData);

        fileWriter.close();
    }

    // Main method: controls all employee management operations
    public static void main(String[] args) {

        // Validate the number of command-line arguments
        if (args.length != 1) {
            System.out.println("Please provide exactly one argument.");
            return;
        }

        // Store the user's command
        String command = args[0];

        // Check whether the command is empty
        if (command.trim().isEmpty()) {
            System.out.println("Invalid argument!");
            return;
        }

        // Display all employee names
        if (command.equals("l")) {

            System.out.println("Loading data ...");

            try {
                for (String employeeName : loadEmployeeData().split(",")) {
                    System.out.println(employeeName);
                }

            } catch (IOException exception) {
                System.out.println("Error reading employee data.");
            }

            System.out.println("Data Loaded.");

        }

        // Select a random employee
        else if (command.equals("s")) {

            System.out.println("Loading data ...");

            try {
                String[] employeeNames = loadEmployeeData().split(",");

                Random randomGenerator = new Random();

                int randomIndex =
                    randomGenerator.nextInt(employeeNames.length);

                System.out.println(employeeNames[randomIndex]);

            } catch (IOException exception) {
                System.out.println("Error reading employee data.");
            }

            System.out.println("Data Loaded.");

        }

        // Add a new employee
        else if (command.startsWith("+")) {

            if (command.length() == 1) {
                System.out.println("Please provide an employee name.");
                return;
            }

            System.out.println("Loading data ...");

            try {
                saveEmployeeData(
                    loadEmployeeData() + ", " + command.substring(1));

            } catch (IOException exception) {
                System.out.println("Error adding employee.");
            }

            System.out.println("Data Loaded.");

        }

        // Search for an employee
        else if (command.startsWith("?")) {

            if (command.length() == 1) {
                System.out.println("Please provide an employee name.");
                return;
            }

            System.out.println("Loading data ...");

            try {
                String[] employeeNames = loadEmployeeData().split(",");
                String searchName = command.substring(1);

                int currentIndex = 0;

                // Search until the name is found or the list ends
                while (currentIndex < employeeNames.length &&
                       !employeeNames[currentIndex].trim().equals(searchName)) {
                    currentIndex++;
                }

                if (currentIndex < employeeNames.length) {
                    System.out.println("Employee found!");
                } else {
                    System.out.println("Employee not found!");
                }

            } catch (IOException exception) {
                System.out.println("Error searching employee.");
            }

            System.out.println("Data Loaded.");

        }

        // Count words in employee data
        else if (command.startsWith("c")) {

            System.out.println("Loading data ...");

            try {
                String employeeData = loadEmployeeData().trim();

                int totalWords = employeeData.isEmpty()
                        ? 0
                        : employeeData.split("\\s+").length;

                System.out.println(totalWords + " word(s) found.");

            } catch (IOException exception) {
                System.out.println("Error counting words.");
            }

            System.out.println("Data Loaded.");

        }

        // Update an employee name
        else if (command.startsWith("u")) {

            if (command.length() == 1) {
                System.out.println("Please provide an employee name.");
                return;
            }

            System.out.println("Loading data ...");

            try {
                String[] employeeNames = loadEmployeeData().split(",");
                String employeeToUpdate = command.substring(1);

                // Find and update the employee name
                for (int currentIndex = 0;
                     currentIndex < employeeNames.length;
                     currentIndex++) {

                    if (employeeNames[currentIndex].trim()
                            .equals(employeeToUpdate)) {

                        employeeNames[currentIndex] = "Updated";
                    }
                }

                saveEmployeeData(String.join(",", employeeNames));

            } catch (IOException exception) {
                System.out.println("Error updating employee.");
            }

            System.out.println("Data Updated.");

        }

        // Delete an employee
        else if (command.startsWith("d")) {

            if (command.length() == 1) {
                System.out.println("Please provide an employee name.");
                return;
            }

            System.out.println("Loading data ...");

            try {
                String[] employeeNames = loadEmployeeData().split(",");
                String employeeToDelete = command.substring(1);

                List<String> remainingEmployees =
                    new ArrayList<>(Arrays.asList(employeeNames));

                // Remove the requested employee
                remainingEmployees.remove(employeeToDelete);

                saveEmployeeData(String.join(",", remainingEmployees));

            } catch (IOException exception) {
                System.out.println("Error deleting employee.");
            }

            System.out.println("Data Deleted.");

        }

        // Display an error message for unsupported commands
        else {

            System.out.println("Invalid argument: " + command);

            System.out.println(
                "Use l, s, +name, ?name, c, uname, or dname.");
        }
    }
}

