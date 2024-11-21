package com.yun.system.elasticsearch;

import com.yun.system.domain.question.es.QuestionES;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yun
 * @date 2024/11/18 9:28
 * @desciption: 与es数据库交互
 */
@Repository
public interface QuestionRepository extends ElasticsearchRepository<QuestionES, Long> {
}
