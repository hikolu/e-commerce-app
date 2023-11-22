package com.hikolu.ecommerceapp.model;

import com.hikolu.ecommerceapp.dto.ProductDTOMain;
import com.hikolu.ecommerceapp.dto.UserDTOProfile;

public class ObjectMapper {

    // convert User to UserDTOProfile
    public static UserDTOProfile mapToUserDTO(User user) {

        UserDTOProfile userDTOProfile = new UserDTOProfile(user.getUsername(), user.getEmail());

        return userDTOProfile;
    }

    // convert Product to ProductDTOMain
    public static ProductDTOMain mapToProductDTO(Product product) {

        ProductDTOMain productDTOMain = new ProductDTOMain(product.getName(), product.getPrice(), product.getImage());

        return productDTOMain;
    }

    public static WebUser mapUserToWebUser(User user) {

        WebUser webUser = new WebUser(user.getUsername(), user.getPassword(), user.getEmail());

        return webUser;
    }
}
