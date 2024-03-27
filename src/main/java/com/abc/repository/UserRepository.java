package com.abc.repository;

import com.abc.bean.Form;
import com.abc.bean.Params;
import com.abc.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface UserRepository extends Mapper<User> {

    List<User> findBySearch(@Param("params") Params params);

    void insertUser(@Param("form") Form form);

    void updateUser(@Param("form") Form form);

    void deleteUser(Integer id);

    Form findByName( @Param("username")String username);

    Form findByNameAndPassword(@Param("username")String username, @Param("password")String password);


}