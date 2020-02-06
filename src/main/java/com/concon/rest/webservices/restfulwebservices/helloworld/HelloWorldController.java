package com.concon.rest.webservices.restfulwebservices.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class HelloWorldController {


    @Autowired
    private MessageSource messageSource;

  //  @RequestMapping(method= RequestMethod.GET, path="/hello-world")- it provides us same result
    @GetMapping(path="/hello-world")
    public String helloWorld(){
    return "Hello World";
}
//hello-world-bean
    @GetMapping(path="/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello World Bean, %s ",name));
    }

    @GetMapping(path="/hello-world-internationalized")
    public String helloWorldInternationalized(){
        return messageSource.getMessage("good.morning.message",null, LocaleContextHolder.getLocale());
    }

}
