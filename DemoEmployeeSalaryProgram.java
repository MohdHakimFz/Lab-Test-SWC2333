/*
 * Programmer:Mohd Hakim Bin Mohd Fauzi
 * ID Number: AM2307014329
 * Date: 13.03.2024
 * Program: This Code is about Employee Salary Processing and need print the output into Notepad.txt not in Console.
 */
// Import necessary Java classes
import java.io.*;
import java.util.*;

/**
 * This class represents an Employee with a name, base salary, and years of service.
 */
class Employee {
    // Declare instance variables for the Employee class
    String name;
    double baseSalary;
    int yearsOfService;

    /**
     * Constructs an Employee with the given name, base salary, and years of service.
     */
    Employee(String name, double baseSalary, int yearsOfService) {
        // Initialize instance variables
        this.name = name;
        this.baseSalary = baseSalary;
        this.yearsOfService = yearsOfService;
    }

    /**
     * Calculates the annual salary of the employee.
     * @return the annual salary
     */
    double calculateAnnualSalary() {
        // Calculate annual salary based on base salary and years of service
        return baseSalary + (baseSalary * 0.05 * yearsOfService);
    }
}

/**
 * This is the main class where the program starts.
 */
public class DemoEmployeeSalaryProgram {
    public static void main(String[] args) {
        // Create a list to store Employee objects
        List<Employee> employees = new ArrayList<>();
        // Declare a PrintWriter object for output
        PrintWriter outStream = null;

        // Read data from the "employeeSalaries.txt" file
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Hakim\\Downloads\\employeeSalaries.txt"))) {
            String line;
            // Read each line from the file
            while ((line = reader.readLine()) != null) {
                // Use StringTokenizer to split the line into parts based on the comma separator
                StringTokenizer st = new StringTokenizer(line, ",");
                // Extract employee details from the tokens
                String name = st.nextToken();
                double baseSalary = Double.parseDouble(st.nextToken());
                int yearsOfService = Integer.parseInt(st.nextToken());
                // Create a new Employee object and add it to the list
                employees.add(new Employee(name, baseSalary, yearsOfService));
            }
        } catch (IOException e) {
            // Print an error message if there's a problem reading the file
            System.out.println("Error reading file: " + e.getMessage());
        }

        // Store Employee Data
        try {
            // Create a PrintWriter object for writing to a file
            outStream = new PrintWriter(new FileWriter("C:\\Users\\Hakim\\Downloads\\employeeData.txt"));
            
            // Write the header to the file
            outStream.println("Employee's Name\t\tTotal Annual Salaries\t\tYears of service");
            outStream.println("------------------------------------------------------------");

            // Calculate and display the annual salary for each employee
            for (Employee employee : employees) {
                double annualSalary = employee.calculateAnnualSalary();
                // Write the employee data to the file in the specified format
                outStream.println(employee.name + "\t\t\t\t" + annualSalary + "\t\t\t\t" + employee.yearsOfService);
            }

            // Initialize variables to store top performer and least experienced employee
            Employee topPerformer = null;
            Employee leastExperience = null;

            // Initialize variables to store maximum salary and maximum years of service
            double maxSalary = 0;
            int maxYearsOfService = Integer.MAX_VALUE;

            // Iterate over the employees list
            for (Employee employee : employees) {
                // Calculate the annual salary of the employee
                double annualSalary = employee.calculateAnnualSalary();

                // If the annual salary of the current employee is greater than maxSalary
                // update maxSalary and topPerformer
                if (annualSalary > maxSalary) {
                    maxSalary = annualSalary;
                    topPerformer = employee;
                }

                // If the years of service of the current employee is less than maxYearsOfService
                // update maxYearsOfService and leastExperience
                if (employee.yearsOfService < maxYearsOfService) {
                    maxYearsOfService = employee.yearsOfService;
                    leastExperience = employee;
                }
            }

            // Write the name of the top performer to the file
            outStream.println("\nTop performer: " + topPerformer.name);

            // Write the name of the employee with the least years of service to the file
            outStream.println("\nEmployee with least years of service: " + leastExperience.name);

            for (Employee employee : employees) {
                outStream.println(employee.name + "," + employee.calculateAnnualSalary() + "," + employee.yearsOfService);
            }
            System.out.println("Processing Completely Successfully.");
        } catch (IOException e) {
            // Print an error message if there's a problem writing to the file
            System.out.println("Error writing to file: " + e.getMessage());
        } finally {
            // Close the PrintWriter
            if (outStream != null) {
                outStream.close();
            }
        }
    }
}