package com.pablo.taskmanager.repository.kanbancard;


import com.pablo.taskmanager.model.kanbancard.KanbanCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IKanbanCardRepository extends JpaRepository<KanbanCard, Long> {
}
