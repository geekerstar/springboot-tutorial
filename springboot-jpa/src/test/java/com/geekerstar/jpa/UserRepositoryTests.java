package com.geekerstar.jpa;

import com.geekerstar.model.User;
import com.geekerstar.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.text.DateFormat;
import java.util.Date;

/**
 * @author geekerstar
 * date: 2019-08-20 15:46
 * description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {
    @Resource
    private UserRepository userRepository;

    @Test
    public void testSave() {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formatDate = dateFormat.format(date);

        userRepository.save(new User("aa", "aa123456", "aa@qq.com", "aa", formatDate));
        userRepository.save(new User("bb", "bb123456", "bb@qq.com", "bb", formatDate));
        userRepository.save(new User("cc", "cc123456", "cc@qq.com", "cc", formatDate));

        Assert.assertEquals(3, userRepository.findAll().size());
        Assert.assertEquals("bb", userRepository.findByUserNameOrEmail("bb", "bb@qq.com").getNickName());
    }

    @Test
    public void testBaseQuery() {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormat.format(date);
        User user = new User("ff", "ff123456", "ff@126.com", "ff", formattedDate);
        userRepository.findAll();
        userRepository.findById(3L);
        userRepository.save(user);
        user.setId(2L);
        userRepository.delete(user);
        userRepository.count();
        userRepository.existsById(3L);
    }

    @Test
    public void testCustomSql() {
        userRepository.modifyById("neo", 3L);
        userRepository.deleteById(3L);
        userRepository.findByEmail("ff@126.com");
    }


    @Test
    public void testPageQuery() {
        int page = 1, size = 2;
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        userRepository.findAll(pageable);
        userRepository.findByNickName("aa", pageable);
    }
}
