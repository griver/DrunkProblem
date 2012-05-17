package ProblemOfDrunks;

import ProblemOfDrunks.game.exeption.GameBuilderException;
import ProblemOfDrunks.game.exeption.GameStepException;
import ProblemOfDrunks.field.utils.FieldPrinter;
import ProblemOfDrunks.field.impl.SquareField;
import ProblemOfDrunks.game.impl.DrunkGame;
import ProblemOfDrunks.game.IGame;
import ProblemOfDrunks.game.IGameBuilder;
import ProblemOfDrunks.game.impl.StandartBuilder;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 06.03.12
 * Time: 0:29
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    static public void main(String[] args) {
        try{
            IGameBuilder builder = new StandartBuilder(new SquareField(15, 15), new DrunkGame());
            //IGameBuilder builder = new StandartBuilder(new HexagonalField(15, 15), new DrunkGame());
            IGame game = builder.buildGame();
            for(int i = 1; i<= 800; i++) {
                game.nextStep();

                if(i % 100 == 0) {
                    System.out.println("Ход номер: "+ Integer.toString(i));
                    FieldPrinter.print(game.getField());
                }
            }
        }catch (GameBuilderException e) {
            System.out.println("Exception occurred in buildGame() method");
        } catch (GameStepException e) {
            System.out.println("Game Error");
        }
    }
}
