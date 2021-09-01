package ink.qs.eduservice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ink.qs.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import ink.qs.eduservice.entity.TeacherCondQuery;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author qs
 * @since 2021-02-15
 */
public interface EduTeacherService extends IService<EduTeacher> {

    /**
     * 教师信息条件查询接口
     * @param page 分页对象
     * @param condQuery 条件存储对象
     */
    void condQuery(Page<EduTeacher> page, TeacherCondQuery condQuery);
}
