package com.accenture.tigital.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.accenture.tigital.libraries.decorators.BasicAuth;
import com.accenture.tigital.libraries.decorators.BearerAuth;
import com.accenture.tigital.libraries.enums.UserRole;
import com.accenture.tigital.libraries.typings.UserInput;
import com.accenture.tigital.models.User;
import com.accenture.tigital.services.UserService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @BearerAuth
    @GetMapping
    public Page<User> list(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size){
        return userService.paginated(page, size);
    }

    @BasicAuth
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(HttpServletRequest request){
        UserInput userInput = (UserInput) request.getAttribute("requestBody");
        userInput.setRole(UserRole.EMPLOYEE);
        return userService.create(userInput);
    }
}
