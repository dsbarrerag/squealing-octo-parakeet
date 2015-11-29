package util;

import java.util.List;
import java.util.regex.*;

import org.antlr.v4.runtime.Token;

import controller.App;
import model.TipoErrores;

public class Ident {
	
	public void identCheck(List<Token> tokens, int identLevel) {
		int rt = 0;
		Pattern space = Pattern.compile(" ");
		Pattern tab = Pattern.compile("\t");
		for(Token token: tokens){
			Matcher sp = space.matcher(token.getText());
			Matcher tb = tab.matcher(token.getText());
			if(sp.find(0)){
				rt += spacesCheck(sp, token);
			}
			else if(tb.find()){
				rt += token.getText().length();
			}
		}
		if(rt != identLevel)
			App.getInstance().setError(tokens.get(0).getLine(), tokens.get(0).getText() , TipoErrores.IDENTACION);
	}

	private int spacesCheck(Matcher sp, Token token) {
		int rt = 0;
		int spaces = 0;
		while(sp.find()){
			spaces ++;
		}
		spaces++;
		if(spaces % 4 == 0)
			rt += spaces/4;
		else
			App.getInstance().setError(token.getLine(), token.getText(), TipoErrores.ESPACIOS);
		return rt;
		
	}

	
	
}
