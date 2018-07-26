
CREATE DATABASE o2o;

use o2o;
-- 店铺区域表
CREATE TABLE tb_area(
  area_id int(2) NOT NULL  AUTO_INCREMENT,
  area_name VARCHAR(255) NOT NULL ,
  priority INT(2) NOT NULL DEFAULT 0,
  create_time DATETIME DEFAULT NULL ,
  edit_time DATETIME DEFAULT NULL,
  PRIMARY KEY (area_id),
  UNIQUE KEY UK_AREA(area_name)
)ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;

-- 用户信息表
CREATE TABLE tb_person_info(
  user_id INT(10) NOT NULL AUTO_INCREMENT,
  name VARCHAR(32) DEFAULT NULL ,
  profile_img VARCHAR(1024) DEFAULT NULL,
  email VARCHAR(1024) DEFAULT NULL ,
  gender VARCHAR(2) DEFAULT NULL ,
  enable_status INT(2) NOT NULL DEFAULT 0 COMMENT '0：禁止，1：允许',
  user_type INT(2) NOT NULL DEFAULT 1 COMMENT '1:顾客，2：店家，3：管理员',
  create_time DATETIME DEFAULT NULL ,
  edit_time DATETIME DEFAULT NULL ,
  PRIMARY KEY (user_id)
)ENGINE = InnoDB,AUTO_INCREMENT = 1,DEFAULT CHARSET = utf8;

-- 微信用户验证表
CREATE TABLE tb_wechat_auth(
  wechat_auth_id int(10) NOT NULL AUTO_INCREMENT,
  user_id INT(10) NOT NULL ,
  open_id VARCHAR(1024) NOT NULL ,
  create_time DATETIME DEFAULT NULL,
  PRIMARY KEY (wechat_auth_id),
  CONSTRAINT fk_wechatauth_profile
    FOREIGN KEY (user_id) REFERENCES tb_person_info(user_id)
)ENGINE = InnoDB,AUTO_INCREMENT = 1,DEFAULT CHARSET = utf8;

-- local帐号验证表
CREATE TABLE tb_local_auth(
  local_auth_id INT(10) NOT NULL AUTO_INCREMENT,
  user_id INT(10) NOT NULL ,
  username VARCHAR(128) NOT NULL ,
  password VARCHAR(128) NOT NULL ,
  create_time DATETIME DEFAULT NULL ,
  edit_time DATETIME DEFAULT NULL ,
  PRIMARY KEY (local_auth_id),
  UNIQUE KEY uk_local_profile(username),
  CONSTRAINT fk_localauth_profile
  FOREIGN KEY (user_id) REFERENCES tb_person_info(user_id)
)ENGINE = InnoDB,AUTO_INCREMENT = 1,DEFAULT CHARSET = utf8;
-- 增加唯一索引
ALTER TABLE tb_wechat_auth add UNIQUE INDEX (open_id);

-- 头条表
CREATE TABLE tb_headline(
  line_id INT(100) NOT NULL AUTO_INCREMENT,
  line_name VARCHAR(1000) DEFAULT NULL ,
  line_link VARCHAR(2000) NOT NULL ,
  line_img VARCHAR(2000) NOT NULL ,
  priority INT(2) DEFAULT NULL ,
  enable_status INT(2) NOT NULL DEFAULT 0,
  create_time DATETIME DEFAULT NULL ,
  edit_time DATETIME DEFAULT NULL ,
  PRIMARY KEY (line_id)
)ENGINE = InnoDB,AUTO_INCREMENT = 1,DEFAULT CHARSET = utf8;

-- 商店分类表
CREATE TABLE tb_shop_category(
  shop_category_id INT(11) NOT NULL AUTO_INCREMENT,
  shop_category_name VARCHAR(100) NOT NULL DEFAULT '',
  shop_category_desc VARCHAR(1000) DEFAULT '',
  shop_category_img VARCHAR(2000) DEFAULT NULL ,
  priority INT(2) NOT NULL DEFAULT 0,
  create_time DATETIME DEFAULT NULL ,
  edit_time DATETIME DEFAULT NULL ,
  parent_id INT(11) DEFAULT NULL ,
  PRIMARY KEY (shop_category_id),
  CONSTRAINT fk_shop_category_self
    FOREIGN KEY(parent_id)
    REFERENCES tb_shop_category(shop_category_id)
)ENGINE = InnoDB,AUTO_INCREMENT = 1,DEFAULT CHARSET = utf8;

-- o2o表
CREATE TABLE tb_shop(
  shop_id INT(10) NOT NULL AUTO_INCREMENT,
  owner_id INT(10) NOT NULL COMMENT '店铺创建人',
  area_id INT(5) DEFAULT NULL ,
  shop_category_id INT(11) DEFAULT NULL ,
  shop_name VARCHAR(256) NOT NULL ,
  shop_desc VARCHAR(1024) DEFAULT NULL ,
  shop_addr VARCHAR(200) DEFAULT NULL ,
  phone VARCHAR(128) DEFAULT NULL ,
  shop_img VARCHAR(1024) DEFAULT NULL ,
  priority INT(3) DEFAULT 0,
  create_time DATETIME DEFAULT NULL ,
  edit_time DATETIME DEFAULT NULL ,
  enable_status INT(2) NOT NULL DEFAULT 0,
  advice VARCHAR(255) DEFAULT NULL ,
  PRIMARY KEY (shop_id),
  CONSTRAINT fk_shop_area
    FOREIGN KEY (area_id) REFERENCES tb_area(area_id),
  CONSTRAINT fk_shop_profile
    FOREIGN KEY (owner_id) REFERENCES tb_person_info(user_id),
  CONSTRAINT fk_shop_shopcate
    FOREIGN KEY (shop_category_id) REFERENCES tb_shop_category(shop_category_id)
)ENGINE = InnoDB,AUTO_INCREMENT = 1,DEFAULT CHARSET = utf8;

-- 商品分类表
CREATE TABLE tb_product_category(
  product_category_id INT(11) NOT NULL AUTO_INCREMENT,
  product_category_name VARCHAR(100) NOT NULL ,
  priority INT(2) DEFAULT 0,
  create_time DATETIME DEFAULT NULL ,
  shop_id INT(20) NOT NULL DEFAULT 0,
  PRIMARY KEY (product_category_id),
  CONSTRAINT fk_procate_shop
    FOREIGN KEY (shop_id) REFERENCES tb_shop(shop_id)
)ENGINE = InnoDB,AUTO_INCREMENT = 1,DEFAULT CHARSET = utf8;

-- 商品图片详情表
CREATE TABLE tb_product_img(
  product_img_id INT(20) NOT NULL AUTO_INCREMENT,
  img_addr VARCHAR(2000) NOT NULL ,
  img_desc VARCHAR(2000) DEFAULT NULL ,
  priority INT(2) DEFAULT 0,
  create_time DATETIME DEFAULT NULL ,
  product_id INT(20) DEFAULT NULL,
  PRIMARY KEY (product_img_id),
  CONSTRAINT fk_proimg_product
    FOREIGN KEY (product_id)
    REFERENCES tb_product(product_id)
)ENGINE = InnoDB,AUTO_INCREMENT = 1,DEFAULT CHARSET = utf8;

-- 商品表
CREATE TABLE tb_product (
  product_id INT(100) NOT NULL AUTO_INCREMENT,
  product_name VARCHAR(100) NOT NULL ,
  product_desc VARCHAR(2000) DEFAULT NULL ,
  img_addr VARCHAR(2000) DEFAULT '',
  normal_price VARCHAR(100) DEFAULT NULL ,
  promotion_price VARCHAR(100) DEFAULT NULL ,
  priority INT(2) NOT NULL DEFAULT 0,
  create_time DATETIME DEFAULT NULL ,
  edit_time DATETIME DEFAULT NULL ,
  enable_status INT(2) NOT NULL DEFAULT 0,
  product_category_id INT(11) DEFAULT NULL ,
  shop_id INT(20) NOT NULL DEFAULT 0,
  PRIMARY KEY (product_id),
  CONSTRAINT fk_product_procate
    FOREIGN KEY (product_category_id)
      REFERENCES tb_product_category(product_category_id),
  CONSTRAINT fk_product_shop
    FOREIGN KEY (shop_id) REFERENCES tb_shop(shop_id)
)ENGINE = InnoDB,AUTO_INCREMENT = 1,DEFAULT CHARSET = utf8;

-- 奖品表
CREATE TABLE tb_award(
  award_id INT(10) NOT NULL AUTO_INCREMENT,
  award_name VARCHAR(256) NOT NULL,
  award_desc VARCHAR(1024) DEFAULT NULL ,
  award_img VARCHAR(1024) DEFAULT NULL ,
  point INT(10) NOT NULL DEFAULT 0,
  priority INT(2) DEFAULT NULL ,
  create_time DATETIME DEFAULT NULL ,
  edit_time DATETIME DEFAULT NULL ,
  enable_status INT(2) NOT NULL DEFAULT 0,
  shop_id INT(10) DEFAULT NULL ,
  PRIMARY KEY (award_id),
  KEY fk_award_shop_idx(shop_id),
  CONSTRAINT fk_award_shop
  FOREIGN KEY (shop_id) REFERENCES tb_shop(shop_id)
)ENGINE = InnoDB,AUTO_INCREMENT = 1,DEFAULT CHARSET = utf8;

-- 用户奖品映射
CREATE TABLE tb_user_award_map(
  user_award_id INT(10) NOT NULL AUTO_INCREMENT,
  user_id INT(10) NOT NULL ,
  award_id INT(10) NOT NULL ,
  shop_id INT(10) NOT NULL ,
  operator_id INT(10) DEFAULT NULL,
  create_time DATETIME DEFAULT NULL ,
  used_status INT(2) NOT NULL DEFAULT 0,
  point INT(10) DEFAULT NULL ,
  PRIMARY KEY (user_award_id),
  KEY fk_user_award_map_profile(user_id),
  KEY fk_user_award_map_award(award_id),
  KEY fk_user_award_map_shop(shop_id),
  CONSTRAINT fk_user_award_map_profile FOREIGN KEY (user_id) REFERENCES tb_person_info(user_id),
  CONSTRAINT fk_user_award_map_award FOREIGN KEY (award_id) REFERENCES tb_award(award_id),
  CONSTRAINT fk_user_award_map_shop FOREIGN KEY (shop_id) REFERENCES tb_shop(shop_id),
  CONSTRAINT fk_user_award_map_operator FOREIGN KEY (operator_id) REFERENCES tb_person_info(user_id)
)ENGINE = InnoDB,AUTO_INCREMENT = 1,DEFAULT CHARSET = utf8;

-- 用户消费的商品映射
CREATE TABLE tb_user_product_map(
  user_product_id INT(10) NOT NULL AUTO_INCREMENT,
  user_id INT(10) DEFAULT NULL,
  product_id INT(100) DEFAULT NULL,
  shop_id INT(10) DEFAULT NULL,
  operator_id INT(10) DEFAULT NULL,
  create_time DATETIME DEFAULT NULL ,
  point INT(10) DEFAULT 0 ,
  PRIMARY KEY (user_product_id),
  KEY fk_user_product_map_profile(user_id),
  KEY fk_user_product_map_product(product_id),
  KEY fk_user_product_map_shop(shop_id),
  KEY fk_user_product_map_operator(operator_id),
  CONSTRAINT fk_user_product_map_profile FOREIGN KEY (user_id) REFERENCES  tb_person_info(user_id),
  CONSTRAINT fk_user_product_map_product FOREIGN KEY (product_id) REFERENCES  tb_product(product_id),
  CONSTRAINT fk_user_product_map_shop FOREIGN KEY (shop_id) REFERENCES  tb_shop(shop_id),
  CONSTRAINT fk_user_product_map_operator FOREIGN KEY (operator_id) REFERENCES  tb_person_info(user_id)
)ENGINE = InnoDB,AUTO_INCREMENT = 1,DEFAULT CHARSET = utf8;

-- 用户店铺积分映射
CREATE TABLE tb_user_shop_map(
  user_shop_id INT(10) NOT NULL AUTO_INCREMENT,
  user_id INT(10) NOT NULL ,
  shop_id INT(10) NOT NULL,
  create_time DATETIME DEFAULT NULL ,
  point INT(10) DEFAULT NULL ,
  PRIMARY KEY (user_shop_id),
  UNIQUE KEY uq_user_shop(user_id,shop_id),
  KEY fk_user_shop_shop(shop_id),
  CONSTRAINT fk_user_shop_shop FOREIGN KEY (shop_id) REFERENCES  tb_shop(shop_id),
  CONSTRAINT fk_user_shop_user FOREIGN KEY (user_id) REFERENCES  tb_person_info(user_id)
)ENGINE = InnoDB,AUTO_INCREMENT = 1,DEFAULT CHARSET = utf8;

-- 销量统计
CREATE TABLE tb_product_sell_daily(
  product_id INT(100) DEFAULT NULL ,
  shop_id INT(10) DEFAULT NULL,
  create_time DATETIME DEFAULT NULL ,
  total INT(10) DEFAULT 0 ,
  KEY fk_product_sell_product(product_id),
  KEY fk_product_sell_shop(shop_id),
  CONSTRAINT fk_product_sell_product FOREIGN KEY (product_id) REFERENCES tb_product(product_id),
  CONSTRAINT fk_product_sell_shop FOREIGN KEY (shop_id) REFERENCES  tb_shop(shop_id)
)ENGINE = InnoDB,AUTO_INCREMENT = 1,DEFAULT CHARSET = utf8;

-- 店铺授权
CREATE TABLE tb_shop_auth_map(
  shop_auth_id INT(10) NOT NULL AUTO_INCREMENT,
  employee_id INT(10) NULL,
  shop_id int(10) NOT NULL ,
  title VARCHAR(256) DEFAULT NULL ,
  title_flag INT(2) DEFAULT NULL ,
  create_time DATETIME DEFAULT NULL ,
  edit_time DATETIME DEFAULT NULL ,
  enable_status INT(2) NOT NULL DEFAULT 0,
  PRIMARY KEY (shop_auth_id),
  KEY fk_shop_auth_map_shop(shop_id),
  KEY uk_shop_auth_map(employee_id,shop_id),
  CONSTRAINT fk_shop_auth_map_employee FOREIGN KEY (employee_id) REFERENCES tb_person_info(user_id),
  CONSTRAINT fk_shop_auth_map_shop FOREIGN KEY (shop_id) REFERENCES tb_shop(shop_id)
)ENGINE = InnoDB,AUTO_INCREMENT = 1,DEFAULT CHARSET = utf8;