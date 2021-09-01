package ink.qs.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ink.qs.eduservice.entity.EduTeacher;
import ink.qs.eduservice.entity.TeacherCondQuery;
import ink.qs.eduservice.mapper.EduTeacherMapper;
import ink.qs.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author qs
 * @since 2021-02-15
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public void condQuery(Page<EduTeacher> page, TeacherCondQuery condQuery) {
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        if (condQuery == null) {
            baseMapper.selectPage(page,queryWrapper);
            return;
        }

        String name = condQuery.getName();
        Integer level = condQuery.getLevel();
        String begin = condQuery.getBegin();
        String end = condQuery.getEnd();

        if (ObjectUtils.isNotEmpty(name)) {
            queryWrapper.eq("name",name);
        }
        if (ObjectUtils.isNotEmpty(level)) {
            queryWrapper.eq("level",level);
        }
        if (ObjectUtils.isNotEmpty(begin)) {
            queryWrapper.ge("begin",begin);
        }
        if (ObjectUtils.isNotEmpty(end)) {
            queryWrapper.le("end",end);
        }

        baseMapper.selectPage(page,queryWrapper);
    }
}
