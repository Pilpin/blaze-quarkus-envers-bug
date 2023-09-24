package io.pilpin.mre;

import org.hibernate.envers.RevisionListener;

public class CustomRevisionEntityListener implements RevisionListener {
    public void newRevision(Object revisionEntity) {
        CustomRevisionEntity customRevisionEntity = (CustomRevisionEntity) revisionEntity;
    }
}


