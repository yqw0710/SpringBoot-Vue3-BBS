package com.yuan.bbs.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 来自:https://xiaorui2.github.io/2019/08/18/SpringBoot%E5%BC%80%E5%8F%91%E5%8D%81%E5%9B%9B-%E8%BF%87%E6%BB%A4%E6%95%8F%E6%84%9F%E8%AF%8D/
 */
@Slf4j
@Component
public class SensitiveFilterUtil {

    // 替换符
    private static final String REPLACEMENT = "***";
    // 根节点
    private final TrieNode rootNode = new TrieNode();

    @PostConstruct
    public void init() {
        try (
                InputStream is = this.getClass().getClassLoader().getResourceAsStream("sensitive.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(is))
        ) {
            String keyword;
            while ((keyword = reader.readLine()) != null) {
                // 添加到前缀树
                this.addKeyword(keyword);
            }
            log.info("加载敏感词文件成功！");
        } catch (IOException e) {
            log.error("加载敏感词文件失败: " + e.getMessage());
        }
    }

    /**
     * 过滤敏感词
     *
     * @param text 待过滤的文本
     * @return 过滤后的文本
     */
    public String filter(String text) {
        if (!StringUtils.hasText(text)) {
            return null;
        }
        // 指针1
        TrieNode tempNode = rootNode;
        // 指针2
        int begin = 0;
        // 指针3
        int position = 0;
        // 结果
        StringBuilder sb = new StringBuilder();

        while (position < text.length()) {
            char c = text.charAt(position);
            // 跳过符号
            if (isSymbol(c)) {
                // 若指针1处于根节点,将此符号计入结果,让指针2向下走一步
                if (tempNode == rootNode) {
                    sb.append(c);
                    begin++;
                }
                // 无论符号在开头或中间,指针3都向下走一步
                position++;
                continue;
            }
            // 检查下级节点
            tempNode = tempNode.getSubNode(c);
            if (tempNode == null) {
                // 以begin开头的字符串不是敏感词
                sb.append(text.charAt(begin));
                // 进入下一个位置
                position = ++begin;
                // 重新指向根节点
                tempNode = rootNode;
            } else if (tempNode.isKeywordEnd()) {
                // 发现敏感词,将begin~position字符串替换掉
                sb.append(REPLACEMENT);
                // 进入下一个位置
                begin = ++position;
                // 重新指向根节点
                tempNode = rootNode;
            } else {
                // 检查下一个字符
                position++;
            }
        }
        // 将最后一批字符计入结果
        sb.append(text.substring(begin));
        return sb.toString();
    }

    // 将一个敏感词添加到前缀树中
    private void addKeyword(String keyword) {
        TrieNode tempNode = rootNode;
        for (int i = 0; i < keyword.length(); i++) {
            char c = keyword.charAt(i);
            TrieNode subNode = tempNode.getSubNode(c);
            if (subNode == null) {
                // 初始化子节点
                subNode = new TrieNode();
                tempNode.addSubNode(c, subNode);
            }
            // 指向子节点,进入下一轮循环
            tempNode = subNode;
            // 设置结束标识
            if (i == keyword.length() - 1) {
                tempNode.setKeywordEnd(true);
            }
        }
    }

    // 判断是否为符号
    private boolean isSymbol(Character c) {
        // 0x2E80~0x9FFF 是东亚文字范围
        return !isAsciiAlphanumeric(c) && (c < 0x2E80 || c > 0x9FFF);
    }

    private boolean isAsciiAlphanumeric(Character c) {
        return (c >= 48 && c <= 57) || (c >= 97 && c <= 122);
    }

    // 前缀树
    private static class TrieNode {
        // 子节点(key是下级字符,value是下级节点)
        private final Map<Character, TrieNode> subNodes = new HashMap<>();
        // 关键词结束标识
        private boolean isKeywordEnd = false;

        public boolean isKeywordEnd() {
            return isKeywordEnd;
        }

        public void setKeywordEnd(boolean keywordEnd) {
            isKeywordEnd = keywordEnd;
        }

        public void addSubNode(Character c, TrieNode node) {
            subNodes.put(c, node);
        }

        public TrieNode getSubNode(Character c) {
            return subNodes.get(c);
        }
    }
}