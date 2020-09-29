package zbo.jpahibernate.account;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public class Account
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private int id;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "transactionId", foreignKey = @ForeignKey(name = "Fk_account_transaction_id"))
    private Set<Transaction> transactions = new HashSet<>();

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Set<Transaction> getTransactions()
    {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions)
    {
        this.transactions = transactions;
    }
}
