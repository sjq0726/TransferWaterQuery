package com.fuzamei.demo.model.VO;

import com.fuzamei.demo.model.User;
import lombok.Data;

@Data
public class UserVO {
    private User user;

    private String token;
}
