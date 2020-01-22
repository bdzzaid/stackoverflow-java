package zaid.opencsv.translatemappingstrategy;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
public class Article
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String ean;

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("Article{");
        sb.append("ean='").append(ean).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
