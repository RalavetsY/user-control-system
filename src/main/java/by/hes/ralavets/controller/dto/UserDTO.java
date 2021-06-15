package by.hes.ralavets.controller.dto;

import by.hes.ralavets.entity.Role;
import by.hes.ralavets.entity.UserAccount;
import lombok.Data;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class UserDTO {

    private Long id;

    @Pattern(regexp="^[A-Za-z]{3,16}$", message="Only latin symbols (3-16)")
    private String username;

    @Pattern(regexp="^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{3,16}$",
            message="Only latin symbols and digits (3-16). Each at least 1 time")
    private String password;

    @Pattern(regexp="^[A-Za-z]{1,16}$", message="Only latin symbols (1-16)")
    private String firstName;

    @Pattern(regexp="^[A-Za-z]{1,16}$", message="Only latin symbols (1-16)")
    private String lastName;

    private Role role;

    private boolean status;

    private Date createdAt;

    public UserDTO(UserAccount userAccount) {
        this.id = userAccount.getId();
        this.username = userAccount.getUsername();
        this.firstName = userAccount.getFirstName();
        this.lastName = userAccount.getLastName();
        this.role = userAccount.getRole();
        this.status = userAccount.isStatus();
        this.createdAt = userAccount.getCreatedAt();
    }

    public UserDTO() {

    }

    public UserAccount getUserAccount() {
        UserAccount userAccount = new UserAccount();
        userAccount.setId(this.getId());
        userAccount.setUsername(this.getUsername());
        userAccount.setPassword(this.getPassword());
        userAccount.setFirstName(this.getFirstName());
        userAccount.setLastName(this.getLastName());
        userAccount.setRole(this.getRole());
        userAccount.setStatus(this.isStatus());
        return userAccount;
    }

}