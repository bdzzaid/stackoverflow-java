package zaid.opencsv.translatemappingstrategy;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Data //lombok annotation
public class Transaction implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long transactionId;

    @Size(max = 30)
    private String timestamp;

    private int receiptNo;

    private int receiptPosNo; // (e.g. 1-x then again 1-y)

    @JoinColumn(name = "article$ean", referencedColumnName = "ean")
    @OneToOne(cascade = CascadeType.ALL)
    private Article article$ean;

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("Transaction{");
        sb.append("transactionId=").append(transactionId);
        sb.append(", timestamp='").append(timestamp).append('\'');
        sb.append(", receiptNo=").append(receiptNo);
        sb.append(", receiptPosNo=").append(receiptPosNo);
        sb.append(", article$ean=").append(article$ean);
        sb.append('}');
        return sb.toString();
    }
}
