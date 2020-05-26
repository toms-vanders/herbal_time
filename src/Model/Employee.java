package Model;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Used to represent employees of CS Works
 */
public class Employee extends Person {
    private Integer regNum;
    private Integer kontoNum;
    private BigDecimal salary;

    public Employee(String cpr, String fname, String lname, Date dob, char sex, String email,
                    String phoneNum, String streetName, String streetNum, String zip, String countryCode,
                    String country, Integer regNum, Integer kontoNum, BigDecimal salary) {
        // Creates the Person object
        super(cpr, fname, lname, dob, sex, email, phoneNum, streetName, streetNum, zip, countryCode, country);
        // Sets the rest of the parameters
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
