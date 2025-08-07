package com.arturk.controller;

import com.arturk.dto.UserDto;
import com.arturk.service.UserAggregationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v4")
@Tag(name = "Users", description = "API for user operations")
@AllArgsConstructor
public class UserAggregatingController {

    private final UserAggregationService userAggregationService;

    @Operation(
            summary = "Get aggregated users",
            description = "Returns aggregated list of users from all configured database sources",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful response with list of users",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = UserDto.class))
                            )
                    ),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping("/users")
    public List<UserDto> getUsers() {
        return userAggregationService.getAggregatedUsers();
    }
}
