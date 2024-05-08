package vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductVO {
	private int pno;
	private String pname;
	private int price;
	private int dcratio;
	private String prodesc;
	private int qty;
	private String imgfile;
	private String bigfile;
}
