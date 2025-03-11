package com.pablo.taskmanager.model.boardcolumn;

import com.pablo.taskmanager.model.board.Board;
import com.pablo.taskmanager.model.kanbancard.KanbanCard;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "kanbanCards")
public class BoardColumn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToMany(mappedBy = "boardColumn", cascade = CascadeType.ALL)
    private List<KanbanCard> kanbanCards;

    public BoardColumn(Long id) {
        this.id = id;
    }
}
