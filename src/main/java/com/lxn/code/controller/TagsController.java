package com.lxn.code.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lxn.code.bean.Tag;
import com.lxn.code.service.TagsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TagsController {
    @Autowired
    private TagsServiceImpl service;

    @GetMapping("/tags")
    public String tags(Model model, @RequestParam(value = "start",defaultValue = "1")int start){
        PageHelper.startPage(start,3);
        List<Tag> tags = service.listTag();
        PageInfo<Tag> info = new PageInfo<>(tags);
        model.addAttribute("page",info);
        return "admin/tags";
    }
    @RequestMapping("/deleteTags")
    public String delete(Tag tag){
        service.deleteTag(tag.getId());
        return "redirect:/admin/tags";
    }


    @GetMapping("/tags/input")
    public String input(){
        return "admin/tags-input";
    }

    @GetMapping("/updateTags")
    public String update(Model model,Tag tag){
        model.addAttribute("tag",tag);
        Tag dbtag = service.getTag(tag.getId());
        model.addAttribute("dbtag",dbtag);
        return "admin/tags-inputs";
    }

    @PostMapping("/tags/save")
    public String save(@RequestParam("name") String name, RedirectAttributes attributes){
        Tag tag = new Tag();
        tag.setName(name);
        Tag byName = service.findByName(name);
        if (byName!=null){
            attributes.addFlashAttribute("message","插入失败，类型重复！！！");
            return "redirect:/admin/tags";
        }
        int i = service.saveTag(tag);
        if (i!=0){
            attributes.addFlashAttribute("message","操作成功");
        }else {
            attributes.addFlashAttribute("message","操作失败");
        }

        return "redirect:/admin/tags";
    }

    @PostMapping("/tags/update/{id}")
    public String updates(@RequestParam("name") String name, @PathVariable("id") Long id, RedirectAttributes attributes){
        Tag byName = service.findByName(name);
        if (byName!=null){
            attributes.addFlashAttribute("message","插入失败，类型重复！！");
            return "redirect:/admin/tags";
        }
        int updateTag = service.updateTag(id, name);
        if (updateTag == 1){
            attributes.addFlashAttribute("message","更新成功");
        }else {
            attributes.addFlashAttribute("message","更新失败");
        }

        return "redirect:/admin/tags";
    }


}
