package zbo.jpahibernate.answer;

import javax.persistence.*;

@Entity
@Table(name = "answer_multichoice")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class TextValue extends Value
{
    @Column
    private String text;

    public TextValue()
    {
    }

    public TextValue(String text)
    {
        this.text = text;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("TextValue{");
        sb.append("text='").append(text).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
