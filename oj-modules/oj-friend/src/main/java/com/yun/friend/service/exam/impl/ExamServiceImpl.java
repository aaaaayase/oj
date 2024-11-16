package com.yun.friend.service.exam.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yun.common.core.domain.TableDataInfo;
import com.yun.friend.domain.exam.dto.ExamQueryDTO;
import com.yun.friend.domain.exam.vo.ExamVO;
import com.yun.friend.manager.ExamCacheManager;
import com.yun.friend.mapper.exam.IExamMapper;
import com.yun.friend.service.exam.IExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yun
 * @date 2024/11/15 15:38
 * @desciption: 竞赛相关业务逻辑
 */
@Service
public class ExamServiceImpl implements IExamService {
    @Autowired
    private IExamMapper examMapper;

    @Autowired
    private ExamCacheManager examCacheManager;

    @Override
    public List<ExamVO> list(ExamQueryDTO examQueryDTO) {
        // 这一步按照我的理解是用于初始化你要分页的页码和题目数
        PageHelper.startPage(examQueryDTO.getPageNum(), examQueryDTO.getPageSize());
        // 当你查询出满足条件的结果时 pagehelper就会自动帮你给分
        return examMapper.selectExamList(examQueryDTO);
    }

    @Override
    public TableDataInfo redisList(ExamQueryDTO examQueryDTO) {
        // 从redis中去获取   竞赛列表的数据
        Long listSize = examCacheManager.getListSize(examQueryDTO.getType());
        List<ExamVO> examVOList;
        if (listSize == null || listSize <= 0) {
            examVOList = list(examQueryDTO);
            examCacheManager.refreshCache(examQueryDTO.getType());
            listSize = new PageInfo<>(examVOList).getTotal();
        } else {
            examVOList = examCacheManager.getExamVOList(examQueryDTO);
            listSize = examCacheManager.getListSize(examQueryDTO.getType());
        }
        if (CollectionUtil.isEmpty(examVOList)) {
            return TableDataInfo.empty();
        }

        return TableDataInfo.success(examVOList, listSize);
    }
}
