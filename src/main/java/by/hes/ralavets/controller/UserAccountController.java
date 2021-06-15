package by.hes.ralavets.controller;

import by.hes.ralavets.controller.dto.UserDTO;
import by.hes.ralavets.entity.UserAccount;
import by.hes.ralavets.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@Controller
@RequestMapping("/user")
public class UserAccountController {

    private final UserAccountService userAccountService;

    @Autowired
    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    //    <<<    CREATE    >>>

    @GetMapping("/new")
    public String create(Model model, @AuthenticationPrincipal UserAccount currentUser) {
        model.addAttribute("userDTO", new UserDTO());
        model.addAttribute("currentUser", currentUser);
        return "creationForm";
    }

    @PostMapping("/new")
    public String doCreate(@Valid UserDTO userDTO, Errors errors, Model model,
                           @AuthenticationPrincipal UserAccount currentUser) {
        if (errors.hasErrors()) {
            model.addAttribute("currentUser", currentUser);
            return "creationForm";
        }
        if (userAccountService.isNotUniqueUsername(userDTO.getUsername())) {
            model.addAttribute("currentUser", currentUser);
            model.addAttribute("usernameFail", "Username is not unique");
            return "creationForm";
        }
        return "redirect:/user/" + userAccountService.create(userDTO.getUserAccount()).getId();
    }

    //    <<<    READ    >>>

    @GetMapping
    public String userList(Model model, @AuthenticationPrincipal UserAccount currentUser) {
        model.addAttribute("userList", userAccountService.getAll());
        model.addAttribute("currentUser", currentUser);
        return "userList";
    }

    @GetMapping("/{userId}")
    public String userPage(@PathVariable Long userId, Model model, @AuthenticationPrincipal UserAccount currentUser) {
        model.addAttribute("user", userAccountService.getById(userId));
        model.addAttribute("currentUser", currentUser);
        return "userPage";
    }

    //    <<<    UPDATE    >>>

    @GetMapping("/{userId}/edit")
    public String edit(@PathVariable Long userId, Model model, @AuthenticationPrincipal UserAccount currentUser) {
        model.addAttribute("userDTO", new UserDTO(userAccountService.getById(userId)));
        model.addAttribute("currentUser", currentUser);
        return "editingForm";
    }

    @PostMapping("/{userId}/edit")
    public String doEdit(@Valid UserDTO userDTO, Errors errors, @PathVariable Long userId, Model model,
                         @AuthenticationPrincipal UserAccount currentUser) {
        userDTO.setId(userId);
        if (errors.hasErrors()) {
            model.addAttribute("currentUser", currentUser);
            return "editingForm";
        }
        return "redirect:/user/" + userAccountService.update(userDTO.getUserAccount()).getId();
    }
}