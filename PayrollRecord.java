
public class PayrollRecord {
    private int id;
    private int employeeId;
    private int month;
    private int year;
    private double grossSalary;
    private double deductions;
    private double netSalary;

    public PayrollRecord() {
    }

    public PayrollRecord(int employeeId, int month, int year, double grossSalary, double deductions, double netSalary) {
        this.employeeId = employeeId;
        this.month = month;
        this.year = year;
        this.grossSalary = grossSalary;
        this.deductions = deductions;
        this.netSalary = netSalary;
    }

    public PayrollRecord(int id, int employeeId, int month, int year, double grossSalary,
                         double deductions, double netSalary) {
        this.id = id;
        this.employeeId = employeeId;
        this.month = month;
        this.year = year;
        this.grossSalary = grossSalary;
        this.deductions = deductions;
        this.netSalary = netSalary;
    }

    public int getId() {
        return id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public double getGrossSalary() {
        return grossSalary;
    }

    public double getDeductions() {
        return deductions;
    }

    public double getNetSalary() {
        return netSalary;
    }

    @Override
    public String toString() {
        return "PayrollRecord {" +
                "id=" + id +
                ", employeeId=" + employeeId +
                ", month=" + month +
                ", year=" + year +
                ", grossSalary=" + grossSalary +
                ", deductions=" + deductions +
                ", netSalary=" + netSalary +
                '}';
    }
}
