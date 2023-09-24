package io.pilpin.mre;

import com.blazebit.persistence.view.EntityView;

@EntityView(PhoneEntity.class)
public interface PhoneView extends ContactInfoView {
    String getNumber();
}
