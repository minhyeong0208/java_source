package pack.product;

import lombok.Data;

@Data
public class ProductBean {
	private int no;
	private String name, price, detail, sdate, stock, image;
}