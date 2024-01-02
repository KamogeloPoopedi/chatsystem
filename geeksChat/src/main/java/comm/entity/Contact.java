
package comm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "contactid")
    private  long contactID;
    @ManyToOne
    @JoinColumn(name = "userid")
    private User userID;
    @ManyToOne
   // this references the userid
    @JoinColumn(name = "contactuserid")
    private User contactUserID;


}

