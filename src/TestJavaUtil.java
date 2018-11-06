
public class TestJavaUtil {
		   public static void main(String[] args) {
		      String nixSampleLine = "Line 1 \n Line 2 \n Line 3";
		      String[] lines = nixSampleLine.split("\\s*\\r?\\n\\s*");
		      for (String line : lines) {
		         System.out.println(line);
		      }
		   }
}
