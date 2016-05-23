package com.hlin.sensitive.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hlin.sensitive.KWSeeker;
import com.hlin.sensitive.KWSeekerManage;
import com.hlin.sensitive.KeyWord;
import com.hlin.sensitive.SensitiveWordResult;
import com.hlin.sensitive.conf.Config;

/**
 * Unit test for simple App.
 */
public class KWSeekerManageTest {

    private static Set<KeyWord> loadKeyWords(String wordType) {
        String sensitive_word = Config.newInstance().getString(wordType);
        String[] words = sensitive_word.split(",");
        Set<KeyWord> kws = new HashSet<KeyWord>();
        for (String word : words) {
            kws.add(new KeyWord(word));
        }
        return kws;
    }

    public static void main(String[] args) {

        // 准备数据
        Map<String, KWSeeker> seekers = new HashMap<String, KWSeeker>();
        seekers.put("comment-sensitive-word", new KWSeeker(loadKeyWords("comment-sensitive-word")));
        seekers.put("topic-sensitive-word", new KWSeeker(loadKeyWords("topic-sensitive-word")));

        KWSeekerManage kwSeekerManage = new KWSeekerManage(seekers);
        
        
        // 开始测试
        String wordType1 = "comment-sensitive-word";
        String text1 = "这是一部黄色电影，也是一部AV电影";
        System.out.println("原文：" + text1);
        System.out.println("敏感词：黄色,AV");
        System.out.println("识别结果=============");
        List<SensitiveWordResult> words1 = kwSeekerManage.getKWSeeker(wordType1).findWords(text1);
        System.out.println("返回敏感词及下标：" + words1);
        String s1 = kwSeekerManage.getKWSeeker(wordType1).htmlHighlightWords(text1);
        System.out.println("html高亮：" + s1);
        String r1 = kwSeekerManage.getKWSeeker(wordType1).replaceWords(text1);
        System.out.println("字符替换：" + r1);

        System.out.println("\n**************************************\n");

        String wordType2 = "topic-sensitive-word";
        String text2 = "在中国这是一部黄色电影，在日本这不是一部黄色电影，在美国这不是一部黄色电影";

        System.out.println("原文：" + text2);
        System.out.println("敏感词：中国，美国，黄色");
        System.out.println("识别结果=============");

        List<SensitiveWordResult> words2 = kwSeekerManage.getKWSeeker(wordType2).findWords(text2);
        System.out.println("返回敏感词及下标：" + words2);
        String s2 = kwSeekerManage.getKWSeeker(wordType2).htmlHighlightWords(text2);
        System.out.println("html高亮：" + s2);
        String r2 = kwSeekerManage.getKWSeeker(wordType2).replaceWords(text2);
        System.out.println("字符替换：" + r2);
    }
}
