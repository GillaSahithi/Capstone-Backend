package com.example.vendorservice.converter;

import com.example.vendorservice.dto.VendorDto;
import com.example.vendorservice.model.Vendor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VendorDtoConverter {
    public static List<VendorDto> toDTOs(List<Vendor> vendors){
        return vendors.stream().map(VendorDtoConverter::toDto).toList();
    }

    public static VendorDto toDto(Vendor vendor) {
        return new VendorDto(
                vendor.getId(),
                vendor.getName(),
                vendor.getDescription(),
                vendor.getContactMail(),
                vendor.getContactPhone(),
                vendor.getAddress(),
                vendor.getPassword()
        );
    }

    public Vendor toEntity(VendorDto vendorDto) {
        Vendor vendor = new Vendor();
        vendor.setId(vendorDto.id());
        vendor.setName(vendorDto.name());
        vendor.setDescription(vendorDto.description());
        vendor.setContactMail(vendorDto.contactMail());
        vendor.setContactPhone(vendorDto.contactPhone());
        vendor.setAddress(vendorDto.address());
        vendor.setPassword(vendorDto.password());
        return vendor;
    }
}
