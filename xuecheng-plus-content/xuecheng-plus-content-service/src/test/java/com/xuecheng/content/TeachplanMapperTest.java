package com.xuecheng.content;

import com.xuecheng.content.mapper.TeachplanMapper;
import com.xuecheng.content.model.dto.TeachplanDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author woldier
 * @version 1.0
 * @description 测试
 * @date 2023/3/7 17:54
 **/
@SpringBootTest

@Slf4j
public class TeachplanMapperTest {
    @Autowired
    private TeachplanMapper teachplanMapper;

    @Test
    public void test(){
        List<TeachplanDto> teachplanDtos = teachplanMapper.selectTreeNodes(117L);
        log.info(teachplanDtos.toString());
    }
}
