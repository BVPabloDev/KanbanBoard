package com.pablo.taskmanager.service.boardcolumn;

import com.pablo.taskmanager.model.boardcolumn.BoardColumn;
import com.pablo.taskmanager.repository.boardcolumn.IBoardColumnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BoardColumnServiceImpl implements IBoardColumnService {

    @Autowired
    private IBoardColumnRepository iBoardColumnRepository;

    // TODO: Create board column method
    @Override
    public BoardColumn createBoardColumn(BoardColumn boardColumn) {
        if (boardColumn.getName() == null || boardColumn.getName().isEmpty()) {
            throw new IllegalArgumentException("Board column name cannot be empty");
        }
        try {
            return iBoardColumnRepository.save(boardColumn);
        } catch (Exception e) {
            throw new RuntimeException("Error creating board column: " + boardColumn.getName(), e);
        }
    }

    @Override
    public Optional<BoardColumn> updateBoardColumn(Long id, BoardColumn boardColumn) {
        if (boardColumn == null || boardColumn.getName() == null || boardColumn.getName().isEmpty()) {
            throw new IllegalArgumentException("ID and BoardColumn cannot be null");
        }
        if (!iBoardColumnRepository.existsById(id)) {
            return Optional.empty();
        }
        try {
            boardColumn.setId(id);
            return Optional.of(iBoardColumnRepository.save(boardColumn));
        } catch (Exception e) {
            throw new RuntimeException("Error updating board column with ID: " + id, e);
        }
    }


    @Override
    public void deleteBoardColumn(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        if (!iBoardColumnRepository.existsById(id)) {
            throw new IllegalArgumentException("Board column with ID " + id + " not found");
        }
            try {
                iBoardColumnRepository.deleteById(id);
            } catch (Exception e) {
                throw new RuntimeException("Error deleting board column with id: " + id,e);
            }
    }
}
