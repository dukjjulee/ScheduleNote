package org.example.schedulenote.Schedule.controller;

import lombok.RequiredArgsConstructor;
import org.example.schedulenote.Schedule.dto.ScheduleResponse;
import org.example.schedulenote.Schedule.dto.ScheduleSaveRequest;
import org.example.schedulenote.Schedule.dto.ScheduleSaveResponse;
import org.example.schedulenote.Schedule.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ResponseEntity<ScheduleSaveResponse> saveSchedules(
            @RequestBody ScheduleSaveRequest request
    ) {
        return ResponseEntity.ok(scheduleService.save(request));
    }

    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleResponse>> getSchedules(
            @RequestParam String author
    ) {
        return ResponseEntity.ok(scheduleService.findSchedules(author));
    }

    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<ScheduleResponse> getSchedules(
            @PathVariable long scheduleId
    ) {
        return ResponseEntity.ok(scheduleService.findSchedule(scheduleId));
    }


    @PutMapping("/schedules/{scheduleId}")
    public ResponseEntity<ScheduleResponse> updateSchedule(
            @PathVariable long scheduleId,
            @RequestBody ScheduleSaveRequest request
    ) {
        return ResponseEntity.ok(scheduleService.updateSchedule(scheduleId, request));
    }

    @DeleteMapping("/schedules/{scheduleId}")
    public void deleteSchedule(
            @PathVariable long scheduleId,
            @RequestParam String password
    ) {
      scheduleService.deleteSchedule(scheduleId, password);
    }
}
