package com.baixiaowen.alllearning.service.impl;

import com.baixiaowen.alllearning.domain.dto.UserDTO;
import com.baixiaowen.alllearning.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class UserServiceImpl {

    @Autowired
    private UserService userService;

    @Test
    public void saveTest(){
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("bxw-username1");
        userDTO.setPassword("bxw-password1");
        userDTO.setEmail("bxw-email@email.com");
        userDTO.setAge(1);
        userDTO.setPhone("12345678910");
        userDTO.setVersion(1L);

        int save = userService.save(userDTO);

        log.info("{}", save);
    }
}