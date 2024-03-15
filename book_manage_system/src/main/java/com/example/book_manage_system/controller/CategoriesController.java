package com.example.book_manage_system.controller;

import com.example.book_manage_system.common.DataNotFoundException;
import com.example.book_manage_system.common.FlashData;
import com.example.book_manage_system.entity.Book;
import com.example.book_manage_system.entity.Category;
import com.example.book_manage_system.service.BookService;
import com.example.book_manage_system.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/categories")
public class CategoriesController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    BookService bookService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "categories/list";
    }

    @GetMapping("/create")
    public String add(@ModelAttribute Category category, Model model) {
        model.addAttribute("isNew", true);
        return "categories/form";
    }

    @PostMapping("/process")
    public String process(@Validated @ModelAttribute Category category, BindingResult result, Model model, RedirectAttributes ra) {
        FlashData flash;
        try {
            if (result.hasErrors()) {
                model.addAttribute("isNew", category.getId() == null);
                return "categories/form";
            }
            String type = (category.getId() == null) ? "追加" : "編集";
            categoryService.save(category);
            flash = new FlashData().success("カテゴリの" + type + "が完了しました");
        } catch (Exception e) {
            flash = new FlashData().danger("処理中にエラーが発生しました");
        }
        ra.addFlashAttribute("flash", flash);
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("isNew", false);
        try {
            model.addAttribute("category", categoryService.findById(id));
        } catch (DataNotFoundException e) {
            return "redirect:/categories";
        }
        return "categories/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes ra) {
        FlashData flash;
        try {
            List<Book> books = bookService.findByAuthorId(id);
            if (books.isEmpty()) {
                categoryService.findById(id);
                categoryService.delete(id);
                flash = new FlashData().success("カテゴリの削除が完了しました");
            } else {
                flash = new FlashData().danger("書籍に登録されているカテゴリは削除できません");
            }
        } catch (DataNotFoundException e) {
            flash = new FlashData().danger("該当データがありません");
        } catch (Exception e) {
            flash = new FlashData().danger("処理中にエラーが発生しました");
        }
        ra.addFlashAttribute("flash", flash);
        return "redirect:/categories";
    }
}
