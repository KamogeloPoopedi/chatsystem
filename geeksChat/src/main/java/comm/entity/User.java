package comm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "\"User\"")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
        
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
//        @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//        @JoinColumn(name = "UserID")
//        private List<Contact> contact;


}
