package product;

import java.util.ArrayList;

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
		
		ArrayList<Product> filteredByName = filter(products, new NameFilter("새우깡"));
		
		ArrayList<Product> filteredByNameUsingLambda = filter(products, (Product product) -> product.getName().equals("고구마칩"));
		
		System.out.println(filteredByNameUsingLambda.get(0).getName());

		
		ArrayList<Product> filteredByNameAndStore = filter(products, new NameAndStoreFilter("새우깡", "owner"));

		
		
//		System.out.println(filteredByName.get(0).getName());	
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
