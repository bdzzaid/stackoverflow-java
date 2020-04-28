package zbo.jpahibernate.answer;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "answer")
@NamedEntityGraphs({
        @NamedEntityGraph(name = "graph.Answer", attributeNodes = @NamedAttributeNode(value = "value")),
        @NamedEntityGraph(
                name = "graph.AnswerMultichoice",
                attributeNodes = @NamedAttributeNode(value = "value"),
                subgraphs = {
                        @NamedSubgraph(
                                name = "graph.AnswerMultichoice.selected",
                                attributeNodes = {
                                        @NamedAttributeNode("selected")
                                }
                        )
                }
        )
}
)
public class Answer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "value_id", referencedColumnName = "id")
    private Value value;

    @Column
    private LocalDateTime timestamp;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public LocalDateTime getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp)
    {
        this.timestamp = timestamp;
    }

    public Value getValue()
    {
        return value;
    }

    public void setValue(Value value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("Answer{");
        sb.append("id=").append(id);
        sb.append(", value=").append(value);
        sb.append(", timestamp=").append(timestamp);
        sb.append('}');
        return sb.toString();
    }
}
