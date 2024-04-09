package com.general.loan.service;

import com.general.loan.dto.UserDto;
import com.general.loan.entity.UserEntity;
import com.general.loan.exception.UserDoesNotExist;
import com.general.loan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    public static final String ACTIVE = "ACTIVE";
    @Autowired
    private UserRepository userRepository;

    public UserDto validateUser(String userId, String userPassword) {
        UserEntity byUserIdAndUserPassword = userRepository.
                findByUserIdAndUserPasswordAndStatus(userId, userPassword, ACTIVE)
                .orElseThrow(() -> new UserDoesNotExist("incorrect userId and userPassword"));
        List<UserEntity> entities = new ArrayList<>();
        entities.add(byUserIdAndUserPassword);
        List<UserDto> userDtoList = new ArrayList<>();
        constructListOfUserDto(userDtoList, entities);
        return userDtoList.stream().findFirst().get();
    }

    public UserDto createUser(UserDto userDto) {
        UserEntity userEntity = constructUserEntity(userDto);
        Optional<UserEntity> byUserIdAndUserPasswordAndStatus = userRepository.
                findByUserIdAndStatus(userEntity.getUserId(), ACTIVE);
        if (byUserIdAndUserPasswordAndStatus.isPresent()) {
            throw new UserDoesNotExist("given userId is already available,create different one");
        }
        UserEntity userSaved = userRepository.save(userEntity);
        List<UserDto> userDtoList = new ArrayList<>();
        List<UserEntity> userEntities = new ArrayList<>();
        userEntities.add(userSaved);
        constructListOfUserDto(userDtoList, userEntities);
        return userDtoList.get(0);

    }

    private UserEntity constructUserEntity(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setMobileNumber(userDto.getMobileNumber());
        userEntity.setUserId(userDto.getUserId());
        userEntity.setUserPassword(userDto.getUserPassword());
        userEntity.setStatus(UserEntity.Status.ACTIVE);
        userEntity.setCreationDate(new Date().toString());
        userEntity.setUpdatedDate(new Date().toString());
        userEntity.setUserType(UserEntity.UserType.AGENT);
        return userEntity;
    }

    public List<UserDto> getAllUserList() {
        List<UserDto> userDtoList = new ArrayList<>();

        List<UserEntity> userEntities = userRepository.findAllByUserTypeAndStatus(UserEntity.UserType.AGENT, ACTIVE);
        constructListOfUserDto(userDtoList, userEntities);
        return userDtoList;
    }

    private void constructListOfUserDto(List<UserDto> userDtoList, List<UserEntity> userEntities) {
        if (!userEntities.isEmpty()) {
            userEntities.forEach((user) -> {
                UserDto userDto = new UserDto();
                userDto.setFirstName(user.getFirstName());
                userDto.setLastName(user.getLastName());
                userDto.setEmail(user.getEmail());
                userDto.setMobileNumber(user.getMobileNumber());
                userDto.setUserId(user.getUserId());
                userDto.setUserPassword(user.getUserPassword());
                userDto.setStatus(user.getStatus().toString());
                userDto.setCreationDate(user.getCreationDate());
                userDto.setUpdatedDate(user.getUpdatedDate());
                userDto.setUserType(user.getUserType());
                userDtoList.add(userDto);

            });
        }
    }
}
