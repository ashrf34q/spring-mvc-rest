package guru.springframework.springmvcrest.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class VendorListDTO {
    List<VendorDTO> vendors;
}
