package com.vrr.entity.auth;

import com.vrr.code.auth.ProviderType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "VRR_USER")
public class User {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "EMAIL", length = 100)
    @NotNull
    @Size(max = 100)
    private String email;

    @Column(name = "IS_EMAIL_VERIFIED", length = 1)
    @Size(min = 1, max = 1)
    @NotNull
    private String isEmailVerified;

    @Column(name = "PASSWORD", length = 128)
    @NotNull
    @Size(max = 128)
    private String password;

    @Column(name = "USERNAME", length = 100)
    @NotNull
    @Size(max = 100)
    private String username;

    @Column(name = "PROFILE_IMAGE_URL", length = 100)
    @NotNull
    @Size(max = 100)
    private String profileImageUrl;

    @Column(name = "PROVIDER_TYPE", length = 20)
    @NotNull
//    @Convert(converter = ProviderType.class)
    private ProviderType providerType;

    @Column(name = "CREATED_AT")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public User(
            @NotNull @Size(max = 100) String username,
            @NotNull @Size(max = 512) String email,
            @NotNull @Size(max = 1) String isEmailVerified,
            @NotNull @Size(max = 512) String profileImageUrl,
            @NotNull ProviderType providerType,
            @NotNull LocalDateTime createdAt,
            @NotNull LocalDateTime updatedAt
    ) {
        this.username = username;
        this.password = "NO_PASSWORD";
        this.email = email != null ? email : "NO_EMAIL";
        this.isEmailVerified = isEmailVerified;
        this.profileImageUrl = profileImageUrl != null ? profileImageUrl : "";
        this.providerType = providerType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
