
import java.io.*;
import java.util.*;

public class EmployeeManager {

    // Method for reading employee data from file
    public static String readEmployeeData() throws IOException {
        BufferedReader reader = new BufferedReader(
            new FileReader("employees.txt"));

        String employeeData = reader.readLine();
        reader.close();

        return employeeData;
    }

    // Method for writing employee data to file
    public static void writeEmployeeData(String employeeData)
            throws IOException {

        BufferedWriter writer = new BufferedWriter(
            new FileWriter("employees.txt"));

        writer.write(employeeData);
        writer.close();
    }

    public static void main(String[] args) {

        // Check the number of arguments
        if (args.length != 1) {
            System.out.println("Please provide exactly one argument.");
            return;
        }

        // Load employee data
        if (args[0].equals("l")) {

            System.out.println("Loading data ...");

            try {
                String employeeData = readEmployeeData();
                String[] employees = employeeData.split(",");

                for (String employee : employees) {
                    System.out.println(employee);
                }

            } catch (IOException exception) {
                System.out.println("Error reading employee data.");
            }

            System.out.println("Data Loaded.");

        } else if (args[0].equals("s")) {

            // Select a random employee
            System.out.println("Loading data ...");

            try {
                String employeeData = readEmployeeData();
                System.out.println(employeeData);

                String[] employees = employeeData.split(",");

                Random random = new Random();
                int randomIndex = random.nextInt(employees.length);

                System.out.println(employees[randomIndex]);

            } catch (IOException exception) {
                System.out.println("Error reading employee data.");
            }

            System.out.println("Data Loaded.");

        } else if (args[0].contains("+")) {

            // Add a new employee
            System.out.println("Loading data ...");

            try {
                String employeeName = args[0].substring(1);
                String employeeData = readEmployeeData();

                writeEmployeeData(employeeData + ", " + employeeName);

            } catch (IOException exception) {
                System.out.println("Error adding employee.");
            }

            System.out.println("Data Loaded.");

        } else if (args[0].contains("?")) {

            // Search for an employee
            System.out.println("Loading data ...");

            try {
                String employeeData = readEmployeeData();
                String[] employees = employeeData.split(",");

                boolean employeeFound = false;
                String searchName = args[0].substring(1);

                for (int index = 0;
                     index < employees.length && !employeeFound;
                     index++) {

                    if (employees[index].trim().equals(searchName)) {
                        System.out.println("Employee found!");
                        employeeFound = true;
                    }
                }

            } catch (IOException exception) {
                System.out.println("Error searching employee.");
            }

            System.out.println("Data Loaded.");

        } else if (args[0].contains("c")) {

            // Count words in employee data
            System.out.println("Loading data ...");

            try {
                String employeeData = readEmployeeData();
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

            } catch (IOException exception) {
                System.out.println("Error counting words.");
            }

            System.out.println("Data Loaded.");

        } else if (args[0].contains("u")) {

            // Update an employee
            System.out.println("Loading data ...");

            try {
                String employeeData = readEmployeeData();
                String[] employees = employeeData.split(",");
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

        } else if (args[0].contains("d")) {

            // Delete an employee
            System.out.println("Loading data ...");

            try {
                String employeeData = readEmployeeData();
                String[] employees = employeeData.split(",");
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
