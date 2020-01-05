package com.geekerstar.mp.advance.method;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * @author dyz
 * @program boot-use
 * @create 2019-09-11 16:03
 * <p>
 * Sql注入器
 */
public class DeleteAllMethod extends AbstractMethod {
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        // 执行的sql
        String sql = "delete from " + tableInfo.getTableName();
        // mapper接口方法名
        String method = "deleteAll";

        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);

        return addDeleteMappedStatement(mapperClass, method, sqlSource);
    }
}
