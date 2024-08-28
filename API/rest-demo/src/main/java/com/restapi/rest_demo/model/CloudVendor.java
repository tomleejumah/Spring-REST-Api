package com.restapi.rest_demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "cloud_vendor_info")
public class CloudVendor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vendor_id")
    private Integer vendorId;

    @Column(name = "vendor_name")
    private String vendorName;

    @Column(name = "vendor_address")
    private String vendorAddress;

    @Column(name = "vendor_phone_number")
    private String vendorPhoneNumber;

    public CloudVendor(){}

    public CloudVendor(String vendorName, String vendorAddress, String vendorPhoneNumber) {
//        this.vendorId = vendorId;
        this.vendorName = vendorName;
        this.vendorAddress = vendorAddress;
        this.vendorPhoneNumber = vendorPhoneNumber;
    }

    public CloudVendor(CloudVendor cloudVendor) {
    }

    @Override
    public String toString() {
        return "CloudVendor{" +
                "vendorId=" + vendorId +
                ", vendorName='" + vendorName + '\'' +
                ", vendorAddress='" + vendorAddress + '\'' +
                ", vendorPhoneNumber='" + vendorPhoneNumber + '\'' +
                '}';
    }
}
