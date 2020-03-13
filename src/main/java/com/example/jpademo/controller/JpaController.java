package com.example.jpademo.controller;

import com.example.jpademo.entity.Member;
import com.example.jpademo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JpaController {

    @Autowired
    MemberRepository repository;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String getMainPage() {
        return "This is the JPA test page";
    }

    @RequestMapping(value="/findall", method = RequestMethod.GET)
    public String FindAll() {
        String result = "";
        for(Member member : repository.findAll()) {
            result += member.toString() + "</br>";
        }
        return result;
    }
    @RequestMapping(value="/findall", method = RequestMethod.GET)
    public String FindById(@RequestParam("id") long id) {
        String result = "";
        for(Member member : repository.findById(id)) {
            result += member.toString() + "</br>";
        }
        return result;
    }
    @RequestMapping(value="/findbylastname", method = RequestMethod.GET)
    public String FindByLastName(@RequestParam("lastname") String lastName) {
        String result = "";
        for(Member member : repository.findByLastName(lastName)) {
            result += member.toString() + "</br>";
        }
        return result;
    }

    @RequestMapping(value="/add", method = RequestMethod.GET)
    public String addMember(@RequestParam("firstname") String firstName, @RequestParam("lastname") String lastName, @RequestParam("email") String email) {

        repository.save(new Member (firstName, lastName, email));

        return "Addition done!";
    }

    @RequestMapping(value="/update", method = RequestMethod.GET)
    public String updateMember(@RequestParam("id") long id, @RequestParam("firstname") String firstName, @RequestParam("lastname") String lastName, @RequestParam("email") String email) {

        Member member = repository.findById(id).get(0);

        member.setFirstName(firstName);
        member.setLastName(lastName);
        member.setEmail(email);
        repository.save(member);

        return "Update done!";
    }

    /*@RequestMapping(value="/delete", method = RequestMethod.GET)
    public String deleteMember(@RequestParam("id") long id) {

        repository.deleteById(id);

        return "Delete done!";
    }*/
}
