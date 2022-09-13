package ua.task.test.users.mapper;

import org.springframework.stereotype.Component;
import ua.task.test.model.UserDTO;
import ua.task.test.users.entity.UserEntity;

@Component
public class UserMapper {
    public UserDTO map(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId().toString());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setFirstName(userEntity.getFirstName());
        userDTO.setLastName(userEntity.getLastName());
        userDTO.setBirthDate(userEntity.getBirthDate());
        userDTO.setAddress(userEntity.getAddress());
        userDTO.setPhoneNumber(userEntity.getPhoneNumber());
        return userDTO;
    }
}
