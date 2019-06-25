package com.linhuanjie.javase;

import java.util.Arrays;

public class Test1 {

    public static void main(String[] args) {
        Action banzhuan = new Action();
        banzhuan.setToDo("搬砖");
        Action manage = new Action();
        manage.setToDo("管理");
        Action accountMoney = new Action();
        accountMoney.setToDo("数钱");

        Employee zhangsan = new Employee();
        zhangsan.setActions(new Action[]{banzhuan,accountMoney});


        Department yanfa = new Department();
        yanfa.setName("研发");
        yanfa.setEmployee(zhangsan);
        System.out.println(zhangsan);
    }


}


/**
 * 员工
 */
class Employee{
    private String name;
    private int age;

    private Department department;
    private Role role;
    private Action[] actions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Action[] getActions() {
        return actions;
    }

    public void setActions(Action[] actions) {
        this.actions = actions;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\''+"\n" +
                ", age=" + age +"\n" +
                ", department=" + department +"\n" +
                ", role=" + role +"\n" +
                ", actions=" + Arrays.toString(actions) +"\n" +
                '}'+"\n" ;
    }
}

/**
 * 部门
 */
class Department {
    private String name;
    private Employee employee;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Department{" +"\n" +
                "name='" + name + '\'' +"\n" +
                ", employee=" + employee +"\n" +
                '}'+"\n";
    }
}

/**
 * 角色
 */
class Role {
    private String name;
    private Employee[] employees;
    private Action action;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee[] getEmployees() {
        return employees;
    }

    public void setEmployees(Employee[] employees) {
        this.employees = employees;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "Role{" +"\n" +
                "name='" + name + '\'' +"\n" +
                ", employees=" + Arrays.toString(employees) +"\n" +
                ", action=" + action +"\n" +
                '}'+"\n" ;
    }
}

/**
 * 功能
 */
class Action{
    private String toDo;
    private Employee[] employees;
    private Action[] action;

    public String getToDo() {
        return toDo;
    }

    public void setToDo(String toDo) {
        this.toDo = toDo;
    }

    public Employee[] getEmployees() {
        return employees;
    }

    public void setEmployees(Employee[] employees) {
        this.employees = employees;
    }

    public Action[] getAction() {
        return action;
    }

    public void setAction(Action[] action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "Action{" +"\n" +
                "toDo='" + toDo + '\'' +"\n" +
                ", employees=" + Arrays.toString(employees) +"\n" +
                ", action=" + Arrays.toString(action) +"\n" +
                '}'+"\n";
    }
}

