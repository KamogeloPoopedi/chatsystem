package comm.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "\"User\"")
@Data
public class User {
    // Note the double quotes around the table name
        
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "\"UserID\"") // Note the double quotes around the column name
        private Long userId;

        @Column(name = "\"Fname\"" , nullable = true) // Note the double quotes around the column name
        private String FName;

        @Column(name = "\"LName\"", nullable = true) // Note the double quotes around the column name
        private String LName;

        @Column(name = "email") // No need for double quotes if the column name is in lowercase
        private String email;

        @Column(name = "password") // No need for double quotes if the column name is in lowercase
        private String password;

        @Column(name = "\"userName\"") // Note the double quotes around the column name
        private String userName;
        @OneToMany(cascade = CascadeType.ALL)
        @JoinColumn(name = "UserID")
        private List<Contact> contact;

    public User(Long userId, String FName, String LName, String email, String password, String userName) {
        this.userId = userId;
        this.FName = FName;
        this.LName = LName;
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.contact = contact;

    }

    public User() {

    }
    

    public long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
