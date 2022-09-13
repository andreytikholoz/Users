package ua.task.test.users.repository;

import ua.task.test.users.entity.UserEntity;

import java.util.Date;
import java.util.List;

public interface UserRepository {
    UserEntity addUser(UserEntity userEntity);

    void deleteUser(String id);

    UserEntity editUser(UserEntity userEntity);

    UserEntity replaceUser(UserEntity userEntity);

    List<UserEntity> getUserListByBirthdateRange(Date birthDate1, Date birthDate2);
}
