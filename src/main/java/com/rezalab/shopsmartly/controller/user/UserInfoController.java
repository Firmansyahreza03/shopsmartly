package com.rezalab.shopsmartly.controller.user;

import com.rezalab.shopsmartly.constant.ControllerConstant;
import com.rezalab.shopsmartly.service.base.wrapper.Response;
import com.rezalab.shopsmartly.service.user.UserInfoService;
import com.rezalab.shopsmartly.service.user.wrapper.UserInfoWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("user-info")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/join")
    public ResponseEntity<Response> findByJoinDate(@RequestParam LocalDate date) throws Exception {
        List<UserInfoWrapper> data = userInfoService.findByJoinDate(date);
        Response response = toResponse(data, ControllerConstant.SUCCESS_FINDBYJOINDATE + date,
                ControllerConstant.FAILED_FIND);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<Response> insertUserInfo(@RequestBody UserInfoWrapper wrapper) throws Exception {
        UserInfoWrapper data = userInfoService.save(wrapper);
        Response response = toResponse(data, ControllerConstant.SUCCESS_INSERT_DATA,
                ControllerConstant.FAILED_INSERT_DATA);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Response> findById(@PathVariable("id") Long id) throws Exception {
        UserInfoWrapper data = userInfoService.findById(id);
        Response response = toResponse(data, ControllerConstant.SUCCESS_FINDBYID + data.getId(),
                ControllerConstant.FAILED_FIND);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    public ResponseEntity<Response> delete(@RequestParam Long id) throws Exception {
        Boolean data = userInfoService.delete(id);
        Response response = toResponse(data, ControllerConstant.SUCCESS_DELETE,
                ControllerConstant.FAILED_FIND);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<Response> findAll() throws Exception {
        List<UserInfoWrapper> data = userInfoService.findAll();
        Response response = toResponse(data, ControllerConstant.SUCCESS_FINDALL,
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
