package cn.zs.learn.bigdate.mahout;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.*;
import org.apache.mahout.cf.taste.impl.recommender.*;
import org.apache.mahout.cf.taste.impl.similarity.*;
import org.apache.mahout.cf.taste.model.*;
import org.apache.mahout.cf.taste.neighborhood.*;
import org.apache.mahout.cf.taste.recommender.*;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.*;
import java.util.*;

class UserBased {

	private UserBased() {
	}

	public static void main(String[] args) throws Exception {

		DataModel model = new FileDataModel(new File(UserBased.class.getResource("/mahout/data.txt").getPath()));

		UserSimilarity similarity = new PearsonCorrelationSimilarity(model);

		UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, similarity, model);

		Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

		List<RecommendedItem> recommendations = recommender.recommend(1, 1);

		for (RecommendedItem recommendation : recommendations) {
			System.out.println(recommendation);
		}
	}
}
