package com.abc.service;

import com.abc.bean.Form;
import com.abc.bean.Params;
import com.abc.bean.User;
import com.github.pagehelper.PageInfo;

public interface UserService {
    //查询所有

    PageInfo findBySearch(Params params);

    void add(Form form);

    void update(Form form);

    void delete(Integer id);

    Form login(Form form);

    Form register(Form form);


    User findById(Integer integer);
}

