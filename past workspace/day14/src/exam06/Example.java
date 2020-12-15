package exam06;

public class Example {
	public static void main(String[] args) {
		Dotprinter d = new Dotprinter();
		d.printer();
		
		InkjetPrinter i = new InkjetPrinter();
		i.printer();
		
		LaserPrinter l = new LaserPrinter();
		l.printer();
		
		Printer p = new Dotprinter();
		p.printer();
		
		p = new InkjetPrinter();
		p.printer();
		
		p = new LaserPrinter();
		p.printer();
	}
}
