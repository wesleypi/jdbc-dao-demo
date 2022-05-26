package model.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Seller implements Serializable {
    private Integer id;
    private String name;
    private String email;
    private Date birthDay;
    private BigDecimal baseSalary;

    private Department department;

    public Seller() {
    }

    public Seller(Integer id, String name, String email, Date birthDay, BigDecimal baseSalary, Department department) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDay = birthDay;
        this.baseSalary = baseSalary;
        this.department = department;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public BigDecimal getBaseSalary() {
        return baseSalary;
    }

    public Department getDepartment() {
        return department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seller seller = (Seller) o;
        return id.equals(seller.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Seller{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthDay=" + birthDay +
                ", baseSalary=" + baseSalary +
                ", department=" + department +
                '}';
    }
}
