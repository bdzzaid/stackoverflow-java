package zbo.openapi.cards;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.function.Consumer;
import java.util.function.Function;

@Schema(description = "Card associated to a game")
public class Card
{

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Valeur de la card (1 -> 13), 1 = AS, 13 = Roi", example = "11")
    private int value;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Pique, Coeur, Carreau, Trefle", example = "Coeur")
    private Shape shape;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "UTF-16 Shape form see https://unicode.org/emoji/charts/full-emoji-list.html#2660", example = "Coeur")
    private char shapeForm;

    @JsonIgnore
    private Function<Integer, String> displayValue;

    @JsonCreator
    public Card(int value, Shape shape)
    {
        this.value = value;
        this.shape = shape;
        this.shapeForm = getUtf16Emoji(shape);
        this.displayValue = CardPackage.PACKAGE_52_DISPLAY_VALUE;
    }

    public Card withDisplayValue(Function<Integer, String> displayValue) {
        this.displayValue = displayValue;
        return this;
    }

    public int getValue()
    {
        return value;
    }

    public void setValue(int value)
    {
        this.value = value;
    }

    public Shape getShape()
    {
        return shape;
    }

    public void setShape(Shape shape)
    {
        this.shape = shape;
    }

    private char getUtf16Emoji(Card.Shape shape) {
        // https://unicode.org/emoji/charts/full-emoji-list.html#2660
        switch (shape) {
            case PIQUE:
                return '\u2660';
            case COEUR:
                return '\u2665';
            case CARREAU:
                return '\u2666';
            case TREFLE:
                return '\u2663';
            default:
                return '?';
        }

    }

    @Override
    public String toString()
    {
        return String.format("%2s %s - %2s DE %s ", value, shapeForm, this.displayValue.apply(value) , shape);
    }

    @Schema(description = "List of 2FA types available in the platform")
    public enum Shape
    {
        PIQUE,
        COEUR,
        CARREAU,
        TREFLE;


    }
}
