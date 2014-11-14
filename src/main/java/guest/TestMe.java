//package guest;
//
//import java.util.List;
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.FileSystemXmlApplicationContext;
//
//
//
//public class TestMe {
//
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		ApplicationContext appContext = new FileSystemXmlApplicationContext("/src/main/webapp/WEB-INF/application-context.xml");
//		
//		String[] names = appContext.getBeanDefinitionNames();
//		for (int i=0;i<names.length;i++)
//		{
//			System.out.println("bean:"+names[i]);
//		}
//		GuestDAO controller = (GuestDAO)appContext.getBean("guestDAO");
//		System.out.println("controller:"+controller);
//		Guest guest = new Guest("fergus7");
//		controller.persist(guest);
//		
//		List<Guest> results = controller.findAll();
//		System.out.println("done"+results.size());
//		System.out.println("done"+guest);
//	}
//
//}
