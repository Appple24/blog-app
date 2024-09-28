//package com.blogging.entites;
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.*;
//
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//public class Post {
//   @Autowired
//    private Comment comm;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer postId;
//    private String title;
//    @Column(length = 10000)
//    private String content;
//    private String imageName;
//    private Date addedDate;
//
//    @ManyToOne
//    @JoinColumn(name="catId")
//    private Category category;
//    @ManyToOne
//    private users user;
//
//    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Comment> comments = new ArrayList<>();
//
//    private List<String> res=new ArrayList<>();
//    @Override
//    public String toString()
//    {
//        String s="";
//        for(Comment e:com)
//        {
//            int i=e.getId();
//            s+=i+" ";
//        }
//        return s;
//
//    }
//
//}
package com.blogging.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    private String title;

    @Column(length = 10000)
    private String content;

    private String imageName;

    private Date addedDate;

    @ManyToOne
    @JoinColumn(name="catId")
    private Category category;

    @ManyToOne
    private users user;

    // Bi-directional mapping with Comment entity
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    private List<String> res = new ArrayList<>();

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Comment e : comments) {
            int i = e.getId();
            s.append(i).append(" ");
        }
        return s.toString();
    }
}
