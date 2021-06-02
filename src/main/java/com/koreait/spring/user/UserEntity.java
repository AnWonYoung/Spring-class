package com.koreait.spring.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// lombok라이브러리 사용으로 getter, setter을 생성
@Getter
@Setter
@ToString // 값을 찍을 수 있도록
public class UserEntity {
    private int iuser;
    private String uid;
    private String upw;
    private String unm;
    private int gender;
    private String regdt;
    private String profileImg;
}
