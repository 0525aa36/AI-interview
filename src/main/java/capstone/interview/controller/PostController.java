package capstone.interview.controller;

import capstone.interview.model.Post;
import capstone.interview.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/posts")
    public String createPost(@ModelAttribute Post post, @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        if (!imageFile.isEmpty()) {
            String fileName = saveImageFile(imageFile); // 파일 저장하는 메서드
            post.setImageUrl("/uploads/" + fileName); // 저장된 파일 경로 설정
        }
        postService.savePost(post);
        return "redirect:/community"; // 글 작성 후 커뮤니티 페이지로 리다이렉트
    }

    @GetMapping("/posts/new")
    public String createPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "new-post";  // 새 글 작성 폼으로 이동
    }

    private String saveImageFile(MultipartFile imageFile) throws IOException {
        String fileName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
        Path uploadPath = Paths.get("uploads"); // 파일을 저장할 디렉토리 설정

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath); // 디렉토리 없으면 생성
        }

        Files.copy(imageFile.getInputStream(), uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        return fileName;
    }
}
