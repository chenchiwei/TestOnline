package com.ccw.testonline.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ccw.testonline.bean.IdsBean;
import com.ccw.testonline.bean.PageBean;
import com.ccw.testonline.bean.QuestionBean;
import com.ccw.testonline.common.controller.CommonController;
import com.ccw.testonline.common.utils.ReadExcelUtils;
import com.ccw.testonline.common.vo.PageVO;
import com.ccw.testonline.entity.Course;
import com.ccw.testonline.entity.Options;
import com.ccw.testonline.entity.Question;
import com.ccw.testonline.exception.ServiceException;

@Controller
@RequestMapping("/question")
public class QuestionController extends CommonController<Question, QuestionBean> {
	
	
	@RequestMapping("/findAllByCourseId/{courseId}")
	@ResponseBody
	public PageVO<Question> findAllByCourseId(PageBean pageBean,@PathVariable Integer courseId){

		PageVO<Question> pageVo=null;
		try {
			Map<String,Object> paramMap=new HashMap<>();
			paramMap.put("courseId", courseId);
			paramMap.put("SORT", "q.question_level");
			paramMap.put("DIR", "asc");
			pageVo=commonService.findListByPage(pageBean, super.STATEMENT, paramMap);
			for (Question question : pageVo.getRows()) {
				question.formateScore();
			}
		} catch (ServiceException e) {
			logger.error("分页查询信息失败！");
			e.printStackTrace();
		}
		
		return pageVo;
	}
	
	@RequestMapping(value="/importExcel/{courseId}", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> uploadDoc(MultipartFile file,@PathVariable Integer courseId){
		Map<String,Object> map=new HashMap<>();
		if(!file.isEmpty()){
			try {
			//算出文件类型
			String oriName=file.getOriginalFilename();
			int index=oriName.lastIndexOf(".");
			String docType=oriName.substring(index+1);
			if(!docType.equals("xls")){
				map.put("success", false);
				return map;
			}
			List<Question> questionList=ReadExcelUtils.read(file.getInputStream(), courseId);
			commonService.addAll(questionList, super.STATEMENT, "insertSelective");
			for (Question question : questionList) {
				if(question.getOptions().size()>0){
					for(Options option:question.getOptions()){
						option.setQuestion(question);
						commonService.add(option, "com.ccw.testonline.entity.Options", "insertSelective");
					}
				}
			}
				map.put("success", true);
			} catch (Exception e) {
				map.put("success", false);
				e.printStackTrace();
			} 
		}
		
		return map;
	} 
	
	@RequestMapping("/listPage")
	@ResponseBody
	public PageVO<Object> findListByAllPage(PageBean pageBean){
		PageVO<Object> pageVo=null;
		try {
			int count=commonService.getNum(null, STATEMENT, "getCount1");
			pageVo=this.commonService.findListByPage(pageBean, STATEMENT, "findListByPage1",count);
			//pageVo=this.commonService.findListByPage(pageBean,STATEMENT,"findListByPage1");
		} catch (ServiceException e) {
			logger.error("分页查询信息失败！");
			e.printStackTrace();
		}
		
		return pageVo;
	}
	

	/**
	 * 删除
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(@RequestBody List<IdsBean> ids){
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			if(ids.size()==0){
				result.put("success", false);
				return result;
			}
			commonService.delete(ids, "com.ccw.testonline.entity.Options", "deleteByQueId");
			int flag=this.commonService.delete(ids,STATEMENT);
			if(flag>0){
				result.put("success", true);
			}
		} catch (Exception e) {
			logger.error("删除信息失败！");
			result.put("success", false);
			e.printStackTrace();
		} 
		
		return result;
	}
}
