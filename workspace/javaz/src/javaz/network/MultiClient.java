package javaz.network;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MultiClient extends JFrame implements ActionListener, KeyListener {
	private static final long serialVersionUID = 1L;
	private static final int SERVER_PORT = 5000;
	private BufferedReader br; // 서버의 메시지 읽기
	private PrintWriter pw; // 서버에게 메시지 보내기
	private Socket clientSocket;

	private JLabel titleLbl, nicknameLbl;	// 닉네임 표시 레이블
	private JTextField chatTxt;				// 채팅 내용 입력 필드
	private JButton clearBtn, saveBtn;		// CLEAR, SAVE 버튼
	private JTextArea chatArea;				// 채팅 내용 표시 영역
	private JPanel panel;
	private JScrollPane scrollPane;
	private String nickname;

	public MultiClient() {
		// 1. 대화명을 입력받아 nickname에 저장
		//    대화명을 입력하지 않은 경우 실행 중단
		nickname = JOptionPane.showInputDialog("대화명을 입력해 주세요");
		
		if (nickname != null) {
			nickname = nickname.trim();
			
			if (nickname.isEmpty()) {
				JOptionPane.showMessageDialog(null, "이름을 입력하지 않았습니다. 대화를 종료합니다.");
				return;
			} 
			
		} else {
			JOptionPane.showMessageDialog(null, "취소하였습니다. 대화를 종료합니다.");
			return;
		}
		

		titleLbl = new JLabel("JAVA CHAT v.1", JLabel.CENTER);
		nicknameLbl = new JLabel(nickname);
		chatTxt = new JTextField(10);
		clearBtn = new JButton("CLEAR");
		saveBtn = new JButton("SAVE");
		chatArea = new JTextArea();
		panel = new JPanel();

		titleLbl.setFont(new Font("Arial Black", Font.BOLD, 15));
		titleLbl.setPreferredSize(new Dimension(350, 40));

		chatArea.setLineWrap(true);
		chatArea.setWrapStyleWord(true);
		chatArea.setEditable(false);
		scrollPane = new JScrollPane(chatArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		chatTxt.addKeyListener(this);
		clearBtn.addActionListener(this);
		saveBtn.addActionListener(this);

		panel.add(nicknameLbl);
		panel.add(chatTxt);
		panel.add(clearBtn);
		panel.add(saveBtn);

		add(scrollPane, "Center");
		add(titleLbl, "North");
		add(panel, "South");
		setSize(350, 300);
		setTitle("JAVA CHAT v.1");
		setLocation(300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			// 채팅 창 오픈 시 ---------------------------
			@Override
			public void windowOpened(WindowEvent e) {
				try {
					// 2. 서버 연결 및 입출력 스트림 생성
					clientSocket = new Socket("localhost", SERVER_PORT);
					
					// 중복 확인
					if (MultiServer.isDuplicate(nickname)) {
						JOptionPane.showMessageDialog(null, "중복된 대화명입니다. 대화를 종료합니다.");
						windowClosing(e);
						return;
					}
					
					// 2.1 입출력 스트림 생성
					br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
					pw = new PrintWriter(clientSocket.getOutputStream(), true);
					
					// 2.2 서버로 "nickname#Hello Server!" 전송
					pw.println(nickname + "#Hello Server!");
					
					// 3. 서버에서 더 이상 읽어올 값이 없을 때까지
					//    메시지를 반복하여 수신하고 채팅 화면에 덧붙이기를
					//    스레드로 처리하고 시작시키기
					new Thread(() -> {
						try {
							while (br != null) {
								chatArea.append(br.readLine().trim() + "\n");
							}
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}).start();
					chatTxt.requestFocus();
					
				} catch (SocketException e1){
					System.err.println(">> 서버에 연결하지 못했습니다.");
					System.err.println(">> 서버 가동 상태를 확인해 주세요.");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			// 채팅 창 종료시 ---------------------------
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("WINDOW CLOSING....");
				// 4. 서버 연결 종료 처리 - 서버에 -1 전송
				pw.println("-1");
				// 채팅 창 닫기
				dispose();
				
//				System.exit(0);
				
				// 입출력 스트림 및 소켓 닫기
				try {
					if (pw != null) { pw.close(); }
					if (br != null) { br.close(); }
					if (clientSocket != null) { clientSocket.close(); }
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				System.exit(0);
			}
		});
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// 5. 엔터키 입력 시 - 값이 입력되어 있는 경우
		if (e.getKeyCode() == 10 && !chatTxt.getText().isEmpty()) {
			// 5.1 입력값을 서버로 전송하고
			pw.println(chatTxt.getText());
			// 입력값 지우기
			chatTxt.setText("");
			// 입력 필드 포커스 맞추기
			chatTxt.requestFocus();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		// 6. CLEAR 버튼이 눌린 경우 이벤트 처리
		if (obj == clearBtn) {
			System.out.println("CLEAR 버튼 클릭됨!!");
			// 입력값 지우기
			chatArea.setText("");
			// 입력 필드 포커스 맞추기
			chatTxt.requestFocus();
		}
		
		// 7. SAVE 버튼이 눌린 경우 이벤트 처리
		if (obj == saveBtn) {
			// 파일 저장 대화창 띄우기
			JFileChooser fc = new JFileChooser("C:\\dev\\");
			fc.setAcceptAllFileFilterUsed(false);
			
			// 확장자 txt로 필터링
			FileNameExtensionFilter extFilter = 
					new FileNameExtensionFilter("텍스트 (*.txt)", "txt");
			fc.addChoosableFileFilter(extFilter);
			
			System.out.println(chatArea.getText());
			// a. 저장 버튼이 눌린 경우
			if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				// b. 선택된 파일을 받아와서
				File file = fc.getSelectedFile();
				// c. 이미 존재하는 경우에는
				if (file.exists()) {
					// d. 덮어쓸 것인지 여부를 확인하고 대화 내용을 파일로 저장
					if (JOptionPane.showConfirmDialog(this, "파일이 존재합니다.\n덮어쓸까요?", 
								"알림", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						filesave(file.toString());
					}
				} else { // e. 존재하지 않는 경우에는
				   // 대화 내용을 파일로 저장
					filesave(file.toString());
				}
			}
		}
	}
	
	public void filesave(String filename) {
		try {
			filename = filename.endsWith(".txt") ? filename : filename + ".txt";
			FileWriter fw = new FileWriter(filename);
			System.out.println(chatArea.getText());
			fw.write(chatArea.getText());
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JOptionPane.showMessageDialog(this, "대화 내용이 저장되었습니다.", 
				"알림", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void main(String[] args) {
		new MultiClient();
	}
}
