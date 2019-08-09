import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity//
@Table(name="emp")
public class Employee {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="a")
	@SequenceGenerator(name="a",sequenceName="mySeq")
	@Column(name="eid",length=20)
	private int id;
	@Column(name="ename",length=20)
	String name;
	@Column(name="esalary",length=20)
	int salary;
	public Employee(int id, String name, int salary) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
	}
	public Employee() {}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	
}
