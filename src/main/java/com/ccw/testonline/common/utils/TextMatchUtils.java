package com.ccw.testonline.common.utils;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

/**
 * 
 * 
 * Title: TestOnline <br>
 * Description: 使用余弦相似度算法<br>
 * 
 * @author <a href="mailto:775302619@qq.com">陈炽伟</a><br>
 * @e-mail: 775302619@qq.com <br>
 * @version 1.0 <br>
 * @creatdate 2014年12月31日 上午10:55:40 <br>
 * 
 */
public class TextMatchUtils {

	/**
	 * 计算相似度
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 * @throws IOException
	 */
	public static double caculateSimiarity(String str1, String str2)
			throws IOException {

		Map<String, Integer> tf1 = getTF(str1);
		Map<String, Integer> tf2 = getTF(str2);
		Map<String, MutablePair<Integer, Integer>> tfs = new HashMap<String, MutablePair<Integer, Integer>>();
		for (String key : tf1.keySet()) {
			MutablePair<Integer, Integer> pair = new MutablePair<Integer, Integer>(
					tf1.get(key), 0);
			tfs.put(key, pair);
		}
		for (String key : tf2.keySet()) {
			MutablePair<Integer, Integer> pair = tfs.get(key);
			if (null == pair) {
				pair = new MutablePair<Integer, Integer>(0, tf2.get(key));
			} else {
				pair.setRight(tf2.get(key));
			}
		}
		double d = caclIDF(tfs);

		return d;

	}

	public static void main(String[] args) throws IOException {
		
		String str1 = "<!-- 一个题目对应一个学生回答的答案，一对一 -->";
		Map<String, Integer> tf1 = getTF(str1);
		String str2 = "一个题目，一对一";
		Map<String, Integer> tf2 = getTF(str2);
		Map<String, MutablePair<Integer, Integer>> tfs = new HashMap<String, MutablePair<Integer, Integer>>();
		for (String key : tf1.keySet()) {
			MutablePair<Integer, Integer> pair = new MutablePair<Integer, Integer>(
					tf1.get(key), 0);
			tfs.put(key, pair);
		}
		for (String key : tf2.keySet()) {
			MutablePair<Integer, Integer> pair = tfs.get(key);
			if (null == pair) {
				pair = new MutablePair<Integer, Integer>(0, tf2.get(key));
			} else {
				pair.setRight(tf2.get(key));
			}
		}
		double d = caclIDF(tfs);
		System.out.println(d);
	}
	
	public static Map<String, Integer> getTF(String str) throws IOException {
		Map<String, Integer> map = new HashMap<String, Integer>();
		IKSegmenter ikSegmenter = new IKSegmenter(new StringReader(str), true);
		Lexeme lexeme = null;
		while ((lexeme = ikSegmenter.next()) != null) {
			String key = lexeme.getLexemeText();
			Integer count = map.get(key);
			if (null == count) {
				count = 1;
			} else {
				count = count + 1;
			}
			map.put(key, count);
		}
		return map;
	}

	public static double caclIDF(Map<String, MutablePair<Integer, Integer>> tf) {
		double d = 0;
		if (tf.isEmpty()) {
			return d;
		}
		double denominator = 0;
		double sqdoc1 = 0;
		double sqdoc2 = 0;
		Pair<Integer, Integer> count = null;
		for (String key : tf.keySet()) {
			count = tf.get(key);
			denominator += count.getLeft() * count.getRight();
			sqdoc1 += count.getLeft() * count.getLeft();
			sqdoc2 += count.getRight() * count.getRight();
		}
		d = denominator / (Math.sqrt(sqdoc1) * Math.sqrt(sqdoc2));
		return d;
	}
}
