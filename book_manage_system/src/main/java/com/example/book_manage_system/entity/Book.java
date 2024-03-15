package com.example.book_manage_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "books")
public class Book extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "タイトルが未入力です")
    @Size(max = 255, message = "タイトルは255文字以内で入力してください")
    private String title;

    @NotBlank(message = "価格が未入力です")
    @Min(value = 0, message = "価格は0以上の整数を入力してください")
    private String price;

    @NotNull(message = "著者が未入力です")
    @ManyToOne
    private Author author;

    @NotNull(message = "カテゴリが未入力です")
    @ManyToOne
    private Category category;

}
