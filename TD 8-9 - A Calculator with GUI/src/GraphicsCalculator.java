import javafx.application.Application;
import javafx.stage.Stage;   
import javafx.scene.*;   
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class GraphicsCalculator extends Application {   
	Tokenizer tok;

	Label resultat;

	Button b(char c) {
		Button b = new Button(String.valueOf(c));
		int x = 50, y = 40;
		b.setMaxSize(x, y);b.setMinSize(x, y);
		b.setOnAction(value -> update(b.getText().charAt(0)));
		return b;
	}

	private void update(char c) {
		this.tok.readChar(c);

		switch(c) {
			case 'C': resultat.setText(""); break;
			case '=': 
				resultat.setText(String.valueOf(this.tok.calc.getResult())); 
				break;
			default: resultat.setText(resultat.getText() + c);
		}
	}

	@Override
	public void start(Stage stage) {

		tok = new Tokenizer();

		resultat = new Label("");
		resultat.setMinHeight(40); resultat.setMaxHeight(40);

		Scene scene = new Scene(new VBox(
				new HBox(resultat), 
				new HBox(b('7'), b('8'), b('9'), b('+')),
				new HBox(b('4'), b('5'), b('6'), b('-')),
				new HBox(b('1'), b('2'), b('3'), b('*')),
				new HBox(b('0'), b('.'), b('C'), b('/')),
				new HBox(b('('), b(')'), b('$'), b('='))
				));
		stage.setScene(scene);

		scene.setOnKeyTyped(e -> {
			Character c = e.getCharacter().charAt(0);
			if("1234567890C=+-*./()$".contains(""+c)) update(c);
		});

		stage.setResizable(false);

		stage.setTitle("CalculatriX");
		stage.setHeight(265); stage.setWidth(200);
		stage.setX(600);stage.setY(300);

		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}