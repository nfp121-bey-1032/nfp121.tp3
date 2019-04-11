package question1;

public class UneUtilisation {

	public static void main(String[] args) throws Exception {
		Pile p1 = new Pile(6);
		Pile p2 = new Pile(10);

		p1.empiler(new PolygoneRegulier(5, 100));
		p1.empiler("polygone");
		p1.empiler(new Integer(100));
		System.out.println(" la pile p1 = " + p1); 

		p2.empiler(new Integer(1000));
		p1.empiler(p2);
		System.out.println(" la p1 = " + p1); 

		try {
			p1.empiler(new PolygoneRegulier(4,100));
			// ....
			String s = (String) p1.depiler();
												
		} catch (Exception e) {
			e.printStackTrace();
		} // catch
	} 
} 