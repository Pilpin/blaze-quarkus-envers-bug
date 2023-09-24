package io.pilpin.mre;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@Audited(withModifiedFlag = true)
@Entity
@Table(name = "coordonnee")
@Inheritance
@DiscriminatorColumn(name = "discriminator")
@DynamicUpdate
public abstract class ContactInfoEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Version
    @NotAudited
    private Short version;

    public static ContactInfoEntity build(ContactInfoInput input) {
        if (input instanceof AddressInput a) {
            return new AddressEntity(a);
        } else if (input instanceof PhoneInput t) {
            return new PhoneEntity(t);
        } else {
            return null;
        }
    }

    public abstract <T extends ContactInfoInput> void update(T input);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContactInfoEntity that)) return false;

        if (!Objects.equals(id, that.id)) return false;
        return Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (version != null ? version.hashCode() : 0);
        return result;
    }
}
