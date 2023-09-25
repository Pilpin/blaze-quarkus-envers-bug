package io.pilpin.mre;

import jakarta.persistence.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import java.util.Objects;

@Audited(withModifiedFlag = true)
@Entity
@Table(name = "coordonnee")
@Inheritance
@DiscriminatorColumn(name = "discriminator")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getVersion() {
        return version;
    }

    public void setVersion(Short version) {
        this.version = version;
    }

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
