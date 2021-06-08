package com.koreait.spring.user;

import org.apache.ibatis.annotations.Mapper;
// executeUpdate >> 영향을 받은 행 1개 (int 타입으로만 반환)
// insert, delete, update가 해당

@Mapper
public interface UserMapper {
    int insUser(UserEntity param);
//  xml의 db문을 mapping
    UserEntity selUser(UserEntity param);
    int updUser(UserEntity param);
}
