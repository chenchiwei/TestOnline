package com.ccw.testonline.common.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 
 * 
 * Title: TestOnline <br>
 * Description:验证码类 <br>
 * 
 * @author <a href="mailto:775302619@qq.com">陈炽伟</a><br>
 * @e-mail: 775302619@qq.com <br>
 * @version 1.0 <br>
 * @creatdate 2015年1月2日 下午4:36:41 <br>
 *
 */
public class ValidateCodeUtils {
	/**
	 * 
	* @Title createVerifyImage 
	* @Description 生成验证图片与验证码
	* @param width
	* @param height
	* @param codeAmount
	* @return
	 */
	public static Map<String, Object> createVerifyImage(Integer width, Integer height, Integer codeAmount) {
		String codeString = "";
		BufferedImage image = createImage(width, height, 255, 255, 255);
		Graphics2D pen = image.createGraphics();
		pen.setColor(makeRandomColor());
		for(int i=0; i < codeAmount; i++) {
			Font font = makeRandomFont(width, height);
			pen.setFont(font);
			String code = makeRandomSingleCode();
			// 文字x坐标＝宽分配+随机95%-105%偏移
			Float x = i*width/codeAmount*(100 + random.nextInt(11) - 5)*0.01f;
			// 文字y坐标＝文字大小的80%+随机5%-15%偏移
			Float y = (height*(random.nextInt(11) + 5))*0.01f + font.getSize()*0.8f;
			pen.setTransform(makeRotate(x.intValue(), y.intValue()));
			pen.drawString(code, x, y);
			codeString += code;
		}
		pen.dispose();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", codeString);
		result.put("image", image);
		return result;
	}
	
	// 产生画布
	public static BufferedImage createImage(Integer width, Integer height, 
			Integer b_r, Integer b_g, Integer b_b) {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D pen = image.createGraphics();
		pen.setColor(new Color(b_r, b_g, b_b));
		pen.fillRect(0, 0, width, height);
		drawBackground(width, height, pen);
		return image;
	}
	
	// 随机背景
	public static void drawBackground(Integer width, Integer height, Graphics2D pen) {
		for(int i=0; i < 50; i++) {
			pen.setColor(makeRandomColor());
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			pen.drawOval(x, y, 1, 1);
		}
	}
	
	// 随机字体
	public static Font makeRandomFont(Integer width, Integer height) {
		String[] fontType = {"Sans serif", "Serif", "Fixed width", "Various"};
		Integer key = random.nextInt(fontType.length);
		Integer isBold = random.nextInt(2);
		Integer fontSize = (int) (height*0.01*(random.nextInt(11) + 80));
		return new Font(fontType[key], isBold, fontSize);
	}
	
	// 随机颜色
	public static Color makeRandomColor() {
		return new Color(random.nextInt(256), 
									random.nextInt(256), 
									random.nextInt(256));
	}
	
	// 随机转化角度
	public static AffineTransform makeRotate(Integer x, Integer y) {
		AffineTransform trans = new AffineTransform();
		trans.rotate((random.nextInt(31) - 15) * 3.14 / 180, x, y);
		float scaleSize = random.nextFloat()+0.8f;
		if(scaleSize>1f) scaleSize = 1f;
		trans.scale(scaleSize, scaleSize);
		return trans;
	}
	
	// 随机字符串
	public static String makeRandomSingleCode() {
		int key = random.nextInt(7);
		String singleCode = null;
		// 要去除0,1,l,o,I,O等难识别的字
		switch(key) {
		// A-H
		case 0:
			singleCode = String.valueOf((char) (random.nextInt(8)+65));
			break;
		// J-N
		case 1:
			singleCode = String.valueOf((char) (random.nextInt(5)+74));
			break;
		// P-Z
		case 2:
			singleCode = String.valueOf((char) (random.nextInt(11)+80));
			break;
		// a-k
		case 3:
			singleCode = String.valueOf((char) (random.nextInt(11)+97));
			break;
		// m-z
		case 4:
			singleCode = String.valueOf((char) (random.nextInt(14)+109));
			break;
		// 0-9
		case 5:
			singleCode = String.valueOf((char) (random.nextInt(8) + 50));
			break;
		case 6:
			singleCode = String.valueOf((char) (random.nextInt(8) + 50));
			break;
		default:
			singleCode = String.valueOf((char) (random.nextInt(8) + 50));
		}
		return singleCode;
	}
	
	private static Random random = new Random(new Date().getTime() + 1);// 随机数来源
}
