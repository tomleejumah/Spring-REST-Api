package com.restapi.rest_demo.repository;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.restapi.rest_demo.model.CloudVendor;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class CloudVendorRepositoryTest {

  @Autowired
  private CloudVendorRepository cloudVendorRepository;
  CloudVendor cloudVendor;

  @BeforeEach
  void setUp() {
    cloudVendor = new CloudVendor( "Test Vendor", "Test Address", "TestNumber");
    cloudVendorRepository.save(cloudVendor);

  }

  @AfterEach
  void tearDown() {
    cloudVendor = null;
    cloudVendorRepository.deleteAll();
  }

  // success testCase
  @Test
  void testGetCloudVendorByName() {
   List< CloudVendor> vendorFound = cloudVendorRepository.findByVendorName("Test Vendor");
//     assert vendorFound.get(0).getVendorId().equals(cloudVendor.getVendorId());
     assert vendorFound.get(0).getVendorAddress().equals(cloudVendor.getVendorAddress());
  }

  // failure testcase
  @Test
  void testGetCloudVendorByName_NotFound() {
    List< CloudVendor> vendorFound = cloudVendorRepository.findByVendorName("yutyu");
    assertThat(vendorFound.isEmpty()).isTrue();
//   assertThat(vendorFound.isEmpty()).isFalse();
  }
}
