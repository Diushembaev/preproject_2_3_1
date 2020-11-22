package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;
import web.service.UserServiceImpl;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    public String printUsers(ModelMap model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping(value = "/user")
    public String editUser(ModelMap model, @RequestParam String id) {
        User user = userService.getUserById(Long.valueOf(id));
        model.addAttribute("user",user);
        return "userEdit";
    }
    @PostMapping(value = "/user")
    public String saveUser(ModelMap model,
                           @RequestParam String id,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam String email) {
        User user = userService.getUserById(Long.valueOf(id));
        user.setFirstName(name);
        user.setLastName(surname);
        user.setEmail(email);
        userService.editUser(user);
        return "redirect:/users";
    }

    @PostMapping(value = "/users")
    public String deleteUser(ModelMap model,
                             @RequestParam String id) {
        userService.deleteUser(userService.getUserById(Long.valueOf(id)));
        return "redirect:/users";
    }
    @PostMapping(value = "/useradd")
    public String addUser(ModelMap model,
                          @RequestParam String name,
                          @RequestParam String surname,
                          @RequestParam String email) {
        userService.addUser(new User(name, surname, email));
        return "redirect:/users";
    }
}
