package com.restapi.rest_demo.controller;

import com.restapi.rest_demo.model.CloudVendor;
import com.restapi.rest_demo.response.ResponseHandler;
import com.restapi.rest_demo.service.CloudVendorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/cloudvendor")
@Tag(name = "User", description = "Vendor management APIs")
public class CloudVendorApiController {

    private CloudVendorService cloudVendorService;

    public CloudVendorApiController(CloudVendorService cloudVendorService) {
        this.cloudVendorService = cloudVendorService;
    }

// get specific cloud vendor
    public static final Logger logInfo = LoggerFactory.getLogger(CloudVendor.class);
    @Operation(summary = "Gets a cloudVendor by ID", description = "Returns a cloudVendor based on the ID")
    @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(schema = @Schema(implementation = CloudVendor.class)))
    @ApiResponse(responseCode = "404", description = "User not found")
    @GetMapping("{vendorId}")
    public ResponseEntity<Object> getCloudVendorDetails(@PathVariable("vendorId") Integer vendorID){
        logInfo.info("CloudVendor Logging is enabled");
        logInfo.debug("debug logging");
        return ResponseHandler.ResponseBuilder("Req Vendor details are given here"
                , HttpStatus.OK, cloudVendorService.getCloudVendorById(vendorID));
    }

// get all cloud vendors in db
    @GetMapping("/")
    public List<CloudVendor> getAllCloudVendorDetails(){
       return cloudVendorService.getAllCloudVendors();
    }

    @PostMapping("/")
    public String createCloudVendorDetails(@RequestBody CloudVendor cloudVendor){
        cloudVendorService.createCloudVendor(cloudVendor);
        return "Cloud Vendor created successfully";
    }

    @PutMapping("{vendorId}")
     public String updateCloudVendorDetails(@PathVariable("vendorId") Integer vendorId,@RequestBody CloudVendor cloudVendor){
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
    public String deleteCloudVendorDetails(@PathVariable("vendorId")Integer vendorID){
      return  cloudVendorService.deleteCloudVendor(vendorID);
        // return "deleted";
    }
}
