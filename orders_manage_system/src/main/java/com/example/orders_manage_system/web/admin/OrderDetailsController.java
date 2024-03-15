package com.example.orders_manage_system.web.admin;

import com.example.orders_manage_system.common.FlashData;
import com.example.orders_manage_system.entity.Order;
import com.example.orders_manage_system.entity.OrderDetail;
import com.example.orders_manage_system.service.OrderDetailService;
import com.example.orders_manage_system.service.OrderService;
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

@Controller
@RequestMapping("/admin/orderdetails")
public class OrderDetailsController {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderDetailService orderDetailService;

    /*
     * 新規作成画面表示
     */
    @GetMapping(value = "/create/{id}")
    public String form(@PathVariable Integer id, OrderDetail orderDetail, Model model, RedirectAttributes ra) {
        try {
            // 存在確認
            Order order = orderService.findById(id);
            model.addAttribute("order", order);
            model.addAttribute("orderDetail", orderDetail);
        } catch (Exception e) {
            FlashData flash = new FlashData().danger("該当データがありません");
            ra.addFlashAttribute("flash", flash);
            return "redirect:/admin/orders";
        }
        return "admin/orderdetails/create";
    }

    /*
     * 新規登録
     */
    @PostMapping(value = "/create/{id}")
    public String register(@PathVariable Integer id, @Valid OrderDetail orderDetail, BindingResult result, Model model, RedirectAttributes ra) {
        FlashData flash;
        try {
            if (result.hasErrors()) {
                return "admin/orderdetails/create/" + id;
            }
            // 新規登録
            // TODO::orderはどう関連付ける？
            Order order = orderService.findById(id);
            OrderDetail input = new OrderDetail(
                    orderDetail.getId(),
                    orderDetail.getUnit(),
                    order,
                    orderDetail.getProduct());
            orderDetailService.save(input);
            flash = new FlashData().success("新規作成しました");
        } catch (Exception e) {
            flash = new FlashData().danger("処理中にエラーが発生しました");
        }
        ra.addFlashAttribute("flash", flash);
        return "redirect:/admin/orders/view/" + id;
    }

    /*
     * 編集画面表示
     */
    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Integer id, Model model, RedirectAttributes ra) {
        try {
            // 存在確認
            OrderDetail orderDetail = orderDetailService.findById(id);
            model.addAttribute("orderDetail", orderDetail);
        } catch (Exception e) {
            FlashData flash = new FlashData().danger("該当データがありません");
            ra.addFlashAttribute("flash", flash);
            return "redirect:/admin/orders/view/" + id;
        }
        return "admin/orderdetails/edit";
    }

    /*
     * 更新
     */
    @PostMapping(value = "/edit/{id}")
    public String update(@PathVariable Integer id, @Valid OrderDetail orderDetail, BindingResult result, Model model, RedirectAttributes ra) {
        FlashData flash;
        try {
            if (result.hasErrors()) {
                return "admin/orderdetails/edit";
            }
            orderDetailService.findById(id);
            // 更新
            orderDetailService.save(orderDetail);
            flash = new FlashData().success("更新しました");
        } catch (Exception e) {
            flash = new FlashData().danger("該当データがありません");
        }
        ra.addFlashAttribute("flash", flash);
        return "redirect:/admin/orders/view/" + id;
    }
}
