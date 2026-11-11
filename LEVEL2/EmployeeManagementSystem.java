
package LEVEL2;

import java.util.ArrayList;
import java.util.Scanner;

class Employee {

    int id;
    String name;
    double salary;

    Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    void display() {
        System.out.println("Employee ID: " + id);
        System.out.println("Employee Name: " + name);
        System.out.println("Employee Salary: " + salary);
        System.out.println("----------------------");
    }
}

public class EmployeeManagementSystem {

    static ArrayList<Employee> employees = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int choice;

        do {
            System.out.println("\n================================");
            System.out.println("   EMPLOYEE MANAGEMENT SYSTEM");
            System.out.println("================================");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");

            System.out.print("\nEnter your choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addEmployee(sc);
                    break;

                case 2:
                    viewEmployees();
                    break;

                case 3:
                    updateEmployee(sc);
                    break;

                case 4:
                    deleteEmployee(sc);
                    break;

                case 5:
                    System.out.println("Thank you for using Employee Management System!");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 5);

        sc.close();
    }

    // ADD EMPLOYEE
    static void addEmployee(Scanner sc) {

        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Employee e : employees) {
            if (e.id == id) {
                System.out.println("Employee ID already exists!");
                return;
            }
        }

        System.out.print("Enter Employee Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Employee Salary: ");
        double salary = sc.nextDouble();

        Employee emp = new Employee(id, name, salary);

        employees.add(emp);

        System.out.println("Employee added successfully!");
    }

    // VIEW EMPLOYEES
    static void viewEmployees() {

        if (employees.isEmpty()) {
            System.out.println("No employees found!");
            return;
        }

        System.out.println("\nAll Employee Details:");

        for (Employee e : employees) {
            e.display();
        }
    }

    // UPDATE EMPLOYEE
    static void updateEmployee(Scanner sc) {

        System.out.print("Enter Employee ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Employee e : employees) {

            if (e.id == id) {

                System.out.print("Enter new name: ");
                e.name = sc.nextLine();

                System.out.print("Enter new salary: ");
                e.salary = sc.nextDouble();

                System.out.println("Employee updated successfully!");
                return;
            }
        }

        System.out.println("Employee not found!");
    }

    // DELETE EMPLOYEE
    static void deleteEmployee(Scanner sc) {

        System.out.print("Enter Employee ID to delete: ");
        int id = sc.nextInt();

        for (int i = 0; i < employees.size(); i++) {

            if (employees.get(i).id == id) {

                employees.remove(i);

                System.out.println("Employee deleted successfully!");
                return;
            }
        }

        System.out.println("Employee not found!");
    }
}
