package comm.Dto;

import lombok.Data;

@Data
public class MessageDto {
    private  long senderId;
    private long receiverId;
    private String content;
}
