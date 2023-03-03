package com.example.polls.repository;

import com.example.polls.model.Choice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ChoiceRepository extends JpaRepository<Choice, Long> {
    List<Choice> findByPollId(Long pollId);

    @Modifying
    @Transactional
    void deleteChoicesByPollId(Long pollId);
}
