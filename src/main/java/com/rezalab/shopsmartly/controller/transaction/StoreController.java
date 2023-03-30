package com.rezalab.shopsmartly.controller.transaction;

import com.rezalab.shopsmartly.constant.ControllerConstant;
import com.rezalab.shopsmartly.service.base.wrapper.Response;
import com.rezalab.shopsmartly.service.transaction.StoreService;
import com.rezalab.shopsmartly.service.transaction.wrapper.StoreWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @PostMapping
    public ResponseEntity<Response> insertStore(@RequestBody StoreWrapper wrapper) throws Exception {
        StoreWrapper data = storeService.save(wrapper);
        Response response = toResponse(data, ControllerConstant.SUCCESS_INSERT_DATA,
                ControllerConstant.FAILED_INSERT_DATA);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Response> findById(@PathVariable("id") Long id) throws Exception {
        StoreWrapper data = storeService.findById(id);
        Response response = toResponse(data, ControllerConstant.SUCCESS_FINDBYID,
                ControllerConstant.FAILED_FIND);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    public ResponseEntity<Response> delete(@RequestParam Long id) throws Exception {
        Boolean data = storeService.delete(id);
        Response response = toResponse(data, ControllerConstant.SUCCESS_DELETE,
                ControllerConstant.FAILED_FIND);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<Response> findAll() throws Exception {
        List<StoreWrapper> data = storeService.findAll();
        Response response = toResponse(data, ControllerConstant.SUCCESS_FINDALL,
                ControllerConstant.FAILED_FIND);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<Response> getPageable(@RequestParam String sSearch) throws Exception {
        List<StoreWrapper> data = storeService.getPageable(sSearch);
        Response response = toResponse(data, ControllerConstant.SUCCESS_FINDALL,
                ControllerConstant.FAILED_FIND);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<Response> findByName(@RequestParam String name) throws Exception {
        StoreWrapper data = storeService.findByName(name);
        Response response = toResponse(data, ControllerConstant.SUCCESS_FINDBYNAME + data.getName(),
                ControllerConstant.FAILED_FIND);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<Response> findByCode(@RequestParam String code) throws Exception {
        StoreWrapper data = storeService.findByCode(code);
        Response response = toResponse(data, ControllerConstant.SUCCESS_FINDBYCODE + data.getCode(),
                ControllerConstant.FAILED_FIND);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<Response> findByAddress(@RequestParam String address, String secondAddress) throws Exception {
        StoreWrapper data = storeService.findByAddress(address, secondAddress);
        Response response = toResponse(data, ControllerConstant.SUCCESS_FINDBYADDRESS + data.getAddress()
                + " OR " + data.getSecondAddress(), ControllerConstant.FAILED_FIND);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    private Response toResponse(Object data, String message, String error) {
        Response response = new Response();
        response.setData(data);
        response.setMessage(data != null ? message : error);
        return response;
    }
}
