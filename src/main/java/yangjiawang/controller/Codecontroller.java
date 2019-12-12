package yangjiawang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.servlet.ServletOutputStream;
import javax.imageio.ImageIO;
/**
 * @author YANGJIAWANG
 * @DATE 2019/12/6
 * @TIME 20:16
 **/
@Controller
@CrossOrigin("*")
public class Codecontroller {
    private static final String str="asdfghjklzxcvbnmqwertyuiop";
    private Random  random=new Random();

    //随机生成4个字符
    public String getStr() {
        String s="";
        int len=str.length();
        for(int i=0;i<4;i++) {
            s+=str.charAt(random.nextInt(len));
        }

        return s;
    }
    public Color getcolor() {
        int red=random.nextInt(256);
        int green=random.nextInt(256);
        int blue=random.nextInt(256);
        Color color=new Color(red,green,blue);
        return color;
    }
@RequestMapping("/Code")
    public  void  Code(HttpServletResponse resp, HttpServletRequest req,HttpSession session)throws Exception{
    //生成验证码图片
    //画板
    BufferedImage image=new BufferedImage(70,20,BufferedImage.TYPE_INT_RGB);
    //画笔
    Graphics pen=image.getGraphics();
    //矩形
    pen.fillRect(0, 0, 70, 20);
    //字体
    pen.setFont(new Font("微软雅黑",Font.BOLD,20));
    //获取4个字符
    String code=getStr();
    //绘制图片
    for(int i=0;i<code.length();i++) {
        pen.setColor(getcolor());
        pen.drawString(String.valueOf(code.charAt(i)), i*15+5, 20);
    }
    //response对象绘制图片到页面
    ServletOutputStream sos=resp.getOutputStream();
    ImageIO.write(image, "jpg", sos);
    sos.flush();
    sos.close();

}


}
