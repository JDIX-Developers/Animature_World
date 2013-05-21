package com.jdix.animature.utils;

import java.util.Map.Entry;

/**
 * @author Razican (Iban Eguia)
 * @param <K> - The X coordinate of the position
 * @param <V> - The Y coordinate of the position
 */
public class PosEntry<K, V> implements Entry<K, V> {

	private final K	key;
	private V		value;

	/**
	 * @param key - The key of the entry
	 * @param value - The value of the entry
	 */
	public PosEntry(final K key, final V value)
	{
		this.key = key;
		this.value = value;
	}

	@Override
	public K getKey()
	{
		return key;
	}

	@Override
	public V getValue()
	{
		return value;
	}

	@Override
	public V setValue(final V value)
	{
		return this.value = value;
	}
}