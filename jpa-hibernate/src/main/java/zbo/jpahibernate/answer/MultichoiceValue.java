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

    public MultichoiceValue(Set<MultichoiceOptionSelected> choice)
    {
        this.choice = choice;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "mcValueId")
    private Set<MultichoiceOptionSelected> choice = new HashSet<>();

    public Set<MultichoiceOptionSelected> getChoice()
    {
        return choice;
    }

    public void setChoice(Set<MultichoiceOptionSelected> choice)
    {
        this.choice = choice;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("MultichoiceValue{");
        sb.append("choice=").append(choice);
        sb.append('}');
        return sb.toString();
    }
}
