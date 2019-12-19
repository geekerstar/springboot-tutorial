package com.geekerstar.mybatis.plus.crud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geekerstar.mybatis.plus.crud.entity.People;
import com.geekerstar.mybatis.plus.crud.mapper.PeopleMapper;
import com.geekerstar.mybatis.plus.crud.service.IPeopleService;
import org.springframework.stereotype.Service;


/**
 * @author geekerstar
 * date: 2019/12/19 20:55
 * description:
 */
@Service
public class PeopleServiceImpl extends ServiceImpl<PeopleMapper, People> implements IPeopleService {
}
