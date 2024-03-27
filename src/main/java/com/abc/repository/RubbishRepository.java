package com.abc.repository;

import com.abc.bean.Rubbish;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RubbishRepository extends Mapper {

    List<Rubbish> findAll();
}
