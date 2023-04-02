package com.rezalab.shopsmartly.controller.user;

import com.rezalab.shopsmartly.constant.ControllerConstant;
import com.rezalab.shopsmartly.exception.InvalidLoginException;
import com.rezalab.shopsmartly.security.JwtComponent;
import com.rezalab.shopsmartly.service.base.wrapper.Response;
import com.rezalab.shopsmartly.service.user.UserAccountService;
import com.rezalab.shopsmartly.service.user.wrapper.LoginWrapper;
import com.rezalab.shopsmartly.service.user.wrapper.UserAccountWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequestMapping("user-account")
public class UserAccountController {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtComponent jwtComponent;

    @PostMapping("/login")
    public ResponseEntity<Response> findByEmail(@RequestBody UserAccountWrapper wrapper) throws Exception {
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(wrapper.getEmail(), wrapper.getPassword()))
                    .isAuthenticated();
        } catch (Exception e) {
            throw new InvalidLoginException(ControllerConstant.LOGIN_FAILED);
        }

        UserAccountWrapper data = userAccountService.findByEmail(wrapper.getEmail());
        String token = jwtComponent.generateToken(Duration.ofDays(1), data.getId().toString());

        LoginWrapper result = new LoginWrapper();
        result.setEmail(data.getEmail());
        result.setRole(data.getRoleCode());
        result.setVersion(data.getVersion());
        result.setToken(token);

        Response response = toResponse(result, ControllerConstant.LOGIN_SUCCESS,
                ControllerConstant.LOGIN_FAILED);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Response> insertAccount(@RequestBody UserAccountWrapper wrapper) throws Exception {
        UserAccountWrapper data = userAccountService.save(wrapper);
        Response response = toResponse(data, ControllerConstant.SUCCESS_FINDALL,
                ControllerConstant.FAILED_FIND);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Response> findById(@PathVariable("id") Long id) throws Exception {
        UserAccountWrapper data = userAccountService.findById(id);
        Response response = toResponse(data, ControllerConstant.SUCCESS_FINDBYID + data.getId(),
                ControllerConstant.FAILED_FIND);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    public ResponseEntity<Response> delete(@RequestParam Long id) throws Exception {
        Boolean data = userAccountService.delete(id);
        Response response = toResponse(data, ControllerConstant.SUCCESS_DELETE,
                ControllerConstant.FAILED_FIND);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    private Response toResponse(Object data, String message, String error) {
        Response response = new Response();
        response.setData(data);
        response.setMessage(data != null ? message : error);
        return response;
    }
}
