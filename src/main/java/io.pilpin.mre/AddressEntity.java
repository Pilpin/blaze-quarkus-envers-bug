package io.pilpin.mre;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.hibernate.envers.Audited;

@Audited(withModifiedFlag = true)
@Entity
@DiscriminatorValue("ADDRESS")
public class AddressEntity extends ContactInfoEntity {
    private String street;
    private String postalcode;
    private String city;
    private String country;

    public AddressEntity() {

    }

    public AddressEntity(AddressInput input) {
        this.update(input);
    }

    @Override
    public <T extends ContactInfoInput> void update(T input) {
        if (input instanceof AddressInput a) {
            this.street = a.street();
            this.postalcode = a.postalcode();
            this.city = a.city();
            this.country = a.country();
        }
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
