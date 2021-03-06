package com.vrr.application.api.domain.auth.controller;

import com.epages.restdocs.apispec.ResourceDocumentation;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vrr.application.api.global.libs.ApiDocumentUtils;
import com.vrr.common.code.auth.ProviderType;
import com.vrr.common.code.auth.RoleType;
import com.vrr.domain.entity.auth.User;
import com.vrr.domain.entity.auth.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.headers.HeaderDocumentation;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class AuthSignInControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @Test
    void ?????????_????????????_?????????() throws Exception {
        // given
        HashMap<String, String> body = new HashMap<>();
        body.put("email", "ksy@cmcom.kr");
        body.put("password", "helloWorld22!!");

        // when
        User user = userRepository.save(User.builder()
                .username("sysnar")
                .email("ksy@cmcom.kr")
                .serialNumber("ksy@cmcom.kr")
                .password(passwordEncoder.encode("helloWorld22!!"))
                .roleType(RoleType.USER)
                .providerType(ProviderType.VRR)
                .profileImageUrl("")
                .emailVerified("Y")
                .build());

        // then
        mockMvc.perform(post("/api/1/auth/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .content(objectMapper.writeValueAsString(body))
                )
                .andExpect(status().isCreated())
                .andDo(document("v1-auth-signin",
                        ApiDocumentUtils.getDocumentRequest(),
                        ApiDocumentUtils.getDocumentResponse(),
                        ResourceDocumentation.resource(
                                ResourceSnippetParameters.builder()
                                        .tag("?????? API")
                                        .summary("????????? API")
                                        .description("OAuth2 ????????? ?????? ????????? ??????, ?????????/???????????? ????????? ???????????? ???????????? API ?????????.")
                                        .requestFields(
                                                fieldWithPath("email").type(JsonFieldType.STRING).description("???????????? ??? ????????? ?????????(?????????)"),
                                                fieldWithPath("password").type(JsonFieldType.STRING).description("?????????(?????????)??? ????????????")
                                        )
                                        .responseHeaders(headerWithName(HttpHeaders.SET_COOKIE).description("Refresh Token"))
                                        .responseFields(fieldWithPath("accessToken").type(JsonFieldType.STRING).description("????????? ?????? ????????? ???????????? ????????? ??????"))
                                        .build()
                        ),
                        requestFields(
                                fieldWithPath("email").type(JsonFieldType.STRING).description("???????????? ??? ????????? ?????????(?????????)"),
                                fieldWithPath("password").type(JsonFieldType.STRING).description("?????????(?????????)??? ????????????")
                        ),
                        responseHeaders(HeaderDocumentation.headerWithName(HttpHeaders.SET_COOKIE).description("Refresh Token")),
                        responseFields(fieldWithPath("accessToken").type(JsonFieldType.STRING).description("????????? ?????? ????????? ???????????? ????????? ??????"))
                ));
    }
}