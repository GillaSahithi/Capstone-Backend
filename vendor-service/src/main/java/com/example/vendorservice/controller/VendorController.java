package com.example.vendorservice.controller;

import com.example.vendorservice.converter.VendorDtoConverter;
import com.example.vendorservice.dto.VendorDto;
import com.example.vendorservice.model.Vendor;
import com.example.vendorservice.service.VendorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vendor")
public class VendorController {
    private final VendorService vendorService;
    private final VendorDtoConverter converter;

    @PostMapping
    public ResponseEntity<VendorDto> saveVendor(@Valid @RequestBody VendorDto dto) {
        Vendor vendor = converter.toEntity(dto);
        vendor = vendorService.createVendor(vendor);
        var responseBody = converter.toDto(vendor);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @GetMapping
    public ResponseEntity<List<VendorDto>> getAllVendors() {
        var vendors = vendorService.getAllVendors();
        if(vendors.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(converter.toDTOs(vendors));
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<VendorDto> getVendorById(@PathVariable String id) {
//        var vendor = vendorService.getVendorById(id);
//        if (vendor != null) {
//            return ResponseEntity.ok(converter.toDto(vendor));
//        }
//        return ResponseEntity.notFound().build();
//    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVendorById(@PathVariable String id) {
        Optional<Vendor> vendor = vendorService.getVendorById(id);
        if (vendor.isPresent()) {
            return ResponseEntity.ok(vendor.get());
        } else {
            return ResponseEntity.status(404).body("Vendor not found with id: " + id);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendorDto> updateVendor(@PathVariable String id, @Valid @RequestBody VendorDto dto) {
        Vendor updatedVendor = vendorService.updateVendor(id, converter.toEntity(dto));
        return ResponseEntity.ok(converter.toDto(updatedVendor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVendor(@PathVariable String id) {
        vendorService.deleteVendor(id);
        return ResponseEntity.ok("Vendor deleted");
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<VendorDto> getVendorByContactMail(@PathVariable String email) {
        var vendor = vendorService.getVendorByContactMail(email);
        if (vendor != null) {
            return ResponseEntity.ok(converter.toDto(vendor));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<String> getVendorContactMailById(@PathVariable String id) {
        String email = vendorService.getVendorContactMailById(id);
        return ResponseEntity.ok(email);
    }

    @DeleteMapping("/email/{email}")
    public ResponseEntity<String> deleteVendorContactMail(@PathVariable String email) {
        vendorService.deleteVendorByContactMail(email);
        return ResponseEntity.ok("Vendor deleted");
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<List<?>> getProductsByVendorId(@PathVariable("id")  String id){
        return ResponseEntity.ok(vendorService.getProductsByVendorId(id));
    }
}
