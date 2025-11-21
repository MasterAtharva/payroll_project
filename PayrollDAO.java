
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PayrollDAO {

    public void addPayrollRecord(PayrollRecord record) {
        String sql = "INSERT INTO payrolls (employee_id, month, year, gross_salary, deductions, net_salary) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, record.getEmployeeId());
            ps.setInt(2, record.getMonth());
            ps.setInt(3, record.getYear());
            ps.setDouble(4, record.getGrossSalary());
            ps.setDouble(5, record.getDeductions());
            ps.setDouble(6, record.getNetSalary());

            ps.executeUpdate();
            System.out.println("Payroll record added successfully!");

        } catch (SQLException e) {
            System.out.println("Error adding payroll record.");
            e.printStackTrace();
        }
    }

    public List<PayrollRecord> getPayrollsByEmployeeId(int employeeId) {
        String sql = "SELECT * FROM payrolls WHERE employee_id = ?";
        List<PayrollRecord> records = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, employeeId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    PayrollRecord record = new PayrollRecord(
                            rs.getInt("id"),
                            rs.getInt("employee_id"),
                            rs.getInt("month"),
                            rs.getInt("year"),
                            rs.getDouble("gross_salary"),
                            rs.getDouble("deductions"),
                            rs.getDouble("net_salary")
                    );
                    records.add(record);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching payroll records.");
            e.printStackTrace();
        }

        return records;
    }
}
