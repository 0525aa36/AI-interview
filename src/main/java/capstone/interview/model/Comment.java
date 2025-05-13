package capstone.interview.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;



@Entity
public class Comment {

    // 기본 키 설정
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 기본 키

    private String content;

    // Post와의 관계 설정
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    // 기본 생성자
    public Comment() {
    }

    // Getter 및 Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

}
