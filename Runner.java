import java.util.ArrayList;
import java.util.List;

import compareDiceSets.Statistics;
import compareDiceSets.Tuple;

public class Runner {

	public static void main(String ... args) {
		Card c1 = new Card("red", 1, 3);
		Card c2 = new Card("red", 1, 4);
		Card c3 = new Card("red", 1, 5);
		Card c4 = new Card("red", 1, 6);
		Card c5 = new Card("red", 1, 7);
		Card c6 = new Card("red", 1, 8);
		Card c7 = new Card("red", 1, 9);

		Card c8 = new Card("red", 2, 4);
		Card c9 = new Card("red", 2, 6);
		Card c10 = new Card("red", 2, 8);

		Card c11 = new Card("red", 3, 8);
		Card c12 = new Card("red", 3, 10);
		List<List<Integer>> resultsPerStat = new ArrayList<List<Integer>>();
		resultsPerStat.add(new ArrayList<Integer>()); resultsPerStat.add(new ArrayList<Integer>()); resultsPerStat.add(new ArrayList<Integer>());
		resultsPerStat.add(new ArrayList<Integer>()); resultsPerStat.add(new ArrayList<Integer>()); resultsPerStat.add(new ArrayList<Integer>());
		List<Integer> eachStatAsSet = new ArrayList<Integer>();
		eachStatAsSet.add(0); eachStatAsSet.add(0); eachStatAsSet.add(0);
		eachStatAsSet.add(0); eachStatAsSet.add(0); eachStatAsSet.add(0);
		for (int i = 0; i < 10000000; i++) {
			Deck oneDeck = new Deck();
			oneDeck.addCard(c1); oneDeck.addCard(c2); oneDeck.addCard(c3); oneDeck.addCard(c4);
			oneDeck.addCard(c5); oneDeck.addCard(c6); oneDeck.addCard(c7); oneDeck.addCard(c8);
			oneDeck.addCard(c9); oneDeck.addCard(c10); oneDeck.addCard(c11); oneDeck.addCard(c12);
			Simulation sim = new Simulation(oneDeck, 1);
			List<Integer> a = sim.processStats();
			for (int j = 0; j < a.size(); j++) {
				resultsPerStat.get(j).add(a.get(j));
				eachStatAsSet.set(j, eachStatAsSet.get(j) + a.get(j));
			}
		}
		List<String> eachStatAsSetStrings = Statistics.statsPack(eachStatAsSet);
		List<List<String>> resultsPerStatStrings = new ArrayList<List<String>>();
		for (List<Integer> singleStat : resultsPerStat)
			resultsPerStatStrings.add(Statistics.statsPack(singleStat));
		System.out.println("min; max; mean; geometric mean; median; mode; sample variance; sample standard deviation; population variance; population standard deviation;");
		//System.out.println("total of each stat then treated as sample: ");
		//for (String str : eachStatAsSetStrings)
		//	System.out.print(str + ", ");
		//System.out.println();
		for (int i = 0; i < 6; i++) {
			System.out.println("stat " + (i + 1));
			List<Tuple<Double, Integer>> probDist = Statistics.approximateProbabilityDistribution(resultsPerStat.get(i));
			for (String str : resultsPerStatStrings.get(i))
				System.out.print(str + ", ");
			System.out.println();
			for (Tuple<Double, Integer> oneDist : probDist)
				System.out.print("" + oneDist.getSecond() + ": ~" + (oneDist.getFirst() * 100) + "%, ");
			System.out.println();
		}
		System.out.println("pooled sample varaince: " + Statistics.pooledSampleVariance(resultsPerStat)
		+ " pooled sample standard deviation: " + Statistics.pooledSampleStandardDeviation(resultsPerStat));

	}
}