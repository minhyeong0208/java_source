package pack.order;

import java.util.Hashtable;

// 카트는 DB에 존재하지 않고 RAM에 존재함.
public class CartMgr {
	// key-value 형식으로 담기 위해 사용
	private Hashtable<String, OrderBean> hCart = new Hashtable<String, OrderBean>();
	
	public void addCart(OrderBean obean) { // obean에는 id, product_no, quantity가 들어가 있음.
		String product_no = obean.getProduct_no();
		int quantity = Integer.parseInt(obean.getQuantity());
		
		// 주문번호가 같은 경우, 수량 추가
		if(quantity > 0) {
			// 동일 상품 주문 시, 주문 수량만 증가
			if(hCart.containsKey(product_no)) {             // product_no인 키를 가지고 있다면 실행, 이미 1회 이상 주문된 상품인 경우
				OrderBean temp = hCart.get(product_no);  // 카드에 있는 상품 중, 해당 상품 번호에 해당하는 경우, 꺼낸다.
				quantity += Integer.parseInt(temp.getQuantity()); // 기존의 quantity에 추가
				temp.setQuantity(Integer.toString(quantity)); // quantity에 추가 후 다시 설정
				hCart.put(product_no, temp);
			} else { // 카트에 없는 경우, 실행 
				hCart.put(product_no, obean);  // Cart에 담기는 최초의 상품(상품의 종류는 다름)				
			}
		}
	}
	
	public Hashtable<String, OrderBean> getCartList() {
		return hCart;
	}
	
	// 주문 수량 등을 수정 -> 카트에 담은 내역을 수정하기 위한 메소드
	public void updateCart(OrderBean obean) {
		String product_no = obean.getProduct_no();  // 수정하려는 상품
		hCart.put(product_no, obean);
	}
	
	public void deleteCart(OrderBean obean) {
		String product_no = obean.getProduct_no();
		hCart.remove(product_no); // 카트를 삭제 
	}
}
