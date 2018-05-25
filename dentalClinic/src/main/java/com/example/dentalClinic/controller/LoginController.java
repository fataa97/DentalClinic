package com.example.dentalClinic.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.dentalClinic.model.DentistProfile;
import com.example.dentalClinic.model.User;
import com.example.dentalClinic.service.DentistService;
import com.example.dentalClinic.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
    @Autowired
    private DentistService dentistService;

	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult
					.rejectValue("email", "error.user",
							"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration");
			
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/user/home", method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("user", user);
		modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("adminMessage","Welcome To Dental Clinic");
		modelAndView.setViewName("user/home");
		return modelAndView;
	}
	
    @RequestMapping(value={"/dentists/new"}, method = RequestMethod.GET)
    public ModelAndView dentistRegister(){
        ModelAndView modelAndView = new ModelAndView();
        DentistProfile dentist = new DentistProfile();
        modelAndView.addObject("dentist", dentist);
        modelAndView.setViewName("dentist/registration");
        return modelAndView;
    }

    @RequestMapping(value = "/dentists/new", method = RequestMethod.POST)
    public ModelAndView createDentist(@Valid DentistProfile dentist, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("dentist/registration");
        } else {
            dentistService.saveDentist(dentist, user);
            modelAndView.addObject("successMessage", "Dentist has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.addObject("dentist", new DentistProfile());
            modelAndView.setViewName("dentist/registration");

        }
        return modelAndView;
    }
	

}
