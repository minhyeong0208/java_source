package pack.order;

import java.util.Hashtable;

public class CartMgr {
	// key-value 형식으로 담기 위해 사용
	private Hashtable<String, OrderBean> hCart = new Hashtable<String, OrderBean>();
	
	public void addCart(OrderBean obean) {
		
	}
	
	public Hashtable<String, OrderBean> getCartList() {
		return hCart;
	}
	
	public void updateCart(OrderBean obean) {
		
	}
	
	public void deleteCart(OrderBean obean) {
		
	}
}
