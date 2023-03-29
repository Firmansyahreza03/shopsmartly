package com.rezalab.shopsmartly.controller.master;

import com.rezalab.shopsmartly.constant.ControllerConstant;
import com.rezalab.shopsmartly.service.base.wrapper.Response;
import com.rezalab.shopsmartly.service.master.RoleService;
import com.rezalab.shopsmartly.service.master.wrapper.RoleWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public ResponseEntity<Response> insertRole(@RequestBody RoleWrapper wrapper) throws Exception {
        RoleWrapper data = roleService.save(wrapper);
        Response response = toResponse(data, ControllerConstant.SUCCESS_INSERT_DATA,
                ControllerConstant.FAILED_INSERT_DATA);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Response> findById(@PathVariable("id") Long id) throws Exception {
        RoleWrapper data = roleService.findById(id);
        Response response = toResponse(data, ControllerConstant.SUCCESS_FINDBYID + data.getId(),
                ControllerConstant.FAILED_FIND);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    public ResponseEntity<Response> delete(@RequestParam Long id) throws Exception {
        Boolean data = roleService.delete(id);
        Response response = toResponse(data, ControllerConstant.SUCCESS_DELETE,
                ControllerConstant.FAILED_FIND);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<Response> findAll() throws Exception {
        List<RoleWrapper> data = roleService.findAll();
        Response response = toResponse(data, ControllerConstant.SUCCESS_FINDALL,
                ControllerConstant.FAILED_FIND);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<Response> findByCode(@RequestParam String code) throws Exception {
        RoleWrapper data = roleService.findByCode(code);
        Response response = toResponse(data, ControllerConstant.SUCCESS_FINDBYCODE + data.getCode(),
                ControllerConstant.FAILED_FIND);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<Response> findByName(@RequestParam String name) throws Exception {
        RoleWrapper data = roleService.findByName(name);
        Response response = toResponse(data, ControllerConstant.SUCCESS_FINDBYNAME + data.getName(),
                ControllerConstant.FAILED_FIND);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PutMapping
    public ResponseEntity<Response> updateById(@RequestParam Long id, @RequestBody RoleWrapper wrapper) throws Exception {
        RoleWrapper data = roleService.updateById(id, wrapper);
        Response response = toResponse(data, ControllerConstant.SUCCESS_UPDATE,
                ControllerConstant.FAILED_UPDATE);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    private Response toResponse(Object wrapper, String message, String error) {
        Response response = new Response();
        response.setData(wrapper);
        response.setMessage(wrapper != null ? message : error);
        return response;
    }
}