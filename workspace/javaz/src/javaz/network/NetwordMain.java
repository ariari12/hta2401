package javaz.network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

// InetAddress 클래스
// - IP 주소를 표현
// - 로컬 컴퓨터의 IP 주소 및 도메인 이름을 DNS에서
//   검색한 후 IP 주소를 가져옴

// DNS server
// - 도메인 이름을 IP 주소로 변환해주는 서버
public class NetwordMain {
	public static void main(String[] args) throws IOException {
		InetAddress ia = InetAddress.getLocalHost();
		
		System.out.println("로컬 호스트 이름: " + ia.getHostName());
		System.out.println("로컬 호스트 IP 주소: " + ia.getHostAddress());
		System.out.println();
		
		ia = InetAddress.getByName("www.naver.com");
		System.out.println("호스트 이름: " + ia.getHostName());
		System.out.println("호스트 IP 주소: " + ia.getHostAddress());
		System.out.println();
		
		InetAddress[] naverAddrs = InetAddress.getAllByName("www.naver.com");
		
		for (InetAddress addr : naverAddrs) {
			System.out.println("호스트 이름: " + addr.getHostName());
			System.out.println("호스트 IP 주소: " + addr.getHostAddress());
		}
		System.out.println("------------------------------");
		
		// URL 객체 생성 - 특정 웹 페이지 읽어오기
		String address = "https://docs.oracle.com/en/java/javase/11/docs/api";
		URL url = new URL(address);
		
//		// 1byte 스트림으로 읽기
//		InputStream is = url.openStream();
//		FileOutputStream fos = new FileOutputStream("api_1.html"); 
//		int inputNum = 0;
//		
//		while ((inputNum = is.read()) != -1) {
//			System.out.write(inputNum);
//			fos.write(inputNum);
//		}
//		
//		fos.close();		
//		is.close();
//		System.out.println("------------------------------");
		
		// 2byte 스트림으로 읽기
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
		BufferedWriter bw = new BufferedWriter(new FileWriter("api.html"));
		
		String input = "";
		
		while ((input = br.readLine()) != null) {
			System.out.println(input);
			bw.write(input);
			bw.newLine();
		}
		
		bw.close();
		br.close();
		System.out.println("------------------------------");
		
		URLConnection urlCon = url.openConnection();
		System.out.println("URL: " + address);
		
		// 지정된 URL의 헤더 정보 보기
		Map<String, List<String>> urlCons = urlCon.getHeaderFields();
		urlCons.forEach((key, value) -> System.out.println(key + " : " + value));
		System.out.println("마지막 수정 일자 : " 
		    + new SimpleDateFormat("yyyy년 MM월 dd일").format(new Date(urlCon.getLastModified())));
		System.out.println("문서의 인코딩 : " + urlCon.getContentEncoding());
		
	}
}
