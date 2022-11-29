package ru.geekbrains.hw7;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public List<StudentDto> findAll() {
        return (List<StudentDto>) studentRepository.findAll().stream().map(StudentDto::new);
    }
    public StudentDto findById(Long id) {
        Optional<Student> studentOpt = studentRepository.findById(id);
        if (!studentOpt.isEmpty()) {
            StudentDto student = studentMapper.mapToDto(studentOpt.get());
            return student;
        }
        return null;
    }
    public void create(StudentDto dto){
        Student student = new Student(1L, dto.getName(), dto.getAge());
        studentRepository.save(student);
    }
    @Transactional
    public void update(StudentDto dto){
        Optional<Student> studentOpt = studentRepository.findById(dto.getId());
        if (!studentOpt.isEmpty()) {
            Student student = new Student();
            student.setId(dto.getId());
            student.setName(dto.getName());
            student.setAge(dto.getAge());
            studentRepository.save(student);
        }
    }
    public void delete(Long id){
        studentRepository.deleteById(id);
    }
}
