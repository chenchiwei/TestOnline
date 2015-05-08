package com.ccw.testonline.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ccw.testonline.bean.MajorBean;
import com.ccw.testonline.common.controller.CommonController;
import com.ccw.testonline.entity.Major;

@Controller
@RequestMapping("/major")
public class MajorController extends CommonController<Major, MajorBean> {
	
}
