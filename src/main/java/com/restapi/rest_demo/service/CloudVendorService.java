package com.restapi.rest_demo.service;

import java.util.List;

import com.restapi.rest_demo.model.CloudVendor;

public interface CloudVendorService {

  public String createCloudVendor(CloudVendor cloudVendor);
  public String updateCloudVendor (CloudVendor cloudVendor);
  public String deleteCloudVendor(String cloudVendorId);
  public CloudVendor getCloudVendorById(String cloudVendorId);
  public List<CloudVendor> getAllCloudVendors();
  
}