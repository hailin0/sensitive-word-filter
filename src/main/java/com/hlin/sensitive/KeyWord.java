package com.hlin.sensitive;

import java.io.Serializable;

/**
 * 
 * 关键词
 * 
 * @author hailin0@yeah.net
 * @createDate 2016年5月22日
 *
 */
public class KeyWord implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -6050328795034034286L;

    /**
     * 关键词内容
     */
    private String word;

    /**
     * （单字符）词的前缀,支持正则
     */
    private String pre;

    /**
     * （单字符）词的后缀，支持正则
     */
    private String sufix;

    /**
     * 关键词长度
     */
    private int wordLength = 0;

    /**
     * @param word
     */
    public KeyWord(String word) {
        this.word = word;
        this.wordLength = word.length();
    }

    /**
     * @param word
     * @param pre
     * @param sufix
     */
    public KeyWord(String word, String pre, String sufix) {
        this(word);
        this.pre = pre;
        this.sufix = sufix;
    }

    /**
     * @return the word
     */
    public String getWord() {
        return word;
    }

    /**
     * @param word the word to set
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     * @return the wordLength
     */
    public int getWordLength() {
        return wordLength;
    }

    /**
     * @param wordLength the wordLength to set
     */
    public void setWordLength(int wordLength) {
        this.wordLength = wordLength;
    }

    /**
     * @return the pre
     */
    public String getPre() {
        return pre;
    }

    /**
     * @param pre the pre to set
     */
    public void setPre(String pre) {
        this.pre = pre;
    }

    /**
     * @return the sufix
     */
    public String getSufix() {
        return sufix;
    }

    /**
     * @param sufix the sufix to set
     */
    public void setSufix(String sufix) {
        this.sufix = sufix;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((word == null) ? 0 : word.hashCode());
        result = prime * result + wordLength;
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        KeyWord other = (KeyWord) obj;
        if (word == null) {
            if (other.word != null)
                return false;
        } else if (!word.equals(other.word))
            return false;
        if (wordLength != other.wordLength)
            return false;
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "KeyWord [word=" + word + ", wordLength=" + wordLength + "]";
    }

}
