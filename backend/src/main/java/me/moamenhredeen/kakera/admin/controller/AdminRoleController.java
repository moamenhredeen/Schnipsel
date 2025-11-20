package me.moamenhredeen.kakera.admin.controller;

import me.moamenhredeen.kakera.admin.dto.UserFilter;
import me.moamenhredeen.kakera.model.Role;
import me.moamenhredeen.kakera.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/roles")
public class AdminRoleController {

    private final RoleService roleService;

    public AdminRoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public String list(Model model, @RequestParam(required = false) String search) {
        model.addAttribute("roles", this.roleService.getAllRoles(search));
        model.addAttribute("search", search);
        return "admin/role/list";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("role", this.roleService.getById(id).orElseThrow());
        return "admin/role/details";
    }


    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("role", new Role());
        return "admin/role/create";
    }


    @PostMapping("/create")
    public String create(Role role) {
        roleService.create(role);
        return "redirect:/admin/roles";
    }

    @PostMapping("/delete/{id}")
    public String deleteRole(@PathVariable Long id){
        this.roleService.deleteById(id);
        return "redirect:/admin/roles";
    }
}
