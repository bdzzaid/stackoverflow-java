package zbo.openapi.cards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import zbo.openapi.cards.Card;

import java.util.*;
import java.util.function.Function;

public class CardPackage
{

    public static final List<Card> PACKAGE_52 = new ArrayList<>();

    public static final Function<Integer, String> PACKAGE_52_DISPLAY_VALUE = (val ->
    {
        switch (val) {
            case 1:
                return "AS";
            case 11:
                return "V";
            case 12:
                return "D";
            case 13:
                return "R";
            default:
                return val.toString();
        }
    });


    static {
        for(int i = 1; i<13;i++)
        {
            for (Card.Shape shape : Card.Shape.values())
                PACKAGE_52.add(new Card(i, shape).withDisplayValue(PACKAGE_52_DISPLAY_VALUE));
        }
    }


}
