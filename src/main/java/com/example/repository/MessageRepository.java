package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.Message;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findByPostedBy(Integer postedBy);
    
    @Modifying
    @Query("DELETE FROM Message WHERE messageId = :messageId")
    int deleteByMessageId(Integer messageId);
    
    @Modifying
    @Query("UPDATE Message SET messageText = :newText WHERE messageId = :messageId")
    int updateMessageText(Integer messageId, String newText);
}
