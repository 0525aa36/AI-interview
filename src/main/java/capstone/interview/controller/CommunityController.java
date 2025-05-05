package capstone.interview.controller;

import capstone.interview.model.Post;
import capstone.interview.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CommunityController {

    @Autowired
    private PostService postService;

    @GetMapping("/community")
    public String showCommunityPage(Model model) {
        List<Post> posts = postService.getAllPosts();
        if (posts.isEmpty()) {
            model.addAttribute("message", "No posts available");
        } else {
            model.addAttribute("posts", posts);
        }
        return "community";
    }

    @GetMapping("/index")
    public String showIndexPage(Model model) {
        List<Post> posts = postService.getAllPosts();
        if (posts.isEmpty()) {
            model.addAttribute("message", "No posts available");
        } else {
            model.addAttribute("posts", posts);
        }
        return "index";
    }

}

