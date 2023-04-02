package com.rezalab.shopsmartly.controller.master;

import com.rezalab.shopsmartly.constant.ControllerConstant;
import com.rezalab.shopsmartly.service.base.wrapper.Response;
import com.rezalab.shopsmartly.service.master.CategoryService;
import com.rezalab.shopsmartly.service.master.wrapper.CategoryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Response> insertCategory(@RequestBody CategoryWrapper wrapper) throws Exception {
        CategoryWrapper data = categoryService.save(wrapper);
        Response response = toResponse(data, ControllerConstant.SUCCESS_INSERT_DATA,
                ControllerConstant.FAILED_INSERT_DATA);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Response> findById(@PathVariable("id") Long id) throws Exception {
        CategoryWrapper data = categoryService.findById(id);
        Response response = toResponse(data, ControllerConstant.SUCCESS_FINDBYID + data.getId(),
                ControllerConstant.FAILED_FIND);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    public ResponseEntity<Response> delete(@RequestParam Long id) throws Exception {
        Boolean data = categoryService.delete(id);
        Response response = toResponse(data, ControllerConstant.SUCCESS_DELETE,
                ControllerConstant.FAILED_FIND);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<Response> findAll() throws Exception {
        List<CategoryWrapper> data = categoryService.findAll();
        Response response = toResponse(data, ControllerConstant.SUCCESS_FINDALL,
                ControllerConstant.FAILED_FIND);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/page")
    public ResponseEntity<Response> getPageable(@RequestParam String sSearch) throws Exception {
        List<CategoryWrapper> data = categoryService.getPageable(sSearch);
        Response response = toResponse(data, ControllerConstant.SUCCESS_FINDALL,
                ControllerConstant.FAILED_FIND);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/name")
    public ResponseEntity<Response> findByName(@RequestParam String name) throws Exception {
        CategoryWrapper data = categoryService.findByName(name);
        Response response = toResponse(data, ControllerConstant.SUCCESS_FINDBYNAME + data.getName(),
                ControllerConstant.FAILED_FIND);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/code")
    public ResponseEntity<Response> findByCode(@RequestParam String code) throws Exception {
        CategoryWrapper data = categoryService.findByCode(code);
        Response response = toResponse(data, ControllerConstant.SUCCESS_FINDBYCODE + data.getCode(),
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
