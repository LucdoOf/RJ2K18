package fr.ragejam.utils;

public class Math {
	
	public static double getIntegralPart(double value){
		double fractionalPart = value % 1;
		double integralPart = value - fractionalPart;
		return integralPart;
	}

}
