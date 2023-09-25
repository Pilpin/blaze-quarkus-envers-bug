package io.pilpin.mre;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.EntityViewInheritance;
import com.blazebit.persistence.view.IdMapping;

@EntityView(ContactInfoEntity.class)
@EntityViewInheritance
public interface ContactInfoView {
    @IdMapping
    Long getId();

    Short getVersion();
}
