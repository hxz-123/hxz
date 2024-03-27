package com.abc.service;

import com.abc.bean.Rubbish;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;

import java.util.List;

public interface RubbishService {
    List<Rubbish> findAll();
    Rubbish findById(Integer id);

    String message(String message) throws NoApiKeyException, InputRequiredException;
}
