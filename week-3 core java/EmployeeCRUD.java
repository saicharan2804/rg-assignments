import java.util.ArrayList;

public class EmployeeCRUD {
    private ArrayList<Employee> employees = new ArrayList<>();

    // Create
    public void addEmployee(Employee employee) {
        employees.add(employee);
        System.out.println("Employee added: " + employee);
    }

    // Read
    public Employee getEmployee(int id) {
        for (Employee e : employees) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    // Update
    public void updateEmployee(Employee employee) {
        for (Employee e : employees) {
            if (e.getId() == employee.getId()) {
                e.setName(employee.getName());
                e.setDepartment(employee.getDepartment());
                System.out.println("Employee updated: " + e);
                return;
            }
        }
        System.out.println("Employee not found: " + employee.getId());
    }

    // Delete
    public void deleteEmployee(int id) {
        Employee employeeToDelete = null;
        for (Employee e : employees) {
            if (e.getId() == id) {
                employeeToDelete = e;
                break;
            }
        }
        if (employeeToDelete != null) {
            employees.remove(employeeToDelete);
            System.out.println("Employee deleted: " + employeeToDelete);
        } else {
            System.out.println("Employee not found: " + id);
        }
    }

    // Display All Employees
    public void displayAllEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            for (Employee e : employees) {
                System.out.println(e);
            }
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        EmployeeCRUD employeeCRUD = new EmployeeCRUD();

        // Adding Employees
        employeeCRUD.addEmployee(new Employee(1, "John Doe", "Engineering"));
        employeeCRUD.addEmployee(new Employee(2, "Jane Smith", "Marketing"));

        // Displaying all employees
        System.out.println("All Employees:");
        employeeCRUD.displayAllEmployees();

        // Reading an Employee
        System.out.println("Read Employee with ID 1:");
        System.out.println(employeeCRUD.getEmployee(1));

        // Updating an Employee
        System.out.println("Updating Employee with ID 2:");
        employeeCRUD.updateEmployee(new Employee(2, "Jane Doe", "Sales"));

        // Displaying all employees after update
        System.out.println("All Employees after update:");
        employeeCRUD.displayAllEmployees();

        // Deleting an Employee
        System.out.println("Deleting Employee with ID 1:");
        employeeCRUD.deleteEmployee(1);

        // Displaying all employees after deletion
        System.out.println("All Employees after deletion:");
        employeeCRUD.displayAllEmployees();
    }
}