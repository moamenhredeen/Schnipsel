package me.moamenhredeen.kakera.admin.controller;

import me.moamenhredeen.kakera.model.User;
import me.moamenhredeen.kakera.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/users")
public class AdminUserController {

    private final UserService userService;

    public AdminUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String users(Model model, @RequestParam(required = false) String search) {
        Page<User> users;
        if (search != null) {
            users = this.userService.search(search);
        } else {
            users = this.userService.getAllUsers();
        }
        model.addAttribute("search", search);
        model.addAttribute("users", users);
        return "admin/user/list";
    }


    @GetMapping("{id}")
    public String userById(Model model, @PathVariable Long id) {
        var user = this.userService.getById(id);
        if (user.isEmpty()) {
            return "admin/not-found";
        }
        model.addAttribute("user", user.get());
        return "admin/user/details";
    }

    @GetMapping("create")
    public String createUserForm(Model model) {
        model.addAttribute("user", new User());
        return "admin/user/create";
    }

    @PostMapping("create")
    public String createUser(Model model, User user) {
        try {
            this.userService.register(user);
            return "redirect:/admin/users";
        } catch (Exception e) {
            model.addAttribute("user", new User());
            model.addAttribute("error", e.getMessage());
            return "admin/user/create";
        }
    }

    @GetMapping("edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        try {
            var user = this.userService.getById(id);
            model.addAttribute("user", user.orElseThrow());
            return "/admin/user/edit";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/admin/users";
        }
    }


    @PostMapping("edit")
    public String editUser(User user, Model model) {
        try {
            this.userService.update(user);
            return "redirect:/admin/users";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/admin/users/edit/%s".formatted(user.getId());
        }
    }


    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        this.userService.deleteById(id);
        return "redirect:/admin/users";
    }
}
