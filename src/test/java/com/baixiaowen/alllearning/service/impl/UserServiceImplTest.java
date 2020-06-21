package com.baixiaowen.alllearning.service.impl;

import com.baixiaowen.alllearning.domain.dto.UserDTO;
import com.baixiaowen.alllearning.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@Slf4j
public class UserServiceImplTest {

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

    /**
     * 乐观锁使用的规则
     * 1、如果个更新数据中不带version字段：不适用乐观锁。并且version不会累加
     * 2、如果更新字段中带有version，但与数据库中不一致，更新失败
     * 3、如果带有更新数据中带有version，并且与数据库中一致，更新成功，并且version会累加
     */
    @Test
    public void updateTest(){
        Long id = 1274698793900138498L;

        UserDTO userDTO = new UserDTO();
        userDTO.setPassword("password10000");
        userDTO.setAge(10000);
        userDTO.setVersion(1L);

        int update = userService.update(id, userDTO);

        log.info("{}", update);
    }

    @Test
    public void deleteTest(){

        int delete = userService.delete(1274698793900138498L);

        log.info("{}", delete);
    }

}