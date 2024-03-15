package com.example.enkai.web.admin;

import com.example.enkai.common.FlashData;
import com.example.enkai.entity.Event;
import com.example.enkai.service.BaseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/events/mylist")
public class MyEventController {
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
        return "admin/events/mylist/list";
    }

    /*
     * 新規作成画面表示
     */
    @GetMapping(value = "/create")
    public String form(Event event, Model model) {
        model.addAttribute("event", event);
        return "admin/events/mylist/create";
    }

    /*
     * 新規登録
     */
    @PostMapping(value = "/create")
    public String register(@Valid Event event, BindingResult result, Model model, RedirectAttributes ra) {
        FlashData flash;
        try {
            if (result.hasErrors()) {
                return "admin/events/mylist/create";
            }
            // 新規登録
            eventService.save(event);
            flash = new FlashData().success("新規作成しました");
        } catch (Exception e) {
            flash = new FlashData().danger("処理中にエラーが発生しました");
        }
        ra.addFlashAttribute("flash", flash);
        return "redirect:/admin/events/mylist";
    }

    /*
     * 編集画面表示
     */
    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Integer id, Model model, RedirectAttributes ra) {
        try {
            // 存在確認
            Event event = eventService.findById(id);
            model.addAttribute("event", event);
        } catch (Exception e) {
            FlashData flash = new FlashData().danger("該当データがありません");
            ra.addFlashAttribute("flash", flash);
            return "redirect:/admin/events/mylist";
        }
        return "admin/events/mylist/edit";
    }

    /*
     * 更新
     */
    @PostMapping(value = "/edit/{id}")
    public String update(@PathVariable Integer id, @Valid Event event, BindingResult result, Model model, RedirectAttributes ra) {
        FlashData flash;
        try {
            if (result.hasErrors()) {
                return "admin/events/mylist/edit";
            }
            eventService.findById(id);
            // 更新
            eventService.save(event);
            flash = new FlashData().success("更新しました");
        } catch (Exception e) {
            flash = new FlashData().danger("該当データがありません");
        }
        ra.addFlashAttribute("flash", flash);
        return "redirect:/admin/events/mylist";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes ra) {
        FlashData flash;
        try {
            eventService.deleteById(id);
            flash = new FlashData().success("カテゴリの削除が完了しました");
        } catch (Exception e) {
            flash = new FlashData().danger("処理中にエラーが発生しました");
        }
        ra.addFlashAttribute("flash", flash);
        return "redirect:/admin/events/mylist";
    }
}
