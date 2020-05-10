package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Employee extends Person {
    private Integer regNum;
    private Integer kontoNum;
    private BigDecimal salary;

    public Employee(String cpr, String fname, String lname, LocalDate dob, char sex, String email, String phoneNum,
                    String streetName, String streetNum, Integer zip, String countryCode, String country,
                    Integer regNum, Integer kontoNum, BigDecimal salary) {
        super(cpr, fname, lname, dob, sex, email, phoneNum, streetName, streetNum, zip, countryCode, country);
        this.regNum = regNum;
        this.kontoNum = kontoNum;
        this.salary = salary;
    }

    public Integer getRegNum() { return regNum; }
    public void setRegNum(Integer regNum) { this.regNum = regNum; }

    public Integer getKontoNum() { return kontoNum; }
    public void setKontoNum(Integer kontoNum) { this.kontoNum = kontoNum; }

    public BigDecimal getSalary() { return salary; }
    public void setSalary(BigDecimal salary) { this.salary = salary; }

    @Override
    public String toString() {
        return "Employee{" +
                "regNum=" + regNum +
                ", kontoNum=" + kontoNum +
                ", salary=" + salary +
                '}';
    }
}
