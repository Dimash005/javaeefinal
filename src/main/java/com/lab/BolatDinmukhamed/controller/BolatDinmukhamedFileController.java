package com.lab.BolatDinmukhamed.controller;

import com.lab.BolatDinmukhamed.entity.BolatDinmukhamedCourse;
import com.lab.BolatDinmukhamed.service.BolatDinmukhamedCourseService;
import com.lab.BolatDinmukhamed.service.BolatDinmukhamedFileStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class BolatDinmukhamedFileController {

    private final BolatDinmukhamedFileStorageService fileStorageService;
    private final BolatDinmukhamedCourseService courseService;

    @PostMapping("/upload/syllabus/{courseId}")
    public ResponseEntity<Map<String, String>> uploadSyllabus(
            @PathVariable Long courseId,
            @RequestParam("file") MultipartFile file) {

        log.info("Uploading syllabus for course {}", courseId);
        String fileName = fileStorageService.storeFile(file);

        BolatDinmukhamedCourse course = courseService.findCourseById(courseId);
        course.setSyllabusUrl(fileName);

        return ResponseEntity.ok(Map.of("fileName", fileName, "message", "Syllabus uploaded successfully"));
    }

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("file") MultipartFile file) {
        log.info("Uploading file: {}", file.getOriginalFilename());
        String fileName = fileStorageService.storeFile(file);
        return ResponseEntity.ok(Map.of("fileName", fileName, "message", "File uploaded successfully"));
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        log.info("Downloading file: {}", fileName);
        Resource resource = fileStorageService.loadFileAsResource(fileName);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}