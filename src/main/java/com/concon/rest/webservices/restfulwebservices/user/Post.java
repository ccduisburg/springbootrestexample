package com.concon.rest.webservices.restfulwebservices.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue
    private Integer id;
    private String description;
    @ManyToOne(fetch= FetchType.LAZY)
    @JsonIgnore//User ayrintilari istemedigim icin Post icerisindekji user i ignore ediyorum
    private User user;
}
