package io.pilpin.mre;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.hibernate.envers.RevisionType;

@Converter
public class RevisionTypeAttributeConverter implements AttributeConverter<RevisionType, Byte> {
    @Override
    public Byte convertToDatabaseColumn(RevisionType attribute) {
        if (attribute == null) return null;
        return attribute.getRepresentation();
    }

    @Override
    public RevisionType convertToEntityAttribute(Byte dbData) {
        return RevisionType.fromRepresentation(dbData);
    }
}
