package io.pilpin.mre;

import com.blazebit.persistence.CTE;
import jakarta.persistence.*;
import org.hibernate.envers.RevisionType;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@CTE
public class ContactInfoRevisionCTE {
    @Id
    private Long id;

    private LocalDateTime timestamp;

    private UUID userId;

    @Convert(converter = RevisionTypeAttributeConverter.class)
    private RevisionType revisionType;

    private Long contactInfoId;
}
