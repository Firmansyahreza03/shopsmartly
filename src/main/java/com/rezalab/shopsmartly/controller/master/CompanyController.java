package com.rezalab.shopsmartly.controller.master;

import com.rezalab.shopsmartly.constant.ControllerConstant;
import com.rezalab.shopsmartly.service.base.wrapper.Response;
import com.rezalab.shopsmartly.service.master.CompanyService;
import com.rezalab.shopsmartly.service.master.wrapper.CompanyWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping
    public ResponseEntity<Response> insertCompany(@RequestBody CompanyWrapper wrapper) throws Exception {
        CompanyWrapper data = companyService.save(wrapper);
        Response response = toResponse(data, ControllerConstant.SUCCESS_INSERT_DATA,
                ControllerConstant.FAILED_INSERT_DATA);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Response> findById(@PathVariable("id") Long id) throws Exception {
        CompanyWrapper data = companyService.findById(id);
        Response response = toResponse(data, ControllerConstant.SUCCESS_FINDBYID + data.getId(),
                ControllerConstant.FAILED_FIND);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    public ResponseEntity<Response> delete(@RequestParam Long id) throws Exception {
        Boolean data = companyService.delete(id);
        Response response = toResponse(data, ControllerConstant.SUCCESS_DELETE,
                ControllerConstant.FAILED_FIND);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<Response> findAll() throws Exception {
        List<CompanyWrapper> data = companyService.findAll();
        Response response = toResponse(data, ControllerConstant.SUCCESS_FINDALL,
                ControllerConstant.FAILED_FIND);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/page")
    public ResponseEntity<Response> getPageable(@RequestParam String sSearch) throws Exception {
        List<CompanyWrapper> data = companyService.getPageable(sSearch);
        Response response = toResponse(data, ControllerConstant.SUCCESS_FINDALL,
                ControllerConstant.FAILED_FIND);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/name")
    public ResponseEntity<Response> findByName(@RequestParam String name) throws Exception {
        CompanyWrapper data = companyService.findByName(name);
        Response response = toResponse(data, ControllerConstant.SUCCESS_FINDBYNAME + data.getName(),
                ControllerConstant.FAILED_FIND);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/address")
    public ResponseEntity<Response> findByAddress(@RequestParam String address) throws Exception {
        CompanyWrapper data = companyService.findByAddress(address);
        Response response = toResponse(data, ControllerConstant.SUCCESS_FINDBYADDRESS + data.getAddress(),
                ControllerConstant.FAILED_FIND);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/zip")
    public ResponseEntity<Response> findByZip(@RequestParam String zip) throws Exception {
        CompanyWrapper data = companyService.findByZip(zip);
        Response response = toResponse(data, ControllerConstant.SUCCESS_FINDBYCODE + data.getZip(),
                ControllerConstant.FAILED_FIND);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/phone")
    public ResponseEntity<Response> findByPhoneNumber(@RequestParam String phoneNumber) throws Exception {
        CompanyWrapper data = companyService.findByPhoneNumber(phoneNumber);
        Response response = toResponse(data, ControllerConstant.SUCCESS_FINDBYPHONE + data.getPhoneNumber(),
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
