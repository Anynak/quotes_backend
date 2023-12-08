package com.cameleoon.quotes.dto.mapers;

import com.cameleoon.quotes.dto.requests.UserRequest;
import com.cameleoon.quotes.dto.responses.Author;
import com.cameleoon.quotes.dto.responses.UserResponse;
import com.cameleoon.quotes.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserResponse toUserResponse(User user);

    Author toAuthor(User user);

    User toUser(UserRequest userRequest);
}
