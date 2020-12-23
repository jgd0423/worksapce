package human;

import java.util.ArrayList;
import java.util.Scanner;

import db.DbMethod;
import db.HumanDTO;
import iot.Aircondition;
import iot.Cucu;
import iot.Elevator;
import iot.Light;
import iot.RemoteControl;
import iot.Security;
import iot.TV;


public class Example01 {
	static String aptId = "";
	
	public static int elevatorMenu() {
		Scanner sc = new Scanner(System.in);
		Elevator elevator = new Elevator(aptId);
		int nowFloor = 1;
		int stopFloor = 0;
		
		while (true) {
			String menuMessage = "";
			menuMessage += "몇층으로 가시겠습니까? (현재 "+ nowFloor +"층, 프로그램 나가기: 0) : ";

			System.out.print(menuMessage);
			stopFloor = sc.nextInt();
			if (stopFloor == 0) {
				System.out.println("-- 엘리베이터 프로그램 종료 -- ");
				break;
			} else if (nowFloor < stopFloor) {
				elevator.goingUp(nowFloor, stopFloor);
				nowFloor = stopFloor;
			} else if (nowFloor > stopFloor) {
				elevator.goingDown(nowFloor, stopFloor);
				nowFloor = stopFloor;
			} else if (nowFloor == stopFloor) {
				elevator.goingNone(nowFloor, stopFloor);
			}
		}
		
		return nowFloor;
	}
	
	public static boolean securityMenu() {
		Scanner sc = new Scanner(System.in);
		RemoteControl security = new Security(aptId);
		boolean status = false;

		String menuMessage = "";
		menuMessage += "메뉴를 선택하세요(security ON: 1, security OFF: 0): ";
		System.out.print(menuMessage);
		String selectedMenu = sc.next();
		if (selectedMenu.equals("1")) {
			if (status == true) {
				System.out.println("-- 보안 프로그램이 실행되고 있습니다. --");
			} else {
				security.on();
				status = true;					
			}
		} else if (selectedMenu.equals("0")) {
			if (status == false) {
				System.out.println("-- 보안 프로그램이 종료되어있습니다. --");
			} else {
				security.off();
				status = false;
			}
		}

		return status;
	}
	
	public static boolean airconditionMenu() {
		Scanner sc = new Scanner(System.in);
		RemoteControl aircondition = new Aircondition(aptId);
		boolean status = false;

		String menuMessage = "";
		menuMessage += "메뉴를 선택하세요(aircondition ON: 1, aircondition OFF: 0): ";
		System.out.print(menuMessage);
		String selectedMenu = sc.next();
		if (selectedMenu.equals("1")) {
			if (status == true) {
				System.out.println("-- 에어컨이 켜져있습니다. --");
			} else {
				aircondition.on();
				status = true;					
			}
		} else if (selectedMenu.equals("0")) {
			if (status == false) {
				System.out.println("-- 에어컨이 꺼져있습니다. --");
			} else {
				aircondition.off();
				status = false;
			}
		}
		
		return status;
	}
	
	public static boolean lightMenu() {
		Scanner sc = new Scanner(System.in);
		RemoteControl light = new Light(aptId);
		boolean status = false;

		String menuMessage = "";
		menuMessage += "메뉴를 선택하세요(light ON: 1, light OFF: 0): ";
		System.out.print(menuMessage);
		String selectedMenu = sc.next();
		if (selectedMenu.equals("1")) {
			if (status == true) {
				System.out.println("-- 전등이 켜져있습니다. --");
			} else {
				light.on();
				status = true;					
			}
		} else if (selectedMenu.equals("0")) {
			if (status == false) {
				System.out.println("-- 전등이 꺼져있습니다. --");
			} else {
				light.off();
				status = false;
			
			}
		}
		
		return status;
	}
	
	public static boolean tvMenu() {
		Scanner sc = new Scanner(System.in);
		RemoteControl tv = new TV(aptId);
		boolean status = false;
		
		String menuMessage = "";
		menuMessage += "메뉴를 선택하세요(tv ON: 1, tv OFF: 0): ";
		System.out.print(menuMessage);
		String selectedMenu = sc.next();
		if (selectedMenu.equals("1")) {
			if (status == true) {
				System.out.println("-- 티비가 켜져있습니다. --");
			} else {
				tv.on();
				status = true;					
			}
		} else if (selectedMenu.equals("0")) {
			if (status == false) {
				System.out.println("-- 티비가 꺼져있습니다. --");
			} else {
				tv.off();
				status = false;
			}
		}
		
		return status;
	}
	
	public static boolean cucuMenu() {
		Scanner sc = new Scanner(System.in);
		RemoteControl cucu = new Cucu(aptId);
		boolean status = false;

		String menuMessage = "";
		menuMessage += "메뉴를 선택하세요(cucu ON: 1, cucu OFF: 0): ";
		System.out.print(menuMessage);
		String selectedMenu = sc.next();
		if (selectedMenu.equals("1")) {
			if (status == true) {
				System.out.println("-- 쿠쿠가 켜져있습니다. --");
			} else {
				cucu.on();
				status = true;					
			}
		} else if (selectedMenu.equals("0")) {
			if (status == false) {
				System.out.println("-- 쿠쿠가 꺼져있습니다. --");
			} else {
				cucu.off();
				status = false;
			}
		}
		
		return status;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);	
		String idInputMessage = "아이디를 입력하세요 : ";
		System.out.print(idInputMessage);
		aptId = sc.next();
		int nowFloor = 0;
		ArrayList<String> statusList = new ArrayList<>();
		
		RemoteControl[] rcList = new RemoteControl[5];
		RemoteControl r0 = new Security(aptId);
		RemoteControl r1 = new Aircondition(aptId);
		RemoteControl r2 = new Light(aptId);
		RemoteControl r3 = new TV(aptId);
		RemoteControl r4 = new Cucu(aptId);
		rcList[0] = r0;
		rcList[1] = r1;
		rcList[2] = r2;
		rcList[3] = r3;
		rcList[4] = r4;
		
		while (true) {
			
			String menuMessage = "";
			menuMessage += "1. Elevator\n";
			menuMessage += "2. Security\n";
			menuMessage += "3. Aircondition\n";
			menuMessage += "4. Light\n";
			menuMessage += "5. TV\n";
			menuMessage += "6. Cucu\n";
			menuMessage += "0. 종료\n";
			menuMessage += "메뉴를 선택하세요 : ";
			System.out.print(menuMessage);
			
			String selectedMenu = sc.next();
			if (selectedMenu.equals("0")) {
				System.out.println("-- 프로그램 종료 --");
				break;
			} else if (selectedMenu.equals("1")) {
				nowFloor = elevatorMenu();
			} else if (selectedMenu.equals("2")) {
				rcList[0].setStatus(securityMenu());
			} else if (selectedMenu.equals("3")) {
				rcList[1].setStatus(airconditionMenu());
			} else if (selectedMenu.equals("4")) {
				rcList[2].setStatus(lightMenu());
			} else if (selectedMenu.equals("5")) {
				rcList[3].setStatus(tvMenu());
			} else if (selectedMenu.equals("6")) {
				rcList[4].setStatus(cucuMenu());
			}
		}
		
		for (RemoteControl r : rcList) {
			if (r.isStatus() == true) {
				r.on();
				statusList.add("1");
			} else {
				r.off();
				statusList.add("0");
			}
		}
		
		HumanDTO dto = new HumanDTO(
				aptId, 
				statusList.get(0), 
				statusList.get(1), 
				statusList.get(2), 
				statusList.get(3), 
				statusList.get(4));
		
		DbMethod db = new DbMethod();
		db.setInsert(dto);
  	}
}
