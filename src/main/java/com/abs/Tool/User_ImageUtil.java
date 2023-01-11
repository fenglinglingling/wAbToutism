package com.abs.Tool;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

public class User_ImageUtil {
    public String savaimage(HttpServletRequest request,MultipartFile ImageFile, String user_id) {
        String realPath = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
        String realPath2 = request.getSession().getServletContext().getRealPath("/");//获取绝对路径
        System.out.println(realPath2);

        //获取绝对路径后 确定文件上传到那个文件夹
        File rootPath = new File(realPath + "User_image");
        if (!rootPath.exists()) rootPath.mkdirs();
         String uploadimageName = ImageFile.getOriginalFilename();//获取上传文件的名字
        String substring = uploadimageName.substring(uploadimageName.lastIndexOf("."));
        //设置头像名字: image+用户id
        String imageName = "image" + user_id+substring;
        //创建文件对象
        File file1 = new File(rootPath, imageName);//保存再服务器的文件对象
        try {
            ImageFile.transferTo(file1);
            return file1.getPath();//返回存储的路径
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
