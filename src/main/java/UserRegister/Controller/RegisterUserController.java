package UserRegister.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import UserRegister.Repository.UserRepository;
import UserRegister.model.Student;
import UserRegister.model.StudentForm;

@Controller
@RequestMapping("/users/register")
public class RegisterUserController {
    @Autowired
    UserRepository userRepository;

    @ModelAttribute
    StudentForm setupForm(){
        return new StudentForm();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showPage(){
    	if(loginUserController.User!=null)
        	loginUserController.User=null;
        return "userRegister";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String register(@Validated StudentForm studentForm,BindingResult result){
        if(result.hasErrors()){
            return showPage();
        }
        Student student = new Student();
        Student checkId=userRepository.findOne(studentForm.getId());
        student.setId(studentForm.getId());
        student.setName(studentForm.getName());
        student.setPass(studentForm.getPass());
        if(checkId==null)
	        {
	        	userRepository.save(student);
	        	return "redirect:/users/login";
	        }
        return "redirect:/users/register";
    }
        
    }
