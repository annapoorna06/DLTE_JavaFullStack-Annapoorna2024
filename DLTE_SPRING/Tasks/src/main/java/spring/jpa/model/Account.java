package spring.jpa.model;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="User_Account")
public class Account implements Serializable {
    @Id
    @Column
    private String userName;
    @Column
    private String password;
    @Column
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Column
    private String emailId;
    @Column
    private String Address;
    @Column
    private Long phoneNumber;

    public Account() {
    }

    @Override
    public String toString() {
        return "Account{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", emailId='" + emailId + '\'' +
                ", Address='" + Address + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }

    public Account(String userName, String password, Date dateOfBirth, String emailId, String address, Long phoneNumber) {
        this.userName = userName;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.emailId = emailId;
        Address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
