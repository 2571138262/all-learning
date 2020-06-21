package com.baixiaowen.alllearning.mapper;

import com.baixiaowen.alllearning.domain.entity.UserDO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Slf4j
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void find() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("username", "username1");

        List<UserDO> userDOList = userMapper.selectByMap(params);

        log.info("{}", userDOList);
    }

}