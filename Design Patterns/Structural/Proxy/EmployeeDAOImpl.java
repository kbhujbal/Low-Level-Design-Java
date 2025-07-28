public class EmployeeDAOImpl implements EmployeeDAO{
    
    @Override
    public void create(String client, EmployeeDo obj) throws Exception{

        //Creates a new row
        System.out.println("Created new row in the employee table");
    }

    @Override
    public void delete(String client, int employeeId) throws Exception{

        // delete a row
        System.out.println("deleted a row with employeeID : " + employeeId);
    }

    @Override
    public EmployeeDo get(String client, int employeeId) throws Exception{
        System.out.println("Fetching data from DB");
        return new EmployeeDo();
    }
}
