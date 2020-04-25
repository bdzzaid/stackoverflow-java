package zbo.jpahibernate.answer;

import javax.persistence.*;

@Entity
@Table(name = "answer_options")
public class MultichoiceOptionSelected
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private int id;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("MultichoiceOptionSelected{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
