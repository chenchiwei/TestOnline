package com.ccw.testonline.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ccw.testonline.bean.AddAnswerBean;
import com.ccw.testonline.bean.AnswerBean;
import com.ccw.testonline.bean.ResultBean;
import com.ccw.testonline.common.controller.CommonController;
import com.ccw.testonline.common.utils.GenerateScoreUtils;
import com.ccw.testonline.entity.Answer;
import com.ccw.testonline.entity.Homework;
import com.ccw.testonline.entity.Question;
import com.ccw.testonline.entity.Student;
import com.ccw.testonline.entity.StudentHomework;
import com.ccw.testonline.exception.ServiceException;

@Controller
@RequestMapping("/answer")
public class AnswerController extends CommonController<Answer, AnswerBean> {
	
	@RequestMapping("/addAnswer/{stuId}/{tea_hw_id}")
	@ResponseBody
	public Map<String,Object> addAnswer(@RequestBody AddAnswerBean bean,@PathVariable Integer stuId,@PathVariable Integer tea_hw_id){
		List<ResultBean> checkboxResult = bean.getCheckboxResult();
		Map<Object,Object> chechboxMap=new HashMap<Object,Object>();
		List<ResultBean> result=new ArrayList<>();
		Map<String,Object> map=new HashMap<>();
		try {
			result.addAll(bean.getRadioResult());
			for (ResultBean resultBean : checkboxResult) {
				String value=null;
				if((value=(String) chechboxMap.get(resultBean.getId()))!=null){
					value=value+","+resultBean.getValue();
					chechboxMap.put(resultBean.getId(), value);
				}else{
					chechboxMap.put(resultBean.getId(), resultBean.getValue());
				}
			}
			
			for (Map.Entry<Object,Object> entry : chechboxMap.entrySet()) {
				ResultBean resultBean=new ResultBean();
				resultBean.setId(Integer.parseInt(entry.getKey().toString()));
				resultBean.setValue(entry.getValue().toString());
				result.add(resultBean);
			}
			result.addAll(bean.getTextResult());
			List<Answer> answers=new ArrayList<>();
			
			StudentHomework studentHomework=new StudentHomework(new Student(stuId),new Homework(tea_hw_id),new Date());
			commonService.add(studentHomework, "com.ccw.testonline.entity.StudentHomework", "insertSelective");
			for(ResultBean resultBean:result){
				Answer answer=new Answer(studentHomework,resultBean.getValue(),new Question(resultBean.getId()));
				answers.add(answer);
			}
			
			commonService.addAll(answers, super.STATEMENT, "insertSelective");
			
			List<Question> list = null;
			List<Question> listTwo = null;
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("id", tea_hw_id);
			paramMap.put("stuId", stuId);
			list = commonService.findList(paramMap,
					"com.ccw.testonline.entity.Question", "showMyHomework1");
			listTwo = commonService.findList(paramMap,
					"com.ccw.testonline.entity.Question", "showMyHomework2");
			float score = GenerateScoreUtils.generate(list, listTwo);
			studentHomework.setScore(score);
			commonService.edit(studentHomework, "com.ccw.testonline.entity.StudentHomework", "updateScore");
			map.put("success", true);
		} catch (ServiceException | IOException e) {
			map.put("success", false);
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		return map;
		
	}
	
	@RequestMapping("/showAnswer/{teaHwId}")
	public ModelAndView showAnswer(@PathVariable Integer teaHwId) {
		ModelAndView modelAndView = new ModelAndView();
		List<Question> list = null;
		List<Question> listTwo = null;
		try {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("teaHwId", teaHwId);
			list = commonService.findList(paramMap,
					"com.ccw.testonline.entity.Question", "showAnswer1");
			
			listTwo = commonService.findList(paramMap,
					"com.ccw.testonline.entity.Question", "showAnswer2");
			list.addAll(listTwo);
			paramMap.put("id", teaHwId);
			Homework homework=(Homework) commonService.findOne(paramMap, "com.ccw.testonline.entity.Homework", "selectOne");
			modelAndView.addObject("homework", homework);
		} catch (ServiceException e) {
			logger.error("分页查询信息失败！");
			e.printStackTrace();
		} finally {
			modelAndView.addObject("list", list);
			modelAndView.addObject("tea_hw_id", teaHwId);
			modelAndView.setViewName("page/home/showhkanswer");
		}

		return modelAndView;
	}
}
