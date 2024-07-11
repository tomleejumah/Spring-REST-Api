package com.restapi.rest_demo.controller;

import com.restapi.rest_demo.model.CloudVendor;
import com.restapi.rest_demo.response.ResponseHandler;
import com.restapi.rest_demo.service.CloudVendorService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cloudvendor")
public class CloudVendorApiController {

    CloudVendorService cloudVendorService;

    public CloudVendorApiController(CloudVendorService cloudVendorService) {
        this.cloudVendorService = cloudVendorService;
    }

// get specific cloud vendor
    @GetMapping("{vendorId}")
    public ResponseEntity<Object> getCloudVendorDetails(@PathVariable("vendorId") String vendorID){
        return ResponseHandler.ResponseBuilder("Req Vendor details are given here"
                , HttpStatus.OK, cloudVendorService.getCloudVendorById(vendorID));
    }

// get all cloud vendors in db
    @GetMapping()
    public List<CloudVendor> getAllCloudVendorDetails(){
       return cloudVendorService.getAllCloudVendors();
    }

    @PostMapping
    public String createCloudVendorDetails(@RequestBody CloudVendor cloudVendor){
        cloudVendorService.createCloudVendor(cloudVendor);
        return "Cloud Vendor created successfully";
    }

    @PutMapping("{vendorId}")
     public String updateCloudVendorDetails(@PathVariable("vendorId") String vendorId,@RequestBody CloudVendor cloudVendor){
        CloudVendor updatedVendor = cloudVendorService.getCloudVendorById(vendorId);
//        CloudVendor updatedVendor = new CloudVendor(cloudVendor);
        try {
            updatedVendor.setVendorId(vendorId); // Ensure the ID is set correctly for update
            updatedVendor.setVendorAddress(cloudVendor.getVendorAddress());
            updatedVendor.setVendorName(cloudVendor.getVendorName());
            updatedVendor.setVendorPhoneNumber(cloudVendor.getVendorPhoneNumber());
            //            return ResponseEntity.ok(message);
            return cloudVendorService.updateCloudVendor(updatedVendor);
        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            return e.getMessage();
        }
}

    @DeleteMapping("{vendorId}")
    public String deleteCloudVendorDetails(@PathVariable("vendorId")String vendorID){
      return  cloudVendorService.deleteCloudVendor(vendorID);
        // return "deleted";
    }
}
