package zbo.jpahibernate.answer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.HashSet;
import java.util.Set;


@SpringBootTest
public class AnswerRepositoryTest
{
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private PlatformTransactionManager transactionManager;

    @BeforeEach
    @Transactional
    void insert()
    {
        Answer answer = new Answer();
        if (answerRepository.count() > 0)
        {
            Set<MultichoiceOptionSelected> choice = new HashSet<>();
            choice.add(new MultichoiceOptionSelected());
            choice.add(new MultichoiceOptionSelected());
            answer.setValue(new MultichoiceValue(choice));
            answerRepository.save(answer);
        }
        answer = new Answer();
        answer.setValue(new RatingValue(1));
        answerRepository.save(answer);
        answer = new Answer();
        answer.setValue(new TextValue("hello"));
        answerRepository.save(answer);
    }

    @Test
    void findAll()
    {
        System.out.println( answerRepository.findAllByGraph());
    }

    @Test
    @Transactional
    void findAllAgain()
    {
        System.out.println(answerRepository.findAllByGraph());
    }

    @Test
    void retry()
    {
        TransactionTemplate template = new TransactionTemplate(transactionManager);
        template.executeWithoutResult(s -> insert());
        template.executeWithoutResult(s -> System.out.println(answerRepository.findAllByGraph()));
    }

}
