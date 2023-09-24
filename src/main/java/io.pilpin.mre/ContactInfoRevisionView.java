package io.pilpin.mre;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import org.hibernate.envers.RevisionType;

import java.time.LocalDateTime;
import java.util.UUID;

@EntityView(ContactInfoRevisionCTE.class)
public interface ContactInfoRevisionView {
    @IdMapping
    Long getId();

    LocalDateTime getTimestamp();

    UUID getUserId();

    RevisionType getRevisionType();

    Long getContactInfoId();
}
