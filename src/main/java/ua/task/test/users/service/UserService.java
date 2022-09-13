package ua.task.test.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ua.task.test.model.UserDTO;
import ua.task.test.model.UserListDTO;
import ua.task.test.users.entity.UserEntity;
import ua.task.test.users.mapper.UserMapper;
import ua.task.test.users.repository.UserRepositoryImpl;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@PropertySource(ignoreResourceNotFound = true, value = "classpath:age.properties")
public class UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRepositoryImpl userRepository;

    @Value("${age}")
    int userAgeCanRegister;

    public UserDTO createUser(UserDTO userDTO) {
        UserEntity userEntity = createUserEntity(userDTO);
        userEntity.setId(UUID.randomUUID());
        LocalDate birthDateOfUserInLocalDate = userDTO
                .getBirthDate()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        if (birthDateOfUserInLocalDate.isAfter(LocalDate.now())) {
            throw new IllegalStateException("Birth date is incorrect");
        }
        if (Period.between(birthDateOfUserInLocalDate, LocalDate.now()).getYears() < userAgeCanRegister) {
            throw new IllegalStateException("User age less 18");
        }
        return userMapper.map(userRepository.addUser(userEntity));
    }

    public void deleteUser(String id) {
        userRepository.deleteUser(id);
    }

    public UserDTO editUser(UserDTO userDTO) {
        UserEntity userEntity = createUserEntity(userDTO);
        userEntity.setId(UUID.fromString(userDTO.getId()));
        return userMapper.map(userRepository.editUser(userEntity));
    }

    public UserDTO replaceUser(UserDTO userDTO) {
        UserEntity userEntity = createUserEntity(userDTO);
        userEntity.setId(UUID.fromString(userDTO.getId()));
        return userMapper.map(userRepository.replaceUser(userEntity));
    }

    public UserListDTO getUserList(Date birthDate1, Date birthDate2) {
        List<UserEntity> userEntityListByBirthdayRange = userRepository.getUserListByBirthdateRange(birthDate1, birthDate2);
        List<UserDTO> userDTOList = userEntityListByBirthdayRange
                .stream()
                .map(userMapper::map)
                .collect(Collectors.toList());
        UserListDTO userListDTO = new UserListDTO();
        userListDTO.setUsers(userDTOList);
        return userListDTO;
    }

    private UserEntity createUserEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setBirthDate(userDTO.getBirthDate());
        userEntity.setAddress(userDTO.getAddress());
        userEntity.setPhoneNumber(userDTO.getPhoneNumber());
        return userEntity;
    }

}
