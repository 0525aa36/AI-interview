package capstone.interview.service;

import capstone.interview.model.Post;

import java.util.List;


public interface PostService {
    List<Post> getAllPosts();  // Method to retrieve all posts

    void savePost(Post post);



}