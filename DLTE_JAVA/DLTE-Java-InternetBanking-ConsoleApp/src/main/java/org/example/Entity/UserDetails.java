package org.example.Entity;

import java.io.Serializable;
import java.util.Date;
    public class UserDetails implements Serializable {
        private String userName;
        private String password;
        private Date dateOfBirth;
        private String address;
        private String emailId;
        private long phoneNumber;

        public UserDetails(String userName, String password, Date dateOfBirth, String address, String emailId, long phoneNumber) {
            this.userName = userName;
            this.password = password;
            this.dateOfBirth = dateOfBirth;
            this.address = address;
            this.emailId = emailId;
            this.phoneNumber = phoneNumber;
        }

        @Override
        public String toString() {
            return "UserDetails{" +
                    "userName='" + userName + '\'' +
                    ", password='" + password + '\'' +
                    ", dateOfBirth=" + dateOfBirth +
                    ", address='" + address + '\'' +
                    ", emailId='" + emailId + '\'' +
                    ", phoneNumber=" + phoneNumber +
                    '}';
        }

        public String getuserName() {
            return userName;
        }

        public void setuserName(String userName) {
            this.userName = userName;
        }

        public String getpassword() {
            return password;
        }

        public void setpassword(String password) {
            this.password = password;
        }

        public Date getdateOfBirth() {
            return dateOfBirth;
        }

        public void setdateOfBirth(Date dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public String getaddress() {
            return address;
        }

        public void setaddress(String address) {
            this.address = address;
        }

        public String getemailId() {
            return emailId;
        }

        public void setemailId(String emailId) {
            this.emailId = emailId;
        }

        public long getphoneNumber() {
            return phoneNumber;
        }

        public void setphoneNumber(long phoneNumber) {
            this.phoneNumber = phoneNumber;
        }


    }
