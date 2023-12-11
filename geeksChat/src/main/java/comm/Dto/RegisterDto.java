package comm.Dto;

import lombok.Data;

@Data
public class RegisterDto {
    private String email;
    private String password;
    private String userName;
}
