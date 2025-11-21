
import java.util.List;
import java.util.Scanner;

public class PayrollApp {

    private static final EmployeeDAO employeeDAO = new EmployeeDAO();
    private static final PayrollDAO payrollDAO = new PayrollDAO();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            showMenu();
            choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    addEmployeeFlow();
                    break;
                case 2:
                    listEmployeesFlow();
                    break;
                case 3:
                    updateSalaryFlow();
                    break;
                case 4:
                    deleteEmployeeFlow();
                    break;
                case 5:
                    generatePayrollFlow();
                    break;
                case 6:
                    viewPayrollsFlow();
                    break;
                case 0:
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 0);

        scanner.close();
    }

    private static void showMenu() {
        System.out.println("\n==== Employee Payroll System ====");
        System.out.println("1. Add Employee");
        System.out.println("2. List All Employees");
        System.out.println("3. Update Employee Base Salary");
        System.out.println("4. Delete Employee");
        System.out.println("5. Generate Payroll for Employee");
        System.out.println("6. View Payrolls for Employee");
        System.out.println("0. Exit");
        System.out.println("=================================");
    }

    private static void addEmployeeFlow() {
        System.out.println("\n--- Add Employee ---");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter position: ");
        String position = scanner.nextLine();

        double baseSalary = readDouble("Enter base salary: ");

        Employee employee = new Employee(name, position, baseSalary);
        employeeDAO.addEmployee(employee);
    }

    private static void listEmployeesFlow() {
        System.out.println("\n--- Employee List ---");
        List<Employee> employees = employeeDAO.getAllEmployees();

        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            for (Employee emp : employees) {
                System.out.println(emp);
            }
        }
    }

    private static void updateSalaryFlow() {
        System.out.println("\n--- Update Employee Salary ---");
        int id = readInt("Enter employee ID: ");
        double newSalary = readDouble("Enter new base salary: ");
        employeeDAO.updateEmployeeSalary(id, newSalary);
    }

    private static void deleteEmployeeFlow() {
        System.out.println("\n--- Delete Employee ---");
        int id = readInt("Enter employee ID: ");
        employeeDAO.deleteEmployee(id);
    }

    private static void generatePayrollFlow() {
        System.out.println("\n--- Generate Payroll ---");
        int empId = readInt("Enter employee ID: ");

        Employee emp = employeeDAO.getEmployeeById(empId);
        if (emp == null) {
            System.out.println("Employee not found.");
            return;
        }

        int month = readInt("Enter month (1-12): ");
        int year = readInt("Enter year (e.g. 2025): ");

        // Simple payroll calculation logic
        double base = emp.getBaseSalary();
        double hra = base * 0.20;  // House Rent Allowance 20%
        double da = base * 0.10;   // Dearness Allowance 10%
        double gross = base + hra + da;

        double tax = gross * 0.10; // 10% tax
        double deductions = tax;
        double net = gross - deductions;

        PayrollRecord record = new PayrollRecord(empId, month, year, gross, deductions, net);
        payrollDAO.addPayrollRecord(record);

        System.out.println("Payroll generated:");
        System.out.println("Employee: " + emp.getName());
        System.out.println("Base Salary: " + base);
        System.out.println("Gross Salary: " + gross);
        System.out.println("Deductions: " + deductions);
        System.out.println("Net Salary: " + net);
    }

    private static void viewPayrollsFlow() {
        System.out.println("\n--- View Payrolls ---");
        int empId = readInt("Enter employee ID: ");

        List<PayrollRecord> records = payrollDAO.getPayrollsByEmployeeId(empId);
        if (records.isEmpty()) {
            System.out.println("No payroll records found for this employee.");
        } else {
            for (PayrollRecord record : records) {
                System.out.println(record);
            }
        }
    }

    // Helper methods for safe number input
    private static int readInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                String line = scanner.nextLine();
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private static double readDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                String line = scanner.nextLine();
                return Double.parseDouble(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
