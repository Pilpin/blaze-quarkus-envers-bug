package io.pilpin.mre;

import com.blazebit.persistence.view.EntityViewManager;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ContactInfoDAO {
    @Inject
    EntityManager em;
    @Inject
    EntityViewManager evm;

    public ContactInfoView get(Long id) {
        if (id == null) return null;
        return evm.find(em, ContactInfoView.class, id);
    }

    @Transactional
    public ContactInfoView create(ContactInfoInput input) {
        ContactInfoEntity coordonnee = ContactInfoEntity.build(input);
        em.persist(coordonnee);

        return this.get(coordonnee.getId());
    }

    @Transactional
    public ContactInfoView edit(Long id, ContactInfoInput input) {
        ContactInfoEntity coordonnee = em.find(ContactInfoEntity.class, id);
        coordonnee.update(input);

        return this.get(id);
    }

    @Transactional
    public void remove(Long id) {
        em.remove(em.find(ContactInfoEntity.class, id));
    }
}
