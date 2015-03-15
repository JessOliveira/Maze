package pt.c02classes.s01knowledge.s02app.app;

import pt.c02classes.s01knowledge.s01base.impl.BaseConhecimento;
import pt.c02classes.s01knowledge.s01base.impl.Statistics;
import pt.c02classes.s01knowledge.s01base.inter.IBaseConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;
import pt.c02classes.s01knowledge.s01base.inter.IStatistics;
import pt.c02classes.s01knowledge.s02app.actors.EnquirerAnimals;
import pt.c02classes.s01knowledge.s02app.actors.EnquirerMaze;
import pt.c02classes.s01knowledge.s02app.actors.ResponderMaze;
import pt.c02classes.s01knowledge.s02app.actors.ResponderAnimals;


import java.util.Scanner;

public class OrchestratorInit
{
	public static void main(String[] args)
	{
		IEnquirer enq = null;
		IResponder resp = null;
		IStatistics stat;
		
		IBaseConhecimento base = new BaseConhecimento();
		
		String game;
		Scanner scanner = new Scanner(System.in);
		stat = new Statistics();
		
		boolean cont = true;
		while (cont){
			System.out.print("Escolha Animals(A) ou Maze(M): ");
			game = scanner.nextLine();
			switch (game.toUpperCase()){
			case "A":
				base.setScenario("animals");			//Choose Animals
				String listaAnimais[] = base.listaNomes();
		        for (int animal = 0; animal < listaAnimais.length; animal++) {
					System.out.println("Enquirer com " + listaAnimais[animal] + "...");
					stat = new Statistics();
					resp = new ResponderAnimals(stat, listaAnimais[animal]);
					enq = new EnquirerAnimals();
					enq.connect(resp);
					enq.discover();
					System.out.println("----------------------------------------------------------------------------------------\n");
		        }	
		        break;
			case "M":
				base.setScenario("Maze");				//Choose Maze
				System.out.println("Enquirer com Mordor...");
				resp = new ResponderMaze(stat, "mordor");
				enq = new EnquirerMaze();
				enq.connect(resp);
				enq.discover();
				System.out.println("----------------------------------------------------------------------------------------\n");
				cont = false;
				break;
			default:
				System.out.println("Jogo não encontrado, digite C para continuar");
				game = scanner.nextLine();
				switch (game.toUpperCase()){
					case "C":
						break;							//Continue
					default:
						cont = false;					//Leave the game
				        System.out.println("----------------------------------------------------------------------------------------\n");
						break;
				}
				break;
			}
		}
        if (enq != null) {
            enq.connect(resp);
            enq.discover();
        }
		}
	}

