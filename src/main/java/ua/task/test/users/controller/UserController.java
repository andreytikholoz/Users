package ua.task.test.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ua.task.test.api.UserApi;
import ua.task.test.model.UserDTO;
import ua.task.test.model.UserListDTO;
import ua.task.test.users.service.UserService;

import java.util.Date;

@RestController
public class UserController implements UserApi {

    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<UserDTO> createUser(UserDTO userDTO) {
        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    @Override
    public ResponseEntity<Void> deleteUser(String id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<UserDTO> editUser(UserDTO userDTO) {
        return ResponseEntity.ok(userService.editUser(userDTO));
    }

    @Override
    public ResponseEntity<UserDTO> replaceUser(UserDTO userDTO) {
        return ResponseEntity.ok(userService.replaceUser(userDTO));
    }

    @Override
    public ResponseEntity<UserListDTO> getUserList(Date birthDate1, Date birthDate2) {
        return ResponseEntity.ok(userService.getUserList(birthDate1, birthDate2));
    }

}
