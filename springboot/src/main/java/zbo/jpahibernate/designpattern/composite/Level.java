package zbo.jpahibernate.designpattern.composite;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "level")
public class Level
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private int id;
    @Column
    private String name;
    @ManyToOne
    @JoinColumn(name = "parentId")
    private Level parent;
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Level> children;

    @Transient
    public boolean isLeaf()
    {
        return (children == null || children.size() == 0);
    }

    @Transient
    public boolean isRoot()
    {
        return (parent == null);
    }

    @Transient
    public int getLevelNumber()
    {
        return getLevelNumber(1);
    }

    @Transient
    private int getLevelNumber(int currentLevel)
    {
        return parent == null ? currentLevel : parent.getLevelNumber(currentLevel + 1);
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Level getParent()
    {
        return parent;
    }

    public void setParent(Level parent)
    {
        this.parent = parent;
    }

    public Set<Level> getChildren()
    {
        return children;
    }

    public void setChildren(Set<Level> children)
    {
        this.children = children;
    }
}
