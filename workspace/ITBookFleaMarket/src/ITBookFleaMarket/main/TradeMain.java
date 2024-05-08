package ITBookFleaMarket.main;

import java.util.List;
import java.util.Scanner;

import ITBookFleaMarket.dao.MemberDAO;
import ITBookFleaMarket.dao.TradeDAO;
import ITBookFleaMarket.vo.BookVO;
import ITBookFleaMarket.vo.TradeLogVO;
import ITBookFleaMarket.vo.TradePostVO;

public class TradeMain {
	private Scanner sc;
	private TradePostVO tpvo;
	private TradeLogVO tlvo;
	private BookVO bvo;
	private TradeDAO tdao;
	private MemberDAO mdao;

	// 0.멤버필드들을 초기화하는 생성자
	public TradeMain() {
		sc = new Scanner(System.in);
		tpvo = new TradePostVO();
		new TradeLogVO();
		bvo = new BookVO();
		tdao = new TradeDAO();
		mdao = new MemberDAO();
	}

	public void trademenu() { // 1.메인 메뉴
		MemberMain memberMain = new MemberMain();
		while (true) {
			if (MemberMain.loginId.equals("")) { memberMain.menu(); }
			
			System.out.println();
			System.out.println(">> 책 판매/구매 메뉴 -----------------------");
			System.out.println("   1.판매   2.구매   3.뒤로");
			System.out.print(">> 선택 : ");
			String input = sc.nextLine();

//			switch (input) {
//			case "1":
//				sell();
//				break;
//			case "2":
//				buy();
//				break;
//			case "3":
////				System.out.println("시스템을 종료합니다.");
////				sc.close();
////				System.exit(0);
//				if (MemberMain.loginId.equals("")) {
//					memberMain.menu(); break;
//				} else {
//					memberMain.userMenu(); break;
//				}
//			default:
//				System.out.println(">> 1 ~ 3을 입력해주세요.");
//			}
//			
			if (input.equals("1")) { sell(); }
			if (input.equals("2")) { buy(); }
			
			memberMain.userMenu(); break;
			
			
//			System.out.println();
		} // END while
	}// END menu()

	public void sell() { // 1.1 판매 메뉴
		while (true) {
			System.out.println();
			System.out.println(">> 책 판매 -----------------------");
			System.out.println("   1.나의 거래글 조회   2.거래글 등록   3.뒤로");
			System.out.print(">> 선택 : ");
			String input = sc.nextLine();

			switch (input) {
			case "1":
				postListMember();
				break;
			case "2":
				uploadPost();
				break;
			case "3":
				trademenu();
			default:
				System.out.println(">> 1 ~ 3을 입력해주세요.");
			}
			System.out.println();
		} // END while
	}// END sell

	public void postListMember() { // 1.1.1 나의 거래글 조회
		List<TradePostVO> tradeList = tdao.selectMemberPost(MemberMain.loginId);
		if (tradeList == null || tradeList.size() < 1) {
			System.out.println(">> 등록한 거래글이 없습니다.");
			trademenu();
		} else { // 그렇지 않은 경우
			System.out.println("-------------------------------------------");
			System.out.println(" 글번호 | 도서명(도서번호) | 글쓴이 | 가격 | 거래번호 | 등록일자 | 조회수");
			System.out.println("-------------------------------------------");
			for (TradePostVO tpvo : tradeList) {
				bvo = tdao.selectBook(tpvo.getBookNo());
				System.out.print(" " + tpvo.getPostNo() + " | ");
				if (tdao.selectTradeLog(tpvo.getTrLogNo()).getTradeState().equals("판매완료")) {
					System.out.print("(판매완료)");
				}
				System.out.println(bvo.getBookName() + "(" + tpvo.getBookNo() + ") | " + bvo.getAuthor() + " | "
						+ bvo.getBookPrice() + "원 | " + tpvo.getTrLogNo() + " | " + tpvo.getPostRegdate() + " | "
						+ tpvo.getPostHitNo());
			}
		}

		while (true) {
			System.out.println("-------------------------------------------");
			System.out.println("   1.거래글 수정   2.거래글 삭제   3.뒤로");
			System.out.print(">> 선택 : ");
			String input = sc.nextLine();

			switch (input) {
			case "1":
				changePost();
				break;
			case "2":
				deletePost();
				break;
			case "3":
				trademenu();
			default:
				System.out.println(">> 1 ~ 3을 입력해주세요.");
			}
			System.out.println();
		} // END while

	}

	public void changePost() { // 1.1.1.1 글 수정
		System.out.println(">> 수정할 거래글의 글번호를 입력해주세요.");

		int inputPno = 0;
		while (true) {
			System.out.print(">> 입력 : ");
			String inputPnoStr = sc.nextLine();
			try {
				inputPno = Integer.parseInt(inputPnoStr);
				if (tdao.selectTradePost(inputPno) != null) {
					if (!tdao.selectTradeLog(tdao.selectTradePost(inputPno).getTrLogNo()).getTradeState().equals("판매중")) {
						System.out.println(">> 판매완료 또는 예약중인 글은 수정할 수 없습니다.");
						postListMember();
					} else
						break;
				} else {
					System.out.println(">> 글이 존재하지 않습니다.");
					System.out.println();
				}
			} catch (NumberFormatException e) {
				System.out.println(">> 숫자를 입력해주세요.");
				System.out.println();
			}
		}

		tpvo = tdao.selectTradePost(inputPno);
		bvo = tdao.selectBook(tpvo.getBookNo());

		System.out.println(">> 거래글을 수정합니다.(변경을 원치 않을 시 ENTER)");

		System.out.println("   기존 메모  : " + tpvo.getMemo());
		System.out.print("   새로운 메모 : ");
		String newMemo = sc.nextLine();
		if (newMemo.isEmpty()) {
			tpvo.setMemo(newMemo);
		}

		System.out.println("   기존 가격  : " + bvo.getBookPrice());
		int newPrice;
		while (true) {
			System.out.print("   새로운 가격 : ");
			String newPriceStr = sc.nextLine();
			try {
				newPrice = Integer.parseInt(newPriceStr);
				if (tdao.selectTradePost(inputPno) != null)
					break;
			} catch (NumberFormatException e) {
				System.out.println(">> 숫자만 입력해주세요.");
				System.out.println();
			}
		}
		bvo.setBookPrice(newPrice);

		System.out.println("   기존 책 상태  : " + bvo.getBookState());
		System.out.print("   새로운 책 상태 : ");
		System.out.println("   1.최상   2.상   3.중   4.하   5.최하");
		while (true) {
			System.out.print(">> 선택 : ");
			String newState = sc.nextLine();

			if (newState.equals("1")) {
				bvo.setBookState("최상");
				break;
			} else if (newState.equals("2")) {
				bvo.setBookState("상");
				break;
			} else if (newState.equals("3")) {
//				bvo.setBookState("즁");
				bvo.setBookState("중");
				break;
			} else if (newState.equals("4")) {
				bvo.setBookState("하");
				break;
			} else if (newState.equals("5")) {
				bvo.setBookState("최하");
				break;
			} else {
				System.out.println(">> 1 ~ 5을 입력해주세요.");
				System.out.println();
			}
		} // END while

		if (tdao.updatePost(bvo, inputPno)) {
			if (tdao.updatePost(tpvo, inputPno)) {
				System.out.println(">> 거래글 수정이 완료되었습니다.");
			} else {
				System.out.println(">> 거래글 수정에 실패하였습니다.");
			}
		} else {
			System.out.println(">> 거래글 수정에 실패하였습니다.");
		}

		postListMember();
	}

	public void deletePost() { // 1.1.1.2 글 삭제
		System.out.println(">> 삭제할 거래글의 글번호를 입력해주세요.");

		int inputPno = 0;
		while (true) {
			System.out.print(">> 입력 : ");
			String inputPnoStr = sc.nextLine();
			try {
				inputPno = Integer.parseInt(inputPnoStr);
				if (tdao.selectTradePost(inputPno) != null)
					break;
			} catch (NumberFormatException e) {
				System.out.println(">> 숫자를 입력해주세요.");
				System.out.println();
			}
		}
		System.out.println(">> 'y' 입력 시 " + inputPno + "번 글을 삭제합니다.");
		System.out.print(">> ");
		if (!sc.nextLine().equals("y")) {
			System.out.println(">> 글 삭제 취소.");
			System.out.println("   거래 홈으로 돌아갑니다.");
			trademenu();
		}

		if (!tdao.updatePostLog(inputPno)) {
			System.out.println(">> 글 삭제 실패.");
			System.out.println("   잠시 후 다시 시도해주세요.");
			trademenu();
		}

		if (!tdao.deletePost(inputPno)) {
			System.out.println(">> 글 삭제 실패.");
			System.out.println("   잠시 후 다시 시도해주세요.");
			trademenu();
		}

		System.out.println(">> " + inputPno + "번 글 삭제 완료.");
		postListMember();
	}

	public void uploadPost() { // 1.1.2 글 등록
		tpvo = new TradePostVO();
		bvo = new BookVO();

		System.out.println();
		System.out.println(">> 거래글 등록하기");

		while (true) {
			System.out.print("   ISBN : ");
			String isbnInputStr = sc.nextLine();
			try {
				int isbnInput = Integer.parseInt(isbnInputStr);
				if (isbnInputStr != null)
					if (tdao.bookSellerCheck(isbnInput, MemberMain.loginId)) {
						bvo.setIsbn(isbnInput);
						break;
					} else {
						System.out.println(">> 해당 책으로 등록된 글이 이미 있습니다.");
						System.out.println("   메뉴로 돌아갑니다...");
						trademenu();
					}
			} catch (NumberFormatException e) {
				System.out.println(">> 숫자를 입력해주세요.");
			}
		}

		while (true) {
			System.out.print("   도서 이름 : ");
			String nameInput = sc.nextLine();
			if (nameInput != null) {
				bvo.setBookName(nameInput);
				break;
			} else {
				System.out.println(">> 제목을 입력해주세요.");
				System.out.println();
			}
		}

		System.out.println("   도서 분류 : ");
		System.out.println("   1.OS   2.프로그래밍   3.자료구조");
		while (true) {
			System.out.print(">> 선택 : ");
			String groupInput = sc.nextLine();

			if (groupInput.equals("1")) {
				bvo.setBookGroup("OS");
				break;
			} else if (groupInput.equals("2")) {
				bvo.setBookGroup("프로그래밍");
				break;
			} else if (groupInput.equals("3")) {
				bvo.setBookGroup("자료구조");
				break;
			} else {
				System.out.println(">> 1 ~ 3을 입력해주세요.");
				System.out.println();
			}
		} // END while

		while (true) {
			System.out.print("   도서 가격 : ");
			String priceInputStr = sc.nextLine();
			try {
				int priceInput = Integer.parseInt(priceInputStr);
				if (priceInputStr != null)
					bvo.setBookPrice(priceInput);
				break;
			} catch (NumberFormatException e) {
				System.out.println(">> 숫자만 입력해주세요.");
				System.out.println();
			}
		}

		System.out.println("   도서 상태 : ");
		System.out.println("   1.최상   2.상   3.중   4.하   5.최하");
		while (true) {
			System.out.print(">> 선택 : ");
			String stateInput = sc.nextLine();

			if (stateInput.equals("1")) {
				bvo.setBookState("최상");
				break;
			} else if (stateInput.equals("2")) {
				bvo.setBookState("상");
				break;
			} else if (stateInput.equals("3")) {
//				bvo.setBookState("즁");
				bvo.setBookState("중");
				break;
			} else if (stateInput.equals("4")) {
				bvo.setBookState("하");
				break;
			} else if (stateInput.equals("5")) {
				bvo.setBookState("최하");
				break;
			} else {
				System.out.println(">> 1 ~ 5을 입력해주세요.");
				System.out.println();
			}
		} // END while

		while (true) {
			System.out.print("   글쓴이 : ");
			String authorInput = sc.nextLine();
			if (authorInput != null) {
				bvo.setAuthor(authorInput);
				break;
			} else {
				System.out.println(">> 상태를 입력해주세요.");
				System.out.println();
			}
		}

		while (true) {
			System.out.print("   출판사 : ");
			String punblisherInput = sc.nextLine();
			if (punblisherInput != null) {
				bvo.setPublisher(punblisherInput);
				break;
			} else {
				System.out.println(">> 상태를 입력해주세요.");
				System.out.println();
			}
		}

		System.out.print("   메모 : ");
		String memoInput = sc.nextLine();
		tpvo.setMemo(memoInput);

		if (!tdao.makePost(bvo, MemberMain.loginId)) {
			System.out.println(">> 거래글 등록에 실패하였습니다.");
			return;
		}

		int bookNoinput = tdao.bringbookNo(bvo.getIsbn(), MemberMain.loginId);

		if (!tdao.makePost(tlvo, bookNoinput)) {
			System.out.println(">> 거래글 등록에 실패하였습니다.");
			return;
		}

		if (!tdao.makePost(tpvo, bookNoinput)) {
			System.out.println(">> 거래글 등록에 실패하였습니다.");
			return;
		}
		System.out.println(">> 거래글이 등록되었습니다.");
	}

	public void buy() { // 1.2 구매 메뉴(전체 거래글 조회)(조회수/등록순)
		System.out.println();
		System.out.println(">> 거래글 정렬");
		System.out.println("   1.조회수   2.등록순   3.뒤로");
		while (true) {
			System.out.print(">> 선택 : ");
			String input = sc.nextLine();

			switch (input) {
			case "1":
				tradePostList("posthitno DESC");
				break;
			case "2":
				tradePostList("postregdate DESC");
				break;
			case "3":
				trademenu();
			default:
				System.out.println(">> 1 ~ 3을 입력해주세요.");
			}
		}
	}

	// 페이지 뽑기 + v 입력시 검색 + s 입력시 상세보기
	public void pagefindselect() {
		System.out.println("   1.이전 페이지   2.다음 페이지   3.뒤로");
		System.out.println("   f.검색        v.글 상세보기");
		System.out.print(">> 선택 : ");
		while (true) {
			String input = sc.nextLine();

			switch (input) {
			case "1":
				// ();
				break;
			case "2":
				// ();
				break;
			case "3":
				trademenu();
			case "f":
				findTradepost();
			case "v":
				viewTradepost();
			default:
				System.out.println(">> 올바른 키를 입력해주세요.");
			}
			System.out.println();
		} // END while
	}

	public void tradePostList(String order) { // 1.2.1 전체 거래글 조회
		List<TradePostVO> tradeList = tdao.selectTradePost(order);
		if (tradeList == null || tradeList.size() < 1) {
			System.out.println(">> 등록된 글이 없습니다.");
		} else { // 그렇지 않은 경우
			System.out.println("-------------------------------------------");
			System.out.println(" 글번호 | 도서명 | 글쓴이 | 가격 | 판매자 | 등록일자 | 조회수");
			System.out.println("-------------------------------------------");
			for (TradePostVO tpvo : tradeList) {
				bvo = tdao.selectBook(tpvo.getBookNo());
				System.out.print(" " + tpvo.getPostNo() + " | ");
				if (tdao.selectTradeLog(tpvo.getTrLogNo()).getTradeState().equals("판매완료")) {
					System.out.print("(판매완료)");
				}
				System.out.println(bvo.getBookName() + " | " + bvo.getAuthor() + " | " + bvo.getBookPrice() + "원 | "
						+ bvo.getSeller() + " | " + tpvo.getPostRegdate() + " | " + tpvo.getPostHitNo());
			}
			System.out.println("-------------------------------------------");
			pagefindselect();
		}
	}

	public void findTradepost() { // 1.2.1.f 거래글 검색
		String groupInput = "";
		String strInput = "";

		while (true) {
			System.out.println("   도서 검색 ---------------------------------");
			System.out.println("   도서 분류 : ");
			System.out.println("   1.전체   2.OS   3.프로그래밍   4.자료구조");
			System.out.print(">> 선택 : ");
			String input = sc.nextLine();

			if (input.equals("1")) {
				groupInput = "";
				break;
			} else if (input.equals("2")) {
				groupInput = "OS";
				break;
			} else if (input.equals("3")) {
				groupInput = "프로그래밍";
				break;
			} else if (input.equals("4")) {
				groupInput = "자료구조";
				break;
			} else {
				System.out.println(">> 1 ~ 4를 입력해주세요.");
				System.out.println();
			}
		} // END while

		System.out.println(">> 통합검색(도서명, 글쓴이, 출판사, 판매자)");
		System.out.print("   검색어 입력 : ");
		String input = sc.nextLine();
		if (!input.isEmpty()) {
			strInput = input;
		}

		List<TradePostVO> tradeList = tdao.selectTradePost(groupInput, strInput);
		if (tradeList == null || tradeList.size() < 1) {
			System.out.println(">> 등록된 글이 없습니다.");
		} else { // 그렇지 않은 경우
			System.out.println(" 글번호 | 도서명 | 글쓴이 | 가격 | 판매자 | 등록일자 | 조회수");
			System.out.println("-------------------------------------------");
			for (TradePostVO tpvo : tradeList) {
				bvo = tdao.selectBook(tpvo.getBookNo());
				System.out.print(" " + tpvo.getPostNo() + " | ");
				if (tdao.selectTradeLog(tpvo.getTrLogNo()).getTradeState().equals("판매완료")) {
					System.out.print("(판매완료)");
				}
				System.out.println(bvo.getBookName() + "|" + bvo.getAuthor() + "|" + bvo.getBookPrice() + "원 | "
						+ bvo.getSeller() + " | " + tpvo.getPostRegdate() + " | " + tpvo.getPostHitNo());
			}
			System.out.println("-------------------------------------------");
			pagefindselect();
		}
	}

	public void viewTradepost() { // 1.2.1.v 거래글 상세보기 --------------------판매자 후기
		System.out.println("   상세보기 할 글번호를 입력해주세요.");

		int inputPno = 0;
		while (true) {
			System.out.print(">> 입력 : ");
			String inputPnoStr = sc.nextLine();
			try {
				inputPno = Integer.parseInt(inputPnoStr);
				if (tdao.selectTradePost(inputPno) != null) {
					break;
				} else {
					System.out.println(">> 글이 존재하지 않습니다.");
					System.out.println();
				}
			} catch (NumberFormatException e) {
				System.out.println(">> 숫자를 입력해주세요.");
				System.out.println();
			}
		}

		if (!tdao.addHitNo(inputPno)) {
			System.out.println(">> 글을 조회할 수 없습니다.");
			System.out.println("   잠시 후 다시 시도해주세요.");
			trademenu();
		}

		tpvo = tdao.selectTradePost(inputPno);
		bvo = tdao.selectBook(tpvo.getBookNo());
		System.out.println(" 글번호 | 도서명 | 글쓴이 | 가격 | 판매자 | 등록일자 | 조회수");
		System.out.println("-------------------------------------------");
		System.out.println(
				" " + tpvo.getPostNo() + " | " + bvo.getBookName() + " | " + bvo.getAuthor() + "|" + bvo.getBookPrice()
						+ "원 | " + bvo.getSeller() + " | " + tpvo.getPostRegdate() + " | " + tpvo.getPostHitNo());
		System.out.println("-------------------------------------------");

		if (tdao.selectTradeLog(tpvo.getTrLogNo()).getTradeState().equals("판매완료")) {
			System.out.println("   ----- 판매완료된 게시물입니다. -----");
		}
		System.out.println("   출판사   : " + bvo.getPublisher());
		System.out.println("   도서 분류 : " + bvo.getBookGroup());
		System.out.println("   도서 상태 : " + bvo.getBookState());
		System.out.println();
		System.out.println("   메모 : " + tpvo.getMemo());
		System.out.println();
		System.out.println("   도서번호 : " + tpvo.getBookNo());
		System.out.println("   isbn  : " + bvo.getIsbn());
		System.out.println("----------------------------------");

		while (true) {
			System.out.println("   1.도서 구매하기   2. 판매자 후기 조회   3.판매자 신고하기   4.목록으로");
			System.out.print(">> 선택 : ");
			String input = sc.nextLine();

			switch (input) {
			case "1":
				if (bvo.getSeller().equals(MemberMain.loginId)) {
					System.out.println(">> 본인이 등록한 상품은 구매할 수 없습니다.");
				} else if (!tdao.selectTradeLog(tpvo.getTrLogNo()).getTradeState().equals("판매중")) {
					System.out.println(">> 판매완료 또는 예약중인 상품은 구매할 수 없습니다.");
				} else {
					purchase(bvo);
					break;
				}
			case "2":
				// 후기조회 되도록 할것
				break;
			case "3":
				if (bvo.getSeller().equals(MemberMain.loginId)) {
					System.out.println(">> 본인 계정은 신고할 수 없습니다.");
				} else {
					report(bvo);
					break;
				}
			case "4":
				buy();
			default:
				System.out.println(">> 1 ~ 4를 입력해주세요.");
			}
			System.out.println();
		} // END while

	}

	public void purchase(BookVO bvo) { // 1.2.1.v.1 구매
		System.out.println(">> " + bvo.getBookName() + "(" + bvo.getBookNo() + ")를 구매합니다.");
		System.out.println("   금액 : " + bvo.getBookPrice() + "원");

		System.out.println("   보유 마일리지     : " + tdao.bringMileage(MemberMain.loginId) + "포인트");
		System.out.println("   사용 가능 마일리지 : " + (int) (bvo.getBookPrice() / 10) + "포인트");
		System.out.println();
		System.out.println("   사용하실 마일리지를 입력해주세요");
		String inputMgStr;
		int inputMg;
		while (true) {
			System.out.print(">> 입력 : ");
			inputMgStr = sc.nextLine();
			try {
				inputMg = Integer.parseInt(inputMgStr);
				if (inputMg > Math.floor(bvo.getBookPrice() / 10)) {
					System.out.println("사용 가능 마일리지를 초과하였습니다.(결제금액의 10%까지 사용 가능)");
				} else if(inputMg > tdao.bringMileage(MemberMain.loginId)) {
					System.out.println("보유 마일리지를 초과하였습니다.");
				} else
					break;
			} catch (NumberFormatException e) {
				System.out.println(">> 숫자를 입력해주세요.");
				System.out.println();
			}
		}
		System.out.println("----------------------------------");
		System.out.println("   도서 가격    : " + bvo.getBookPrice() + "원");
		System.out.println("   사용 마일리지 : " + inputMgStr + "포인트");
		System.out.println("----------------------------------");
		int finalPrice = bvo.getBookPrice() - inputMg;
		System.out.println("   최종 결제금액 : " + finalPrice + "원");
		System.out.println();
		System.out.println("   결제하시겠습니까?");
		System.out.println("   1.결제하기   2.취소");

		while (true) {
			System.out.print(">> 선택 : ");
			String input = sc.nextLine();
			switch (input) {
			case "1":
//				if (!tdao.transactionIn(mdao.selectMember(MemberMain.loginId), finalPrice)) {
//					System.out.println(">> 결제에 실패하였습니다. 잠시 후 다시 시도해주세요.");
//					System.out.println("   메뉴로 돌아갑니다...");
//					trademenu();
//				}
				
				if (!tdao.transactionIn(mdao.selectMember(MemberMain.loginId), finalPrice, bvo.getBookNo())) {
					System.out.println(">> 결제에 실패하였습니다. 잠시 후 다시 시도해주세요.");
					System.out.println("   메뉴로 돌아갑니다...");
					trademenu();
				}

//				if (!tdao.updateMileage(MemberMain.loginId, inputMg, (int) (finalPrice * 0.005))) {
//					System.out.println(">> 결제에 실패하였습니다. 잠시 후 다시 시도해주세요.");
//					System.out.println("   메뉴로 돌아갑니다...");
//					trademenu();
//				}

				if (!tdao.addMileageLog(MemberMain.loginId, "도서 구매", inputMg)) {
					System.out.println(">> 결제에 실패하였습니다. 잠시 후 다시 시도해주세요.");
					System.out.println("   메뉴로 돌아갑니다...");
					trademenu();
				}

//				if (!tdao.addMileageLog(MemberMain.loginId, "구매 적립", (int) (finalPrice * 0.005))) {
//					System.out.println(">> 결제에 실패하였습니다. 잠시 후 다시 시도해주세요.");
//					System.out.println("   메뉴로 돌아갑니다...");
//					trademenu();
//				}
				
				if (!mdao.updateMileage(MemberMain.loginId, inputMg)) {
					System.out.println(">> 결제에 실패하였습니다. 잠시 후 다시 시도해주세요.");
					System.out.println("   메뉴로 돌아갑니다...");
					trademenu();
				}

				if (!tdao.soldOut(bvo.getBookNo())) {
					System.out.println(">> 결제에 실패하였습니다. 잠시 후 다시 시도해주세요.");
					System.out.println("   메뉴로 돌아갑니다...");
					trademenu();
				}

				System.out.println(">> 결제 완료되었습니다.");
				System.out.println("   메뉴로 돌아갑니다.");
				trademenu();
				break;
			case "2":
				System.out.println("   결제가 취소되었습니다.");
				System.out.println("   메뉴로 돌아갑니다...");
				trademenu();
				break;
			default:
				System.out.println(">> 1 ~ 2를 입력해주세요.");
			}
		}

	}

	public void report(BookVO bvo) { // 1.2.1.v.2 신고----------계정 정지 메서드 추가하기
		System.out.println(">> 사용자를 신고하시겠습니까?");
		System.out.println("   1.신고하기   2.취소");
		while (true) {
			System.out.print(">> 선택 : ");
			String input = sc.nextLine();
			switch (input) {
			case "1":
				if (!tdao.reportMember(bvo.getSeller())) {
					System.out.println(">> 신고에 실패하였습니다. 잠시 후 다시 시도해주세요.");
					System.out.println("   메뉴로 돌아갑니다...");
					trademenu();
				}

				// 여기에 계정 정지 메서드 추가
				mdao.updateInactivated(bvo.getSeller());

				System.out.println(">> 신고 완료되었습니다.");
				System.out.println("   메뉴로 돌아갑니다.");
				trademenu();
				break;
			case "2":
				System.out.println("   신고가 취소되었습니다.");
				System.out.println("   메뉴로 돌아갑니다...");
				trademenu();
			default:
				System.out.println(">> 1 ~ 2를 입력해주세요.");
			}
		}
	}

//	public static void main(String[] args) {
//		MemberMain.loginId = "aaa";
//		new TradeMain().trademenu();
//	}
}
