package com.abc.service.impl;

import com.abc.bean.Rubbish;
import com.abc.repository.RubbishRepository;
import com.abc.service.RubbishService;
import com.alibaba.dashscope.aigc.conversation.Conversation;
import com.alibaba.dashscope.aigc.conversation.ConversationParam;
import com.alibaba.dashscope.aigc.conversation.ConversationResult;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RubbishServiceImpl implements RubbishService {

    @Autowired
    private RubbishRepository repository;
    @Override
    public List<Rubbish> findAll() {
        List list = repository.findAll();
        // 这里应该调用repository的方法来获取数据
        // 假设repository的方法名为findAll()
        return list;
    }

    @Override
    public Rubbish findById(Integer id) {
        return null;
    }

    @Override
    public String message(String message) throws NoApiKeyException, InputRequiredException {
        Conversation conversation = new Conversation();
        System.out.println("message: "+ message);
        String prompt = message;
        ConversationParam param = ConversationParam
                .builder()
                .model(Conversation.Models.QWEN_TURBO)
                .prompt(prompt)
                .build();
        ConversationResult result = conversation.call(param);
        System.out.println(result.getOutput().getText());
        return result.getOutput().getText();
    }
}
