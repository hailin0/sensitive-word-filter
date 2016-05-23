package com.hlin.sensitive;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;

import com.hlin.sensitive.processor.AbstractFragment;
import com.hlin.sensitive.processor.HTMLFragment;
import com.hlin.sensitive.processor.Highlight;
import com.hlin.sensitive.processor.IgnoreFragment;
import com.hlin.sensitive.processor.Processor;
import com.hlin.sensitive.processor.WordFinder;
import com.hlin.sensitive.util.AnalysisUtil;
import com.hlin.sensitive.util.EmojiUtil;

/**
 * 
 * 关键词搜索器
 * 
 * @author hailin0@yeah.net
 * @createDate 2016年5月22日
 *
 */
public class KWSeeker {

    /**
     * 所有的关键词
     */
    private Set<KeyWord> sensitiveWords;

    /**
     * 关键词树
     */
    private Map<String, Map> wordsTree = new ConcurrentHashMap<String, Map>();

    /**
     * 最短的关键词长度。用于对短于这个长度的文本不处理的判断，以节省一定的效率
     */
    private int wordLeastLen = 0;

    /**
     * 
     * @param sensitiveWords 关键词列表
     */
    public KWSeeker(Set<KeyWord> sensitiveWords) {
        this.sensitiveWords = sensitiveWords;
        reloadKWSeeker();
    }

    /**
     * 初始化
     */
    public void reloadKWSeeker() {
        wordLeastLen = new DataInit().generalTree(sensitiveWords, wordsTree);
    }

    /**
     * 添加一个或多个新的关键词。
     * 
     * @param newWord
     */
    public void addWord(KeyWord... newWord) {
        if (newWord != null && newWord.length > 0) {
            for (KeyWord kw : newWord) {
                if (StringUtils.isNotEmpty(kw.getWord())) {
                    sensitiveWords.add(kw);
                }
            }
            reloadKWSeeker();
        }
    }

    /**
     * 清除所有的关键词
     */
    public void clear() {
        sensitiveWords.clear();
        reloadKWSeeker();
    }

    /**
     * 是否包含指定的词
     * 
     * @param word 指定的关键词
     * @return true:包含,false:不包含
     */
    public boolean contains(String word) {
        if (sensitiveWords.isEmpty() || StringUtils.isEmpty(word)) {
            return false;
        }
        return sensitiveWords.contains(word);
    }

    /**
     * 含有敏感的个数
     * 
     * @return 返回具体的数量
     */
    public int size() {
        return sensitiveWords == null ? 0 : sensitiveWords.size();
    }

    /**
     * 从词库中移除指定的关键词
     * 
     * @param word 指定需要被移除的关键词
     * @return true:移除成功,false:移除失败
     */
    public boolean remove(String word) {
        if (sensitiveWords.isEmpty() || StringUtils.isEmpty(word)) {
            return false;
        }
        return sensitiveWords.remove(new KeyWord(word));
    }

    // *************************操作接口****************************//

    /**
     * 使用指定的处理器进行处理！
     * 
     * @param proc 处理器
     * @param text 目标文本
     * @param fragment 命中词的处理器
     * @return 返回处理结果
     */
    public Object process(Processor proc, String text, AbstractFragment fragment) {
        text = EmojiUtil.filterEmoji(text);
        return proc.process(wordsTree, text, fragment, wordLeastLen);
    }

    /**
     * 高亮处理器
     * 
     * @param text
     * @param fragment
     * @return
     */
    public String highlightWords(String text, AbstractFragment fragment) {
        return (String) process(new Highlight(), text, fragment);
    }

    /**
     * 将指定的字符串中的关键词提取出来。
     * 
     * @param text 指定的字符串。即：预处理的字符串
     * @return 返回其中所有关键词。如果没有，则返回空列表。
     */
    public List<SensitiveWordResult> findWords(String text) {
        return (List<SensitiveWordResult>) process(new WordFinder(), text, null);
    }

    /**
     * 返回将传入的字符串使用HTML的高亮方式处理之后的结果
     * 
     * @param text 传入需要处理的字符串。
     * @return 返回处理后的结果。
     */
    public String htmlHighlightWords(String text) {
        return (String) process(new Highlight(), text, new HTMLFragment("<font color='red'>",
                "</font>"));
    }

    /**
     * 返回替换后的处理结果
     * 
     * @param text 传入需要处理的字符串。
     * @param formatter 指定的高亮方式
     * @return 返回处理后的结果。
     */
    public String replaceWords(String text) {
        return (String) process(new Highlight(), text, new IgnoreFragment("***"));
    }

    /**
     * 
     * 数据初始化
     * 
     * @author hailin0@yeah.net
     * @createDate 2016年5月22日
     *
     */
    private static class DataInit {

        /**
         * 生成的临时词库树。用于在最后生成的时候一次性替换，尽量减少对正在查询时的干扰
         */
        private Map<String, Map> wordsTreeTmp = new HashMap<String, Map>();

        /**
         * 构造、生成词库树。并返回所有敏感词中最短的词的长度。
         * 
         * @param sensitiveWords 词库
         * @param wordsTree 聚合词库的树
         * @return 返回所有敏感词中最短的词的长度。
         */
        public int generalTree(Set<KeyWord> sensitiveWords, Map<String, Map> wordsTree) {
            if (sensitiveWords == null || sensitiveWords.isEmpty() || wordsTree == null) {
                return 0;
            }

            wordsTreeTmp.clear();
            int len = 0;
            for (KeyWord kw : sensitiveWords) {
                if (len == 0) {
                    len = kw.getWordLength();
                } else if (kw.getWordLength() < len) {
                    len = kw.getWordLength();
                }
                AnalysisUtil
                        .makeTreeByWord(wordsTreeTmp, StringUtils.trimToEmpty(kw.getWord()), kw);
            }
            wordsTree.clear();
            wordsTree.putAll(wordsTreeTmp);
            return len;
        }
    }

}
