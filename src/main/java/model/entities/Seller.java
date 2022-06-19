package model.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

public class Seller implements Serializable {
    private Integer id;
    private String name;
    private String email;
    private Date birthDate;
    private BigDecimal baseSalary;

    private Department department;

    public Seller() {
        id = 0;
        name = "Nothing found";
        email = "";
        birthDate = new Date(0);
        baseSalary = new BigDecimal(0);
    }

    public Seller setId(Integer id) {
        this.id = id;
        return this;
    }

    public Seller setName(String name) {
        this.name = name;
        return this;
    }

    public Seller setEmail(String email) {
        this.email = email;
        return this;
    }

    public Seller setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public Seller setBaseSalary(int baseSalary) {
        this.baseSalary = new BigDecimal(baseSalary);
        return this;
    }

    public Seller setDepartment(Department department) {
        this.department = department;
        return this;
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

    public Date getBirthDate() {
        return birthDate;
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
        return "Seller {\n" +
                " id = " + id +
                " , name = " + name +
                " , email = " + email + '\n' +
                " birthDate = " + birthDate +
                " , baseSalary=" + baseSalary + '\n' +
                ' '+ department.toString() + '\n' +
                '}';
    }
}
