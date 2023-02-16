package com.xuecheng.content;

import com.xuecheng.content.mapper.CourseCategoryMapper;
import com.xuecheng.content.model.dto.CourseCategoryTreeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author woldier
 * @version 1.0
 * @description 测试
 * @date 2023/2/16 20:42
 **/
@SpringBootTest
@Slf4j
public class CourseCategoryMapperTest {

    @Autowired
    private  CourseCategoryMapper courseCategoryMapper;



    @Test
    public void testCourseCategoryMapper(){
        List<CourseCategoryTreeDto> courseCategoryTreeDtos = courseCategoryMapper.selectTreeNodes("1");
        log.info(courseCategoryTreeDtos.toString());
    }
}
