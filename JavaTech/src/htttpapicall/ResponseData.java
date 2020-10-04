package htttpapicall;

import java.util.Arrays;

public class ResponseData {
  public String status;
  public Employee employees[];
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public Employee[] getEmployees() {
	return employees;
}
public void setEmployees(Employee[] employees) {
	this.employees = employees;
}
@Override
public String toString() {
	return "ResponseData [status=" + status + ", employees=" + Arrays.toString(employees) + "]";
}
}
