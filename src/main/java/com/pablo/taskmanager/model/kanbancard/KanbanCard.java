package com.pablo.taskmanager.model.kanbancard;

import com.pablo.taskmanager.model.boardcolumn.BoardColumn;
import com.pablo.taskmanager.model.user.User;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"user", "boardColumn"})
public class KanbanCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "board_column_id")
    private BoardColumn boardColumn;
    private Integer orderIndex;

    public KanbanCard(Long id) {
        this.id = id;
    }
}
