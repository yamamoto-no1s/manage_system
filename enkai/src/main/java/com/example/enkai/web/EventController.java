package com.example.enkai.web;

import com.example.enkai.entity.Event;
import com.example.enkai.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class EventController {
    @Autowired
    BaseService<Event> eventService;

    /*
     * 一覧表示
     */
    @GetMapping(path = {"", "/"})
    public String list(Model model) {
        // 全件取得
        List<Event> events = eventService.findAll();
        model.addAttribute("events", events);
        return "events/list";
    }
}
