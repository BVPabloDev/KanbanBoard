package com.pablo.taskmanager.service.kanbancard;

import com.pablo.taskmanager.model.board.Board;
import com.pablo.taskmanager.model.kanbancard.KanbanCard;
import com.pablo.taskmanager.repository.kanbancard.IKanbanCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;


@Service
public class KanbanCardServiceImpl implements IKanbanCardService {

    @Autowired
    private IKanbanCardRepository iKanbanCardRepository;

    private static final Logger logger = LoggerFactory.getLogger(KanbanCardServiceImpl.class);

    @Override
    public Optional<KanbanCard> getKanbanCardById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        if (!iKanbanCardRepository.existsById(id)) {
            throw new IllegalArgumentException("kanbanCard " + id + " not found");
        }
        try {
            KanbanCard kanbanCard = iKanbanCardRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("KanbanCard with ID" + id + "not found"));
            return Optional.of(kanbanCard);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public KanbanCard createKanbanCard(KanbanCard kanbanCard) {
        if (kanbanCard == null) {
            throw new IllegalArgumentException("kanbanCard cannot be null");
        }
        try {
            return iKanbanCardRepository.save(kanbanCard);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while creating KanbanCard: invalid data provided");
        }
    }

    @Override
    public List<KanbanCard> getAllKanbanCards() {
        try {
            return iKanbanCardRepository.findAll();
        } catch (Exception e) {
            logger.warn("Falling back to an empty list due to error: {}", e.getMessage());
            throw new RuntimeException("Error while getting all KanbanCards: invalid data provided");
        }
    }


    @Override
    public Optional<KanbanCard> updateBoard(KanbanCard kanbanCard) {
        if (kanbanCard == null) {
            throw new IllegalArgumentException("board cannot be null");
        }
        try{
            return  Optional.of(iKanbanCardRepository.save(kanbanCard));
        } catch (Exception e) {
            logger.error("Error while updating KanbanCard: {}", e.getMessage(), e);
            throw new RuntimeException("Error while creating KanbanCard: invalid data provided");
        }
    }

    @Override
    public void deleteKanbanCardById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        if (!iKanbanCardRepository.existsById(id)) {
            throw new IllegalArgumentException("Kanban card with ID " + id + " not found");
        }
        try {
            iKanbanCardRepository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("Kanban card with ID " + id + " was not deleted");
        }

    }
}
