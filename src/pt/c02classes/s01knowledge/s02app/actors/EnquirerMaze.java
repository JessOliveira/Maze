package pt.c02classes.s01knowledge.s02app.actors;

import java.util.Scanner;

import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;

public class EnquirerMaze implements IEnquirer {

	IResponder responder;
	
	public void connect(IResponder responder) {
		this.responder = responder;
	}
	
	public boolean discover() {
		Scanner scanner = new Scanner(System.in);
		
		String tipo = "P";
		String[] direction = {"norte", "leste", "oeste", "sul"};
		int i =0;
		
		while (!tipo.equalsIgnoreCase("F")) {

		   switch (tipo.toUpperCase()) {
		      case "P": String resposta = responder.ask(direction[i]);
		                if(resposta.equalsIgnoreCase("passagem"))
		                	tipo = "M";
		                else{
			                if(resposta.equalsIgnoreCase("saida"))
			                	tipo = "F";
			                else{
			                	i++;
		                		tipo = "P";
			                }
		                }
		                break;
		      case "M": boolean moveu = responder.move(direction[i]);
		                System.out.println((moveu)?"  Movimento executado!":"Não é possível mover");
		                
		                i=0;
		                tipo = "P";
		                break;
		   }
		}
		
		boolean moveu = responder.move(direction[i]);
        System.out.println((moveu)?"  Movimento executado!":"Não é possível mover");
        
		if (responder.finalAnswer("cheguei"))
			System.out.println("Você encontrou a saida!");
		else
			System.out.println("Fuém fuém fuém!");
		
		scanner.close();
		
		return true;
	}
	
}
