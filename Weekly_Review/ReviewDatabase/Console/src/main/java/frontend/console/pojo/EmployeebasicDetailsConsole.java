package frontend.console.pojo;

public class EmployeebasicDetailsConsole {
        private String name;
        private String id;
        private String email;
        private long phoneNumber;

        public EmployeebasicDetailsConsole(String name, String id, String email, long phoneNumber) {
            this.name = name;
            this.id = id;
            this.email = email;
            this.phoneNumber = phoneNumber;
        }

    public EmployeebasicDetailsConsole() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

