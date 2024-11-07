package com.nikki.shixunpro.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/file")
public class FileController {
    /**
     * 上传文件
     */
    @RequestMapping("/upload")

    public Map upload(@RequestParam("file") MultipartFile file) throws Exception {
        Map result = new HashMap<>();
        String fileExt = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);

        // 获取当前工作目录
        String currentDirectory = System.getProperty("user.dir");
        String savePath = currentDirectory + "/shixunpro/src/main/resources/static/upload";
        String fileName = new Date().getTime()+"."+fileExt;
        File dest = new File(savePath+"/"+fileName);
        file.transferTo(dest);
        /**
         * 如果使用idea或者eclipse重启项目，发现之前上传的图片或者文件丢失，将下面一行代码注释打开
         * 请将以下的"D:\\springbootq33sd\\src\\main\\resources\\static\\upload"替换成你本地项目的upload路径，
         * 并且项目路径不能存在中文、空格等特殊字符
         */
//		FileUtils.copyFile(dest, new File("D:\\springbootq33sd\\src\\main\\resources\\static\\upload"+"/"+fileName)); /**修改了路径以后请将该行最前面的//注释去掉**/
        result.put("code",200);
        result.put("data",fileName);
        return result;
    }


}
