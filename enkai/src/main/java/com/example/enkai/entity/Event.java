package com.example.enkai.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "events")
public class Event extends AbstractEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255, nullable = false)
    @NotEmpty(message = "イベント名は必須入力です")
    private String name;

    @Column(length = 255, nullable = false)
    @NotEmpty(message = "イベント詳細は必須入力です")
    private String detail;

    @Column
    @NotNull(message = "最大参加者数は必須入力です")
    private Integer maxParticipant;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @NotNull(message = "カテゴリは必須入力です")
    private Category category;

    @OneToMany
    @JoinColumn(name = "user_id")
    @NotNull(message = "ユーザIDは必須入力です")
    private List<EventUser> eventUser;
}
