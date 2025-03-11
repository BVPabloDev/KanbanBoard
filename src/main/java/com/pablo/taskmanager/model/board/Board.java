package com.pablo.taskmanager.model.board;

import com.pablo.taskmanager.model.boardcolumn.BoardColumn;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "boardColumns")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List <BoardColumn> boardColumns;

    public Board(Long id) {
        this.id = id;
    }

}
