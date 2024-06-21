package com.abc.event_registration_system.repository;


import com.abc.event_registration_system.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("SELECT e from Event e WHERE e.username = ?1 ")
    List<Event> findAllEventsWithUsername(String username);

}
