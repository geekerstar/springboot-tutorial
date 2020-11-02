package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import org.springframework.stereotype.Service;

/**
* ${table.comment!} ${table.serviceName} 服务类
*
* @author ${author}
* @since ${date}
*/
<#if kotlin>
    interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
    @Service
    public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

    }
</#if>
