/*
Navicat MySQL Data Transfer

Source Server         : Helpoa
Source Server Version : 50173
Source Host           : 120.24.244.87:3306
Source Database       : testonline

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2015-05-10 10:36:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for academy
-- ----------------------------
DROP TABLE IF EXISTS `academy`;
CREATE TABLE `academy` (
  `academy_id` int(11) NOT NULL AUTO_INCREMENT,
  `school_id` int(11) NOT NULL COMMENT '主键',
  `academy_name` varchar(50) NOT NULL,
  `academy_desc` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`academy_id`),
  KEY `FK_FK_School_Academy` (`school_id`),
  CONSTRAINT `FK_FK_School_Academy` FOREIGN KEY (`school_id`) REFERENCES `school` (`school_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='学院';

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for answer
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer` (
  `answer_id` int(11) NOT NULL AUTO_INCREMENT,
  `stu_hw_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `answer_content` varchar(1000) NOT NULL,
  PRIMARY KEY (`answer_id`),
  KEY `FK_FK_Question_Answer` (`question_id`),
  KEY `stu_hw_id` (`stu_hw_id`),
  CONSTRAINT `answer_ibfk_1` FOREIGN KEY (`stu_hw_id`) REFERENCES `student_homework` (`id`),
  CONSTRAINT `FK_FK_Question_Answer` FOREIGN KEY (`question_id`) REFERENCES `question` (`question_id`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class` (
  `class_id` int(11) NOT NULL AUTO_INCREMENT,
  `major_id` int(11) NOT NULL,
  `class_name` varchar(50) NOT NULL,
  `grade` int(11) NOT NULL,
  PRIMARY KEY (`class_id`),
  KEY `FK_FK_专业_班级` (`major_id`),
  CONSTRAINT `FK_FK_专业_班级` FOREIGN KEY (`major_id`) REFERENCES `major` (`major_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for classes_course
-- ----------------------------
DROP TABLE IF EXISTS `classes_course`;
CREATE TABLE `classes_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `class_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Classes_Course` (`class_id`),
  KEY `FK_Classes_Course2` (`course_id`),
  CONSTRAINT `FK_Classes_Course` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`),
  CONSTRAINT `FK_Classes_Course2` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for classes_homework
-- ----------------------------
DROP TABLE IF EXISTS `classes_homework`;
CREATE TABLE `classes_homework` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `class_id` int(11) NOT NULL,
  `tea_hw_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `class_id` (`class_id`),
  KEY `tea_hw_id` (`tea_hw_id`),
  CONSTRAINT `classes_homework_ibfk_1` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`),
  CONSTRAINT `classes_homework_ibfk_2` FOREIGN KEY (`tea_hw_id`) REFERENCES `homework` (`tea_hw_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for classes_teacher
-- ----------------------------
DROP TABLE IF EXISTS `classes_teacher`;
CREATE TABLE `classes_teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `class_id` int(11) NOT NULL,
  `tea_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `class_id` (`class_id`),
  KEY `tea_id` (`tea_id`),
  CONSTRAINT `classes_teacher_ibfk_1` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`),
  CONSTRAINT `classes_teacher_ibfk_2` FOREIGN KEY (`tea_id`) REFERENCES `teacher` (`tea_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `course_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_name` varchar(50) NOT NULL,
  `course_desc` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for homework
-- ----------------------------
DROP TABLE IF EXISTS `homework`;
CREATE TABLE `homework` (
  `tea_hw_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) DEFAULT NULL,
  `tea_hw_name` varchar(255) NOT NULL,
  `tea_hw_ask_time` datetime NOT NULL,
  `status` int(2) NOT NULL DEFAULT '1',
  PRIMARY KEY (`tea_hw_id`),
  KEY `FK_FK_Class_Course` (`course_id`),
  CONSTRAINT `FK_FK_Class_Course` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for homework_question
-- ----------------------------
DROP TABLE IF EXISTS `homework_question`;
CREATE TABLE `homework_question` (
  `hw_question_id` int(11) NOT NULL AUTO_INCREMENT,
  `tea_hw_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `score` float(4,2) DEFAULT NULL,
  PRIMARY KEY (`hw_question_id`),
  KEY `FK_FK_HomeWork_Question` (`tea_hw_id`),
  KEY `FK_FK_HomeWork_Question2` (`question_id`),
  CONSTRAINT `FK_FK_HomeWork_Question` FOREIGN KEY (`tea_hw_id`) REFERENCES `homework` (`tea_hw_id`),
  CONSTRAINT `FK_FK_HomeWork_Question2` FOREIGN KEY (`question_id`) REFERENCES `question` (`question_id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for major
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major` (
  `major_id` int(11) NOT NULL AUTO_INCREMENT,
  `academy_id` int(11) NOT NULL,
  `major_name` varchar(50) NOT NULL,
  `major_desc` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`major_id`),
  KEY `FK_FK_Academy_Major` (`academy_id`),
  CONSTRAINT `FK_FK_Academy_Major` FOREIGN KEY (`academy_id`) REFERENCES `academy` (`academy_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for options
-- ----------------------------
DROP TABLE IF EXISTS `options`;
CREATE TABLE `options` (
  `option_id` int(11) NOT NULL AUTO_INCREMENT,
  `question_id` int(11) NOT NULL,
  `option_content` varchar(255) NOT NULL,
  PRIMARY KEY (`option_id`),
  KEY `FK_FK_Question_Option` (`question_id`),
  CONSTRAINT `FK_FK_Question_Option` FOREIGN KEY (`question_id`) REFERENCES `question` (`question_id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `question_id` int(11) NOT NULL AUTO_INCREMENT,
  `question_content` varchar(500) NOT NULL,
  `question_type` int(10) NOT NULL,
  `question_level` varchar(4) NOT NULL,
  `question_answer` varchar(1000) DEFAULT NULL,
  `course_id` int(11) NOT NULL,
  PRIMARY KEY (`question_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `question_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for school
-- ----------------------------
DROP TABLE IF EXISTS `school`;
CREATE TABLE `school` (
  `school_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `school_name` varchar(50) NOT NULL,
  `school_desc` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`school_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='学校';

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `stu_id` int(15) NOT NULL AUTO_INCREMENT,
  `class_id` int(11) NOT NULL,
  `stu_name` varchar(20) NOT NULL,
  `stu_password` varchar(50) NOT NULL,
  `stu_sex` int(2) NOT NULL,
  `stu_email` varchar(50) DEFAULT NULL,
  `stu_phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`stu_id`),
  KEY `FK_FK_Class_Student` (`class_id`),
  CONSTRAINT `FK_FK_Class_Student` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4813 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for student_homework
-- ----------------------------
DROP TABLE IF EXISTS `student_homework`;
CREATE TABLE `student_homework` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stu_id` int(11) NOT NULL,
  `tea_hw_id` int(11) NOT NULL,
  `submit_time` datetime NOT NULL,
  `score` float(4,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `stu_id` (`stu_id`),
  KEY `tea_hw_id` (`tea_hw_id`),
  CONSTRAINT `student_homework_ibfk_1` FOREIGN KEY (`stu_id`) REFERENCES `student` (`stu_id`),
  CONSTRAINT `student_homework_ibfk_2` FOREIGN KEY (`tea_hw_id`) REFERENCES `homework` (`tea_hw_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `tea_id` int(11) NOT NULL AUTO_INCREMENT,
  `academy_id` int(11) NOT NULL,
  `tea_name` varchar(20) NOT NULL,
  `tea_sex` int(2) NOT NULL,
  `tea_password` varchar(50) NOT NULL,
  `tea_email` varchar(50) DEFAULT NULL,
  `tea_phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`tea_id`),
  KEY `FK_FK_Academy_Teacher` (`academy_id`),
  CONSTRAINT `FK_FK_Academy_Teacher` FOREIGN KEY (`academy_id`) REFERENCES `academy` (`academy_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1007 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for teacher_course
-- ----------------------------
DROP TABLE IF EXISTS `teacher_course`;
CREATE TABLE `teacher_course` (
  `tea_course_id` int(11) NOT NULL AUTO_INCREMENT,
  `tea_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  PRIMARY KEY (`tea_course_id`),
  KEY `FK_FK_教师_课程` (`tea_id`),
  KEY `FK_FK_教师_课程2` (`course_id`),
  CONSTRAINT `FK_FK_教师_课程` FOREIGN KEY (`tea_id`) REFERENCES `teacher` (`tea_id`),
  CONSTRAINT `FK_FK_教师_课程2` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
