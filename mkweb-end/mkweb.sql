/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.29 : Database - mkweb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mkweb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `mkweb`;

/*Table structure for table `t_article` */

DROP TABLE IF EXISTS `t_article`;

CREATE TABLE `t_article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `file_desc` varchar(120) DEFAULT NULL COMMENT '简介',
  `file_name` varchar(50) DEFAULT NULL COMMENT '文档名称',
  `user_id` bigint(20) DEFAULT NULL,
  `browse_count` bigint(20) DEFAULT '0' COMMENT '浏览量',
  `like_count` bigint(20) DEFAULT '0' COMMENT '点赞量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

/*Data for the table `t_article` */

insert  into `t_article`(`id`,`content`,`file_desc`,`file_name`,`user_id`,`browse_count`,`like_count`) values (26,'<p style=\"text-align:left;\"><span style=\"color: rgb(50,62,50);background-color: rgb(217,203,163);font-size: 14px;font-family: Microsoft YaHei;\">　　时光的单车飞快驶去，岁月的倒影也将消失，白天与黑夜不停的交替，轮回的四季斑驳了谁的岁月，蹉跎了谁的年华。一个人静静地与岁月交错，于平淡之中细细体会生活的深意，去注视，去聆听，去感受那些带着希望的别离以及那些经受沧桑的相逢，不论时光如何飞转，那些落花一样的往事，依然鲜活地存在于我的脑海之中。当岁月和美丽的回忆已成为风中的叹息，我们伤感的眼里也许依然残存旧时的泪痕，模糊了视线，不敢轻易触碰。</span></p>\n<p style=\"text-align:left;\"><span style=\"color: rgb(50,62,50);background-color: rgb(217,203,163);font-size: 14px;font-family: Microsoft YaHei;\">　　生活的列车慢慢的前进，有些人下去，也有人上去，不慌不忙的过着行云流水的日子，有的人知道自己的前方在哪里停靠，生活充实而安逸，有些人庸庸碌碌的过着不起波澜的日子，每天无头鸟似的瞎忙，朦胧的眼神向世界宣告着昏暗思想，一个个皮囊悬浮在空气中，没有生机的灵魂过着糜烂的时间。没有归属，无处生根。有时我们在迷茫青春的时候，日子也慢慢地溜走，不留一点痕迹。</span></p>\n<p style=\"text-align:left;\"><span style=\"color: rgb(50,62,50);background-color: rgb(217,203,163);font-size: 14px;font-family: Microsoft YaHei;\">　　时光不可阻挡，岁月交错中总要有些思量。人生只有在不短的思考中才会有所进步，有所追求，有了目标的人生才不会孤独和无助，只有让自己的心静下来时一些前方的东西才会明朗的展现在我们的面前。让我们不再迷惑于为所谓的挣扎中，谁的年华没有色彩，谁的青春没有耀眼的光芒，只是在岁月的长河里我们的选择不同，所得到的结局就不同，每个人都需要努力才会得到一切自己所要追求的东西和梦想。</span></p>\n<p style=\"text-align:left;\"><span style=\"color: rgb(50,62,50);background-color: rgb(217,203,163);font-size: 14px;font-family: Microsoft YaHei;\">　　生命无常，人生苦短，记忆的时光中我们匆匆走过，走过喧嚣，走过孤寂，时光无情地带走了我们的青春年少，还好我们都在坚持着内心的宁静，岁月的年轮缓缓的从我们身边碾过，往事一幕幕铺陈，让我的生活回忆不至于那么的枯燥，一些美好的记忆还依然鲜活地根植在我的脑海之中。消逝不去，本不该怀旧的年纪，可是我们学不会遗忘，日日夜夜的想念，带着些许的小寂寞，心有不甘常常在无人的街角大声的长啸，发泄着内心的声音，有时候我们会选择相信宿命，认为人与人之间的相遇，就像是上天早已做了安排，人谁也逃不过岁月时光刻下的印迹。</span></p>\n<p style=\"text-align:left;\"><span style=\"color: rgb(50,62,50);background-color: rgb(217,203,163);font-size: 14px;font-family: Microsoft YaHei;\">　　时光荏苒，蹉跎了谁的年华，匆匆行走的岁月长河中，有些人只顾着追寻他人的脚步，忘记了自己的方向，忘记了自己的目标和理想，有些人几顾思量不敢走出自己的道路，因而迷失了方向。迷失了自己。有些人默默坚守，把青春的岁月包裹在温热的怀里，载着它踏上梦想的征途，不留一丝遗憾。不留一点别人靠近的距离，就像是陈孝正为自己规划的一厘米的差距，人生没有从头来过的权利。亦没有后悔的权利，做过的事情，不管有些怎样的结局都会成为过往，我们纵使一味的活在过去的时光里也不会改变一点点发生的故事，向着远方，努力的看看前方的路才是对我们自己的肯定，只有心存希望，才会有拼搏的勇气，才有希望去走更远的路，因为值得，所以一路前行，</span></p>\n<p style=\"text-align:left;\"><span style=\"color: rgb(50,62,50);background-color: rgb(217,203,163);font-size: 14px;font-family: Microsoft YaHei;\">　　那一路上的心酸往事，慢慢的沉淀在内心平和的深处！</span>&nbsp;</p>\n<p>--休息</p>','一路上的心酸往事，慢慢的沉淀在内心平和的深处','时光荏苒，蹉跎了谁的年华',1,13,2),(30,'<p>测试为什么新增不了</p>','测试为什么新增不了','测试为什么新增不了',1,2,4);

/*Table structure for table `t_article_attachments` */

DROP TABLE IF EXISTS `t_article_attachments`;

CREATE TABLE `t_article_attachments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `article_id` bigint(20) DEFAULT NULL,
  `attachment_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_article_attachments` */

/*Table structure for table `t_article_file_catory` */

DROP TABLE IF EXISTS `t_article_file_catory`;

CREATE TABLE `t_article_file_catory` (
  `article_id` bigint(20) NOT NULL,
  `file_catory_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_article_file_catory` */

insert  into `t_article_file_catory`(`article_id`,`file_catory_id`) values (30,1);

/*Table structure for table `t_article_it_catory` */

DROP TABLE IF EXISTS `t_article_it_catory`;

CREATE TABLE `t_article_it_catory` (
  `article_id` bigint(20) NOT NULL,
  `it_catory_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_article_it_catory` */

insert  into `t_article_it_catory`(`article_id`,`it_catory_id`) values (30,1);

/*Table structure for table `t_attachments` */

DROP TABLE IF EXISTS `t_attachments`;

CREATE TABLE `t_attachments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `uid` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `url` text,
  `type` varchar(20) DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_attachments` */

insert  into `t_attachments`(`id`,`uid`,`name`,`status`,`url`,`type`,`size`) values (1,'4a293a0baaff47ba917bf6c0ccd497b5.png','4a293a0baaff47ba917bf6c0ccd497b5.png',NULL,'/static_files/artcle/202004/4a293a0baaff47ba917bf6c0ccd497b5.png','image/png',34897);

/*Table structure for table `t_file_catory` */

DROP TABLE IF EXISTS `t_file_catory`;

CREATE TABLE `t_file_catory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '归类名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `t_file_catory` */

insert  into `t_file_catory`(`id`,`name`) values (1,'技术'),(2,'动态'),(3,'前沿');

/*Table structure for table `t_it_catory` */

DROP TABLE IF EXISTS `t_it_catory`;

CREATE TABLE `t_it_catory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '归类名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `t_it_catory` */

insert  into `t_it_catory`(`id`,`name`) values (1,'java'),(2,'react'),(3,'antd'),(4,'k8s');

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(128) DEFAULT NULL COMMENT '密码',
  `auth` varchar(50) DEFAULT NULL COMMENT '权限以逗号分隔',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`name`,`password`,`auth`) values (1,'游客','89d43e181fb7a6259e096e9b8793361d',NULL),(2,'徐杰','89d43e181fb7a6259e096e9b8793361d','admin,user');

/*Table structure for table `t_user_article` */

DROP TABLE IF EXISTS `t_user_article`;

CREATE TABLE `t_user_article` (
  `user_id` bigint(20) NOT NULL,
  `article_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_user_article` */

insert  into `t_user_article`(`user_id`,`article_id`) values (1,26),(1,30);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
