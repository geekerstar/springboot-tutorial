import com.geekerstar.mybatisplus.MybatisPlusApplication;
import com.geekerstar.mybatisplus.mapper.UserMapper;
import com.geekerstar.mybatisplus.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author geekerstar
 * date: 2019/12/10 21:35
 * description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisPlusApplication.class)
public class SampleTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

}
