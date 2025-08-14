package org.example.schedulenote.Schedule.repository;

import org.example.schedulenote.Schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
