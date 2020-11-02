package ${package.Mapper};

import ${package.Entity}.${entity};
import ${superMapperClassPackage};
import org.apache.ibatis.annotations.Mapper;

/**
* ${table.comment!} ${table.mapperName} Mapper 接口
*
* @author ${author}
* @since ${date}
*/
<#if kotlin>
    interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
    @Mapper
    public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {

    }
</#if>
