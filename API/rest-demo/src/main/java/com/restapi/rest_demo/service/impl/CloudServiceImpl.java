package com.restapi.rest_demo.service.impl;

import java.util.List;
import java.util.Optional;

import com.restapi.rest_demo.exception.CloudVendorException.CloudVendorNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.rest_demo.model.CloudVendor;
import com.restapi.rest_demo.repository.CloudVendorRepository;
import com.restapi.rest_demo.service.CloudVendorService;

@Service
public class CloudServiceImpl implements CloudVendorService{

  @Autowired
  CloudVendorRepository cloudVendorRepository;
  
  public CloudServiceImpl(CloudVendorRepository cloudVendorRepository) {
    this.cloudVendorRepository = cloudVendorRepository;
  }

//  @PostConstruct
//  public void initializeData() {
//    CloudVendor cloudVendorOne = new CloudVendor("Amazon","Home","xxxx");
//    cloudVendorRepository.save(cloudVendorOne);
//
//    cloudVendorRepository.findByVendorName("Amazon").forEach(
//            System.out::println
//    );
//  }

  @Override
  public String createCloudVendor(CloudVendor cloudVendor) {
    cloudVendorRepository.save(cloudVendor);
    return "Vendor inserted";
  }

  @Override
  public String updateCloudVendor(CloudVendor cloudVendor) {
    Optional<CloudVendor> existingVendor = cloudVendorRepository.findById(cloudVendor.getVendorId());
    if (existingVendor.isPresent()) {
      // makes sure we maintain the id
      CloudVendor vendorToUpdate = existingVendor.get();
      vendorToUpdate.setVendorName(cloudVendor.getVendorName());
      vendorToUpdate.setVendorAddress(cloudVendor.getVendorAddress());
      vendorToUpdate.setVendorAddress(cloudVendor.getVendorAddress());
      cloudVendorRepository.save(vendorToUpdate);
      return "update successfully";
    } else {
      throw new IllegalArgumentException("Vendor not found with ID: " + cloudVendor.getVendorId());
    }
  }

  @Override
  public String deleteCloudVendor (Integer cloudVendorId) throws IllegalArgumentException {
    return cloudVendorRepository.findById(cloudVendorId)
        .map(vendor -> {
            cloudVendorRepository.deleteById(cloudVendorId);
            return "Successfully deleted";
        })
        .orElse("Vendor with ID: " + cloudVendorId + " not found");

    // if (cloudVendorRepository.existsById(cloudVendorId)) {
    //   cloudVendorRepository.deleteById(cloudVendorId);
    //   return "successfully deleted";
    // } else {
    //   return ("Vendor with ID: " + cloudVendorId + " not found");
    // }
  }

  @Override
  public CloudVendor getCloudVendorById(Integer cloudVendorId) {

    if(cloudVendorRepository.findById(cloudVendorId).isEmpty())
      throw new CloudVendorNotFoundException("Vendor with id " + cloudVendorId + " not found");
    return cloudVendorRepository.findById(cloudVendorId).get();
  }

  @Override
  public List<CloudVendor> getAllCloudVendors() {
    return cloudVendorRepository.findAll();
    // return List.of();
  }
}
