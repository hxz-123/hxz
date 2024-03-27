package com.abc.service.impl;
import com.abc.bean.Form;
import com.abc.bean.Params;
import com.abc.bean.User;
import com.abc.exception.CustomException;
import com.abc.repository.UserRepository;
import com.abc.service.UserService;
import com.abc.utils.JwtInterceptor;
import com.abc.utils.JwtTokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    private static final Logger log = LoggerFactory.getLogger(JwtInterceptor.class);
    @Override
    public PageInfo<User> findBySearch(Params params) {
        log.info("拦截器已经放行，正式调用接口内部，查询管理员信息");
        //开启分页
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        List<User> list=userRepository.findBySearch(params);
        PageInfo<User> pageinfo = new PageInfo(list);
        return pageinfo;
    }

    @Override
    public void add(Form form) {
        if (form.getUsername() == null || "".equals(form.getUsername())){
            throw new CustomException("用户名不能为空");
        }
        User user = userRepository.findByName(form.getUsername());
        if (user != null){
            throw new CustomException("该用户名已存在，请勿重复添加");
        }
        userRepository.insertUser(form);
    }

    @Override
    public void update(Form form) {
        userRepository.updateUser(form);
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteUser(id);
    }

    @Override
    public Form login(Form form) {
        if (form.getUsername() == null || "".equals(form.getUsername())){
            throw new CustomException("用户名不能为空");
        }
        if (form.getPassword() == null || "".equals(form.getPassword())){
            throw new CustomException("密码不能为空");
        }
        Form form1 = userRepository.findByNameAndPassword(form.getUsername(),form.getPassword());
        if (form1 == null){
            throw new CustomException("用户名或密码输入错误");
        }
        //生成该登录用户对应的token，然后跟着user一起返回前台
        String token = JwtTokenUtils.genToken(form1.getId().toString(), form1.getPassword());
        form1.setToken(token);

        return form1;
    }
    @Override
    public Form register(Form form) {
        if (form.getUsername() == null || "".equals(form.getUsername())){
            throw new CustomException("用户名不能为空");
        }
        if (form.getPassword() == null || "".equals(form.getPassword())){
            throw new CustomException("密码不能为空");
        }
        if (form.getRePassword() == null || "".equals(form.getRePassword())){
            throw new CustomException("请再次输入密码");
        }
        if (form.getPassword().equals(form.getRePassword()) == false){
            throw new CustomException("请确保两次输入的密码一致");
        }
        Form form1 = userRepository.findByName(form.getUsername());
        if (form1 != null) {
            throw new CustomException("用户名已存在");
        }
        userRepository.insertUser(form);
        return form1;
    }

    @Override
    public User findById(Integer integer) {
        return userRepository.selectByPrimaryKey(integer);
    }
}