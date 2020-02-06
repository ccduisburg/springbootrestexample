package com.concon.rest.webservices.restfulwebservices.user;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@ApiModel(description = "All details about the user.")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    @Size(min=2,message="Name should have atleast 2 characters")
    private String name;
    @Past
    private Date birthDate;

    @OneToMany(mappedBy ="user" )
    private List<Post> posts;


}
