package zbo.jpahibernate.answer;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "answer_multichoice")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class MultichoiceValue extends Value
{
    public MultichoiceValue() {}

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "mcValueId")
    private Set<MultichoiceOptionSelected> selected = new HashSet<>();

    public MultichoiceValue(Set<MultichoiceOptionSelected> selected)
    {
        this.selected = selected;
    }

    public Set<MultichoiceOptionSelected> getSelected()
    {
        return selected;
    }

    public void setSelected(Set<MultichoiceOptionSelected> selected)
    {
        this.selected = selected;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("MultichoiceValue{");
        sb.append("selected=").append(selected);
        sb.append('}');
        return sb.toString();
    }
}
