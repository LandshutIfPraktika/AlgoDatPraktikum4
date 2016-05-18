package de.s_gheldd.skiplist;


public interface Dictionary
{




    Elem search(final long key);
	void insert(final Elem element);
	Elem delete(final long key);
	Elem findMin();
	Elem findMax();
	// Elem successor(long key);
	// Elem predecessor(long key);
}
