package com.samples.money;

import java.util.ArrayList;
import java.util.List;

public class MoneyBag implements IMoney{
	
	private List<Money> fMonies = new ArrayList<Money>(5);
	
	public static IMoney create(IMoney m1, IMoney m2) {
		MoneyBag result = new MoneyBag();
		m1.appendTo(result);
		m2.appendTo(result);
		return result.simplify();
	}
	public IMoney add(IMoney m){
		return m.addMoneyBag(this);
	}

	@Override
	public IMoney addMoney(Money m) {
		return MoneyBag.create(m, this);
	}

	@Override
	public IMoney addMoneyBag(MoneyBag s) {
		return MoneyBag.create(s, this);
	}
	
	public void appendBag(MoneyBag aBag){
		for(Money each:aBag.fMonies){
			appendMoney(each);
		}
	}
	
	public void appendMoney(Money aMoney) {
		if(aMoney.isZero())return;
		IMoney old =findMonecy(aMoney.currency());
		if(old == null){
			fMonies.add(aMoney);
			return;
		}
		fMonies.remove(old);
		Money sum = (Money) old.add(aMoney);
		if(sum.isZero()){
			return;
		}
		fMonies.add(sum);
	}
	public boolean equals(Object anObject){
		if(isZero()){
			if(anObject instanceof IMoney){
				return ((IMoney)anObject).isZero();
			}
		}
		if(anObject instanceof MoneyBag){
			MoneyBag aMoneyBag = (MoneyBag) anObject;
			if(aMoneyBag.fMonies.size() != fMonies.size()){
				return false;
			}
			for(Money each:fMonies){
				if(!aMoneyBag.contains(each)){
					return false;
		}
			}
			return true;
		}
		
		return false;
}
	

	private IMoney findMonecy(String currency) {
		for(Money each:fMonies){
			if(each.currency().equals(currency)){
				return each;
			}
		}
		return null;
	}
	private boolean contains(Money m) {
		Money found =(Money) findMonecy(m.currency());
		if(found == null)return false;		
		return found.amount() == m.amount();
	}
	public int hashCode(){
		int hash =0;
		for(Money each:fMonies){
			hash ^= each.hashCode();
		}
		
		return hash;
		
	}
	@Override
	public boolean isZero() {
		return fMonies.size()==0;
	}
	@Override
	public IMoney multiply(int factor) {
		MoneyBag result = new MoneyBag();
		if(factor != 0){
			for(Money each:fMonies){
				result.appendMoney((Money)each.multiply(factor));
			}
		}
		return result;
	}
	@Override
	public IMoney negate() {
		MoneyBag result = new MoneyBag();
		for(Money each:fMonies){
			result.appendMoney((Money)each.negate());
		}
		return result;
	}
	private IMoney simplify(){
		if(fMonies.size() == 1){
			return fMonies.iterator().next();
		}
		return this;
	}

	@Override
	public IMoney subtract(IMoney m) {
		return null;
	}
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		for(Money each:fMonies){
			buffer.append(each);
		}
		buffer.append("}");
		return buffer.toString();
		
	}
	
	@Override
	public void appendTo(MoneyBag m) {

		m.appendBag(this);
	}

	
}
