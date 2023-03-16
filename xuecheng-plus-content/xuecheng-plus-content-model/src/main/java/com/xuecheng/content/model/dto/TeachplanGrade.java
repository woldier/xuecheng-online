package com.xuecheng.content.model.dto;

/**
 * @author woldier
 * @version 1.0
 * @description  数据库表Teachplan等级表的枚举类
 * @date 2023/3/16 15:29
 **/
public enum TeachplanGrade {
    GRADE1(1),GRADE2(2);

    private Integer grade;

    TeachplanGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getGrade() {
        return grade;
    }
}
