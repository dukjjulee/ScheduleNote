package org.example.schedulenote.Schedule.service;

import lombok.RequiredArgsConstructor;
import org.example.schedulenote.Schedule.dto.ScheduleResponse;
import org.example.schedulenote.Schedule.dto.ScheduleSaveRequest;
import org.example.schedulenote.Schedule.dto.ScheduleSaveResponse;
import org.example.schedulenote.Schedule.entity.Schedule;
import org.example.schedulenote.Schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    //일정 생성
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

    //일정 목록 조회
    @Transactional(readOnly = true)
    public List<ScheduleResponse> findSchedules(String author) {
        List<Schedule> schedules = scheduleRepository.findAll();
        List<ScheduleResponse> dtos = new ArrayList<>();

        if (author == null) {

            //전체 일정 반환
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
        //author 파라미터 있을 시 해당 작성자의 일정만 반환
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

    //일정 단일 조회
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

    //일정 수정
    @Transactional
    public ScheduleResponse updateSchedule(long scheduleId, ScheduleSaveRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("Schedule를 찾을 수 없습니다.")
        );

        //수정 요청시 비밀번호 검증
        //null-safe 비교 사용
        if (!ObjectUtils.nullSafeEquals(schedule.getPassword(), request.getPassword())) {
            throw new IllegalArgumentException("비밀번호 불일치");
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

    //일정 삭제
    @Transactional
    public void deleteSchedule(long scheduleId, String password) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("Schedule를 찾을 수 없습니다.")
        );

        //삭제 요청시 비밀번호 검증
        if (!ObjectUtils.nullSafeEquals(schedule.getPassword(), password)) {
            throw new IllegalArgumentException("비밀번호 불일치");
        }
        scheduleRepository.deleteById(scheduleId);
    }
}
