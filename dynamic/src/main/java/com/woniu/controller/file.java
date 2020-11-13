package com.woniu.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.woniu.utils.JSONResult;
import io.swagger.annotations.Api;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


/** 
* @author 作者:jiejiang E-mail:1289687985@qq.com: 
* @version 创建时间：2020年10月25日 下午7:57:57 
* 方法说明 :
*/
@RestController
@RequestMapping("file")
@Api("文件上传")
public class file {
	
	@PostMapping
	public JSONResult insertMedia(@RequestParam("files") List<MultipartFile> files) throws Exception{
		ArrayList<String> list=new ArrayList<>();
		if(!files.isEmpty()) {
			try {
				for (MultipartFile mu : files) {
					//获取文件名
					String string = mu.getOriginalFilename();
					//截取后缀
					String ext = string.substring(string.lastIndexOf("."));
					//生成文件名
					String filename=UUID.randomUUID().toString()+ext;
					//存放的路径
					String path="D:\\Userdata\\photo"+filename;
					//获取文件输入，获取文件源，file(保存地址，文件命名）
					FileUtils.copyInputStreamToFile(mu.getInputStream(), new File("D:\\UserData\\photo",filename));
					list.add(path);
				}
				//文件路径保存到数据库中
			} catch (Exception e) {
				throw new Exception("文件上传失败");
			}
		}else {
			throw new Exception("文件不能为空");
		}
		return new JSONResult("200","success",list,null);
	}
}
