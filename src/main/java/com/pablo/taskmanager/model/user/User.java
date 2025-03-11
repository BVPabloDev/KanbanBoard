package com.pablo.taskmanager.model.user;

import com.pablo.taskmanager.model.kanbancard.KanbanCard;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"tasks", "password"})
@Table(name = "\"user\"", schema = "kanban")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<KanbanCard> tasks;

    public User(Long id) {
        this.id = id;
    }

    public User(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

}
