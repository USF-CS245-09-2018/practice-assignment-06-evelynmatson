package src;

public class Practice06Test {
	
	protected Queue queue;
	protected Stack stack;
	protected final int size_of_test = 500;
	protected final String[] expressionList = {"level", "Anna", "A nut for a jar of tuna!"};
	protected final String[] wrongList = {"first", "Matter fact", "Landed on his hip and busted his lip"};
	
	
	public Practice06Test() {
		queue = new ArrayQueue();
		stack = new ArrayStack();
	}
	
	
	public void clearData() {
		while (!queue.empty()) {
			queue.dequeue();
		}
		while (!stack.empty()) {
			stack.pop();
		}
	}
	
	
	public boolean isPalindrome(String item) {
		clearData();
		for (int i = 0; i < item.length(); i++) {
			// Before pushing/enqueueing, make sure the character is alphabetic.
			Character chr = item.substring(i,i+1).toCharArray()[0];
			chr = Character.toLowerCase(chr);
			if (Character.isAlphabetic(chr)) {
				stack.push(chr);
				queue.enqueue(chr);
			}
		}

		while (! stack.empty() && ! queue.empty()) {
			if (! stack.pop().equals(queue.dequeue())) {
				return false;
			}
		}
		
		// At this point, the stack AND the queue should be empty. But check in case...
		if (!stack.empty() || ! queue.empty())
			return false;
		
		return true;
	}
	
	
	public void runTest() {
		// Theory: The src.Queue and src.Stack constructors have been called.
		int grade = 0;
		try {
			// If the stack and queue are empty (default), that's a milestone.
			if (stack.empty())
				grade += 5;
			if (queue.empty())
				grade += 5;
			System.out.println("[+" + grade + "%] src.Queue and src.Stack declared correctly.");
	
			// First tests: is the queue correct? Is the stack correct?
			String first = "first";
			String second = "second";
			queue.enqueue(first);
			queue.enqueue(second);
			if (queue.dequeue() == first && queue.dequeue() == second && queue.empty()) {
				System.out.println("[+10%] src.Queue functions appear correct.");
				grade += 10;
			} else {
				System.out.println("[ XX ] One or more src.Queue functions appear incorrect.");
			}
			
			stack.push(first);
			stack.push(second);
			if (stack.pop() == second && stack.pop() == first && stack.empty()) {
				System.out.println("[+10%] src.Stack functions appear correct.");
				grade += 10;
			} else {
				System.out.println("[ XX ] One or more src.Stack functions appear incorrect.");
			}
			
			// Additional sanity test: place a large number of items in the stack / queue to see if it resizes.
			for (int i = 0; i < size_of_test; i++) {
				queue.enqueue(Integer.MAX_VALUE);
				stack.push(Integer.MIN_VALUE);
			}
			boolean emptiedEarly = false;
			for (int i = 0; i < size_of_test; i++) {
				if ( queue.empty() || stack.empty()) {
					emptiedEarly = true;
				}
				queue.dequeue();
				stack.pop();
			}
			if (queue.empty() && stack.empty() && ! emptiedEarly) {
				System.out.println("[+10%] src.Stack and src.Queue appear to resize correctly.");
				grade += 10;
			} else {
				System.out.println("[ XX ] src.Stack or src.Queue do not resize correctly.");
			}
			
			System.out.println("====================");
	
			// Second tests: does it correctly find palindromes?
			for (String expression : expressionList) {
				if (isPalindrome(expression)) {
					System.out.println("[+10%] \"" + expression + "\" is a palindrome (correct).");
					grade += 10;
				}
				else {
					System.out.println("[ XX ] \"" + expression + "\" determined to be a non-palindrome (incorrect).");
				}
			}
			
			System.out.println("====================");
	
			// Third tests: can it correctly identify non-palindromes?
			for (String expression : wrongList) {
				// System.out.print(expression + " determined to be: ");
				if (isPalindrome(expression)) {
					System.out.println("[ XX ] \"" + expression + "\" determined to be a palindrome (incorrect).");
				}
				else {
					System.out.println("[+10%] \"" + expression + "\" is a non-palindrome (correct)");
					grade += 10;
				}
			}
		} catch (Exception e) {
			// Do nothing
		} finally {
			System.out.println("====================");
			System.out.println("Grade for this assignment: " + grade + "%");
		}
	}

	public static void main(String[] args) {
		Practice06Test test = new Practice06Test();
		test.runTest();
	}

}
