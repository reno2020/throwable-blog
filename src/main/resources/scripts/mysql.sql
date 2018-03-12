
CREATE TABLE IF NOT EXISTS `t_category` (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
  category_name VARCHAR(30) NOT NULL COMMENT '类型名称',
  pid BIGINT UNSIGNED DEFAULT '0' COMMENT '父类型ID',
  sort TINYINT NOT NULL DEFAULT 1 COMMENT '排序数'
) COMMENT '文章类型表';

CREATE TABLE IF NOT EXISTS `t_category_count` (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
  category_id BIGINT UNSIGNED COMMENT '类型id',
  category_name VARCHAR(30) NOT NULL COMMENT '类型名称',
  total_count INT UNSIGNED DEFAULT '0' COMMENT '数量',
  INDEX idx_category_id(category_id)
) COMMENT '文章类型数量统计表';

CREATE TABLE IF NOT EXISTS `t_article` (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
  title VARCHAR(50) NOT NULL COMMENT '标题',
  category_id BIGINT UNSIGNED COMMENT '类型id',
  category_name VARCHAR(30) COMMENT '类型名称',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  views INT COMMENT '浏览数量',
  replies INT COMMENT '回复数量',
  INDEX idx_title(title),
  INDEX idx_category_id(category_id),
  INDEX idx_create_time(create_time),
  INDEX idx_update_time(update_time)
)COMMENT '文章表';

CREATE TABLE IF NOT EXISTS `t_article_content`(
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
  article_id BIGINT UNSIGNED COMMENT '文章id',
  content LONGTEXT COMMENT '文章内容',
  UNIQUE unique_article_id(article_id)
)COMMENT '文章内容表';

CREATE TABLE IF NOT EXISTS `t_link`(
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
  title VARCHAR(30) COMMENT '名称',
  url VARCHAR(50) COMMENT 'url',
  sort TINYINT COMMENT '排序参数',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  is_delete TINYINT NOT NULL DEFAULT 0 COMMENT '是否删除'
)COMMENT '首页链接';
