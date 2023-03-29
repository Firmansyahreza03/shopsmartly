package com.rezalab.shopsmartly.controller.transaction;

import com.rezalab.shopsmartly.constant.ControllerConstant;
import com.rezalab.shopsmartly.service.base.wrapper.Response;
import com.rezalab.shopsmartly.service.transaction.ProductService;
import com.rezalab.shopsmartly.service.transaction.wrapper.ProductWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Response> insert(@RequestBody ProductWrapper wrapper) throws Exception {
        ProductWrapper data = productService.save(wrapper);
        Response response = toResponse(data, ControllerConstant.SUCCESS_INSERT_DATA,
                ControllerConstant.FAILED_INSERT_DATA);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Response> findById(@PathVariable("id") Long id) throws Exception {
        ProductWrapper data = productService.findById(id);
        Response response = toResponse(data, ControllerConstant.SUCCESS_FINDBYID,
                ControllerConstant.FAILED_FIND);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    public ResponseEntity<Response> delete(@RequestParam Long id) throws Exception {
        Boolean data = productService.delete(id);
        Response response = toResponse(data, ControllerConstant.SUCCESS_DELETE,
                ControllerConstant.FAILED_FIND);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<Response> findAll() throws Exception {
        List<ProductWrapper> data = productService.findAll();
        Response response = toResponse(data, ControllerConstant.SUCCESS_FINDALL,
                ControllerConstant.FAILED_FIND);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<Response> getPageable(@RequestParam String sSearch) throws Exception {
        List<ProductWrapper> data = productService.getPageable(sSearch);
        Response response = toResponse(data, ControllerConstant.SUCCESS_FINDALL,
                ControllerConstant.FAILED_FIND);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<Response> findByName(@RequestParam String name) throws Exception {
        ProductWrapper data = productService.findByName(name);
        Response response = toResponse(data, ControllerConstant.SUCCESS_FINDBYNAME,
                ControllerConstant.FAILED_FIND);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<Response> findByCode(@RequestParam String code) throws Exception {
        ProductWrapper data = productService.findByCode(code);
        Response response = toResponse(data, ControllerConstant.SUCCESS_FINDBYCODE,
                ControllerConstant.FAILED_FIND);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/category/name")
    public ResponseEntity<Response> findByCategoryName(@RequestParam String name) throws Exception {
        ProductWrapper data = productService.findByCategoryName(name);
        Response response = toResponse(data, ControllerConstant.SUCCESS_FINDBYNAME,
                ControllerConstant.FAILED_FIND);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/category/code")
    public ResponseEntity<Response> findByCategoryCode(@RequestParam String code) throws Exception {
        ProductWrapper data = productService.findByCategoryCode(code);
        Response response = toResponse(data, ControllerConstant.SUCCESS_FINDBYCODE,
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
