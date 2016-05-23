# sensitive-word-filter


敏感词处理器器，支持返回敏感词，高亮敏感词，替换敏感词等操作

1，使用方法，参考测试类
com.hlin.sensitive.test.KWSeekerManageTest


# jar包下载地址
https://github.com/hailin0/sensitive-word-filter/tree/master/target/sensitive-word-filter-0.0.1-SNAPSHOT.jar




# 演示效果

原文：这是一部黄色电影，也是一部AV电影
敏感词：黄色,AV
识别结果=============
返回敏感词及下标：[SensitiveWordResult [word=黄色, positions=[PositionNode [startPosition=4, endPosition=6]]], SensitiveWordResult [word=AV, positions=[PositionNode [startPosition=12, endPosition=14]]]]
html高亮：这是一部<font color='red'>黄色</font>电影，也是一部<font color='red'>AV</font>电影
字符替换：这是一部***电影，也是一部***电影

**************************************

原文：在中国这是一部黄色电影，在日本这不是一部黄色电影，在美国这不是一部黄色电影
敏感词：中国，美国，黄色
识别结果=============
返回敏感词及下标：[SensitiveWordResult [word=中国, positions=[PositionNode [startPosition=1, endPosition=3]]], SensitiveWordResult [word=黄色, positions=[PositionNode [startPosition=6, endPosition=8], PositionNode [startPosition=18, endPosition=20], PositionNode [startPosition=29, endPosition=31]]], SensitiveWordResult [word=美国, positions=[PositionNode [startPosition=23, endPosition=25]]]]
html高亮：在<font color='red'>中国</font>这是一部<font color='red'>黄色</font>电影，在日本这不是一部<font color='red'>黄色</font>电影，在<font color='red'>美国</font>这不是一部<font color='red'>黄色</font>电影
字符替换：在***这是一部***电影，在日本这不是一部***电影，在***这不是一部***电影
