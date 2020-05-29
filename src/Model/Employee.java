package Model;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Used to represent employees of CS Works
 *
 * @author Daniel Zoltan Ban
 * @author Mikuláš Dobrodej
 * @author Adrian Mihai Dohot
 * @author Damian Hrabąszcz
 * @author Toms Vanders
 * @version 1.0 (29.05.2020)
 *
 * Date: 29.05.2020
 */
public class Employee extends Person {
    private Integer regNum;
    private Integer kontoNum;
    private BigDecimal salary;

    /**
     * Constructor of Employee
     * @param cpr CPR number of employee
     * @param fname first name of employee
     * @param lname last name of employee
     * @param dob date of birth of employee
     * @param sex sex of employee
     * @param email email of employee
     * @param phoneNum phone number of employee
     * @param streetName street name of employee
     * @param streetNum street number of employee
     * @param zip zip code of employee
     * @param countryCode ISO country code of employee's country
     * @param country country of employee
     * @param regNum registration number of employee
     * @param kontoNum bank account number of employee
     * @param salary salary of employee
     */
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
