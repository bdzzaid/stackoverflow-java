package zbo.jpahibernate.answer;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 *
 */
public interface AnswerRepository extends JpaRepository<Answer, Integer>
{

    @Query("SELECT a FROM Answer a")
    @EntityGraph(attributePaths = {"value"}, type = EntityGraph.EntityGraphType.LOAD)
    List<Answer> findAllByGraph();

}
