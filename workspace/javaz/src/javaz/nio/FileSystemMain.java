package javaz.nio;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

// FileSystem
// - 파일 시스템 관련 인터페이스
// - 

// FileStore
// - 파티션 표시
public class FileSystemMain {
	public static void main(String[] args) throws IOException {
		FileSystem fileSystem = FileSystems.getDefault();
		
//		System.out.println(fileSystem); // 주소값 나옴
		
		for (FileStore fs : fileSystem.getFileStores()) {
			System.out.println("드라이브: " + fs.name());
			System.out.println("파일 시스템: " + fs.type());
			System.out.println("전체 용량(GB): " + fs.getTotalSpace() / 1024 / 1024 / 1024);
			System.out.println("사용 중인 용량(GB): " 
							+ (fs.getTotalSpace() - fs.getUnallocatedSpace()) 
								/ 1024 / 1024 /1024);
			System.out.println("사용 가능한 용량(GB): " 
								+ fs.getUsableSpace() / 1024 / 1024 / 1024);
			
			System.out.println();
		}
	}

}
