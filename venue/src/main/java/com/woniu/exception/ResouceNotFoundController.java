package com.woniu.exception;


import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
public class ResouceNotFoundController implements ErrorController {

	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return "/error";
	}
	@RequestMapping("error")
	public ModelAndView handel404(HttpServletRequest request)throws Exception {
		ModelAndView view=new ModelAndView();
		String header = request.getHeader("X-Requested-With");
		if(header!=null&&header.equalsIgnoreCase("XMLHttpRequest")) {
			FastJsonJsonView jsonView = new FastJsonJsonView();
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("code", "404");
			map.put("message", "找不到资源");
			jsonView.setAttributesMap(map);
			view.setView(jsonView);
		}else {
			view.setViewName("redirect:/error/404.html");
		}
		return view;
	}
}
