package io.github.zhiweicoding.dao.es;

import com.xpc.easyes.core.conditions.interfaces.BaseEsMapper;
import io.github.zhiweicoding.models.UserBean;
import org.springframework.stereotype.Component;

/**
 * @author zhiwei
 * @description 针对表【t_content(内容表)】的es操作Mapper
 * @createDate 2022-03-20 15:41:26
 * @Entity com.smartcode.books.models.ContentMapper
 */
@Component
public interface UserMapper extends BaseEsMapper<UserBean> {

}




