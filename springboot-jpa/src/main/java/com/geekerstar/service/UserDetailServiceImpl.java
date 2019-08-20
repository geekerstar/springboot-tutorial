package com.geekerstar.service;

import com.geekerstar.model.UserDetail;
import com.geekerstar.param.UserDetailParam;
import com.geekerstar.repository.UserDetailRepository;
import com.mysql.cj.util.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author geekerstar
 * date: 2019-08-20 15:35
 * description:
 */
public class UserDetailServiceImpl implements UserDetailService {

    @Resource
    private UserDetailRepository userDetailRepository;

    @Override
    public Page<UserDetail> findByCondition(UserDetailParam detailParam, Pageable pageable) {
        return userDetailRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            // equal示例
            if (!StringUtils.isNullOrEmpty(detailParam.getIntroduction())) {
                predicates.add(cb.equal(root.get("introduction"), detailParam.getIntroduction()));
            }

            // like示例
            if (!StringUtils.isNullOrEmpty(detailParam.getRealName())) {
                predicates.add(cb.like(root.get("realName"), "%" + detailParam.getRealName() + "%"));
            }

            // between示例
            if (detailParam.getMinAge() != null && detailParam.getMaxAge() != null) {
                Predicate agePredicate = cb.between(root.get("age"), detailParam.getMinAge(), detailParam.getMaxAge());
                predicates.add(agePredicate);
            }

            // greaterThan 大于等于示例
            if (detailParam.getMinAge() != null) {
                predicates.add(cb.greaterThan(root.get("age"), detailParam.getMinAge()));
            }
            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        }, pageable);
    }
}
