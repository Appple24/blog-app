//package com.blogging.entites;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//@Entity
//@Getter
//@Setter
//@Table(name="Comments")
//public class Comment {
//    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    private Integer id;
//    private String content;
//
//    @ManyToOne
//    @JoinColumn(name = "post_id")  // Define the foreign key column
//    private Post post;
//}
package com.blogging.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="Comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;

    // Bi-directional mapping
    @ManyToOne
    @JoinColumn(name = "post_id")  // Define the foreign key column
    private Post post;
}
