# 疫情可视化系统
## 介绍

使用了SpringCloud Eureka + Hadoop MapReude 共同处理数据

## 项目目标

1. 使用Java爬虫爬取疫情相关数据

2. 使用Hadoop HDFS并接收实时疫情数据

3. 将分析结果数据存入MySQL

4. 搭建SpringCloud+Echarts项目对数据结果进行可视化

## 项目背景

通过疫情大数据可视化系统的建设及实施，使得从局部作战到中心指挥，让战“疫”指挥官对疫情防控心中有“数”，科学决策，下好疫情防控、救治、复工复产“一盘棋”，更高效地帮助防疫指挥部开展统筹、协调、决策工作，尽快打赢疫情防控战役。

## 项目架构

![image-20220821105158012](https://github.com/moyutata/COVID-19/blob/main/_v_images/image-20220821105158012.png)

## 项目截图

### 展示了全国及各省份的疫情数据

![image-20220821105247335](https://github.com/moyutata/COVID-19/blob/main/_v_images/image-20220821105247335.png)

### 展示了各省份疫情境外输入数据

![image-20220821105429053](https://github.com/moyutata/COVID-19/blob/main/_v_images/image-20220821105429053.png)

### 展示了全国每日疫情趋势

![image-20220821105441290](https://github.com/moyutata/COVID-19/blob/main/_v_images/image-20220821105441290.png)

### 展示了各省份城市的疫情数据

![image-20220821105450383](https://github.com/moyutata/COVID-19/blob/main/_v_images/image-20220821105450383.png)

### 展示了搜索城市疫情数据

![image-20220821105505547](https://github.com/moyutata/COVID-19/blob/main/_v_images/image-20220821105505547.png)

## 项目改进

<details>
  <summary>数据库设计</summary>
  - [ ] countryTrend 表无法忽略重复数据插入，可能为键/索引冲突
  - [ ] 主键未使用 '表名_id'
</details>

- [ ] 前端美化
