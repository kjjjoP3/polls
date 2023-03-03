package com.example.polls.repository;

import com.example.polls.model.Poll;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {
    Optional<Poll> findById(Long pollId);

    Page<Poll> findByCreatedBy(Long userId, Pageable pageable);

    long countByCreatedBy(Long userId);

    List<Poll> findByIdIn(List<Long> pollIds);

    List<Poll> findByIdIn(List<Long> pollIds, Sort sort);

    @Modifying
    @Query("DELETE FROM Vote v WHERE v.choice.id IN (SELECT c.id FROM Choice c WHERE c.poll.id = :pollId)")
    void deleteVotesByPollId(@Param("pollId") Long pollId);

    @Modifying
    @Query("DELETE FROM Choice c WHERE c.poll.id = :pollId")
    void deleteChoicesByPollId(@Param("pollId") Long pollId);
}
