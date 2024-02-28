package GUI_exam;

import io.cucumber.java.en.*;
import sol2.GUI;
import sol2.Logic;
import sol2.LogicImpl;
import sol2.Position;

import static org.junit.jupiter.api.Assertions.*;

public class GUIExamSteps {

    private GUI grid;
    private Logic logic;

    @Given("una griglia di celle di dimensione prefissata {int}")
    public void unaGrigliaDiCelleDiDimensionePrefissata(int arg0) { this.grid = new GUI(arg0); }

    @Given("una logica per il sistema di dimensione prefissata {int}")
    public void unaLogicaPerIlSistema(int arg0) { this.logic = new LogicImpl(arg0); }

    @When("l'utente clicca su una cella in posizione {int} and {int}")
    public void lUtenteCliccaSuUnaCellaInPosizioneXY(int arg0, int arg1) {
        this.logic.hit(new Position(arg0, arg1));
    }

    @Then("quella cella {int},{int} dovrebbe essere numerata in modo incrementale")
    public void quellaCellaDovrebbeEssereNumerataInModoIncrementale(int arg0, int arg1) {
        Position position = new Position(arg0, arg1);
        this.logic.getMarks().add(position);
        int expectedNumber = this.logic.getMarks().indexOf(position) + 1;
        assertTrue(this.logic.getMark(position).isPresent() && this.logic.getMark(position).get() == expectedNumber);
    }

    @And("la cella in posizione {int},{int} dovrebbe mostrare il numero")
    public void laCellaInPosizioneXYDovrebbeMostrareIlNumero(int arg0, int arg1) {
        Position position = new Position(arg0, arg1);
        this.logic.getMarks().add(position);
        assertTrue(this.logic.getMark(position).isPresent() && this.logic.getMark(position).get() == this.logic.getMarks().indexOf(position) + 1);
    }

    @Given("con alcune celle già selezionate {int},{int}")
    public void cheUnaGrigliaDiCelleConAlcuneCelleGiàSelezionate(int arg0, int arg1) {
        this.logic.hit(new Position(arg0, arg1));
    }

    @When("l'utente clicca su una cella in posizione {int},{int} che non è adiacente a nessuna cella selezionata {int},{int}")
    public void lUtenteCliccaSuUnaCellaInPosizioneXYCheNonÈAdiacenteANessunaCellaSelezionata(int arg0, int arg1, int arg2, int arg3) {
        Position selectedPosition = new Position(arg0, arg1);
        Position nonAdjacentPosition = new Position(arg2, arg3);
        this.logic.hit(nonAdjacentPosition);
        assertFalse(this.logic.getMarks().contains(selectedPosition));
    }
}
