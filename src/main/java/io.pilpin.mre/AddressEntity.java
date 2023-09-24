package io.pilpin.mre;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.envers.Audited;

@Getter
@Setter
@NoArgsConstructor
@Audited(withModifiedFlag = true)
@Entity
@DiscriminatorValue("ADDRESS")
public class AddressEntity extends ContactInfoEntity {
    private String street;
    private String postalcode;
    private String city;
    private String country;

    public AddressEntity(@NonNull AddressInput input) {
        this.update(input);
    }

    @Override
    public <T extends ContactInfoInput> void update(T input) {
        if (input instanceof AddressInput a) {
            this.street = a.getStreet();
            this.postalcode = a.getPostalcode();
            this.city = a.getCity();
            this.country = a.getCountry();
        }
    }
}
