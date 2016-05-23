package com.hlin.sensitive.processor;

import org.apache.commons.lang3.StringUtils;

import com.hlin.sensitive.KeyWord;

/**
 * 高亮的方式。
 * 
 * @author hailin0@yeah.net
 * @createDate 2016年5月22日
 *
 */
public class HTMLFragment extends AbstractFragment {

    /**
     * 开口标记
     */
    private String open;

    /**
     * 封闭标记
     */
    private String close;

    /**
     * 初始化开闭标签
     * 
     * @param open 开始标签，如< b >、< font >等。
     * @param close 结束标签，如< /b >、< /font >等。
     */
    public HTMLFragment(String open, String close) {
        this.open = StringUtils.trimToEmpty(open);
        this.close = StringUtils.trimToEmpty(close);
    }

    @Override
    public String format(KeyWord word) {
        return open + word.getWord() + close;
    }
}
