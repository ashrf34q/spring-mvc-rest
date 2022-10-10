package guru.springframework.springmvcrest.services;

import guru.springframework.springmvcrest.api.v1.mapper.VendorMapper;
import guru.springframework.springmvcrest.api.v1.model.VendorDTO;
import guru.springframework.springmvcrest.domain.Vendor;
import guru.springframework.springmvcrest.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorMapper vendorMapper;
    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorMapper vendorMapper, VendorRepository vendorRepository) {
        this.vendorMapper = vendorMapper;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public List<VendorDTO> getAllVendors() {
        return vendorRepository.findAll()
                .stream()
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                    vendorDTO.setVendor_URL(getReturnURL(vendor.getId()));
                    return vendorDTO;
                })
                .collect(Collectors.toList());
    }

    private String getReturnURL(Long id) {
        return "/shop/vendors/" + id;
    }

    @Override
    public VendorDTO getVendorById(Long id) {
        return vendorRepository.findById(id)
                .map(vendorMapper::vendorToVendorDTO)
                .map(vendorDTO -> {
                    vendorDTO.setVendor_URL(getReturnURL(id));
                    return vendorDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDTO createVendor(VendorDTO vendorDTO) {

        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);

        return saveAndReturn(vendor);
    }

    private VendorDTO saveAndReturn(Vendor vendor) {
        Vendor savedVendor = vendorRepository.save(vendor);

        VendorDTO savedDTO = vendorMapper.vendorToVendorDTO(savedVendor);

        savedDTO.setVendor_URL(getReturnURL(savedVendor.getId()));

        return savedDTO;
    }

    @Override
    public VendorDTO updateVendor(Long id, VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);
        vendor.setId(id);

        return saveAndReturn(vendor);
    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
        return vendorRepository.findById(id)
                .map(vendor -> {
                    if(vendorDTO != null){
                        vendor.setName(vendorDTO.getName());
                    }

                    VendorDTO savedDTO = vendorMapper.vendorToVendorDTO(vendor);

                    savedDTO.setVendor_URL(getReturnURL(id));
                    return savedDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteVendor(Long id) {

        if(vendorRepository.findById(id).isPresent()) {
            vendorRepository.deleteById(id);
        }
        else{
            throw new ResourceNotFoundException("ID doesn't exist!");
        }
    }
}
