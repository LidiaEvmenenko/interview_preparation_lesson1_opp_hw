package ru.geekbrains.hw7;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/app")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public List<StudentDto> findAll() {
        return studentService.findAll();
    }
    @GetMapping("/{id}")
    public StudentDto findById(@PathVariable Long id) {
        return studentService.findById(id);
    }
    @PostMapping
    public void create(@RequestBody StudentDto dto) {
        studentService.create(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        studentService.delete(id);
    }
}
