package io.pilpin.mre;

import com.blazebit.persistence.view.EntityView;

@EntityView(AddressEntity.class)
public interface AddressView extends ContactInfoView {
    String getStreet();

    String getPostalcode();

    String getCity();

    String getCountry();
}
