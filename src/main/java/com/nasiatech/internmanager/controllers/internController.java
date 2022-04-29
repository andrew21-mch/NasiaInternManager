package com.nasiatech.internmanager.controllers;

import com.nasiatech.internmanager.intern.Intern;
import com.nasiatech.internmanager.exceptions.InternNotFoundException;
import com.nasiatech.internmanager.service.InternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.unbescape.xml.XmlEscape;

import java.util.List;
import java.util.Optional;

@Controller
public class internController {
    InternService internService;

    @Autowired
    public internController(InternService internService) {
        this.internService = internService;
    }

    @GetMapping("/interns")
    public String showInternList(Model model) {
        List<Intern> listInterns = internService.listAll();
        model.addAttribute("listInterns", listInterns);
        return "interns";
    }
    @GetMapping("/interns/new")
    public String addInterns(Model model){
        model.addAttribute("intern", new Intern());
        model.addAttribute("pageTitle","Add New Intern");
        return "addInterns";
    }

    @PostMapping("/interns/save")
    public String saveIntern(Intern intern, RedirectAttributes ra){
        internService.saveIntern(intern);
        ra.addFlashAttribute("message", "Intern successfully saved");
        return "redirect:/interns";
    }

    @GetMapping("/interns/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
        try {
            Intern intern = internService.get(id);
            model.addAttribute("intern", intern);
            model.addAttribute("pageTitle","Edit (Intern ID:" + id +") ");
            return "addInterns";
        }
        catch (InternNotFoundException e){
            e.printStackTrace();
            ra.addFlashAttribute( "message", "Intern with id" + id + "Not found in system");
        }
        return "redirect:/interns";
    }

    @GetMapping("/interns/delete/{id}")
    public String deleteIntern(@PathVariable("id") Long id, RedirectAttributes ra) {
        try {
            internService.delete(id);
            ra.addFlashAttribute("message", "The user with ID: " + id + "has been deleted");
        }
        catch (InternNotFoundException e){
            ra.addFlashAttribute("message", e.getMessage());
            ra.addFlashAttribute( "message", "Intern with id" + id + "Not found in system");
        }
        return "redirect:/interns";
    }



}
