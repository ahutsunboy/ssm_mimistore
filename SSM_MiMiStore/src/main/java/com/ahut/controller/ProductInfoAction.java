package com.ahut.controller;

import com.ahut.pojo.ProductInfo;
import com.ahut.pojo.ProductInfoVo;
import com.ahut.service.ProductInfoService;
import com.ahut.utils.FileNameUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.cj.PreparedQuery;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/prod")
public class ProductInfoAction {

    public static final int Page_Size = 5;

    private String saveFileName="";

    @Autowired
    private ProductInfoService productInfoService;

    /**
     * 查询全部商品数据（不分页）
     * @param model
     * @return
     */
    @RequestMapping("/getAll")
    public String getAll(Model model){
        List<ProductInfo> allProducts = productInfoService.getAll();
        model.addAttribute("list",allProducts);
        return "product";
    }


    /**
     * 显示第一页的五条信息
     * @return
     */
    @RequestMapping("/split")
    public String split(HttpServletRequest request){
        PageInfo<ProductInfo> pageInfo = null;
        ProductInfoVo vo = (ProductInfoVo) request.getSession().getAttribute("prodVo");
        if(vo != null){
            pageInfo = productInfoService.selectByPage(vo,Page_Size);
            request.getSession().removeAttribute("prodVo");
        }else{
            pageInfo = productInfoService.splitPage(1, Page_Size);
        }
        request.setAttribute("info",pageInfo);
        return "product";
    }

    /**
     * 分页
     * @param vo
     * @param session
     */
    @ResponseBody //获取ajax传来的page，这里这借用。
    @RequestMapping("/ajaxsplit")
    public void ajaxsplit(ProductInfoVo vo,HttpSession session){
        PageInfo<ProductInfo> info = productInfoService.selectByPage(vo, Page_Size);
        session.setAttribute("info",info);
    }

    /**
     * 多条件查询
     * @param
     * @param
     */
   /* @ResponseBody
    @RequestMapping("/condition")
    public void selectCondition(ProductInfoVo vo,HttpSession session){
        List<ProductInfo> productInfos = productInfoService.selectCondition(vo);
        *//*获取到的商品信息集合一定要放到分页信息中去，才能让product页面获取到，因为这个页面是接受分页信息的*//*
        PageInfo<ProductInfo> info = new PageInfo<>(productInfos);
        session.setAttribute("info",info);
    }*/


    @ResponseBody //只要要接受ajax的信息回传，就要用到这个注解
    @RequestMapping("/ajaxImg")
    public Object ajaxImg(MultipartFile pimage, HttpServletRequest request){
        //提取生成文件名 UUID + 上传图片后缀 .jpg .png
        saveFileName = FileNameUtil.getUUIDFileName() + FileNameUtil.getFileType(pimage.getOriginalFilename());
        //得到项目中图片储存位置（类似于图片存在服务器中的位置）获取图片存储的image_big文件夹的位置。
        String realPath = request.getServletContext().getRealPath("/image_big");
        //转存：将要上传的图片存入realPath(类似于服务器中的位置)中
        try {
            pimage.transferTo(new File(realPath + File.separator + saveFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //感觉利用jackson也可以传过去
        //返回客户端JSON对象，封装图片路径，为了在页面实现回显
        JSONObject object = new JSONObject();
        object.put("imgurl",saveFileName);

        return object.toString();
    }

    /**
     * 添加新商品
     * @return
     */
    @RequestMapping("/save")
    public String save(ProductInfo info,Model model){
        info.setpImage(saveFileName);
        info.setpDate(new Date());
        int num = -1;
        try {
            num = productInfoService.save(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(num > 0){
            model.addAttribute("msg","添加成功");
        }else{
            model.addAttribute("msg","添加失败");
        }
        saveFileName = "";
        return "forward:/prod/split.action";
    }

    /**
     * 将根据id查询到的数据和回显到修改页面（这个是点击修改除法的方法）
     * @return
     */
    @RequestMapping("/one")
    public String getOne(int pId, ProductInfoVo vo,Model model,HttpSession session){
        ProductInfo info = productInfoService.getById(pId);
        session.setAttribute("prodVo",vo);
        model.addAttribute("prod",info);
        return "update";
    }

    @RequestMapping("/update")
    public String update(ProductInfo info,HttpServletRequest request){
        if(!saveFileName.equals("")){
            info.setpImage(saveFileName);
        }

        int i = -1;
        try {
            i = productInfoService.update(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(i > 0){
            request.setAttribute("msg","修改成功");
        }else{
            request.setAttribute("msg","修改失败");
        }
        saveFileName = "";
        return "forward:/prod/split.action";
    }


    @RequestMapping("/delete")
    public String delete(int pId ,ProductInfoVo vo ,HttpServletRequest request){
        int i = -1;
        try {
            i = productInfoService.delete(pId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(i > 0){
            request.setAttribute("msg","删除成功");
            request.getSession().setAttribute("prodVo",vo);
        }else{
            request.setAttribute("msg","删除失败aaaa");
        }
        return "forward:/prod/split.action";
    }

    @RequestMapping("/deletebatch")
    public String deletebatch(String pids ,HttpServletRequest request){
        String[] ids = pids.split(",");
        int i = -1;
        try {
            i = productInfoService.delete(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(i > 0 ){
            request.setAttribute("msg","删除成功");
        }else{
            request.setAttribute("msg","删除失败");
        }
        return "forward:/prod/split.action";
    }





}
