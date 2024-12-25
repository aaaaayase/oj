-- 消息内容表
create table tb_message_text
(
    text_id         bigint unsigned not null comment '消息内容id',
    message_title   varchar(10)  not null comment '消息标题',
    message_content varchar(200) not null comment '消息内容',
    `create_by`     bigint(8) unsigned NOT NULL COMMENT '创建人',
    `create_time`   datetime     NOT NULL COMMENT '创建时间',
    `update_by`     bigint(8) unsigned DEFAULT NULL COMMENT '更新人',
    `update_time`   datetime DEFAULT NULL COMMENT '更新时间',
    primary key (text_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息内容表'

-- 消息表
create table tb_message
(
    message_id    bigint unsigned not null comment '消息id',
    text_id       bigint unsigned not null comment '消息内容id',
    send_id       bigint unsigned not null comment '消息发送人id',
    rec_id        bigint unsigned not null comment '消息接收人id',
    `create_by`   bigint(8) unsigned NOT NULL COMMENT '创建人',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_by`   bigint(8) unsigned DEFAULT NULL COMMENT '更新人',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    primary key (message_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息表'

-- 用户答题提交表
create table tb_user_submit
(
    submit_id     bigint unsigned not null comment '提交记录id',
    user_id       bigint unsigned not null comment '用户id',
    question_id   bigint unsigned not null comment '题目id',
    exam_id       bigint unsigned comment '竞赛id',
    program_type  tinyint      not null comment '代码类型 0 java  1 cpp',
    user_code     text         not null comment '用户代码',
    pass          tinyint      not null comment '0: 未通过  1：通过',
    exe_message   varchar(500) not null comment '执行结果',
    score         int          not null default '0' comment '得分',
    `create_by`   bigint(8) unsigned NOT NULL COMMENT '创建人',
    `create_time` datetime     NOT NULL COMMENT '创建时间',
    `update_by`   bigint(8) unsigned DEFAULT NULL COMMENT '更新人',
    `update_time` datetime              DEFAULT NULL COMMENT '更新时间',
    primary key (submit_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户答题提交表'

-- 客户端用户与竞赛关系表 竞赛报名
create table tb_user_exam
(
    user_exam_id  bigint unsigned not null comment '用户竞赛关系id',
    user_id       bigint unsigned not null comment '用户id',
    exam_id       bigint unsigned not null comment '竞赛id',
    score         int unsigned comment '得分',
    exam_rank     int unsigned comment '排名',
    `create_by`   bigint(8) unsigned NOT NULL COMMENT '创建人',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_by`   bigint(8) unsigned DEFAULT NULL COMMENT '更新人',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    primary key (user_exam_id)
)
-- 客户端用户表
CREATE TABLE `tb_user`
(
    `user_id`     bigint(20) unsigned NOT NULL COMMENT '⽤⼾id',
    `nick_name`   varchar(32) DEFAULT NULL COMMENT '用户昵称',
    `head_image`  varchar(100) COMMENT '用户头像',
    `sex`         tinyint COMMENT '用户状态1：男 2：女',
    `phone`       char(11) not null comment '手机号',
    `code`        char(6) comment '验证码',
    `email`       varchar(20) comment '邮箱',
    `wechat`      varchar(20) comment '微信号',
    `school_name` varchar(20) comment '学校',
    `major_name`  varchar(20) comment '专业',
    `introduce`   varchar(100) comment '个人介绍',
    `status`      tinyint  not null comment '用户状态0：拉黑 1：正常',
    `create_by`   bigint(8) unsigned NOT NULL COMMENT '创建人',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_by`   bigint(8) unsigned DEFAULT NULL COMMENT '更新人',
    `update_time` datetime    DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户端⽤⼾表';
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='竞赛表';
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='竞赛题目关系表';
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='题目表';
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理端⽤⼾表';