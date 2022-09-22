package br.com.sharetips.entities.pk;

import br.com.sharetips.entities.Subject;
import br.com.sharetips.entities.Tip;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TipSubjectPK implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "tip_id")
    private Tip tip;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    public Tip getTip() {
        return tip;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }

    public Subject getSubject() {
        return subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipSubjectPK that = (TipSubjectPK) o;
        return tip.equals(that.tip) && subject.equals(that.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tip, subject);
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
