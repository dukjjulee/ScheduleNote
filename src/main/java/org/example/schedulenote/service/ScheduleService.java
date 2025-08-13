package org.example.schedulenote.service;

import lombok.RequiredArgsConstructor;
import org.example.schedulenote.dto.ScheduleResponse;
import org.example.schedulenote.dto.ScheduleSaveRequest;
import org.example.schedulenote.dto.ScheduleSaveResponse;
import org.example.schedulenote.entity.Schedule;
import org.example.schedulenote.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleSaveResponse save(ScheduleSaveRequest request) {
        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getContent(),
                request.getAuthor(),
                request.getPassword()
        );
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new ScheduleSaveResponse(
                savedSchedule.getId(),
                savedSchedule.getTitle(),
                savedSchedule.getContent(),
                savedSchedule.getAuthor(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<ScheduleResponse> findSchedules(String author) {
        List<Schedule> schedules = scheduleRepository.findAll();
        List<ScheduleResponse> dtos = new ArrayList<>();

        if (author == null) {
            for (Schedule schedule : schedules) {
                ScheduleResponse scheduleResponse = new ScheduleResponse(
                        schedule.getId(),
                        schedule.getTitle(),
                        schedule.getContent(),
                        schedule.getAuthor(),
                        schedule.getCreatedAt(),
                        schedule.getModifiedAt()
                );
                dtos.add(scheduleResponse);
            }
            return dtos;
        }
        for (Schedule schedule : schedules) {
            if(author.equals(schedule.getAuthor())) {
                ScheduleResponse scheduleResponse = new ScheduleResponse(
                        schedule.getId(),
                        schedule.getTitle(),
                        schedule.getContent(),
                        schedule.getAuthor(),
                        schedule.getCreatedAt(),
                        schedule.getModifiedAt()
                );
                dtos.add(scheduleResponse);
            }
        }
        return dtos;
    }
    @Transactional(readOnly = true)
    public ScheduleResponse findSchedule(long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () ->new IllegalArgumentException("Schedule를 찾을 수 없습니다.")
        );
        return new ScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getAuthor(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    @Transactional
    public ScheduleResponse updateSchedule(long scheduleId, ScheduleSaveRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("Schedule를 찾을 수 없습니다.")
        );
        if (ObjectUtils.nullSafeEquals(schedule.getPassword(), request.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }
        schedule.updateTitleAndAuThor(request.getTitle(), request.getAuthor());
        return new ScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getAuthor(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    @Transactional
    public void deletSchedule(long scheduleId, String password) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("Schedule를 찾을 수 없습니다.")
        );
        if (!ObjectUtils.nullSafeEquals(schedule.getPassword(), password)) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }
        scheduleRepository.deleteById(scheduleId);
    }
}
