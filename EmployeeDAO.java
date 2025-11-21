
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    public void addEmployee(Employee employee) {
        String sql = "INSERT INTO employees (name, position, base_salary) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, employee.getName());
            ps.setString(2, employee.getPosition());
            ps.setDouble(3, employee.getBaseSalary());
            ps.executeUpdate();

            System.out.println("Employee added successfully!");

        } catch (SQLException e) {
            System.out.println("Error adding employee.");
            e.printStackTrace();
        }
    }

    public List<Employee> getAllEmployees() {
        String sql = "SELECT * FROM employees";
        List<Employee> employees = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Employee emp = new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("position"),
                        rs.getDouble("base_salary")
                );
                employees.add(emp);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching employees.");
            e.printStackTrace();
        }

        return employees;
    }

    public Employee getEmployeeById(int id) {
        String sql = "SELECT * FROM employees WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Employee(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("position"),
                            rs.getDouble("base_salary")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching employee.");
            e.printStackTrace();
        }
        return null;
    }

    public void updateEmployeeSalary(int id, double newSalary) {
        String sql = "UPDATE employees SET base_salary = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, newSalary);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Salary updated successfully!");
            } else {
                System.out.println("No employee found with that ID.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating salary.");
            e.printStackTrace();
        }
    }

    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Employee deleted successfully!");
            } else {
                System.out.println("No employee found with that ID.");
            }

        } catch (SQLException e) {
            System.out.println("Error deleting employee.");
            e.printStackTrace();
        }
    }
}
