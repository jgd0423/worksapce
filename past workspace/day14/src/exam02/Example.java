package exam02;

public class Example {
	public static void main(String[] args) {
		ImplClassC ic = new ImplClassC();
		ic.methodA();
		ic.methodB();
		ic.methodC();
		
		FaceA fa = ic;
		fa.methodA();
		
		FaceB fb = ic;
		fb.methodB();
		
		FaceC fc = ic;
		
		fc.methodA();
		fc.methodB();
		fc.methodC();
		
		
	}
}
