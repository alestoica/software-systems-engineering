package domain;

public class Member extends Entity<String> {
    private String password, name, CNP, address, phoneNumber;

    public Member(String password, String name, String CNP, String address, String phoneNumber) {
        this.password = password;
        this.name = name;
        this.CNP = CNP;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Member { " +
                "id: " + id +
                ", password: " + password +
                ", name: " + name +
                ", CNP: " + CNP +
                ", address: " + address +
                ", phoneNumber: " + phoneNumber +
                " }";
    }
}
