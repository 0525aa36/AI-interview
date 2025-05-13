package capstone.interview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
public class FileUploadController {

    @PostMapping("/upload-files")
    public String handleFileUpload(@RequestParam("videoFile") MultipartFile videoFile) {
        // 파일 저장 디렉토리 경로 설정
        String uploadDir = System.getProperty("user.dir") + "/src/main/resources/uploads/video";

        // 디렉토리가 존재하지 않으면 생성
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        try {
            // 파일 저장 경로 설정
            String videoFileName = UUID.randomUUID().toString() + "_" + videoFile.getOriginalFilename();
            String videoFilePath = Paths.get(uploadDir, videoFileName).toString();

            // 파일 저장
            videoFile.transferTo(new File(videoFilePath));

            // 성공 응답
            return "redirect:/Ai-interview-step4";
        } catch (IOException e) {
            e.printStackTrace();
            // 실패 응답
            return "error";
        }
    }
}
