package com.ccw.testonline.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ccw.testonline.bean.OptionBean;
import com.ccw.testonline.common.controller.CommonController;
import com.ccw.testonline.entity.Options;

@Controller
@RequestMapping("/option")
public class OptionController extends CommonController<Options, OptionBean> {

}
