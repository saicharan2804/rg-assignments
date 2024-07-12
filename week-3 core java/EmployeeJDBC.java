import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeJDBC {
    private static final String URL = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String USER = "username";
    private static final String PASSWORD = "password";

    // Establish a connection to the database
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Create
    public void addEmployee(Employee employee) {
        String sql = "INSERT INTO employees (id, name, department) VALUES (?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, employee.getId());
            pstmt.setString(2, employee.getName());
            pstmt.setString(3, employee.getDepartment());
            pstmt.executeUpdate();
            System.out.println("Employee added: " + employee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read
    public Employee getEmployee(int id) {
        String sql = "SELECT * FROM employees WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("department"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update
    public void updateEmployee(Employee employee) {
        String sql = "UPDATE employees SET name = ?, department = ? WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, employee.getName());
            pstmt.setString(2, employee.getDepartment());
            pstmt.setInt(3, employee.getId());
            pstmt.executeUpdate();
            System.out.println("Employee updated: " + employee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete
    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Employee deleted with ID: " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Display All Employees
    public void displayAllEmployees() {
        String sql = "SELECT * FROM employees";
        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Employee employee = new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("department"));
                System.out.println(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        EmployeeJDBC employeeJDBC = new EmployeeJDBC();

        // Adding Employees
        employeeJDBC.addEmployee(new Employee(1, "John Doe", "Engineering"));
        employeeJDBC.addEmployee(new Employee(2, "Jane Smith", "Marketing"));

        // Displaying all employees
        System.out.println("All Employees:");
        employeeJDBC.displayAllEmployees();

        // Reading an Employee
        System.out.println("Read Employee with ID 1:");
        Employee employee = employeeJDBC.getEmployee(1);
        System.out.println(employee);

        // Updating an Employee
        System.out.println("Updating Employee with ID 2:");
        employeeJDBC.updateEmployee(new Employee(2, "Jane Doe", "Sales"));

        // Displaying all employees after update
        System.out.println("All Employees after update:");
        employeeJDBC.displayAllEmployees();

        // Deleting an Employee
        System.out.println("Deleting Employee with ID 1:");
        employeeJDBC.deleteEmployee(1);

        // Displaying all employees after deletion
        System.out.println("All Employees after deletion:");
        employeeJDBC.displayAllEmployees();
    }
}