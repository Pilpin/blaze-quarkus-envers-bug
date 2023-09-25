package io.pilpin.mre;

public record AddressInput(
        String street,
        String postalcode,
        String city,
        String country
) implements ContactInfoInput {
}
