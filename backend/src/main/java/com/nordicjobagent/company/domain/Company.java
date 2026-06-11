package com.nordicjobagent.company.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String website;

    private String headquarters;

    private String country;

    private Boolean hiring;

    private Integer score;

    private Instant lastResearchedAt;
}
