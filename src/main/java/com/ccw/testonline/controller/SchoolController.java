package com.ccw.testonline.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ccw.testonline.bean.SchoolBean;
import com.ccw.testonline.common.controller.CommonController;
import com.ccw.testonline.entity.School;

@Controller
@RequestMapping("/school")
public class SchoolController extends CommonController<School,SchoolBean> {

	
	
}
