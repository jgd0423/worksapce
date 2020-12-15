package exam01;

import java.util.ArrayList;

public class Example {
	public static void main(String[] args) {
//		Driver driver = new Driver();
//		Bus bus = new Bus();
//		Taxi taxi = new Taxi();
//		
//		ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
//		vehicles.add(bus);
//		vehicles.add(taxi);
//		
//		driver.drive(bus); // 매개변수의 다형성
//		driver.drive(taxi);
//		System.out.println(vehicles);
		
		Vehicle vehicle = new Bus();
		vehicle.run();
		
		// if (부모타입 변수 instanceof 자식타입)
		if (vehicle instanceof Bus) {
			Bus bus = (Bus) vehicle;			
		} else {
			System.out.println("강제형변환 불가");
		}
		
		
	}
}
