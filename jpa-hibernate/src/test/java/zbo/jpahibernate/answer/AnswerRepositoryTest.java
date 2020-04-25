package zbo.jpahibernate.answer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AnswerRepositoryTest
{
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private MCOptionRepository mcOptionRepository;
    @Autowired
    private MCValueRepository mcValueRepository;
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private TextValueRepository textValueRepository;
    @Test
    void findAll()
    {

        textValueRepository.deleteAll();
        ratingRepository.deleteAll();
        mcValueRepository.deleteAll();
        mcOptionRepository.deleteAll();
        answerRepository.deleteAll();


        Set<MultichoiceOptionSelected> choice = new HashSet<>();
        choice.add(new MultichoiceOptionSelected());
        choice.add(new MultichoiceOptionSelected());

        Answer answer = new Answer();
        answer.setValue(new MultichoiceValue(choice));
        answerRepository.save(answer);

        answer = new Answer();
        answer.setValue(new RatingValue(1));
        answerRepository.save(answer);
        answer = new Answer();
        answer.setValue(new TextValue("hello"));
        answerRepository.save(answer);

        System.out.println( answerRepository.findAll());
        System.out.println( answerRepository.findAllByGraph());

    }
}
