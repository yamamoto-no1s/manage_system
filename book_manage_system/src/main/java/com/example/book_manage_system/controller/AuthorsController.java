package com.example.book_manage_system.controller;

import com.example.book_manage_system.common.DataNotFoundException;
import com.example.book_manage_system.common.FlashData;
import com.example.book_manage_system.entity.Author;
import com.example.book_manage_system.entity.Book;
import com.example.book_manage_system.service.AuthorService;
import com.example.book_manage_system.service.BookService;
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
@RequestMapping("/authors")
public class AuthorsController {
    @Autowired
    AuthorService authorService;

    @Autowired
    BookService bookService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("authors", authorService.findAll());
        return "authors/list";
    }

    @GetMapping("/create")
    public String add(@ModelAttribute Author author, Model model) {
        model.addAttribute("isNew", true);
        return "authors/form";
    }

    @PostMapping("/process")
    public String process(@Validated @ModelAttribute Author author, BindingResult result, Model model, RedirectAttributes ra) {
        FlashData flash;
        try {
            if (result.hasErrors()) {
                model.addAttribute("isNew", author.getId() == null);
                return "authors/form";
            }
            String type = (author.getId() == null) ? "追加" : "編集";
            authorService.save(author);
            flash = new FlashData().success("著者の" + type + "が完了しました");
        } catch (Exception e) {
            flash = new FlashData().danger("処理中にエラーが発生しました");
        }
        ra.addFlashAttribute("flash", flash);
        return "redirect:/authors";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("isNew", false);
        try {
            model.addAttribute("author", authorService.findById(id));
        } catch (DataNotFoundException e) {
            return "redirect:/authors";
        }
        return "authors/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes ra) {
        FlashData flash;
        try {
            List<Book> books = bookService.findByCategoryId(id);
            if (books.isEmpty()) {
                authorService.findById(id);
                authorService.delete(id);
                flash = new FlashData().success("著者の削除が完了しました");
            } else {
                flash = new FlashData().danger("書籍に登録されている著者は削除できません");
            }
        } catch (DataNotFoundException e) {
            flash = new FlashData().danger("該当データがありません");
        } catch (Exception e) {
            flash = new FlashData().danger("処理中にエラーが発生しました");
        }
        ra.addFlashAttribute("flash", flash);
        return "redirect:/authors";
    }
}
