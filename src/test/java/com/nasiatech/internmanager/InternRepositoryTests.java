package com.nasiatech.internmanager;

import com.nasiatech.internmanager.intern.Intern;
import com.nasiatech.internmanager.intern.InternRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Locale;
import java.util.Optional;

@SpringBootTest
public class InternRepositoryTests {

    private final InternRepository internRepository;
    @Autowired
    public InternRepositoryTests(InternRepository internRepository) {
        this.internRepository = internRepository;
    }

    @Test
    public void testAddIntern(){
        Intern intern = new Intern();

        intern.setEmail("ndrewg@gmail.com");
        intern.setFirstName("Elrou");
        intern.setLastName("Man");
        intern.setPassword("drewbot");
        intern.setPhoneNumber(672469338);

        Intern saveIntern = internRepository.save(intern);

        Assertions.assertThat(saveIntern).isNotNull();
        Assertions.assertThat(saveIntern.getId()).isGreaterThan(0);
    }
    @Test
    public void testReadAll(){
        Iterable<Intern>  interns = internRepository.findAll();
        Assertions.assertThat(interns).hasSizeGreaterThan(0);

        for (Intern user: interns) {
            System.out.println(user.getEmail().toUpperCase(Locale.ROOT));
        }

    }

    @Test
    public void updateIntern(){
        Long Id = 1L;
        Optional<Intern> optionalIntern = internRepository.findById(Id);
        Intern intern = optionalIntern.get();
        intern.setPassword("3223245234");
        internRepository.save(intern);

        Intern updatedIntern = internRepository.findById(Id).get();
        Assertions.assertThat(updatedIntern.getPassword()).isEqualTo("3223245234");
    }

    @Test
    public void getInternByID(){
        Long Id = 1L;
        Optional<Intern> intern = internRepository.findById(Id);
        Intern i = intern.get();
        Assertions.assertThat(i.getFirstName()).isEqualTo("Nyonh");
    }

    @Test
    public void testDelete(){
        Long Id = 6L;
        internRepository.deleteById(Id);
        Assertions.assertThat(internRepository.findById(Id)).isNull();


    }
}
