package guru.springframework.springmvcrest.services;

import guru.springframework.springmvcrest.api.v1.mapper.VendorMapper;
import guru.springframework.springmvcrest.api.v1.model.VendorDTO;
import guru.springframework.springmvcrest.domain.Vendor;
import guru.springframework.springmvcrest.repositories.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class VendorServiceTest {
    public static final Long ID = 4L;
    public static final String URL = "/shop/vendors/4";
    public static final String NAME = "Los Pollos Hermanos";
//    public static final String LAST_NAME = "Goodman";

    @Mock
    VendorRepository vendorRepository;

    VendorService vendorService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        vendorService = new VendorServiceImpl(VendorMapper.INSTANCE, vendorRepository);
    }

    @Test
    void getAllVendorsTest(){
        List<Vendor> vendors = Arrays.asList(new Vendor(), new Vendor());

        when(vendorRepository.findAll()).thenReturn(vendors);

        List<VendorDTO> vendorDTOS = vendorService.getAllVendors();

        assertEquals(2, vendorDTOS.size());
    }


    @Test
    void getVendorById(){
        Vendor vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(NAME);

        when(vendorRepository.findById(anyLong())).thenReturn(Optional.of(vendor));

        VendorDTO vendorDTO = vendorService.getVendorById(ID);

        assertEquals(NAME, vendorDTO.getName());
    }


    @Test
    void createVendor() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        Vendor savedVendor = new Vendor();
        savedVendor.setName(NAME);
        savedVendor.setId(ID);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        VendorDTO savedDTO = vendorService.createVendor(vendorDTO);

        assertEquals(NAME, savedDTO.getName());
        assertEquals(URL, savedDTO.getVendor_URL());
    }


    @Test
    void putVendorTest() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        Vendor savedVendor = new Vendor();
        savedVendor.setName(NAME);
        savedVendor.setId(ID);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        VendorDTO savedDTO = vendorService.updateVendor(ID, vendorDTO);

        assertEquals(NAME, savedDTO.getName());
        assertEquals(URL, savedDTO.getVendor_URL());
    }

    @Test
    void patchVendorTest(){

        // new vendor
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        // original vendor
        Vendor vendor = new Vendor();
        vendor.setName("Walter White LLC");
        vendor.setId(ID);

        Vendor savedVendor = new Vendor();
        savedVendor.setName(NAME);
        savedVendor.setId(ID);

        when(vendorRepository.findById(anyLong())).thenReturn(Optional.of(vendor));
        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        VendorDTO savedDTO = vendorService.patchVendor(ID, vendorDTO);

        assertEquals(NAME, savedDTO.getName());
        assertEquals(URL, savedDTO.getVendor_URL());
    }

    @Test
    void deleteVendorById(){
        Vendor vendor = new Vendor();
        vendor.setId(ID);

        when(vendorRepository.findById(anyLong())).thenReturn(Optional.of(vendor));

        vendorService.deleteVendor(ID);

        verify(vendorRepository, times(1)).deleteById(anyLong());
    }
}