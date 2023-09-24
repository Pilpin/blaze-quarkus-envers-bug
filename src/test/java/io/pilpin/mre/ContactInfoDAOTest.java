package io.pilpin.mre;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.*;

import java.util.List;

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

        List<ContactInfoRevisionView> revisions = repository.getRevisions(em.getId());
        revisions.forEach(it -> System.out.printf(
                "id=%d, ts=%s, user=%s, revtype=%s, coordonnee=%d\n",
                it.getId(),
                it.getTimestamp(),
                it.getUserId(),
                it.getRevisionType(),
                it.getContactInfoId()
        ));

        Assertions.assertNotEquals(0, revisions.size());
    }
}