package io.pilpin.mre;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressInput implements ContactInfoInput {
    private String street;
    private String postalcode;
    private String city;
    private String country;
}
