package idk.archiveutils;

public class MathParser {
	private static final char[] validOperators = { '/', '*', '+', '-' };

	private MathParser() {
		/*
		 * Using a private contructor to prevent instantiation Using class as a
		 * simple static utility class
		 */
	}

	private static int evaluate(final String leftSide, final char oper, final String rightSide) throws IllegalArgumentException {
		// System.out.println("Evaluating: " + leftSide + " (" + oper + ") " +
		// rightSide);
		int total = 0;
		int leftResult = 0;
		int rightResult = 0;

		int operatorLoc = findOperatorLocation(leftSide);
		if (operatorLoc > 0 && operatorLoc < leftSide.length() - 1) {

			leftResult = evaluate(leftSide.substring(0, operatorLoc), leftSide.charAt(operatorLoc),
					leftSide.substring(operatorLoc + 1, leftSide.length()));
		} else {
			try {
				leftResult = Integer.parseInt(leftSide);
			} catch (final Exception e) {
				throw new IllegalArgumentException("Invalid value found in portion of equation: " + leftSide);
			}
		}

		operatorLoc = findOperatorLocation(rightSide);
		if (operatorLoc > 0 && operatorLoc < rightSide.length() - 1) {
			rightResult = evaluate(rightSide.substring(0, operatorLoc), rightSide.charAt(operatorLoc),
					rightSide.substring(operatorLoc + 1, rightSide.length()));
		} else {
			try {
				rightResult = Integer.parseInt(rightSide);
			} catch (final Exception e) {
				throw new IllegalArgumentException("Invalid value found in portion of equation: " + rightSide);
			}
		}

		// System.out.println("Getting result of: " + leftResult + " " + oper +
		// " " + rightResult);
		switch (oper) {
		case '/':
			total = leftResult / rightResult;
			break;
		case '*':
			total = leftResult * rightResult;
			break;
		case '+':
			total = leftResult + rightResult;
			break;
		case '-':
			total = leftResult - rightResult;
			break;
		default:
			throw new IllegalArgumentException("Unknown operator.");
		}
		// System.out.println("Returning a result of: " + total);
		return total;
	}

	private static int findOperatorLocation(final String string) {
		int index = -1;
		for (int i = validOperators.length - 1; i >= 0; i--) {
			index = string.indexOf(validOperators[i]);
			if (index >= 0) {
				return index;
			}
		}
		return index;
	}

	public static int processEquation(String equation) throws IllegalArgumentException {
		equation = equation.replaceAll(" ", "");
		return evaluate(equation, '+', "0");
	}

}
