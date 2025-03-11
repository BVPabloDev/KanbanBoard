package com.pablo.taskmanager.service.boardcolumn;

import com.pablo.taskmanager.model.boardcolumn.BoardColumn;

import java.util.Optional;

public interface IBoardColumnService {

    BoardColumn createBoardColumn(BoardColumn boardColumn);
    Optional<BoardColumn> updateBoardColumn(Long id, BoardColumn boardColumn);
    void deleteBoardColumn(Long id);

}
