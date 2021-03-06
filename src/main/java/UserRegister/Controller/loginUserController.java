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
import UserRegister.model.loginForm;

@Controller
@RequestMapping("/users/login")
public class loginUserController {
    @Autowired
    UserRepository userRepository;
    static Student User;
    @ModelAttribute
    loginForm setupForm(){
        return new loginForm();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showPage(){
    	if(User!=null)
        	User=null;
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String register(@Validated loginForm studentForm,BindingResult result){
    	if(result.hasErrors()){
            return showPage();
        }
        Student users = userRepository.findOne(studentForm.getId());
        String Pass=studentForm.getPass();
        if(users == null)
        return "redirect:/users/register";
        if(users.getPass().equals(Pass))
        {
        User=users;
        return "redirect:/users/toppage";
        }
        else
        return "redirect:/users/login";
    }

}
