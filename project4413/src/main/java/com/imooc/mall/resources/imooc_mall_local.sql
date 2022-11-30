use imooc_mall;
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
SET NAMES utf8mb4;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table imooc_mall_cart
# ------------------------------------------------------------

DROP TABLE IF EXISTS `imooc_mall_cart`;

CREATE TABLE `imooc_mall_cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '购物车id',
  `product_id` int(11) NOT NULL COMMENT '商品id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `quantity` int(11) NOT NULL DEFAULT '1' COMMENT '商品数量',
  `selected` int(11) NOT NULL DEFAULT '1' COMMENT '是否已勾选：0代表未勾选，1代表已勾选',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  COMMENT='购物车';



# Dump of table imooc_mall_category
# ------------------------------------------------------------

DROP TABLE IF EXISTS `imooc_mall_category`;

CREATE TABLE `imooc_mall_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'primary key',
  `name` varchar(32) NOT NULL DEFAULT '' COMMENT 'menu name',
  `type` int(11) NOT NULL COMMENT 'menu level，1 means level 1, 2 means level 2... and so on.',
  `parent_id` int(11) NOT NULL COMMENT 'parent id, AKA the menu which is parent of current menu. if current menu is top menu, then parent id is 0',
  `order_num` int(11) NOT NULL COMMENT 'the order that how show the menu',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'creation time',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update time',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  COMMENT='this table is for items menu';

LOCK TABLES `imooc_mall_category` WRITE;
/*!40000 ALTER TABLE `imooc_mall_category` DISABLE KEYS */;

INSERT INTO `imooc_mall_category` (`id`, `name`, `type`, `parent_id`, `order_num`, `create_time`, `update_time`)
VALUES
(3,'fresh fruit',1,0,1,'2022-06-18 01:17:00','2022-06-28 17:11:26'),
(4,'cheap fruit',2,3,1,'2022-06-18 01:17:00','2022-06-28 16:25:10'),
(5,'seafood',1,0,2,'2022-06-18 01:17:00','2022-06-28 16:25:20'),
(6,'fresh meat',1,0,3,'2022-06-18 01:17:00','2022-06-28 16:25:21'),
(7,'crab',2,5,1,'2022-06-18 01:17:00','2022-06-28 16:25:15'),
(8,'fish',2,5,2,'2022-06-18 01:17:00','2022-06-28 16:25:16'),
(9,'cold drink',1,0,4,'2022-06-20 13:45:28','2022-06-28 16:25:22'),
(10,'Vegetable and egg products',1,0,5,'2022-06-20 13:45:28','2022-06-28 16:25:23'),
(11,'strawberry',2,3,2,'2022-06-18 01:17:00','2022-06-28 15:44:42'),
(12,'kiwi fruit',2,3,3,'2022-06-18 01:17:00','2022-06-28 16:25:12'),
(13,'shrimp',2,5,3,'2022-06-18 01:17:00','2022-06-28 16:25:17'),
(14,'cherry',2,3,4,'2022-06-18 01:17:00','2022-06-28 16:25:12'),
(15,'fresh pineapple',2,27,5,'2022-06-18 01:17:00','2022-02-11 00:42:33'),
(16,'beef and lamb',2,6,1,'2022-06-18 01:17:00','2022-06-28 16:25:18'),
(17,'frozen fruits',2,9,1,'2022-06-18 01:17:00','2022-06-28 16:25:18'),
(18,'mixed vegetable',2,10,1,'2022-06-18 01:17:00','2022-02-11 00:48:27'),
(19,'apple fruits',3,4,1,'2022-06-18 01:17:00','2022-02-11 00:37:02'),
(27,'fruits mix',1,0,7,'2022-06-20 13:45:28','2022-02-10 23:20:36'),
(28,'other fruits',2,3,4,'2022-06-18 01:17:00','2022-06-28 16:25:12');

/*!40000 ALTER TABLE `imooc_mall_category` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table imooc_mall_order
# ------------------------------------------------------------

DROP TABLE IF EXISTS `imooc_mall_order`;

CREATE TABLE `imooc_mall_order` (
  `id` int(64) NOT NULL AUTO_INCREMENT COMMENT 'primary key id',
  `order_no` varchar(128) NOT NULL DEFAULT '' COMMENT 'order it is not primary key',
  `user_id` int(64) NOT NULL COMMENT 'user id',
  `total_price` int(64) NOT NULL COMMENT 'total price of order',
  `receiver_name` varchar(32) NOT NULL COMMENT 'receiver name',
  `receiver_mobile` varchar(32) NOT NULL COMMENT 'receiver mobile phone number',
  `receiver_address` varchar(128) NOT NULL DEFAULT '' COMMENT 'receiver address',
  `order_status` int(10) NOT NULL DEFAULT '10' COMMENT 'order status: 0 means user cancel the order，10 means the order is not paid，20 means is paid，30 means hte order is out of delivery，40 means the order completed',
  `postage` int(10) DEFAULT '0' COMMENT 'postage，default is 0',
  `payment_type` int(4) NOT NULL DEFAULT '1' COMMENT 'payment type, we only support one: 1 means online payment',
  `delivery_time` timestamp NULL DEFAULT NULL COMMENT 'delivery time',
  `pay_time` timestamp NULL DEFAULT NULL COMMENT 'pay time',
  `end_time` timestamp NULL DEFAULT NULL COMMENT 'end time(when the order is completed)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create time',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update time',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  COMMENT='this table is for order';



# Dump of table imooc_mall_order_item
# ------------------------------------------------------------

DROP TABLE IF EXISTS `imooc_mall_order_item`;

CREATE TABLE `imooc_mall_order_item` (
  `id` int(64) NOT NULL AUTO_INCREMENT COMMENT 'primary key id',
  `order_no` varchar(128) NOT NULL DEFAULT '' COMMENT 'order no',
  `product_id` int(11) NOT NULL COMMENT 'product id',
  `product_name` varchar(100) NOT NULL DEFAULT '' COMMENT 'product name',
  `product_img` varchar(128) NOT NULL DEFAULT '' COMMENT 'product img',
  `unit_price` int(11) NOT NULL COMMENT 'unit price for this item',
  `quantity` int(10) NOT NULL DEFAULT '1' COMMENT 'quantity of items',
  `total_price` int(11) NOT NULL DEFAULT '0' COMMENT 'total price of items',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create time',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update time',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  COMMENT='this table is for product list for order';



# Dump of table imooc_mall_product
# ------------------------------------------------------------

DROP TABLE IF EXISTS `imooc_mall_product`;

CREATE TABLE `imooc_mall_product` (
  `id` int(64) NOT NULL AUTO_INCREMENT COMMENT 'primary key id',
  `name` varchar(100) NOT NULL COMMENT 'item name',
  `image` varchar(500) NOT NULL DEFAULT '' COMMENT 'item image',
  `detail` varchar(500)  DEFAULT '' COMMENT 'item details',
  `category_id` int(11) NOT NULL COMMENT 'category id',
  `price` int(11) NOT NULL COMMENT 'price(unit: cents)',
  `stock` int(11) NOT NULL COMMENT 'stock(the amount, not boolean value)',
  `status` int(6) NOT NULL DEFAULT '1' COMMENT 'status or item：0-out of stock，1-can buy',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create time',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update time',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  COMMENT='this table is for items';

LOCK TABLES `imooc_mall_product` WRITE;
/*!40000 ALTER TABLE `imooc_mall_product` DISABLE KEYS */;

INSERT INTO `imooc_mall_product` (`id`, `name`, `image`, `detail`, `category_id`, `price`, `stock`, `status`, `create_time`, `update_time`)
VALUES
(2,'Australia imported large black cherry package sweet black cherry large fruit juicy 500g Extra large fruit','http://127.0.0.1:8083/images/new-chelizi.png','Gross weight of goods：1.0kgItem No.：608323093445Country of origin: Chile ',14,50,10000,1,'2022-06-18 16:08:15','2022-02-11 00:08:25'),
(3,'Tropical Pineapple 500g','http://127.0.0.1:8083/images/new-boluo.png','Trade name: improved new varieties of Hainan fresh tropical fruit',15,1000,60000,1,'2022-06-18 16:10:50','2022-02-11 00:42:42'),
(14,'Zespri New Zealand Sunshine Gold Kiwi 6 per package','http://127.0.0.1:8083/images/new-mihoutao.png','Item No.: 4635056 goods gross weight: 0.71kg goods origin: New Zealand category: gold fruit packaging: simple packing domestic / import: import origin: New Zealand',12,39,77000,1,'2022-06-18 16:11:13','2022-02-10 23:36:48'),
(17,'Reddish Cream Strawberries Approximate weight 500g/20-24 pieces Fresh fruit','http://127.0.0.1:8083/images/new-caomei.png','Gross weight of goods: 0.58kg Origin of goods: Dandong / Nantong / Wuhan category: red strawberry Packaging: simple packing domestic / import: domestic',11,99,84000,1,'2022-06-18 16:11:13','2022-02-10 23:37:48'),
(21,'Chilean original salmon fillet (Atlantic salmon) 240g / bag 4 pieces','http://127.0.0.1:8083/images/new-sanwenyu.png','Gross weight of goods: 260.00g commodity origin: mainland China preservation status: frozen domestic / import: import packaging: simple package category: salmon seawater / freshwater: seawater cooking advice: fried, steamed dishes, grilled origin: Chile',8,499,10000,1,'2022-06-28 15:13:07','2022-02-10 23:38:46'),
(22,'Provencal tomatoes','http://127.0.0.1:8083/images/new-xihongshi.png','Freshly picked fresh fruits and vegetables Healthy Light Food 2.5kg',18,699,30000,1,'2022-06-28 15:16:29','2022-02-11 00:04:29'),
(23,'Durian imported fresh frozen durian','http://127.0.0.1:8083/images/new-liulian.png','Durian imported fresh frozen durian commodity gross weight: 2.27kg 4.8-5.4 kg family aristocrats keep 5 rooms / fruit flesh 30 ~ 40%',28,15,120000,1,'2022-06-28 16:02:13','2022-02-11 00:40:15'),
(24,'Chilean bread crab gift boxed 4.4-4.0 pounds / only raw fresh live cooked frozen large crabs','http://127.0.0.1:8083/images/new-pangxie.png','Gross weight of goods: 3.0kg Origin of goods: Chilean crab Selling method: male crab weight: 2000-4999g Set meal portions: more than 5 people domestic / imported: imported seawater / fresh water: seawater cooking advice: hot pot, stir-fry, barbecue, sashimi, heating ready to eat Packaging: simple packaging Origin: Chile preservation state: frozen male single crab weight: 5.5 two and above Classification: bread crab specialties category: other selling Way: single product',7,222,22200,1,'2022-06-28 16:06:34','2022-02-11 00:05:05'),
(25,'Seedless Grapes in Kulluxiang, Xinjiang Chinese fresh grapes 5 kg','http://127.0.0.1:8083/images/new-putao.png','Gross weight of goods: 2.5kg goods origin: mainland China weight: 2000-3999g package servings: 2 people domestic / imported: domestic whether organic: non-organic single box specifications: 3 pack, 4 pack, 5 pack category: grapes Packaging: simple packaging origin: mainland China selling method: single product',28,222,22200,1,'2022-06-28 16:06:34','2022-02-11 00:44:05'),
(26,'Vietnam imported red heart dragon fruit','http://127.0.0.1:8083/images/new-huolongguo.png','Gross weight of goods: 1.79kg goods origin: Vietnam weight: 1000-1999g category: red heart dragon fruit packaging: simple packing domestic / import: import',28,222,22200,1,'2022-06-28 16:06:34','2022-02-11 00:44:11'),
(27,'Boston lobster fresh chilled seafood aquatic large pack of Polon Australian lobster 400-500g a pack','http://127.0.0.1:8083/images/new-longxia.png','Category: Australian Lobster Specialties Category: Australian Lobster',13,222,22200,1,'2022-06-28 16:06:34','2022-02-11 00:11:30'),
(28,'Beef shank 2kg','http://127.0.0.1:8083/images/new-niurou.png','Fresh yellow beef raw frozen domestic beef high-quality beef hindquarters original cut 90% lean',16,222,22200,1,'2022-06-28 16:06:34','2022-02-11 00:10:40'),
(29,'Red Fuji Apple','http://127.0.0.1:8083/images/new-pingguo.png','Variety: Fuji fruit diameter: 75-80mm specialty category: Yantai apples Packing form: simple packing Origin: Shandong China',28,222,22200,1,'2022-06-28 16:06:34','2022-02-11 00:34:01'),
(36,'Fresh vegetables Fine salad greens Lettuce Romaine lettuce in season','http://127.0.0.1:8083/images/new-shengcai.png','Gross weight of goods: 370.00g',18,222,22200,1,'2022-06-28 16:06:34','2022-02-11 00:38:14'),
(37,'Imported avocado 6 medium fruit single fruit about 130-160g','http://127.0.0.1:8083/images/new-niuyouguo.png','Product Name: Imported avocado 6pcs Item No.: 3628240 Product Gross Weight: 1.2kg Product Origin: Peru, Chile, Mexico',28,222,22200,1,'2022-06-28 16:06:34','2022-02-11 00:47:42'),
(38,'Fresh Mixed Berry Blueberry Red Raspberry','http://127.0.0.1:8083/images/new-shuangmei.png','Country of origin: Canada Package form: simple package ',17,222,22200,1,'2022-06-28 16:06:34','2022-02-11 00:50:54'),
(39,'Ready-to-eat Prawns','http://127.0.0.1:8083/images/new-xia.png','Dried shrimp grilled prawns large prawns dried 500g light dry grilled sea shrimp dried seafood dried',13,40,22200,1,'2022-06-28 16:06:34','2022-02-11 00:51:59'),
(40,'Carrots','http://127.0.0.1:8083/images/new-huluobo.png','Trade name: green fresh carrot number: 4116192  gross weight: 1.07kg origin: Beijing packaging: simple packing classification: radish cooking advice: hot pot, stir-fry, stew',18,222,22200,1,'2022-06-28 16:06:34','2022-02-11 00:53:25'),
(41,'Banana','http://127.0.0.1:8083/images/new-xiangjiao.png','Sweet banana 10 pounds of fresh seasonal fruit large plantain',28,222,22200,1,'2022-06-28 16:06:34','2022-02-11 00:48:03'),
(42,'Sweet corn, cut up sweet','http://127.0.0.1:8083/images/new-yumi.png','Trade name: green fresh sweet corn goods',18,240,22200,1,'2022-06-28 16:06:34','2022-02-11 00:52:19');
/*!40000 ALTER TABLE `imooc_mall_product` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table imooc_mall_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `imooc_mall_user`;

CREATE TABLE `imooc_mall_user` (
  `id` int(64) NOT NULL AUTO_INCREMENT COMMENT 'user id',
  `username` varchar(32) NOT NULL DEFAULT '' COMMENT 'username',
  `password` varchar(50) NOT NULL COMMENT 'user password，encrypted with MD5',
  `personalized_signature` varchar(50) NOT NULL DEFAULT '' COMMENT 'personalized signature',
  `email_address` varchar(100) NOT NULL DEFAULT '' COMMENT 'email address',
  `role` int(4) NOT NULL DEFAULT '1' COMMENT 'role of user，1-means normal user(most likely Consumers)，2-Admin',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create time',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update time',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  COMMENT='this table is for user';

LOCK TABLES `imooc_mall_user` WRITE;
/*!40000 ALTER TABLE `imooc_mall_user` DISABLE KEYS */;

INSERT INTO `imooc_mall_user` (`id`, `username`, `password`, `personalized_signature`, `role`, `create_time`, `update_time`)
VALUES
	(1,'1','1','666',1,'2022-06-16 02:37:33','2022-02-29 14:33:23'),
	(2,'xiaomu','AWRuqaxc6iryhHuA4OnFag==','Updated my signature',2,'2022-06-17 15:11:32','2022-02-10 09:52:12'),
	(9,'xiaomu2','AWRuqaxc6iryhHuA4OnFag==','Have a good day!',2,'2022-02-09 20:39:47','2022-02-11 00:56:02'),
	(10,'mumu','12345678','Sunny weather',1,'2022-02-29 16:53:22','2022-02-29 16:53:22'),
	(11,'mumu3','124567474','',1,'2022-02-29 16:56:07','2022-02-29 16:56:07'),
	(12,'mumu4','SMRMN9k6YmXAjbsJCMdxrQ==','Sunny weather',1,'2022-02-29 17:25:55','2022-02-29 21:59:02'),
	(13,'mumu5','SMRMN9k6YmXAjbsJCMdxrQ==','',2,'2022-02-29 22:09:56','2022-02-29 22:12:11'),
	(14,'imooc','SMRMN9k6YmXAjbsJCMdxrQ==','Moving forward with courage',1,'2022-03-02 22:45:48','2022-03-02 22:45:48'),
	(15,'super2','SMRMN9k6YmXAjbsJCMdxrQ==','',1,'2022-03-07 18:09:47','2022-03-07 18:09:47');

/*!40000 ALTER TABLE `imooc_mall_user` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
