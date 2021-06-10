/*
 Navicat Premium Data Transfer

 Source Server         : 本地数据库
 Source Server Type    : MySQL
 Source Server Version : 50523
 Source Host           : localhost:3306
 Source Schema         : db_phonemall

 Target Server Type    : MySQL
 Target Server Version : 50523
 File Encoding         : 65001

 Date: 23/04/2021 21:23:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `id` int(11) NOT NULL,
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `district` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `province` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `receiver` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES (207, '北京市市辖区', 'aaaa号', '东城区', '18380227666', '北京市', '测试', 206);
INSERT INTO `address` VALUES (208, '成都市', '请将中路18号', '青羊区', '18888888888', '四川省', '周哥', 206);
INSERT INTO `address` VALUES (222, '北京市市辖区', '123', '东城区', '11111111111', '北京市', '123', 2);
INSERT INTO `address` VALUES (230, '太原市', '山西大学', '小店区', '11111111111', '山西省', 'jack', 229);
INSERT INTO `address` VALUES (237, '北京市市辖区', '山西大学\r\n', '东城区', '12312312323', '北京市', '小陈', 236);
INSERT INTO `address` VALUES (247, '长春市', '吉林建筑大学', '南关区', '1231231234', '吉林省', 'G同学', 246);
INSERT INTO `address` VALUES (259, '太原市', '山西大学', '小店区', '11111111111', '山西省', '小陈', 22);

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user`  (
  `id` int(11) NOT NULL,
  `is_sale_man` int(11) NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_user
-- ----------------------------
INSERT INTO `admin_user` VALUES (1, 1, '123456', 'admin');
INSERT INTO `admin_user` VALUES (12, 1, '123456', 'admin01');
INSERT INTO `admin_user` VALUES (13, 1, '123', 'admin02');

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence`  (
  `next_val` bigint(20) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES (267);
INSERT INTO `hibernate_sequence` VALUES (267);
INSERT INTO `hibernate_sequence` VALUES (267);
INSERT INTO `hibernate_sequence` VALUES (267);
INSERT INTO `hibernate_sequence` VALUES (267);
INSERT INTO `hibernate_sequence` VALUES (267);
INSERT INTO `hibernate_sequence` VALUES (267);

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `proid` int(11) NULL DEFAULT NULL,
  `proname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createtime` datetime NULL DEFAULT NULL,
  `content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 259 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (221, 192, '三星 S20 5G版 12GB 128GB 遐想蓝 ', '123', '2021-04-12 20:12:25', '666');
INSERT INTO `message` VALUES (256, 194, '华为 P30 Pro 超感光四摄', '游客', '2021-04-18 21:51:59', '喜欢');
INSERT INTO `message` VALUES (257, 195, 'vivo S6 双模5G超清夜景视频防抖', '游客', '2021-04-18 21:52:16', '哈哈哈哈  好看！');
INSERT INTO `message` VALUES (258, 195, 'vivo S6 双模5G超清夜景视频防抖', '游客', '2021-04-18 21:52:55', '爱了爱了  买！');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` int(11) NOT NULL,
  `address_id` int(11) NULL DEFAULT NULL,
  `order_time` datetime NULL DEFAULT NULL,
  `state` int(11) NULL DEFAULT NULL,
  `total` double NULL DEFAULT NULL,
  `total_integral` int(11) NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (223, 222, '2021-04-12 12:13:18', 3, 5999, 2, 2);
INSERT INTO `order` VALUES (225, 222, '2021-04-13 13:57:01', 2, 1888, 1, 2);
INSERT INTO `order` VALUES (227, 222, '2021-04-13 14:36:49', 4, 5999, 2, 2);
INSERT INTO `order` VALUES (231, 230, '2021-04-14 14:48:23', 2, 8466, 5, 229);
INSERT INTO `order` VALUES (238, 237, '2021-04-17 05:31:08', 1, 3899, 2, 236);
INSERT INTO `order` VALUES (240, 237, '2021-04-17 05:54:26', 1, 4888, 2, 236);
INSERT INTO `order` VALUES (242, 222, '2021-04-17 05:58:20', 2, 4888, 2, 2);
INSERT INTO `order` VALUES (244, 222, '2021-04-17 06:00:31', 2, 4888, 2, 2);
INSERT INTO `order` VALUES (248, 247, '2021-04-17 06:10:01', 2, 7089, 3, 246);
INSERT INTO `order` VALUES (251, 247, '2021-04-17 06:10:17', 2, 4888, 2, 246);
INSERT INTO `order` VALUES (260, 259, '2021-04-18 13:58:16', 1, 1090, 1, 22);
INSERT INTO `order` VALUES (262, 259, '2021-04-18 13:58:24', 2, 4888, 2, 22);
INSERT INTO `order` VALUES (264, 259, '2021-04-18 13:59:56', 3, 4068, 3, 22);

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item`  (
  `id` int(11) NOT NULL,
  `count` int(11) NULL DEFAULT NULL,
  `order_id` int(11) NULL DEFAULT NULL,
  `product_id` int(11) NULL DEFAULT NULL,
  `sub_integral` int(11) NULL DEFAULT NULL,
  `sub_total` double NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of order_item
-- ----------------------------
INSERT INTO `order_item` VALUES (199, 1, 198, 194, 1, 4888);
INSERT INTO `order_item` VALUES (210, 1, 209, 193, 1, 1090);
INSERT INTO `order_item` VALUES (211, 2, 209, 189, 6, 4398);
INSERT INTO `order_item` VALUES (213, 1, 212, 195, 1, 2488);
INSERT INTO `order_item` VALUES (215, 1, 214, 194, 1, 4888);
INSERT INTO `order_item` VALUES (219, 1, 218, 194, 1, 4888);
INSERT INTO `order_item` VALUES (220, 2, 218, 196, 2, 3776);
INSERT INTO `order_item` VALUES (224, 1, 223, 192, 2, 5999);
INSERT INTO `order_item` VALUES (226, 1, 225, 196, 1, 1888);
INSERT INTO `order_item` VALUES (228, 1, 227, 217, 2, 5999);
INSERT INTO `order_item` VALUES (232, 1, 231, 193, 1, 1090);
INSERT INTO `order_item` VALUES (233, 1, 231, 194, 2, 4888);
INSERT INTO `order_item` VALUES (234, 1, 231, 195, 2, 2488);
INSERT INTO `order_item` VALUES (239, 1, 238, 197, 2, 3899);
INSERT INTO `order_item` VALUES (241, 1, 240, 194, 2, 4888);
INSERT INTO `order_item` VALUES (243, 1, 242, 194, 2, 4888);
INSERT INTO `order_item` VALUES (245, 1, 244, 194, 2, 4888);
INSERT INTO `order_item` VALUES (249, 1, 248, 193, 1, 1090);
INSERT INTO `order_item` VALUES (250, 1, 248, 217, 2, 5999);
INSERT INTO `order_item` VALUES (252, 1, 251, 194, 2, 4888);
INSERT INTO `order_item` VALUES (254, 1, 253, 217, 2, 5999);
INSERT INTO `order_item` VALUES (261, 1, 260, 193, 1, 1090);
INSERT INTO `order_item` VALUES (263, 1, 262, 194, 2, 4888);
INSERT INTO `order_item` VALUES (265, 2, 264, 193, 2, 2180);
INSERT INTO `order_item` VALUES (266, 1, 264, 196, 1, 1888);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` int(11) NOT NULL,
  `cs_id` int(11) NULL DEFAULT NULL,
  `date` datetime NULL DEFAULT NULL,
  `description` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `image1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `image2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `image3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `image4` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `integral` int(11) NULL DEFAULT NULL,
  `is_hot` int(11) NULL DEFAULT NULL,
  `market_price` double NULL DEFAULT NULL,
  `shop_price` double NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (189, 173, '2020-05-24 13:22:12', '基本参数\r\n曝光日期2018年12月\r\n屏幕\r\n主屏尺寸6.4\r\n主屏分辨率2310*1080\r\n网络\r\n网络类型4G全网通\r\n硬件\r\n操作系统基于Android\r\n核心数8核\r\nCPU型号麒麟980\r\nCPU频率\r\nGPU型号\r\nRAM容量8GB\r\nROM容量128GB\r\n电池类型不可卸载\r\n电池容量毫安\r\n摄像头\r\n后置摄像头4800万像素万像素\r\n前置摄像头2500万像素万像素\r\n拍照功能\r\n', '/mall/admin/product/img/E36A769C0A4247B6838B9B70F64DC6.jpg', '/mall/admin/product/img/8EB50164C366201087392A8EEED0D9.jpg', '/mall/admin/product/img/1D4BDEF8200E32789615AC3572B520.jpg', '/mall/admin/product/img/C1E5F29C365677C4F2477F517DC797.jpg', 3, 1, 2399, 2199, '华为 |荣耀V20 8GB 128GB 魅丽红 4G全网通');
INSERT INTO `product` VALUES (192, 24, '2020-05-30 07:46:34', '屏幕\r\n主屏尺寸6.2\r\n主屏分辨率QHD+3220*1440\r\n网络\r\n网络类型\r\n硬件\r\n操作系统基于Android\r\n核心数8核\r\nCPU型号高通骁龙865\r\nCPU频率\r\nGPU型号\r\nRAM容量12GB\r\nROM容量128GB\r\n电池类型\r\n电池容量4000毫安', '/mall/admin/product/img/B1610F78B22E48B0F8B1F4DD26F673.jpg', '/mall/admin/product/img/E04EC94E206536A4FD3B694297FCCD.jpg', '/mall/admin/product/img/F89AA597CBC98213071D9AC0F53332.jpg', '/mall/admin/product/img/43AB67618F65BF6C3441DFBA601B05.jpg', 2, 0, 6199, 5999, '三星 S20 5G版 12GB 128GB 遐想蓝 ');
INSERT INTO `product` VALUES (193, 9, '2021-04-14 12:59:21', '屏幕\r\n主屏尺寸6.3\r\n主屏分辨率HD+ 1600x720\r\n网络\r\n网络类型4G全网通\r\n硬件\r\n操作系统基于Android\r\n核心数8核\r\nCPU型号Mediatek MT6765（联发科MT6765）\r\nCPU频率\r\nGPU型号\r\nRAM容量4GB\r\nROM容量128GB\r\n电池类型不可卸载\r\n电池容量5000毫安', '/mall/admin/product/img/484A08488D9520D277A24DC7DA538B.jpg', '/mall/admin/product/img/89CA98F3A0AC546534BE0E352AF187.jpg', '/mall/admin/product/img/657BB7B47E226322DD8CE37C1A088B.jpg', '/mall/admin/product/img/9A212FEE5598940FD8AAEE521519D0.jpg', 1, 1, 1190, 1090, '荣耀 畅玩9A 5000mAh长续航');
INSERT INTO `product` VALUES (194, 8, '2021-04-14 13:00:43', '华为 P30 Pro 超感光四摄 未来影像 移动联通电信4G全面屏全网通手机 8GB 256GB 亮黑色 4G全网通  \r\n麒麟980智慧芯片！超感光徕卡四摄！10倍混合变焦！', '/mall/admin/product/img/91CADC5BB1C436272F5D7AAEA68036.jpg', '/mall/admin/product/img/670E5D628A750EE01C7C141854AE50.jpg', '/mall/admin/product/img/DEACAF6834B77416AF8F5BB8767042.jpg', '/mall/admin/product/img/51DD25AD10C4F40700D5F1DD3D6695.jpg', 2, 1, 4980, 4888, '华为 P30 Pro 超感光四摄');
INSERT INTO `product` VALUES (195, 17, '2021-04-14 13:01:21', 'vivo S6 双模5G超清夜景视频防抖四摄大电池拍照游戏全面屏手机5G全网通 8GB 128GB 多瑙河 5G全网通  \r\n双模5G，3200万超清夜景自拍，4800万超级四摄，4500mAh，超级视频防抖', '/mall/admin/product/img/BCBB4F93282E8B2F3C9490EEBFF59F.jpg', '/mall/admin/product/img/750C75B693E62BCECE82BA205D86B3.jpg', '/mall/admin/product/img/0370E47C0DC8AC9CEE34B911F8D0E8.jpg', '/mall/admin/product/img/68CC15B2D09D0BE63016EA029FAB81.jpg', 2, 1, 2698, 2488, 'vivo S6 双模5G超清夜景视频防抖');
INSERT INTO `product` VALUES (196, 19, '2020-05-30 08:07:41', '屏幕\r\n主屏尺寸 6.53\r\n主屏分辨率754689\r\n网络\r\n网络类型全网通\r\n硬件\r\n操作系统基于Android\r\n核心数核\r\nCPU型号高通(Qualcomm)骁龙710\r\nCPU频率\r\nGPU型号\r\nRAM容量8GB\r\nROM容量128GB\r\n电池类型不可卸载\r\n电池容量5000毫安', '/mall/admin/product/img/C6DE530E6F6E28A4EE98E8F3530251.jpg', '/mall/admin/product/img/A7DD5E98B270B34296547CDB0342F8.jpg', '/mall/admin/product/img/5E2669CFEFCC3CA3CB80C5AD55823E.jpg', '/mall/admin/product/img/806C916F6BBCE2877A259602DB7486.jpg', 1, 1, 1998, 1888, 'vivo Z5x 大电池 三摄拍照手机 8GB 128GB');
INSERT INTO `product` VALUES (197, 15, '2020-05-30 08:17:02', 'vivo X27 Pro全面屏拍照游戏手机 升降摄像头全网通4G手机 8GB 256GB 黑珍珠 4G全网通  \r\n4800万广角夜景三摄 前置3200万升降式摄像头', '/mall/admin/product/img/D3859C796A3F90F19D0D66FA46F786.jpg', '/mall/admin/product/img/9CD280D6C00488923FB3180E6CA878.jpg', '/mall/admin/product/img/5FAA2825A2BFDBFA9B5126CF86302E.jpg', '/mall/admin/product/img/A98CD355C169ACF27E50961652FBE8.jpg', 2, 1, 3999, 3899, 'vivo X27 Pro全面屏拍照游戏手机');
INSERT INTO `product` VALUES (217, 8, '2020-06-11 02:08:40', '值得拥有，，，，，，，，，，，，，，，，，，', '/mall/admin/product/img/862D01BEE78CD3741960227B268603.jpg', '/mall/admin/product/img/CDE3290AFB7B1AAE42DD04D62156F9.jpg', '/mall/admin/product/img/31968F777CF7460587F93D921EE35A.jpg', '/mall/admin/product/img/824DA191593E8F5428F80F685DB701.jpg', 2, 1, 6199, 5999, '华为P40');

-- ----------------------------
-- Table structure for product_category
-- ----------------------------
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category`  (
  `id` int(11) NOT NULL,
  `cname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent_id` int(11) NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of product_category
-- ----------------------------
INSERT INTO `product_category` VALUES (1, '华为', 0, 1);
INSERT INTO `product_category` VALUES (2, 'OPPO', 0, 1);
INSERT INTO `product_category` VALUES (3, 'VIVO', 0, 1);
INSERT INTO `product_category` VALUES (4, '魅族', 0, 1);
INSERT INTO `product_category` VALUES (5, '小米', 0, 1);
INSERT INTO `product_category` VALUES (6, '苹果', 0, 1);
INSERT INTO `product_category` VALUES (7, '三星', 0, 1);
INSERT INTO `product_category` VALUES (8, 'HUAWEI P系列', 1, 2);
INSERT INTO `product_category` VALUES (9, '荣耀畅玩系列', 1, 2);
INSERT INTO `product_category` VALUES (10, 'HUAWEI Mate系列', 1, 2);
INSERT INTO `product_category` VALUES (11, '华为畅享系列', 1, 2);
INSERT INTO `product_category` VALUES (12, 'OPPO R系列', 2, 2);
INSERT INTO `product_category` VALUES (13, 'OPPO A系列', 2, 2);
INSERT INTO `product_category` VALUES (14, 'OPPO Find系列', 2, 2);
INSERT INTO `product_category` VALUES (15, 'VIVO X系列', 3, 2);
INSERT INTO `product_category` VALUES (16, 'VIVO NEX系列', 3, 2);
INSERT INTO `product_category` VALUES (17, 'VIVO S系列', 3, 2);
INSERT INTO `product_category` VALUES (18, 'VIVO Y系列', 3, 2);
INSERT INTO `product_category` VALUES (19, 'VIVO Z系列', 3, 2);
INSERT INTO `product_category` VALUES (20, '小米note系列', 5, 2);
INSERT INTO `product_category` VALUES (21, '小米play系列', 5, 2);
INSERT INTO `product_category` VALUES (22, '小米mix系列', 5, 2);
INSERT INTO `product_category` VALUES (23, 'iPhone', 6, 2);
INSERT INTO `product_category` VALUES (24, '三星', 7, 2);
INSERT INTO `product_category` VALUES (235, 'OPPO K系列', 2, 2);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL,
  `integration` int(11) NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (2, 61, '123', '151135976312', '123');
INSERT INTO `user` VALUES (14, 0, '11', '18388888888', '11');
INSERT INTO `user` VALUES (22, 46, '123456', '18389999999', 'test');
INSERT INTO `user` VALUES (77, 35, '17562619258', '17562619258', 'zhangs');
INSERT INTO `user` VALUES (161, 59, '123456', '11111111111', 'test1');
INSERT INTO `user` VALUES (203, 0, '123456', '18390909090', 'wangwu');
INSERT INTO `user` VALUES (206, 9, 'aaaaaa', '18380808080', 'aaaaaa');
INSERT INTO `user` VALUES (229, 5, '123', '12312312345', 'jack');
INSERT INTO `user` VALUES (236, 0, '123', '12312312323', '答辩');
INSERT INTO `user` VALUES (246, 7, '123', '12312312323', '建大G同学');

SET FOREIGN_KEY_CHECKS = 1;
