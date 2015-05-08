package com.ccw.testonline.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.ccw.testonline.entity.Course;
import com.ccw.testonline.entity.Options;
import com.ccw.testonline.entity.Question;

import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;

/**
 * 
 * 
 * Title: TestOnline <br>
 * Description:读取excel文件 <br>
 * 
 * @author <a href="mailto:775302619@qq.com">陈炽伟</a><br>
 * @e-mail: 775302619@qq.com <br>
 * @version 1.0 <br>
 * @creatdate 2014年12月29日 下午1:55:27 <br>
 * 
 */
public class ReadExcelUtils {

	public static List<Question> read(InputStream inputStream,Integer courseId) throws BiffException, IOException,
		WriteException  {
		List<Question> list=new ArrayList<>();
		try {
			jxl.Workbook rwb = Workbook.getWorkbook(inputStream);

			Sheet rs = (Sheet) rwb.getSheet(0);
			Course course=new Course(courseId); 
			for (int i = 1; i < rs.getRows(); i++) {
				if(rs.getCell(0, i).getType()!=CellType.EMPTY){
					Question question=new Question();
					question.setQuestionContent(rs.getCell(0, i).getContents());
					question.setQuestionType(Integer.parseInt(rs.getCell(1, i).getContents()));
					question.setQuestionLevel(rs.getCell(2, i).getContents());
					question.setCourse(course);
					// 答案
					if(rs.getCell(3, i).getType()!=CellType.EMPTY){
						question.setQuestionAnswer(rs.getCell(3, i).getContents());
					}
					//选择题
					int k=4;
					List<Options> optionList=new ArrayList<>();
					while(rs.getCell(k, i).getType()!=CellType.EMPTY){
						Options options=new Options();
						options.setQuestion(question);
						options.setOptionContent(rs.getCell(k, i).getContents());
						optionList.add(options);
						k++;
					}
					question.setOptions(optionList);
					list.add(question);
				}
				
			}
			
			rwb.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return list;
	}

	public static void main(String args[]) throws BiffException, IOException,
			WriteException {

	}
}
