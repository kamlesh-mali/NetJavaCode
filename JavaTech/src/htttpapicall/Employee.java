package htttpapicall;

public class Employee {
 
    	public int id;
		public String employee_name;
		public int age;
		public String profile_image;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getEmployee_name() {
			return employee_name;
		}
		public void setEmployee_name(String employee_name) {
			this.employee_name = employee_name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public String getProfile_image() {
			return profile_image;
		}
		public void setProfile_image(String profile_image) {
			this.profile_image = profile_image;
		}
		@Override
		public String toString() {
			return "Employee [id=" + id + ", employee_name=" + employee_name + ", age=" + age + ", profile_image="
					+ profile_image + "]";
		}

		
}
