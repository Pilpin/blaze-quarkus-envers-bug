package io.pilpin.mre;

import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.parser.EntityMetamodel;
import com.blazebit.persistence.view.EntityViewManager;
import com.blazebit.persistence.view.EntityViewSetting;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import jakarta.transaction.Transactional;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.envers.boot.internal.EnversService;

import java.util.List;

@ApplicationScoped
public class ContactInfoDAO implements PanacheRepositoryBase<ContactInfoEntity, Long> {
    @Inject
    EntityManager em;
    @Inject
    CriteriaBuilderFactory cbf;
    @Inject
    EntityViewManager evm;

    public ContactInfoView get(Long id) {
        if (id == null) return null;
        return evm.find(em, ContactInfoView.class, id);
    }

    @Transactional
    public ContactInfoView create(ContactInfoInput input) {
        ContactInfoEntity coordonnee = ContactInfoEntity.build(input);
        this.persist(coordonnee);

        return this.get(coordonnee.getId());
    }

    @Transactional
    public ContactInfoView edit(Long id, ContactInfoInput input) {
        ContactInfoEntity coordonnee = this.findById(id);
        coordonnee.update(input);

        return this.get(id);
    }

    @Transactional
    public void remove(Long id) {
        this.deleteById(id);
    }

    public List<ContactInfoRevisionView> getRevisions(Long id) {
        if (id == null) return List.of();

        SessionFactoryImplementor session = em.unwrap(SessionImplementor.class).getSessionFactory();
        EnversService envers = session.getServiceRegistry().getService(EnversService.class);
        String entityName = session.getMappingMetamodel().getEntityDescriptor(ContactInfoEntity.class).getEntityName();
        String auditEntityName = envers.getConfig().getAuditEntityName(entityName);
        EntityMetamodel metamodel = cbf.getService(EntityMetamodel.class);
        EntityType<?> auditedEntity = metamodel.getEntity(auditEntityName);
        System.out.println(auditedEntity.getAttributes());
        var originalId = auditedEntity.getAttribute("originalId");
        System.out.println(originalId.getPersistentAttributeType().describeConstable());

        var cb = cbf.create(em, ContactInfoRevisionCTE.class)
                .with(ContactInfoRevisionCTE.class)
                .from(auditedEntity, "aud")
                .where("aud.originalId.id").eq(id)
                .bind("id").select("aud.originalId.rev.id")
                .bind("timestamp").select("aud.originalId.rev.timestamp")
                .bind("userId").select("aud.originalId.rev.userId")
                .bind("revisionType").select("aud.revtype")
                .end();

        var settings = EntityViewSetting.create(ContactInfoRevisionView.class);
        return evm.applySetting(settings, cb).getResultList();
    }
}
