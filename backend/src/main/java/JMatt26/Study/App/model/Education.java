package JMatt26.Study.App.model;

import JMatt26.Study.App.model.enums.Degree;
import JMatt26.Study.App.model.enums.Department;
import JMatt26.Study.App.model.enums.Program;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Education {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int educationId;

    private Degree degree;

    private Department department;

    private Program major;

    private Program minor;

    public int getEducationId() {
        return educationId;
    }

    public void setEducationId(int educationId) {
        this.educationId = educationId;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Program getMajor() {
        return major;
    }

    public void setMajor(Program major) {
        this.major = major;
    }

    public Program getMinor() {
        return minor;
    }

    public void setMinor(Program minor) {
        this.minor = minor;
    }

    
}
