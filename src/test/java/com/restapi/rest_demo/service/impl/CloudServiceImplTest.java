package com.restapi.rest_demo.service.impl;

import com.restapi.rest_demo.model.CloudVendor;
import com.restapi.rest_demo.repository.CloudVendorRepository;
import com.restapi.rest_demo.service.CloudVendorService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;


class CloudServiceImplTest {

    @Mock
    private CloudVendorRepository cloudVendorRepository;
    private CloudVendorService cloudVendorService;
    AutoCloseable autoCloseable;
    CloudVendor cloudVendor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // MockitoAnnotations.initMocks(this);
        autoCloseable = MockitoAnnotations.openMocks(this);
        cloudVendorService = new CloudServiceImpl(cloudVendorRepository);
        cloudVendor = new CloudVendor("1", "Test Vendor"
            , "Test Address", "TestNumber");
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testCreateCloudVendor() {
//        mock(CloudVendor.class);
//        mock(CloudVendorRepository.class);

        when(cloudVendorRepository.save(cloudVendor)).thenReturn(cloudVendor);

        String expectedMessage = "Vendor inserted";
        String actualMessage = cloudVendorService.createCloudVendor(cloudVendor);

        assertEquals(expectedMessage, actualMessage, "The createCloudVendor method did not return the expected message.");

    }

    @Test
    void testUpdateCloudVendor() {
        when(cloudVendorRepository.findById("1")).thenReturn(Optional.of(cloudVendor));

        // Performing update
        cloudVendor.setVendorName("UpdatedVendorName");
        cloudVendor.setVendorAddress("UpdatedVendorAddress");

        when(cloudVendorRepository.save(cloudVendor)).thenReturn(cloudVendor);

        String expectedMessage = "update successfully";
        String actualMessage = cloudVendorService.updateCloudVendor(cloudVendor);

        assertEquals(expectedMessage, actualMessage, "The createCloudVendor method did not return the expected message.");
    }

    @Test
    void testDeleteCloudVendor() {
        // Arrange
        when(cloudVendorRepository.existsById("1")).thenReturn(true);

        // Act
        cloudVendorService.deleteCloudVendor("1");

        // Assert
        verify(cloudVendorRepository, times(1)).deleteById("1");

//        mock(CloudVendor.class);
//        mock(CloudVendorRepository.class,Mockito.CALLS_REAL_METHODS);
//        doAnswer(Answers.CALLS_REAL_METHODS).when(cloudVendorRepository).deleteById(any());
//        when(cloudVendorRepository.existsById("1")).thenReturn(true);
//        assertThat(cloudVendorService.deleteCloudVendor("1")).
//                isEqualTo("successfully deleted");
    }

    @Test
    void testGetCloudVendorById() {
        // mock(CloudVendor.class);
        // mock(CloudVendorRepository.class);

        when(cloudVendorRepository.findById("1")).thenReturn(Optional.ofNullable(cloudVendor));

        CloudVendor result = cloudVendorService.getCloudVendorById("1");
        assertNotNull(result);
        assertEquals("1", result.getVendorId());
        assertEquals("Test Vendor", result.getVendorName());
        assertEquals("Test Address", result.getVendorAddress());
    }

    @Test
    void testGetAllCloudVendors() {
        // Given
        List<CloudVendor> mockCloudVendors = List.of(
            new CloudVendor("1", "Vendor1", "Description1","phone"),
            new CloudVendor("2", "Vendor2", "Description2","phone")
        );
        when(cloudVendorRepository.findAll()).thenReturn(mockCloudVendors);

        // When
        List<CloudVendor> result = cloudVendorService.getAllCloudVendors();

        // Then
        assertEquals(2, result.size());
        assertEquals("Vendor1", result.get(0).getVendorName());
        assertEquals("Vendor2", result.get(1).getVendorName());
    }
}