package com.blogging.Repository;

import com.blogging.entites.Category;
import com.blogging.entites.Post;
import com.blogging.entites.users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepo extends JpaRepository<Post,Integer> {

    List<Post> findByCategory(Category cat);


    List<Post> findByUser(users user);

    @Query("select p from Post p where p.title like :key")
    List<Post>searchByTitle(@Param( "key") String keyword);
}
