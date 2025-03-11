package com.pablo.taskmanager.repository.board;

import com.pablo.taskmanager.model.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBoardRepository extends JpaRepository<Board, Long> {
}
