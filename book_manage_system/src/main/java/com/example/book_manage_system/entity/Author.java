package com.example.book_manage_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "authors")
public class Author extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "著者が未入力です")
    @Size(max = 255, message = "著者は255文字以内で入力してください")
    private String name;

    @OneToMany(mappedBy = "author")
    private List<Book> books;
}
