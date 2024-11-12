-- 管理端sql
-- 竞赛表
CREATE TABLE `tb_exam`
(
    `exam_id`     BIGINT UNSIGNED NOT NULL COMMENT '竞赛id',
    `title`       VARCHAR(50) NOT NULL COMMENT '竞赛标题',
    `start_time`  DATETIME    NOT NULL COMMENT '竞赛开始时间',
    `end_time`    DATETIME    NOT NULL COMMENT '竞赛结束时间',
    `status`      TINYINT     NOT NULL DEFAULT 0 COMMENT '是否发布 0：未发布 1：已发布',
    `create_by`   BIGINT UNSIGNED NOT NULL COMMENT '创建人',
    `create_time` DATETIME    NOT NULL COMMENT '创建时间',
    `update_by`   BIGINT UNSIGNED COMMENT '更新人',
    `update_time` DATETIME COMMENT '更新时间',
    PRIMARY KEY (`exam_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='竞赛表'
-- 竞赛与题目一对多关系
CREATE TABLE `tb_exam_question`
(
    `exam_question_id` BIGINT UNSIGNED NOT NULL COMMENT '竞赛题目关系id',
    `question_id`      BIGINT UNSIGNED NOT NULL COMMENT '题目id',
    `exam_id`          BIGINT UNSIGNED NOT NULL COMMENT '竞赛id',
    `question_order`   INT      NOT NULL COMMENT '题目顺序',
    `create_by`        BIGINT UNSIGNED NOT NULL COMMENT '创建人',
    `create_time`      DATETIME NOT NULL COMMENT '创建时间',
    `update_by`        BIGINT UNSIGNED COMMENT '更新人',
    `update_time`      DATETIME COMMENT '更新时间',
    PRIMARY KEY (`exam_question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='竞赛题目关系表'
-- 题目表
CREATE TABLE `tb_question`
(
    `question_id`   BIGINT UNSIGNED NOT NULL COMMENT '题目id',
    `title`         VARCHAR(50)   NOT NULL COMMENT '题目标题',
    `difficulty`    TINYINT       NOT NULL COMMENT '题目难度1：简单 2：中等 3：困难',
    `time_limit`    INT           NOT NULL COMMENT '时间限制',
    `space_limit`   INT           NOT NULL COMMENT '空间限制',
    `content`       VARCHAR(1000) NOT NULL COMMENT '题目内容',
    `question_case` VARCHAR(1000) COMMENT '题目用例',
    `default_code`  VARCHAR(500)  NOT NULL COMMENT '默认代码块',
    `main_fuc`      VARCHAR(500)  NOT NULL COMMENT 'main函数',
    `create_by`     BIGINT UNSIGNED NOT NULL COMMENT '创建人',
    `create_time`   DATETIME      NOT NULL COMMENT '创建时间',
    `update_by`     BIGINT UNSIGNED COMMENT '更新人',
    `update_time`   DATETIME COMMENT '更新时间',
    PRIMARY KEY (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='题目表'
-- 管理端用户表
DROP TABLE IF EXISTS `tb_sys_user`;
CREATE TABLE `tb_sys_user`
(
    `user_id`      bigint(20) unsigned NOT NULL COMMENT '⽤⼾id',
    `user_account` varchar(32)  DEFAULT NULL COMMENT '⽤⼾账号',
    `password`     varchar(100) DEFAULT NULL COMMENT '⽤⼾密码',
    `nick_name`    varchar(32)  DEFAULT NULL COMMENT '昵称',
    `create_by`    bigint(8) NOT NULL COMMENT '创建⽤⼾',
    `create_time`  datetime NOT NULL COMMENT '创建时间',
    `update_by`    bigint(8) DEFAULT NULL COMMENT '更新⽤⼾',
    `update_time`  datetime     DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `user_account` (`user_account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理端⽤⼾表'