package com.geekerstar.service;

import com.geekerstar.model.UserDetail;
import com.geekerstar.param.UserDetailParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author geekerstar
 * date: 2019-08-20 15:34
 * description:
 */
public interface UserDetailService {
    Page<UserDetail> findByCondition(UserDetailParam detailParam, Pageable pageable);
}
