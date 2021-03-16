package com.xy.auth.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserToken {
    private Integer userID;
    private String token;
    private LocalDateTime createTime;
    private LocalDateTime expireTime;
}
