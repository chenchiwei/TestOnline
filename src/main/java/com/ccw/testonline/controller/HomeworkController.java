package com.ccw.testonline.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ccw.testonline.bean.AddHomeworkBean;
import com.ccw.testonline.bean.ClassHwBean;
import com.ccw.testonline.bean.HomeworkBean;
import com.ccw.testonline.bean.IdsBean;
import com.ccw.testonline.bean.PageBean;
import com.ccw.testonline.common.controller.CommonController;
import com.ccw.testonline.common.vo.PageVO;
import com.ccw.testonline.entity.Answer;
import com.ccw.testonline.entity.ClassHomework;
import com.ccw.testonline.entity.Classes;
import com.ccw.testonline.entity.Course;
import com.ccw.testonline.entity.HomeWorkQuestion;
import com.ccw.testonline.entity.Homework;
import com.ccw.testonline.entity.Question;
import com.ccw.testonline.exception.DaoException;
import com.ccw.testonline.exception.ServiceException;
import com.ccw.testonline.service.HomeworkService;
import com.ccw.testonline.vo.ClassStatisticsVo;
import com.ccw.testonline.vo.StudentClassVo;

@Controller
@RequestMapping("/homework")
public class HomeworkController extends
		CommonController<Homework, HomeworkBean> {

	@Resource(name = "homeworkService")
	private HomeworkService homeworkService;

	/**
	 * 查找未完成的作业
	 * 
	 * @param stuId
	 * @return
	 */
	@RequestMapping("/findUnfinishHomework/{stuId}")
	@ResponseBody
	public PageVO<Homework> findUnfinishHomework(@PathVariable Integer stuId) {
		PageVO<Homework> pageVo = null;
		try {
			PageBean pageBean = new PageBean(null, null, 1, 10);
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("stuId", stuId);
			pageVo = commonService.findListByPage(pageBean, super.STATEMENT,
					paramMap, "findUnfinishHomework");
			for (Homework homework : pageVo.getRows()) {
				homework.setFlag(1);
			}
		} catch (ServiceException e) {
			logger.error("分页查询信息失败！");
			e.printStackTrace();
		} finally {
		}

		return pageVo;
	}

	/**
	 * 查找已完成的作业
	 * 
	 * @param stuId
	 * @return
	 */
	@RequestMapping("/findFinishedHomework/{stuId}")
	@ResponseBody
	public PageVO<Homework> findFinishedHomework(@PathVariable Integer stuId) {
		PageVO<Homework> pageVo = null;
		try {
			PageBean pageBean = new PageBean(null, null, 1, 10);
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("stuId", stuId);
			pageVo = commonService.findListByPage(pageBean, super.STATEMENT,
					paramMap, "findFinishedHomework");
			for (Homework homework : pageVo.getRows()) {
				homework.setFlag(0);
			}
		} catch (ServiceException e) {
			logger.error("分页查询信息失败！");
			e.printStackTrace();
		} finally {
		}

		return pageVo;
	}

	@RequestMapping("/findAllHomeworkByTea/{teaId}")
	@ResponseBody
	public PageVO<Homework> findListByPage(PageBean pageBean,
			@PathVariable Integer teaId) {
		PageVO<Homework> pageVo = null;
		try {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("teaId", teaId);
			pageVo = commonService.findListByPage(pageBean, super.STATEMENT,
					paramMap, "findAllHomeworkByTea");
		} catch (ServiceException e) {
			logger.error("分页查询信息失败！");
			e.printStackTrace();
		}
		return pageVo;
	}

	/**
	 * 查看未完成作业详情
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/showHomework/{id}")
	public ModelAndView showHomework(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		List<Question> listOne = null;
		List<Question> listTwo = null;
		try {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("id", id);
			listOne = commonService.findList(paramMap,
					"com.ccw.testonline.entity.Question", "showHomework1");
			listTwo = commonService.findList(paramMap,
					"com.ccw.testonline.entity.Question", "showHomework2");
			listOne.addAll(listTwo);
			Homework homework=(Homework) commonService.findOne(paramMap, "com.ccw.testonline.entity.Homework", "selectOne");
			modelAndView.addObject("homework", homework);
		} catch (ServiceException e) {
			logger.error("分页查询信息失败！");
			e.printStackTrace();
		} finally {
			modelAndView.addObject("list", listOne);
			modelAndView.addObject("flag", "edit");
			modelAndView.addObject("tea_hw_id", id);
			modelAndView.setViewName("page/home/showhomework");
		}

		return modelAndView;
	}

	/**
	 * 查看已完成作业详情
	 * 
	 * @param id
	 * @param stuId
	 * @return
	 */
	@RequestMapping("/showMyHomework/{id}/{stuId}")
	public ModelAndView showMyFinishedHomework(@PathVariable Integer id,
			@PathVariable Integer stuId) {
		ModelAndView modelAndView = new ModelAndView();
		List<Question> list = null;
		List<Question> listTwo = null;
		try {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("id", id);
			paramMap.put("stuId", stuId);
			list = commonService.findList(paramMap,
					"com.ccw.testonline.entity.Question", "showMyHomework1");
			for (Question question : list) {
				// 如果是多选
				if (question.getQuestionType() == 2) {
					String[] answerStr = question.getAnswer()
							.getAnswerContent().split(",");
					List<Answer> answerList = new ArrayList<>();
					for (String str : answerStr) {
						Answer answer = new Answer(question.getAnswer()
								.getAnswerId(), null, str, null);
						answerList.add(answer);
					}
					question.setAnswers(answerList);
				}
			}
			listTwo = commonService.findList(paramMap,
					"com.ccw.testonline.entity.Question", "showMyHomework2");
			list.addAll(listTwo);
			Float score = (Float) commonService.findOne(paramMap,
					"com.ccw.testonline.entity.Question", "getScore");
			modelAndView.addObject("score", score);
			Homework homework=(Homework) commonService.findOne(paramMap, "com.ccw.testonline.entity.Homework", "selectOne");
			modelAndView.addObject("homework", homework);
		} catch (ServiceException e) {
			logger.error("分页查询信息失败！");
			e.printStackTrace();
		} finally {
			modelAndView.addObject("list", list);
			modelAndView.addObject("flag", "show");
			modelAndView.addObject("tea_hw_id", id);
			modelAndView.setViewName("page/home/showhomework");
		}

		return modelAndView;
	}

	@RequestMapping("/addHomework")
	@ResponseBody
	public Map<String, Object> addHomework(@RequestBody AddHomeworkBean bean) {
		Map<String, Object> map = new HashMap<>();

		try {
			Homework homework = new Homework(bean.getTeaHwName(), new Date(),
					new Course(bean.getCourseId()));
			commonService.add(homework, super.STATEMENT);
			for (Integer classId : bean.getClassIds()) {
				ClassHomework classHomework = new ClassHomework(new Classes(
						classId), homework);
				commonService.add(classHomework,
						"com.ccw.testonline.entity.ClassHomework",
						"insertSelective");
			}

			Integer[] questionIds = bean.getQuestionIds();
			Float[] scores = bean.getScores();
			for (int i = 0; i < questionIds.length; i++) {
				HomeWorkQuestion homeWorkQuestion = new HomeWorkQuestion(
						homework, new Question(questionIds[i]));
				homeWorkQuestion.setScore(scores[i]);
				commonService.add(homeWorkQuestion,
						"com.ccw.testonline.entity.HomeWorkQuestion",
						"insertSelective");
			}

			map.put("success", true);
		} catch (ServiceException e) {
			map.put("success", false);
			e.printStackTrace();
		}

		return map;

	}

	@Override
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(@RequestBody List<IdsBean> ids) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (ids.size() == 0) {
				result.put("success", false);
				return result;
			}
			commonService
					.delete(ids, "com.ccw.testonline.entity.StudentHomework",
							"deleteByHwId");
			commonService.delete(ids,
					"com.ccw.testonline.entity.ClassHomework", "deleteByHwId");
			commonService.delete(ids,
					"com.ccw.testonline.entity.HomeWorkQuestion",
					"deleteByHwId");
			int flag = commonService.delete(ids, STATEMENT);
			if (flag > 0) {
				result.put("success", true);
			}
		} catch (Exception e) {
			logger.error("删除信息失败！");
			result.put("success", false);
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 查找未完成的作业
	 * 
	 * @param stuId
	 * @return
	 */
	@RequestMapping("/findSituation/{teaHwId}")
	@ResponseBody
	public Map<String, Object> findSituation(@PathVariable Integer teaHwId) {
		Map<String, Object> result = new HashMap<>();
		try {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("teaHwId", teaHwId);
			List<StudentClassVo> list = commonService.findList(paramMap,
					"com.ccw.testonline.entity.StudentHomework",
					"findClassNumAndStuNum");
			if (list.size() == 0) {
				result.put("success", false);
				return result;
			}
			
			int size = 0;
			int index = 0;
			while (index < list.size()) {
				if (index == list.size() - 1) {
					size++;
					index++;
				} else if (list.get(index).getClassId() != list.get(index + 1)
						.getClassId()) {
					size++;
					index++;
				} else {
					size++;
					index=index+2;
				}
			}
			
			String[] classStr = new String[size];
			Integer[] classId = new Integer[size];
			Integer[] finishedNum = new Integer[size];
			Integer[] unFinishedNum = new Integer[size];

			int i = 0;
			int j = 0;
			while (i < list.size()) {
				if (i == list.size() - 1) {
					classStr[j] = list.get(i).getClassName();
					classId[j] = list.get(i).getClassId();
					finishedNum[j] = 0;
					unFinishedNum[j] = list.get(i).getCount();
					i++;
				} else if (list.get(i).getClassId() != list.get(i + 1)
						.getClassId()) {
					classStr[j] = list.get(i).getClassName();
					classId[j] = list.get(i).getClassId();
					finishedNum[j] = 0;
					unFinishedNum[j] = list.get(i).getCount();
					i++;
				} else {
					classStr[j] = list.get(i + 1).getClassName();
					classId[j] = list.get(i + 1).getClassId();
					finishedNum[j] = list.get(i).getCount();
					unFinishedNum[j] = list.get(i + 1).getCount()
							- finishedNum[j];
					i=i+2;
				}
				j++;
			}
			
			
			/*for (int i = 0; i < list.size(); i = i + 2) {
				if (i == list.size() - 1) {
					classStr[j] = list.get(i).getClassName();
					classId[j] = list.get(i).getClassId();
					finishedNum[j] = 0;
					unFinishedNum[j] = list.get(i).getCount();
				} else if (list.get(i).getClassId() != list.get(i + 1)
						.getClassId()) {
					classStr[j] = list.get(i).getClassName();
					classId[j] = list.get(i).getClassId();
					finishedNum[j] = 0;
					unFinishedNum[j] = list.get(i).getCount();
				} else {
					classStr[j] = list.get(i + 1).getClassName();
					classId[j] = list.get(i + 1).getClassId();
					finishedNum[j] = list.get(i).getCount();
					unFinishedNum[j] = list.get(i + 1).getCount()
							- finishedNum[j];
				}
				j++;
			}
*/
			result.put("success", true);
			result.put("classStr", classStr);
			result.put("classId", classId);
			result.put("finishedNum", finishedNum);
			result.put("unFinishedNum", unFinishedNum);
		} catch (ServiceException e) {
			result.put("success", false);
			logger.error("分页查询信息失败！");
			e.printStackTrace();
		} finally {
		}

		return result;
	}

	@RequestMapping("/findFinishedStudent")
	@ResponseBody
	public PageVO<Object> findFinishedStudent(ClassHwBean classHwBean,
			PageBean pageBean) {
		PageVO<Object> pageVO = null;
		// 已完成的
		if (classHwBean.getIsNotFinished() == 0) {
			try {
				pageVO = homeworkService.findFinishedStudent(classHwBean,
						pageBean);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				pageVO = homeworkService.findUnFinishedStudent(classHwBean,
						pageBean);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return pageVO;
	}

	@RequestMapping("/findClassStatistics/{teaHwId}")
	@ResponseBody
	public Map<String,Object> findClassStatistics(@PathVariable Integer teaHwId) {
		Map<String,Object> map=new HashMap<>();
		Map<String, Object> paramMap=new HashMap<>();
		paramMap.put("teaHwId", teaHwId);
		
		List<ClassStatisticsVo> list;
		try {
			list = commonService.findList(paramMap, "com.ccw.testonline.entity.StudentHomework", "findClassStatistics");
			int size=list.size();;
			String[] classStr = new String[size];
			Integer[] classIds = new Integer[size];
			Float[] minScore = new Float[size];
			Float[] maxScore = new Float[size];
			Float[] avgScroe = new Float[size];
			for (int i = 0; i < size; i++) {
				if(list.get(i)!=null){
					classIds[i]=list.get(i).getClassId();
					classStr[i]=list.get(i).getClassName();
					minScore[i]=list.get(i).getMinScore();
					maxScore[i]=list.get(i).getMaxScore();
					avgScroe[i]=list.get(i).getAvgScore();
				}
				
			}
			
			map.put("success", true);
			map.put("classIds", classIds);
			map.put("classStr", classStr);
			map.put("minScore", minScore);
			map.put("maxScore", maxScore);
			map.put("avgScroe", avgScroe);
		} catch (ServiceException e) {
			map.put("success", false);
			e.printStackTrace();
		}
		
		return map;
	}
	
	@RequestMapping("/publishAnswer/{teaHwId}")
	@ResponseBody
	public Map<String,Object> publishAnswer(@PathVariable Integer teaHwId) {
		Map<String,Object> map=new HashMap<>();
		Map<String, Object> paramMap=new HashMap<>();
		paramMap.put("teaHwId", teaHwId);
		
		try {
			Homework homework=new Homework(teaHwId);
			homework.setStatus(2);
			commonService.edit(homework, STATEMENT,"publishAnswer");
			map.put("success", true);
			
		} catch (ServiceException e) {
			map.put("success", false);
			e.printStackTrace();
		}
		
		return map;
	}
	
	/**
	 * 系统评分
	 * 
	 * @param id
	 * @param stuId
	 * @return
	 */
	/*
	 * @RequestMapping("/generateScore/{id}/{stuId}")
	 * 
	 * @ResponseBody public Map<String,Object> generateScore(@PathVariable
	 * Integer id,
	 * 
	 * @PathVariable Integer stuId) { Map<String,Object> map=new HashMap<>();
	 * float score = 0;
	 * 
	 * List<Question> list = null; List<Question> listTwo = null; try {
	 * Map<String, Object> paramMap = new HashMap<>(); paramMap.put("id", id);
	 * paramMap.put("stuId", stuId); list = commonService.findList(paramMap,
	 * "com.ccw.testonline.entity.Question", "showMyHomework1"); listTwo =
	 * commonService.findList(paramMap, "com.ccw.testonline.entity.Question",
	 * "showMyHomework2"); score = GenerateScoreUtils.generate(list, listTwo);
	 * 
	 * map.put("success", true); map.put("score", score); } catch
	 * (ServiceException | IOException e) { map.put("success", false);
	 * logger.error("系统评分失败！"); e.printStackTrace(); }
	 * 
	 * return map; }
	 */

}
