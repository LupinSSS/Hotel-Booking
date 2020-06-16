package com.example.hotel_booking.common.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class ImageRequest {
    private MultipartFile file;
    private String imgId;
    private String classId;
    private String description;
    private String imageTime;

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageTime(String imageTime) {
        this.imageTime = imageTime;
    }

    public MultipartFile getFile() {
        return file;
    }

    public String getImgId() {
        return imgId;
    }

    public String getClassId() {
        return classId;
    }

    public String getDescription() {
        return description;
    }

    public String getImageTime() {
        return imageTime;
    }
}
