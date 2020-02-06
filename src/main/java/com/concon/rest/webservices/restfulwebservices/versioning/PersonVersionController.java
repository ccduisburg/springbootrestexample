package com.concon.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersionController {
      @GetMapping("v1/person")
    public Person person(){
    return new Person("Bob Charlie");
}

  @GetMapping("v2/person")
    public PersonV2 personv2(){
          return new PersonV2(new Name("Bob","Charlie"));
  }


    @GetMapping(value="/person/param",params="version=1")
    public Person paramV1(){
        return new Person("Bob Charlie");
    }
//localhost:8080/person/param?version=2
    @GetMapping(value="/person/param",params="version=2")
    public PersonV2 paramV2(){
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    @GetMapping(value="/person/header",headers="X_API_VERSION=1")
    public Person headerV1(){
        return new Person("Bob Charlie");
    }
    //localhost:8080/person/param?version=2
    @GetMapping(value="/person/header",headers="X_API_VERSION=2")
    public PersonV2 headerV2(){
        return new PersonV2(new Name("Bob", "Charlie"));
    }
}
