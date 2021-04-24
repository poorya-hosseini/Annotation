package com.optiscan.endpoint;

import com.optiscan.entity.Person;
import com.optiscan.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class Registration extends WebMvcConfigurerAdapter {

    @Autowired
    private PersonService service;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/confirm").setViewName("confirm");
        registry.addViewController("/registration").setViewName("registration");
    }

    @GetMapping("/")
    public String registration(Model model) {
        model.addAttribute("person", new Person());
        return "registration";
    }

    @PostMapping("/")
    public ModelAndView save(@Valid @ModelAttribute Person person, BindingResult bindingResult, ModelAndView mav, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            mav.setViewName("registration");
            return mav;
        }
        service.save(person);
        redirectAttributes.addFlashAttribute("saved", person);
        mav.setViewName("redirect:/confirm");
        return mav;
    }
}
