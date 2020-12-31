package product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.function.Predicate;

public class Main {
	public static void main(String[] args) {
		ArrayList<Product> products = new ArrayList<>();
		products.add(new Product("새우깡", 1200, true, "농심", "이마트"));
		products.add(new Product("감자깡", 1200, true, "농심", "이마트"));
		products.add(new Product("양파링", 1000, true, "농심", "홈플러스"));
		products.add(new Product("고구마칩", 3000, true, "오리온", "홈플러스"));
		products.add(new Product("자갈치", 800, true, "오리온", "홈플러스"));
		products.add(new Product("가위", 4000, false, "문방구", "코스트코"));
		products.add(new Product("청소기", 70000, false, "LG", "코스트코"));
		products.add(new Product("양주", 30000, true, "진로", "코스트코"));
		products.add(new Product("곰젤리", 4000, false, "Bear", "코스트코"));
		
		for (Product product : products) {
			System.out.println(product.getName());
		}
		
		
//		Collections.sort(products, new Comparator<Product>() {
//			@Override
//			public int compare(Product proc1, Product proc2) {
//				return proc1.getPrice() - proc2.getPrice();
//			}
//		});
		
		products.sort((Product proc1, Product proc2) -> proc1.getPrice() - proc2.getPrice());
		
		System.out.println("-------------------------");
		
		for (Product product : products) {
			System.out.println(product.getName());
		}
		
		// 클래스에 spread를 이용하면 인자를 여러개 받을 수 있지만 같은 타입밖에 못받음
		ArrayList<Product> filteredByName = filter(products, new NameFilter("새우깡"));
		
		ArrayList<Product> filteredByNameUsingLambda = filter(products, (Product product) -> product.getName().equals("고구마칩"));
//		for (Product product : filteredByNameUsingLambda) {
//			System.out.println(product.getName());
//		}
		
		ArrayList<Product> filteredByMadebyUsingLambda = filter(products, (Product product) -> product.getMadeBy().equals("농심"));
//		for (Product product : filteredByMadebyUsingLambda) {
//			System.out.println(product.getName());
//		}
		
		// 람다식을 사용하면 다른타입의 값들도 동시에 검색 가능함
		ArrayList<Product> filteredByNameAndPriceUsingLambda = filter(products, (Product product) -> product.getPrice() == 4000 && product.getName().equals("가위"));
//		for (Product product : filteredByNameAndPriceUsingLambda) {
//			System.out.println(product.getName());
//		}
		
		ArrayList<Product> filteredByNameAndStore = filter(products, new NameAndStoreFilter("새우깡", "owner"));
		
		ArrayList<Product> filteredByName2 = filterPredicate(products, (Product product) -> product.getName().equals("새우깡"));
//		for (Product product : filteredByName2) {
//			System.out.println(product.getName());
//		}
		
		Predicate<Product> predicate = (Product product) -> product.getName().equals("새우깡") && product.getStore().equals("이마트");
		ArrayList<Product> filteredByNameAndStore2 = filterPredicate(products, predicate);
//		for (Product product : filteredByNameAndStore2) {
//			System.out.println(product.getName());
//		}
		
		
//		System.out.println(filteredByName.get(0).getName());	
	}
	
	public static ArrayList<Product> filterPredicate(ArrayList<Product> products, Predicate<Product> filter) {
		ArrayList<Product> filteredProducts = new ArrayList<>();
		
		for (Product product : products) {
			if (filter.test(product)) {
				filteredProducts.add(product);
			}
		}
		
		return filteredProducts;
	}
	
	// 아래처럼 filter를 구성하면 뭐가 장점임? filter메소드가 변하지 않는다는게 왜 장점이 되는거임?
	public static ArrayList<Product> filter(ArrayList<Product> products, FilterPredicate filterInterface) {
		ArrayList<Product> filteredProducts = new ArrayList<>();
		
		for (Product product : products) {
			if (filterInterface.filter(product)) {
				filteredProducts.add(product);
			}
		}
		
		return filteredProducts;
	}
	
//	public static ArrayList<Product> filterByName(ArrayList<Product> products, String name) {
//		ArrayList<Product> filteredProducts = new ArrayList<>();
//		for(Product product : products) {
//			if(product.getName().equals(name)) {
//				filteredProducts.add(product);
//			}
//		}
//		return filteredProducts;
//	}
//	
//	public static ArrayList<Product> filterByPrice(ArrayList<Product> products, int price) {
//		ArrayList<Product> filteredProducts = new ArrayList<>();
//		for(Product product : products) {
//			if(product.getPrice() <= price) {
//				filteredProducts.add(product);
//			}
//		}
//		return filteredProducts;
//	}
//	
//	public static ArrayList<Product> filterByStoreAndName(ArrayList<Product> products, String name, String store) {
//		ArrayList<Product> filteredProducts = new ArrayList<>();
//		for(Product product : products) {
//			if(product.getName().equals(name) && product.getStore().equals(store)) {
//				filteredProducts.add(product);
//			}
//		}
//		return filteredProducts;
//	}
//	
//	public static ArrayList<Product> filterByStoreAndNameAndFood(ArrayList<Product> products, String name, String store, boolean isFood) {
//		ArrayList<Product> filteredProducts = new ArrayList<>();
//		for(Product product : products) {
//			if(product.getName().equals(name) && product.getStore().equals(store) && product.isFood() == isFood) {
//				filteredProducts.add(product);
//			}
//		}
//		return filteredProducts;
//	}
}
