package com.lxn.code.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lxn.code.bean.Type;
import com.lxn.code.service.TypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeServiceImpl service;
    @GetMapping("/types")
    public String types(Model model, @RequestParam(value = "start",defaultValue = "1")int start){
        PageHelper.startPage(start,3);
        List<Type> types = service.listType();
        PageInfo<Type> info = new PageInfo<>(types);
        model.addAttribute("page",info);
        return "admin/types";
    }

    @RequestMapping("deleteType")
    public String delete(Type type){
        service.deleteType(type.getId());
        return "redirect:/admin/types";
    }

    @GetMapping("/types/input")
    public String input(){
        return "admin/types-input";
    }

    @GetMapping("/updateType")
    public String update(Model model,Type type){
        model.addAttribute("type",type);
        Type dbtype = service.getType(type.getId());
        model.addAttribute("dbtype",dbtype);
        return "admin/types-inputs";
    }

    @PostMapping("/types/save")
    public String save(@RequestParam("name") String name, RedirectAttributes attributes){
        Type type = new Type();
        type.setName(name);
        Type byName = service.findByName(name);
        if (byName!=null){
            attributes.addFlashAttribute("message","插入失败，类型重复！！！");
            return "redirect:/admin/types";
        }
        int i = service.saveType(type);
        if (i!=0){
            attributes.addFlashAttribute("message","操作成功");
        }else {
            attributes.addFlashAttribute("message","操作失败");
        }

        return "redirect:/admin/types";
    }
    @PostMapping("/types/update/{id}")
    public String updates(@RequestParam("name") String name,@PathVariable("id") Long id, RedirectAttributes attributes){
        Type byName = service.findByName(name);
        if (byName!=null){
            attributes.addFlashAttribute("message","插入失败，类型重复！！");
            return "redirect:/admin/types";
        }
        int updateType = service.updateType(id, name);
        if (updateType ==
                1){
            attributes.addFlashAttribute("message","更新成功");
        }else {
            attributes.addFlashAttribute("message","更新失败");
        }

        return "redirect:/admin/types";
    }


}
