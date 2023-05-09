package com.CentraleAchat.userservice.controllers;

import com.CentraleAchat.userservice.services.utilsService.EmailSenderService;
import com.CentraleAchat.userservice.dto.UserDto;
import com.CentraleAchat.userservice.services.entitiesService.UserService;
import com.CentraleAchat.userservice.services.utilsService.KeycloakService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
@Slf4j
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    private UserService userService;
    private EmailSenderService emailSenderService;
    private KeycloakService keycloakService;
    private Keycloak keycloak;

    ///test
    @GetMapping("/clear")
    public void clear() {
        userService.clearEvent();
    }
    @GetMapping("/getLoginErrorsForUser/{userid}")
    public int getLoginErrorsForUser(@PathVariable String userid) {
        int c=userService.getLoginErrorsForUser(userid);
        System.out.println(c);
        return c;
    }
    @GetMapping("/graphvalue/{userid}")
    public Map<Date, List<Integer>> graphvalue(@PathVariable String userid){
        System.out.println(userService.graphvalue(userid));
        return userService.graphvalue(userid);
    }
    @DeleteMapping("/deleteUser/{userId}")
    public void deleteUser(@PathVariable String userId) {
        System.out.println("deleting");
        userService.deleteUser(userId);
        System.out.println("deleted");
    }
    @GetMapping("/getAllUsers")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("/sendMail/{to}/{subject}/{body}")
    public ResponseEntity sendMail(@PathVariable String to, @PathVariable String subject, @PathVariable String body) {
        System.out.println(to+subject+body);
        emailSenderService.sendSimpleEmail(to, subject, body);
        System.out.println("mail sent");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestParam String username, @RequestParam String password) {
        return ResponseEntity.status(HttpStatus.OK).body(KeycloakService.authenticate(username, password));
    }

    //    @PostMapping("/logout")
//    public ResponseEntity logout() {
//        return ResponseEntity.status(HttpStatus.OK).body(KeycloakService.logout(keycloakService.whoAmI()););
//    }
    @CrossOrigin(origins = "*")
    @GetMapping("/profile")
    public ResponseEntity profile() {
        return ResponseEntity.status(HttpStatus.OK).body(keycloakService.whoAmI());
    }

    ///

    @PostMapping("/registerSupplierClient")
    public ResponseEntity registerSupplierClient(@RequestBody UserDto userDto) {
        String result = userService.registerSupplierClient(userDto);
        if (result == "User Already Exist")
            return ResponseEntity.unprocessableEntity().body(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/addOperatorCourier")
    @RolesAllowed({"SYSTEMADMIN", "SUPPLIER", "ADMIN"})
    public ResponseEntity registerOperatorCourier(@Valid @RequestBody UserDto userDto) {
        String result = userService.registerOperatorCourier(userDto);
        if (result == "User Already Exist")
            return ResponseEntity.unprocessableEntity().body(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/updateProfile")
    public ResponseEntity updateProfile(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateProfile(userDto));
    }

    @PutMapping("/updateEmployee")
    public ResponseEntity updateEmployee(@Valid @RequestBody UserDto userDto, @RequestParam String idEmployee) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateEmployee(userDto, idEmployee));
    }

    @PutMapping("/updatePassword")
    public ResponseEntity updatePassword(@RequestParam String newPassword) {
        userService.updatePassword(newPassword);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/deactivateActivateAccount/{userId}")
    public String deactivateActivateAccount(@PathVariable String userId) {
        System.out.println("deactivateActivateAccount");
        return "Acount activated: " + userService.deactivateActivateAccount(userId);
    }

    ///Nadhir start
    @GetMapping("/getNumeroClient/{idClient}")
    public String getNumeroClient(@PathVariable String idClient) {
        return userService.getNumeroClient(idClient);
    }

    ///Nadhir end

}
