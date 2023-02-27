package com.oldsteelimage.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity @Table(name = "images")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@ToString
public class Images {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private byte[] imageData;
    private String imageName;
    private String contentType;

    public Images(byte[] imageData, String imageName, String contentType) {
        this.imageData = imageData;
        this.imageName = imageName;
        this.contentType = contentType;
    }
}
