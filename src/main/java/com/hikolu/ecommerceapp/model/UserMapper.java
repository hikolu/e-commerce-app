package com.hikolu.ecommerceapp.model;

import com.hikolu.ecommerceapp.dto.UserDTO;

public class UserMapper {

    // convert User to UserDTO
    public static UserDTO mapToUserDTO(User user) {

        UserDTO userDTO = new UserDTO(user.getUsername(), user.getEmail());

        return userDTO;
    }
}
