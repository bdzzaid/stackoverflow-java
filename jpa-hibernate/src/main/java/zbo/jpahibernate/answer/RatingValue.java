package zbo.jpahibernate.answer;

import javax.persistence.*;

@Entity
@Table(name = "answer_rating")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

public class RatingValue extends Value
{

    @Column
    private int rating;

    public RatingValue()
    {
    }

    public RatingValue(int rating)
    {
        this.rating = rating;
    }

    public int getRating()
    {
        return rating;
    }

    public void setRating(int rating)
    {
        this.rating = rating;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("RatingValue{");
        sb.append("rating=").append(rating);
        sb.append('}');
        return sb.toString();
    }
}
