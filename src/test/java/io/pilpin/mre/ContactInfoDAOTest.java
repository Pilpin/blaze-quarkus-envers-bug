package io.pilpin.mre;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.*;

@QuarkusTest
class ContactInfoDAOTest {
    @Inject
    ContactInfoDAO repository;

    @Test
    void revisions_should_exist() {
        PhoneInput input = new PhoneInput("00 00 00 00 00");
        ContactInfoView em = repository.create(input);

        input = new PhoneInput("11 11 11 11 11");
        em = repository.edit(em.getId(), input);

        repository.remove(em.getId());
    }
}