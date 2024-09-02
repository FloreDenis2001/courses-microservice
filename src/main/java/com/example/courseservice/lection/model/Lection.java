package com.example.courseservice.lection.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Table(name = "lections")
@Entity(name = "Lection")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Data
public class Lection {

    @Id
    @SequenceGenerator(name = "lection_sequence", sequenceName = "lection_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lection_sequence")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="code", nullable = false)
    private String code;

    @Column(name = "video_url", nullable = false)
    private String videoUrl;

    @Column(name = "support_url")
    private String supportFileUrl;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;





}
