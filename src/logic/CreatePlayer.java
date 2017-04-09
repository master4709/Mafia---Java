package logic;

import playerInfo.*;

public class CreatePlayer {
	
	public Player createPlayer(String name, String role, int position){
		switch(role){
		case "Mafia: Barman": 		return new Barman(name,position);
		case "Bodyguard": 			return new Bodyguard(name,position);
		case "Detective": 			return new Detective(name,position);
		case "Doctor": 				return new Doctor(name,position);
		case "Mafia- GodFather": 	return new GodFather(name,position);
		case "Mafia: Hitman": 		return new Hitman(name,position);
		case "Lyncher": 			return new Lyncher(name,position);
		case "Survivor": 			return new Survivor(name,position);
		case "Vigilante": 			return new Vigilante(name,position);
		case "Mafia: Goon":			return new Goon(name,position);
		default: 					return new Town(name,position);
		}
	}
	
	public Player createPlayer(String name, String role, int position, int status, boolean lynched){
		switch(role){
		case "Mafia: Barman": 		return new Barman(name,position,status,lynched);
		case "Bodyguard": 			return new Bodyguard(name,position,status,lynched);
		case "Detective": 			return new Detective(name,position,status,lynched);
		case "Doctor": 				return new Doctor(name,position,status,lynched);
		case "Mafia- GodFather": 	return new GodFather(name,position,status,lynched);
		case "Mafia: Hitman": 		return new Hitman(name,position,status,lynched);
		case "Lyncher": 			return new Lyncher(name,position,status,lynched);
		case "Survivor": 			return new Survivor(name,position,status,lynched);
		case "Vigilante": 			return new Vigilante(name,position,status,lynched);
		case "Mafia: Goon":			return new Goon(name,position,status,lynched);
		default: 					return new Town(name,position,status,lynched);
		}
	}

}
