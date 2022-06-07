package model.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Seller implements Serializable {
    private final Integer id;
    private final String name;
    private final String email;
    private final Date birthDay;
    private final BigDecimal baseSalary;

    private Department department;

    public Seller() {
        id = 0;
        name = "Nothing found";
        email = "";
        birthDay = new Date();
        baseSalary = new BigDecimal(0);
    }

    public Seller(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.email = builder.email;
        this.birthDay = builder.birthDay;
        this.baseSalary = builder.baseSalary;
        this.department = builder.department;
    }

    public static class Builder{
        private Integer id;
        private String name;
        private  String email;
        private Date birthDay;
        private BigDecimal baseSalary;

        private Department department;

        public static Builder newInstance(){
            return new Builder();
        }
        private Builder(){}

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setBirthDay(Date birthDay) {
            this.birthDay = birthDay;
            return this;
        }

        public Builder setBaseSalary(int baseSalary) {
            this.baseSalary = BigDecimal.valueOf(baseSalary);
            return this;
        }

        public Builder setDepartment(Department department) {
            this.department = department;
            return this;
        }

        public Seller build(){
            return new Seller(this);
        }
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
