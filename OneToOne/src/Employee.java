

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="emp1to11")

public class Employee {
@Id
@Column(name="empid",length=10)
private int eid;
@Column(name="empname",length=10)
private String ename;
@Column(name="empsal",length=10)
private int esal;

@OneToOne
private Address add;//has-a

public int getEsal() {
return esal;
}
public void setEsal(int esal) {
this.esal = esal;
}
public int getEid() {
return eid;
}
public void setEid(int eid) {
this.eid = eid;
}
public String getEname() {
return ename;
}
public void setEname(String ename) {
this.ename = ename;
}
public Address getAdd() {
return add;
}
public void setAdd(Address add) {
this.add = add;
}

}