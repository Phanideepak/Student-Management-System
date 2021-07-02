package com.example.sms.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.sms.entities.Student;
import com.example.sms.services.StudentService;

@Controller
public class StudentController {

	@Autowired
	StudentService studentService;
	
	
	@GetMapping("/students")
	public String listStudents(Model model)
	{
		model.addAttribute("students",studentService.getAllStudents());
		// go to students.html page
		return "students";
	}
	
	// creating a studentForm page for adding new student
	@GetMapping("/students/new")
	public String createStudentForm(Model model) throws ParseException
	{
		
		Student student=new Student(); //To hold form Data.
		
		model.addAttribute("student",student);
		
		// go to create_student.html page
		return "create_student";
	}
	
	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student") Student student)
	{
	    System.out.println(student.getBirthdate());
		studentService.addNewStudent(student);
		// redirect to students.html page
		return "redirect:/students";
	}
	
	// create update student page..
	@GetMapping("/students/edit/{id}")
	public String createEditStudentPage(@PathVariable long id, Model model)
	{
		model.addAttribute("student", studentService.getStudentById(id));
		return "edit_student"; 
		
	}
	
	//Update the student details.
	@PostMapping("/students/{id}")
	public String updateStudentDetails(@PathVariable long id,@ModelAttribute("student") Student student)
	{
		Student existingStudent=studentService.getStudentById(id);
		existingStudent.setId(id);
		existingStudent.setEmail(student.getEmail());
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setBirthdate(student.getBirthdate());
		
		studentService.updateStudent(existingStudent);
		return "redirect:/students";
	}
	
	//Delete student by Id
	@GetMapping("/students/{id}")
	public String deleteStudent(@PathVariable long id)
	{
		studentService.deleteStudentById(id);
		return "redirect:/students";
	}
	
	
	
	// It resolves the String to Date conversion in thyme-leaf
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	
}
