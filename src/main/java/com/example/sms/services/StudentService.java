package com.example.sms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sms.entities.Student;
import com.example.sms.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepo;
	
	public List<Student> getAllStudents()
	{
		return studentRepo.findAll();
	}
	public void addNewStudent(Student student)
	{
		// 2000 is an offset
		var count=studentRepo.count()+2000;
		student.setId(count);
		studentRepo.save(student);
	}
}
