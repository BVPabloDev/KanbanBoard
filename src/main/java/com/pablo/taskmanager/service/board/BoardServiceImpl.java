package com.pablo.taskmanager.service.board;

import com.pablo.taskmanager.model.board.Board;
import com.pablo.taskmanager.repository.board.IBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

@Service
public class BoardServiceImpl implements IBoardService {

    @Autowired
    private IBoardRepository iBoardRepository;

    @Override
    public Board createBoard(Board board) {
        if (board.getName() == null || board.getName().isEmpty()) {
            throw new IllegalArgumentException("Board name cannot be empty");
        }
        try {
            return iBoardRepository.save(board);
        } catch (Exception e) {
            throw new RuntimeException("Error creating board: " + board.getName(), e);
        }
    }

    @Override
    public Optional<Board> getBoardById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        try {
            return iBoardRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error found searching board with ID " + id, e) {
            };
        }
    }

    @Override
    public Page<Board> getAllBoards(Pageable pageable) {
        return iBoardRepository.findAll(pageable);
    }


    @Override
    public List<Board> getAllBoards() {
        return iBoardRepository.findAll();

    }

    @Override
    public Optional<Board> updateBoard(Board board) {
        if (!iBoardRepository.existsById(board.getId())) {
            return Optional.empty();
        }
        try {
            return Optional.of(iBoardRepository.save(board));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteBoard(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        try {
            if (!iBoardRepository.existsById(id)) {
                throw new IllegalArgumentException("Board with ID " + id + " not found");
            }
            iBoardRepository.deleteById(id);
        } catch (DataAccessException e) {
            throw new RuntimeException("Database error while deleting board with ID " + id, e);
        }
    }
}
