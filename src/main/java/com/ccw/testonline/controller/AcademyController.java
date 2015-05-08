package com.ccw.testonline.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ccw.testonline.bean.AcademyBean;
import com.ccw.testonline.common.controller.CommonController;
import com.ccw.testonline.entity.Academy;

@Controller
@RequestMapping("/academy")
public class AcademyController extends CommonController<Academy, AcademyBean>{

	
}
