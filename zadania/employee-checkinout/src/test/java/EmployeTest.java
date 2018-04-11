import employeeCheck.domain.Employee;
import org.junit.Assert;
import org.junit.Test;

public class EmployeTest {

    private static final String TEST_NAME = "Milos";

    @Test
    public void testEmployeeCreation() {
        Employee employee = new Employee();
        employee.setName(TEST_NAME);
        Assert.assertEquals(TEST_NAME, employee.getName());
    }
}
