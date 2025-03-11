package com.pablo.taskmanager.service.kanbancard;

import com.pablo.taskmanager.model.board.Board;
import com.pablo.taskmanager.model.kanbancard.KanbanCard;

import java.util.List;
import java.util.Optional;

public interface IKanbanCardService {
    Optional<KanbanCard> getKanbanCardById(Long id);
    KanbanCard createKanbanCard(KanbanCard kanbanCard);
    List<KanbanCard> getAllKanbanCards();
    Optional<KanbanCard> updateBoard(KanbanCard kanbanCard);
    void deleteKanbanCardById(Long id);
}
