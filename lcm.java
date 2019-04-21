import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Math;

class Lcm {

	boolean isPrime(int integer) {
		for (int i = 2; i < integer; ++i) {
			if ((integer % i) == 0) {
				return false;
			}
		}
		return true;
	}

	int getNextPrime(int integer) {
		if (integer < 3)
			return ++integer;

		while (isPrime(++integer) != true) {
			// do nothing here
		}
		return integer;
	}

	HashMap<Integer, Integer> findFactors(int integer) {
		// The result is histogram of primes. For example, the 20 is factored to primes 2 * 2 * 5.
		// This will make a histogram like that:
		// ____________
		// key | value
		// ------------
		//  2  | 2      because there are two 2s
		//  5  | 1      because there is one 5
		HashMap<Integer, Integer> result = new HashMap<Integer, Integer>();

		if (integer == 0)
			return result;

		int prime = 2;
		while (integer != 1) {
			if ((integer % prime) == 0) {
				integer /= prime;
				// Insert if not present, otherwise increment the histogram value
				int count = result.containsKey(prime) ? result.get(prime) : 0;
				result.put(prime, count + 1);
			}
			else {
				prime = getNextPrime(prime);
			}
		}
		return result;
	}

	int getLowestCommonMultiple(ArrayList<Integer> integers) {
		// First factor all the numbers to primes
		ArrayList<HashMap<Integer, Integer>> factoredIntegers = new ArrayList<HashMap<Integer, Integer>>();
		for (Integer i : integers) {
			factoredIntegers.add(findFactors(i));
		}

		// Find all the common factors
		HashMap<Integer,Integer> commonFactors = new HashMap<Integer,Integer>();
		for (HashMap<Integer,Integer> map : factoredIntegers) {
			for (HashMap.Entry<Integer, Integer> pair : map.entrySet()) {
				Integer key = pair.getKey();
				Integer value = pair.getValue();
				int count = commonFactors.containsKey(key) ? commonFactors.get(key) : 0;
				if (count < value)
					commonFactors.put(key, value);
			}
		}

		// Multiply all the common factors to get the Lowest Common Multiplier
		double result = 1;
		for (HashMap.Entry<Integer, Integer> pair : commonFactors.entrySet()) {
			Integer key = pair.getKey();
			Integer value = pair.getValue();
			result *= Math.pow(key.doubleValue(), value.doubleValue());
		}

		return (int)result;
	}

	public static void main(String args[]) {
		Lcm lcm = new Lcm();

		ArrayList<Integer> integers = new ArrayList<Integer>();
		integers.add(3);
		integers.add(6);
		integers.add(5);
		System.out.print("\nIntegers are: ");
		for (Integer i : integers) {
			System.out.print(i + " ");
		}
		System.out.println("\nLowest common multiple is: " + lcm.getLowestCommonMultiple(integers));
	}
}
