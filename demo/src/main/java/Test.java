import java.util.Arrays;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> values = Arrays.asList(1,2,3,4,5);
		
		values.forEach(value -> System.out.println(values));
		//System.out.println(value);
		System.out.println(values.stream()
			  .filter(e -> e > 2)
			  .filter(e -> e %2 == 0)
			  .findFirst());
			  
		//Timeit.code() -> findFunctional(Tickers.symbols));

}
}
