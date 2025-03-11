package com.pablo.taskmanager.repository.boardcolumn;

import com.pablo.taskmanager.model.boardcolumn.BoardColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBoardColumnRepository extends JpaRepository<BoardColumn, Long> {
}
