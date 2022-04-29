package com.nasiatech.internmanager.service;

import com.nasiatech.internmanager.exceptions.InternNotFoundException;
import com.nasiatech.internmanager.intern.Intern;
import com.nasiatech.internmanager.intern.InternRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InternService {
    @Autowired private InternRepository internRepository;

    public List<Intern> listAll(){
        return (List<Intern>) internRepository.findAll();
    }

    public void saveIntern(Intern intern){
        internRepository.save(intern);
    }

    public Intern get(Long id) throws InternNotFoundException {
        Optional<Intern> internById = internRepository.findById(id);
        if(internById.isPresent()){
            return internById.get();
        }
        else{
            throw new InternNotFoundException("Could not find Any Intern");
        }

    }

    public void delete(Long id) throws InternNotFoundException{
       internRepository.deleteById(id);

    }


}
