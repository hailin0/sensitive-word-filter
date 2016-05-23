package com.hlin.sensitive.processor;

import java.util.Map;

/**
 * 
 * 处理接口
 * 
 * @author hailin0@yeah.net
 * @createDate 2016年5月22日
 *
 */
public interface Processor {
    /**
     * 处理操作
     * 
     * @param wordsTree 词表树
     * @param text 目标文本
     * @param fragment 每个命中的词处理器
     * @param minLen 词树中最短的词的长度
     * @return 返回处理结果
     */
    @SuppressWarnings("rawtypes")
    public Object process(Map<String, Map> wordsTree, String text, AbstractFragment fragment,
            int minLen);
}
