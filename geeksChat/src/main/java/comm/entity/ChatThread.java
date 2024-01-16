package comm.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "chat")
public class ChatThread {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long chatId;
    @ManyToOne
    @JoinColumn(name = "senderId")
    private User sender;
    @ManyToOne
    @JoinColumn(name = "receiverId")
    private  User receiver;
    @Column(name = "content")
    private String content;

    public ChatThread(long senderId, long receiverId, String content) {
    }
}
