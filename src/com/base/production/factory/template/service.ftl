package ${packageName}.${pak};

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ${packageName}.dao.${domainName?cap_first}DAO;
import ${packageName}.model.${domainName?cap_first};
import com.base.utils.ResponseList;

/**
 * ${description}
 * @time	${date}
 */
 @Service("${domainName}Service")
public class ${domainName?cap_first}Service {

	@Resource(name = "${domainName}Dao")
    private ${domainName?cap_first}DAO ${domainName}DAO;
    
    public ResponseList<${domainName?cap_first}> get${domainName?cap_first}List(${domainName?cap_first} ${domainName}) {
        return ${domainName}DAO.get${domainName?cap_first}List(${domainName});
    }
    
    public List<${domainName?cap_first}> get${domainName?cap_first}BaseList(${domainName?cap_first} ${domainName}) {
        return ${domainName}DAO.get${domainName?cap_first}BaseList(${domainName});
    }
    
    public int get${domainName?cap_first}ListCount(${domainName?cap_first} ${domainName}) {
        return ${domainName}DAO.get${domainName?cap_first}ListCount(${domainName});
    }

    public ${domainName?cap_first} get${domainName?cap_first}(${domainName?cap_first} ${domainName}) { 
        return ${domainName}DAO.get${domainName?cap_first}(${domainName});
    }

    public ${pkType} insert${domainName?cap_first}(${domainName?cap_first} ${domainName}) throws Exception {
        return ${domainName}DAO.insert${domainName?cap_first}(${domainName});
    }

    public int update${domainName?cap_first}(${domainName?cap_first} ${domainName}) throws Exception {
        return ${domainName}DAO.update${domainName?cap_first}(${domainName});
    }
    
    public int remove${domainName?cap_first}(${domainName?cap_first} ${domainName}) throws Exception {
        return ${domainName}DAO.remove${domainName?cap_first}(${domainName});
    }
    
}
