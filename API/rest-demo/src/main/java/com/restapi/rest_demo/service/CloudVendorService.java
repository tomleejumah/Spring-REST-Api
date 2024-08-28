package com.restapi.rest_demo.service;

import java.util.List;

import com.restapi.rest_demo.model.CloudVendor;

public interface CloudVendorService {

  public String createCloudVendor(CloudVendor cloudVendor);
  public String updateCloudVendor (CloudVendor cloudVendor);
  public String deleteCloudVendor(Integer cloudVendorId);
  public CloudVendor getCloudVendorById(Integer cloudVendorId);
  public List<CloudVendor> getAllCloudVendors();
  
}