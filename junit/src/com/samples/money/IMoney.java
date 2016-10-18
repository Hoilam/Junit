package com.samples.money;

/**
 *	The common interface for simple Monies and MoneyBags 
 **/
public interface IMoney {

	/**
	 * Adds money to this money 
	 **/
	public abstract Money add(Money m);
	
	/**
	 *	Add a simple Money to this money.This is a helper method for implementing double dispath 
	 **/
	public abstract IMoney addMoney(Money m);
	
	/**
	 *	Adds a MoneyBag to this money.This is a helper method for implementing double dispatch 
	 **/
	public abstract IMoney addMoneyBag(MoneyBag s);
	
	/**
	 *	Tests whether this money is zero 
	 **/
	public abstract boolean isZero();
	
	/**
	 *	Multiplies a money by the given factor 
	 **/
	public abstract IMoney multiply(int facter);
	
	/**
	 *	Negates this money 
	 **/
	public abstract IMoney negate();
	
	/**
	 *	Subtracts a money from this money 
	 **/
	public abstract IMoney subtract(IMoney m);
	
	/**
	 *	Append this to a MoneyBag m
	 *	appendTo() needs to be public because it is used
	 *	polymorphically,but it should not be used by clients
	 *	because it modifies the argument m 
	 **/
	public abstract void appendTo(MoneyBag m);
	
}
