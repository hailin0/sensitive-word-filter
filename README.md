# sensitive-word-filter

敏感词处理器，支持返回敏感词，高亮敏感词，替换敏感词等操作,算法为trie树实现，查找速度快。演示效果如下：
 ![Alt 演示效果](/doc/20160523233326.png "演示效果")

# trie树概述
又称单词查找树，Trie树，是一种树形结构，是一种哈希树的变种。典型应用是用于统计，排序和保存大量的字符串（但不仅限于字符串），所以经常被搜索引擎系统用于文本词频统计。它的优点是：利用字符串的公共前缀来减少查询时间，最大限度地减少无谓的字符串比较，查询效率比哈希树高。关于trie树详细信息请自行baidu


# jar包下载地址 打开如下链接 点击View Raw
https://github.com/hailin0/sensitive-word-filter/blob/master/doc/sensitive-word-filter-0.0.1-SNAPSHOT.jar


# 代码示例
	// 初始化敏感词
	Set<KeyWord> kws1 = new HashSet<KeyWord>();
	kws1.add(new KeyWord("黄色"));
	kws1.add(new KeyWord("AV"));
	
	Set<KeyWord> kws2 = new HashSet<KeyWord>();
	kws2.add(new KeyWord("中国"));
	kws2.add(new KeyWord("美国"));
	kws2.add(new KeyWord("黄色"));
	
	// 根据敏感词,初始化敏感词搜索器
	KWSeeker kwSeeker1 = new KWSeeker(kws1);
	KWSeeker kwSeeker2 = new KWSeeker(kws2);
	
	// 搜索器组,构建敏感词管理器,可同时管理多个搜索器，map的key为自定义搜索器标识
	Map<String, KWSeeker> seekers = new HashMap<String, KWSeeker>();
	String wordType1 = "sensitive-word-1";
	seekers.put(wordType1, kwSeeker1);
	String wordType2 = "sensitive-word-2";
	seekers.put(wordType2, kwSeeker2);
	
	KWSeekerManage kwSeekerManage = new KWSeekerManage(seekers);
	
	// 开始测试
	String text1 = "这是一部黄色电影，也是一部AV电影";
	System.out.println("原文：" + text1);
	System.out.println("敏感词：" + kws1);
	System.out.println("识别结果:");
	
	List<SensitiveWordResult> words1 = kwSeekerManage.getKWSeeker(wordType1).findWords(text1);
	System.out.println("返回敏感词及下标：" + words1);
	String s1 = kwSeekerManage.getKWSeeker(wordType1).htmlHighlightWords(text1);
	System.out.println("html高亮：" + s1);
	String r1 = kwSeekerManage.getKWSeeker(wordType1).replaceWords(text1);
	System.out.println("字符替换：" + r1);
	
	System.out.println("\n=================================================\n");
	
	// 开始测试
	String text2 = "在中国这是一部黄色电影，在日本这不是一部黄色电影，在美国这不是一部黄色电影";
	System.out.println("原文：" + text2);
	System.out.println("敏感词：" + kws2);
	System.out.println("识别结果:");
	
	List<SensitiveWordResult> words2 = kwSeekerManage.getKWSeeker(wordType2).findWords(text2);
	System.out.println("返回敏感词及下标：" + words2);
	String s2 = kwSeekerManage.getKWSeeker(wordType2).htmlHighlightWords(text2);
	System.out.println("html高亮：" + s2);
	String r2 = kwSeekerManage.getKWSeeker(wordType2).replaceWords(text2);
	System.out.println("字符替换：" + r2);


# 使用方法
使用者可以自己通过以上代码构建来调用，也可以使用已经封装好的SimpleKWSeekerProcessor.java
SimpleKWSeekerProcessor采用从配置文件初始化敏感词，并且是单例的，方便任何地方调用。
只需要在classpaht下加入配置文件sensitive-word.properties
配置文件的格式为：
敏感词配置key=value，key和value可以随意更改
key为敏感词的类型，value为敏感词，多个敏感词以'英文逗号'分割
因为.properties文件默认以Unicode编码保存中文。
所以如下配置内容想要以中文方式打开，可以通过浏览器打开在线转码的网站查看中文原文。
例：http://tool.chinaz.com/tools/unicode.aspx ，将如下内容粘贴到转码框，点击 Unicode转中文即可


# 测试代码

com.hlin.sensitive.test.KWSeekerManageTest.java



