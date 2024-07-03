package com.restapi.rest_demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.restapi.rest_demo.model.CloudVendor;
import com.restapi.rest_demo.repository.CloudVendorRepository;
import com.restapi.rest_demo.service.CloudVendorService;

@Service
public class CloudServiceImpl implements CloudVendorService{

  CloudVendorRepository cloudVendorRepository;
  
  public CloudServiceImpl(CloudVendorRepository cloudVendorRepository) {
    this.cloudVendorRepository = cloudVendorRepository;
  }

  @Override
  public String createCloudVendor(CloudVendor cloudVendor) {
    cloudVendorRepository.save(cloudVendor);
    return "Vendor inserted";
  }

  @Override
  public String updateCloudVendor(CloudVendor cloudVendor) {
    cloudVendorRepository.save(cloudVendor);
    return "vendor updated";
  }

  @Override
  public String deleteCloudVendor(String cloudVendorId) {
    cloudVendorRepository.deleteById(cloudVendorId);
    return "successfully deleted";
  }

  @Override
  public CloudVendor getCloudVendorById(String cloudVendorId) {
    return cloudVendorRepository.findById(cloudVendorId).get();
  }

  @Override
  public List<CloudVendor> getAllCloudVendors() {
    return cloudVendorRepository.findAll();
    // return List.of();
  }
}
