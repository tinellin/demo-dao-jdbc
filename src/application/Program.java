package application;

import java.text.ParseException;
import java.util.Date;

import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		Department obj = new Department(30, "Livros");
		Seller obj2 = new Seller(1, "João", "joaozinho@gmail.com", new Date(), 3000.00, obj);
		
		System.out.println(obj);
		System.out.println(obj2);

	}

}
