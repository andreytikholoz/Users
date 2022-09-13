package ua.task.test.users.repository;

import org.springframework.stereotype.Component;
import ua.task.test.users.entity.UserEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserRepositoryImpl implements UserRepository {
    private List<UserEntity> userEntityList;

    public UserRepositoryImpl() {
        userEntityList = new ArrayList<>();
    }

    @Override
    public UserEntity addUser(UserEntity userEntity) {
        userEntityList.add(userEntity);
        return userEntity;
    }

    @Override
    public void deleteUser(String id) {
        userEntityList.removeIf(userEntity -> userEntity.getId().toString().equals(id));
    }

    @Override
    public UserEntity editUser(UserEntity userEntity) {
        UserEntity userEntityToReplace = userEntityList
                .stream()
                .filter(userEntity1 -> userEntity.getId().equals(userEntity1.getId()))
                .findFirst()
                .orElse(null);
        if (userEntityToReplace == null) {
            throw new IllegalStateException("User that you want to edit not found");
        }
        if (userEntity.getEmail() != null) {
            userEntityToReplace.setEmail(userEntity.getEmail());
        }
        if (userEntity.getFirstName() != null) {
            userEntityToReplace.setFirstName(userEntity.getFirstName());
        }
        if (userEntity.getLastName() != null) {
            userEntityToReplace.setLastName(userEntity.getLastName());
        }
        if (userEntity.getBirthDate() != null) {
            userEntityToReplace.setBirthDate(userEntity.getBirthDate());
        }
        if (userEntity.getAddress() != null) {
            userEntityToReplace.setAddress(userEntity.getAddress());
        }
        if (userEntity.getPhoneNumber() != null) {
            userEntityToReplace.setPhoneNumber(userEntity.getPhoneNumber());
        }
        return userEntityList.set(userEntityList.indexOf(userEntityToReplace), userEntityToReplace);
    }

    @Override
    public UserEntity replaceUser(UserEntity userEntity) {
        UserEntity userEntityToReplace = userEntityList
                .stream()
                .filter(userEntity1 -> userEntity.getId().equals(userEntity1.getId()))
                .findFirst()
                .orElse(null);
        if (userEntityToReplace == null) {
            throw new IllegalStateException("User that you want to replace not found");
        }
        userEntityList.set(userEntityList.indexOf(userEntityToReplace), userEntity);
        return userEntity;
    }

    @Override
    public List<UserEntity> getUserListByBirthdateRange(Date birthDate1, Date birthDate2) {
        return userEntityList
                .stream()
                .filter(userEntity -> userEntity.getBirthDate().getTime() > birthDate1.getTime()
                        && userEntity.getBirthDate().getTime() < birthDate2.getTime())
                .collect(Collectors.toList());
    }
}
