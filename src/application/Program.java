package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) throws ParseException {

		SellerDao sellerDao = DaoFactory.createSellerDao();
		DepartmentDao dd = DaoFactory.createDepartmentDao();
		List<Seller> listS = new ArrayList<>();
		List<Department> listD = new ArrayList<>();

		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);

		Department dep = new Department();
		Seller obj1 = new Seller();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		System.out.println("ESCOLHA: ");
		System.out.println("1 - NAVEGAR NO BANCO DE DADOS DE VENDEDORES ");
		System.out.println("2 - NAVEGAR NO BANCO DE DADOS DE DEPARTAMENTOS ");
		System.out.print("DIGITE: ");
		int x = sc.nextInt();

		if (x == 1) {
			System.out.println("\nESCOLHA:\n");
			System.out.println("1 - ADICIONAR\n");
			System.out.println("2 - ATUALIZAR\n");
			System.out.println("3 - DELETAR POR ID\n");
			System.out.println("4 - PROCURAR POR ID\n");
			System.out.println("5 - PROCURAR POR DEPARTAMENTO\n");
			System.out.println("6 - PROCURAR TODOS\n");
			System.out.println("7 - SAIR\\n");
			System.out.print("\nDIGITE: ");
			x = sc.nextInt();

			switch (x) {

			case 1:
				//INSERIR				
				System.out.println("Digite o nome do vendedor: ");
				String name = sc.next();
				System.out.println("Digite o email do vendedor: ");
				String email = sc.next();
				sc.nextLine();
				System.out.println("Digite a data de nascimento do vendedor: ");
				Date birthDate = sdf.parse(sc.next());
				System.out.println("Digite o salário basico do vendedor: ");
				double baseSalary = sc.nextDouble();
				int id = 0;
				System.out.println("Digite o ID do departamento do vendedor: ");
				int departmentId = sc.nextInt();
				dep.setId(departmentId);

				obj1 = new Seller(id, name, email, birthDate, baseSalary, new Department(departmentId,null));
				sellerDao.insert(obj1);
				System.out.println("ADICIONADO!");
				break;

			case 2:
				//ATUALIZAR
				System.out.print("Digite o ID do vendendor: ");
				id = sc.nextInt();
				obj1 = sellerDao.findById(id);
				System.out.print("Digite o novo nome do vendedor: ");
				name = sc.next();
				System.out.print("Digite o novo email do vendedor: ");
				email = sc.next();
				System.out.print("Digite a nova data de nascimento do vendedor: ");
				birthDate = sdf.parse(sc.next());
				System.out.print("Digite o novo salário basico do vendedor: ");
				baseSalary = sc.nextDouble();
				System.out.print("Digite o novo ID do departamento do vendedor: ");
				departmentId = sc.nextInt();
				Seller obj2 = new Seller(id, name, email, birthDate, baseSalary, new Department(departmentId,null));
				sellerDao.update(obj2); 
				System.out.println("ATUALIZADO!");
				break;

			case 3:
				//DELETAR POR ID
				System.out.print("Digite o ID: ");
				id = sc.nextInt();
				sellerDao.deleteById(id);
				System.out.println("DELETADO!");
				break;

			case 4:
				//PROCURAR POR ID
				System.out.print("Digite o ID: ");
				id = sc.nextInt();
				obj1 = sellerDao.findById(id);
				System.out.println(obj1);
				break;

			case 5:
				//PROCURAR POR DEPARTAMENTO				
				System.out.print("Digite o ID do departamento: ");
				departmentId = sc.nextInt();
				dep = new Department(departmentId, null);
				listS = sellerDao.findByDepartment(dep);
				for (Seller sel : listS) {
					System.out.println(sel);
				}
				break;

			case 6:
				//PROCURAR EM TODOS
				listS = sellerDao.findAll();
				for (Seller sel : listS) {
					System.out.println(sel);
				}
				break;

			case 7:
				//SAIR
				break;
			}
		}
		
		if (x == 2) {
			System.out.println("\nESCOLHA:\n");
			System.out.println("1 - ADICIONAR\n");
			System.out.println("2 - ATUALIZAR\n");
			System.out.println("3 - DELETAR POR ID\n");
			System.out.println("4 - PROCURAR POR ID\n");
			System.out.println("5 - PROCURAR TODOS\n");
			System.out.println("6 - SAIR\n");
			System.out.print("DIGITE: ");
			x = sc.nextInt();

			switch (x) {
			case 1:
				//INSERIR
				System.out.print("Digite o ID: ");
				int id = sc.nextInt();
				System.out.print("Digite o nome: ");
				String name = sc.next();
				Department department1 = new Department(id, name);
				dd.insert(department1);
				System.out.println("ADICIONADO!");
				break;
			case 2:
				//ATUALIZAR
				System.out.print("Digite o ID: ");
				id = sc.nextInt();
				System.out.print("Digite o nome: ");
				name = sc.next();
				dep = new Department(id, name);
				dd.update(dep);
				System.out.println("ATUALIZADO!");
				break;
			case 3:
				//DELETAR POR ID
				System.out.print("Digite o ID: ");
				id = sc.nextInt();
				dd.deleteById(id);
				System.out.println("DELETADO!");
				break;
			case 4:
				//PROCURAR POR ID
				System.out.print("Digite o ID: ");
				id = sc.nextInt();
				dep = dd.findById(id);
				System.out.println(dep);
				break;
			case 5:
				//PROCURAR EM TODOS			
				listD = dd.findAll();
				for (Department del : listD) {
					System.out.println(del);
				}
				break;
			case 6:
				//SAIR
				break;
			}
		}
		sc.close();
	}
}
