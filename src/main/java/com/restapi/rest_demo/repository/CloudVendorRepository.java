package com.restapi.rest_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restapi.rest_demo.model.CloudVendor;

import java.util.List;

public interface CloudVendorRepository extends JpaRepository<CloudVendor,Integer> {

    List<CloudVendor> findByVendorName(String strings);
}
