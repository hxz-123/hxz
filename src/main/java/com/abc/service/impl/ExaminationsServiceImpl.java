package com.abc.service.impl;

import com.abc.bean.Examinations;
import com.abc.repository.ExaminationsRepository;
import com.abc.service.ExaminationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExaminationsServiceImpl implements ExaminationsService {
    @Autowired
    private ExaminationsRepository examinationsRepository;
    @Override
    public List<Examinations> findAll() {
        List list = examinationsRepository.findAll();
        return list;
    }
}
