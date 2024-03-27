package com.abc.repository;

import com.abc.bean.Examinations;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ExaminationsRepository extends Mapper {
    List<Examinations> findAll();
}
