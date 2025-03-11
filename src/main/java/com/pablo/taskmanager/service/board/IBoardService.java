package com.pablo.taskmanager.service.board;

import com.pablo.taskmanager.model.board.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface IBoardService {

    Board createBoard(Board board);
    Optional<Board> getBoardById(Long id);
    Page<Board> getAllBoards(Pageable pageable);
    List<Board> getAllBoards();
    Optional <Board> updateBoard(Board board);
    void deleteBoard(Long id);

}
