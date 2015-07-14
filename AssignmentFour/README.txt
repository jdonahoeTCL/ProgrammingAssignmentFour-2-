Some Questions and Answers 


You need 4 counters 1) wordsFound, 2)wordsNotFound, 3)compsFound, 4)compsNotFound ( don't forget to initialize them to 0 since these are sums, and they should be long type). 
 
You should not declare these counters in your MyLinkedList class, they should be declared in your client code 

You should also do the file reading part in the client side. Remember MyLinkedList is just a container to hold dictionary words.

Your contains(E e) method should tell you if it is found or not found depending on its return value, because it returns a boolean value. 
The number of times we go through the loop tells us the number of comparisons per word. So, your loop control variable 'i' tells you how 
many times we compare a word from the book in a corresponding linked list.

If the contains method returns true, increment wordsFound counter by one and  add the value of 'i' to your compsFound variable, otherwise increment 
the wordsNotFound counter by and add the value of 'i' to compsNotFound. The problem is getting the value of the loop control variable 'i' to the client. 
There are multiple ways of solving this problem, and here is a solution.

We can overload the contains method with a second parameter. The argument to this parameter is a single element int array declared in the client code, 
because the changes done in contains method should be visible to the client. You should set this array element to 0 every time you call contains method with a new word to search.

You can implement your contains method (overloaded method) as follows,

	public boolean contains(E e, int[] count) // array i has only one element, value is 0.

and before returning the method, you can set the value of the loop control variable 'i' with count[0]( i.e. count[0] = i;)


In the client, where you call the contains(wordToBeSearched, count) method, you can do the following,

if ( list.contains(wordToBeSearched, i)){
	wordsFound++;
	compsFound += count[0];
}
else{
	wordsNotFound++;
	compsNotFound += count[0];
}

When you are done reading oliver.txt, you have these counters ready to do the average # of comparisons.

Now you can find:
avgcompswordsfound =  compsFound / wordsFound // ( avg # of comps per word)
avgcompswordsnotfound = compsNotFound / wordsNotFound //(avg # of comps per word not found)

