import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/*I implemented this partial executable solution around 2013 ! for amazon code ninja programming contest.*/

public class Fib_Test {
	public static void main(String a[]) throws IOException {
		System.out.println("Enter test Cases:");
		BufferedReader bis2 = new BufferedReader(new InputStreamReader(
				System.in));
		String tc = bis2.readLine();
		int testcase = Integer.parseInt(tc);
		List<BigInteger> l = new ArrayList<BigInteger>();
		for (int b = 0; b < testcase; b++) {
			l.add(new BigInteger(bis2.readLine()));
		}
		Iterator<BigInteger> itr = l.iterator();
		BigInteger num;

		List<BigInteger> fibresult;
		int factor = 0;
		int fib_result = 0;
		int ar1[] = new int[testcase];
		int ar2[] = new int[testcase];
		int counter_pos = 0;
		while (itr.hasNext()) {
			num = itr.next();
			fibresult = fib(num);
			factor = comman_factor(num.intValue());
			ar2[counter_pos] = factor;
			fibresult = fibresult.subList(
					fibresult.indexOf(BigInteger.valueOf(1)), fibresult.size());
			Iterator<BigInteger> it = fibresult.iterator();
			BigInteger pos1;
			BigInteger pos2 = BigInteger.valueOf(factor);
			BigInteger rem = BigInteger.valueOf(0);
			long startTime = System.currentTimeMillis();
			while (it.hasNext()) {
				pos1 = it.next();
				if ((pos1.remainder(pos2)).compareTo(rem) == 0) {
					fib_result = pos1.intValue();
					break;
				}
			}
			if (fib_result == 0 && num.isProbablePrime(1)) {

				fibresult = fib(num.nextProbablePrime());
				fibresult = fibresult.subList(
						fibresult.indexOf(BigInteger.valueOf(1)),
						fibresult.size());
				Iterator<BigInteger> it1 = fibresult.iterator();
				while (it1.hasNext()) {
					pos1 = it1.next();
					if ((pos1.remainder(pos2)).compareTo(rem) == 0) {
						fib_result = pos1.intValue();
						break;
					}
				}
			}

			ar1[counter_pos] = fib_result;
			counter_pos++;
		}
		for (int o = 0; o < counter_pos; o++) {
			System.out.println(ar1[o] + " " + ar2[o]);
		}
	}

	public static List<BigInteger> fib(BigInteger n) {
		List<BigInteger> f = new ArrayList<BigInteger>();
		int c;
		BigInteger first = new BigInteger("0");
		BigInteger second = new BigInteger("1");
		BigInteger fib = new BigInteger("0");
		BigInteger comp = new BigInteger("1000000000000000000");

		long startTime = System.currentTimeMillis();
		for (c = 0; c < n.intValue(); c++) {
			if (c <= 1)
				fib = BigInteger.valueOf(c);
			else {
				fib = first.add(second);
				first = second;
				second = fib;
			}
			if (comp.compareTo(fib) > 0)
				f.add(fib);
		}
		Collections.sort(f);
		return f;
	}

	static int comman_factor(int n) {
		List<Integer> c_f = new ArrayList<Integer>();
		int comman_factor, least_cf = 0;
		long startTime = System.currentTimeMillis();

		for (comman_factor = 2; comman_factor <= n; comman_factor++) {
			if (n % comman_factor == 0)
				c_f.add(comman_factor);
		}
		if (!c_f.isEmpty()) {
			Collections.sort(c_f);
			least_cf = c_f.get(0);
		}
		return least_cf;
	}
}
