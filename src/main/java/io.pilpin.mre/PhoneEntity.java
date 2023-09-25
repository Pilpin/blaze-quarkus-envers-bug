package io.pilpin.mre;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.hibernate.envers.Audited;

@Audited(withModifiedFlag = true)
@Entity
@DiscriminatorValue("PHONE")
public class PhoneEntity extends ContactInfoEntity {
    @Column(length = 30)
    private String number;

    public PhoneEntity() {

    }

    public PhoneEntity(PhoneInput input) {
        this.update(input);
        this.update(input);
    }

    @Override
    public <T extends ContactInfoInput> void update(T input) {
        if (input instanceof PhoneInput p) {
            this.number = p.number();
        }
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
