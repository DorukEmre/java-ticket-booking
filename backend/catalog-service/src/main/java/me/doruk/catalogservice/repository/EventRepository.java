package me.doruk.catalogservice.repository;

import me.doruk.catalogservice.entity.Event;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.persistence.LockModeType;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

  // Locked select to prevent concurrent oversell
  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @Query("select e from Event e where e.id in :ids")
  List<Event> findAllByIdForUpdate(@Param("ids") List<Long> ids);
}
