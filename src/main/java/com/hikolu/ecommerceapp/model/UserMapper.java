package com.hikolu.ecommerceapp.model;

import com.hikolu.ecommerceapp.dto.UserDTOProfile;

public class UserMapper {

    // convert User to UserDTO
    public static UserDTOProfile mapToUserDTO(User user) {

        UserDTOProfile userDTOProfile = new UserDTOProfile(user.getUsername(), user.getEmail());

        return userDTOProfile;
    }
}
