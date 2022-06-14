package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Department implements Serializable {

    private Integer id;
    private final String name;

    public Department() {
        id = 0;
        name = "Nothing found";
    }
    public Department(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
    }
    public static class Builder{
        private Integer id;
        private String name;

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

        public Department build(){
            return new Department(this);
        }
    }

    public void setId (int id){
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Department {" +
                " id = " + id +
                ", name = " + name +' '+
                '}';
    }
}
