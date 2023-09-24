package io.pilpin.mre;

import jakarta.persistence.Column;
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
@DiscriminatorValue("PHONE")
public class PhoneEntity extends ContactInfoEntity {
    @Column(length = 30)
    private String number;

    public PhoneEntity(@NonNull PhoneInput input) {
        this.update(input);
        this.update(input);
    }

    @Override
    public <T extends ContactInfoInput> void update(T input) {
        if (input instanceof PhoneInput p) {
            this.number = p.getNumber();
        }
    }
}
