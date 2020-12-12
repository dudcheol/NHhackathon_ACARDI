package com.nh.saerok.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nh.saerok.dto.Diary;
import com.nh.saerok.dto.Photo;
import com.nh.saerok.service.DiaryService;

@MapperScan(basePackages = {"com.nh.saerok.mapper"})
@CrossOrigin
@RestController
public class DiaryController {
	
	@Autowired
	ServletContext servletContext;
	
	@Autowired
	DiaryService service;
	
	@GetMapping(value="/diary/{baby_no}")
	public List<Diary> selectAll(@PathVariable String baby_no) {
		List<Diary> list = null;
		try {
			list = service.selectAll(baby_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@GetMapping(value="/diary/photo/{diary_no}")
	public List<Photo> selectPhotos(@PathVariable String diary_no) {
		List<Photo> list = null;
		try {
			list = service.selectPhotos(diary_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@GetMapping(value="/diary/{baby_no}/{year}/{month}")
	public List<Diary> selectByDate(@PathVariable String baby_no, @PathVariable String year, @PathVariable String month) {
		List<Diary> list = null;
		try {
			list = service.selectByDate(baby_no, year, month);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@GetMapping(value="/diary/{baby_no}/{num}")
	public Diary selectOne(@PathVariable String baby_no, @PathVariable String num) {
		Diary diary = null;
		try {
			diary = service.selectOne(baby_no, num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return diary;
	}
	
//	@PostMapping(value="/diary")
//	public int insert(@RequestBody Diary diary) {
//		try {
//			return service.insert(diary);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return 0;
//		}
//	}
	
	@DeleteMapping(value="/diary/{num}")
	public int delete(@PathVariable String num) {
		try {
			return service.delete(num);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	@PutMapping(value="/diary")
	public int update(@RequestBody Diary diary) {
		try {
			return service.update(diary);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	// upload
	@PostMapping(value = "/upload")
	public int fileUpload(@RequestParam MultipartFile[] multipartFiles) throws FileNotFoundException {
//		System.out.println(multipartFiles[1].getOriginalFilename());
		
		try {
			for (int i = 0; i <= multipartFiles.length; i++) {
				System.out.println(multipartFiles[i].getOriginalFilename());
				if(multipartFiles[i] != null && !multipartFiles[i].isEmpty()){	
					String fileName = multipartFiles[i].getOriginalFilename(); // 파일 원래 이름
					///
					String realPath = servletContext.getRealPath("/upload"); // 실제 파일 저장할 폴더			
					String today = new SimpleDateFormat("yyMMdd").format(new Date()); // 오늘 날짜
					String saveFolder = realPath + File.separator + today;  // 파일 저장 폴더 (각 날짜별 저장폴더 생성)
					File folder = new File(saveFolder);
					if(!folder.exists())
						folder.mkdirs();
					System.out.println(saveFolder);
					///
					
					Photo photo = new Photo();
	
					if (!fileName.isEmpty()) { // abc-asdfasf-asdfs-fd.png
						String saveFileName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf('.'));
						photo.setSave_path(saveFolder);
						photo.setUpload_name(fileName);
						photo.setSave_name(saveFileName);
		
						File file = new File(saveFolder + "\\"+ saveFileName);
		
						multipartFiles[i].transferTo(file);
					}
					
					String diary_no = service.maxId();// 다이어리 넘버 구하기
					photo.setDiary_no(diary_no);
					service.savePhoto(photo);
					return 1; 
			
				}				
			}				
		} catch (IOException e) {
				e.printStackTrace();
				return 0;
		}

		return 0;
	}
	
	
//	// upload
//	@PostMapping(value = "/upload")
//	public int fileUpload(MultipartFile multipartFile) throws FileNotFoundException {
//		
//		if(multipartFile != null && !multipartFile.isEmpty()){
//			try {
//				
//				String fileName = multipartFile.getOriginalFilename(); // 파일 원래 이름
//				///
//				String realPath = servletContext.getRealPath("/upload"); // 실제 파일 저장할 폴더			
//				String today = new SimpleDateFormat("yyMMdd").format(new Date()); // 오늘 날짜
//				String saveFolder = realPath + File.separator + today;  // 파일 저장 폴더 (각 날짜별 저장폴더 생성)
//				File folder = new File(saveFolder);
//				if(!folder.exists())
//					folder.mkdirs();
//				System.out.println(saveFolder);
//				///
//				
//				Photo photo = new Photo();
//				
//				if (!fileName.isEmpty()) { // abc-asdfasf-asdfs-fd.png
//					String saveFileName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf('.'));
//					photo.setSave_path(saveFolder);
//					photo.setUpload_name(fileName);
//					photo.setSave_name(saveFileName);
//					
//					File file = new File(saveFolder + "\\"+ fileName);
//					
//					multipartFile.transferTo(file);
//				}
//				
//				String diary_no = service.maxId();// 다이어리 넘버 구하기
//				photo.setDiary_no(diary_no);
//				service.savePhoto(photo);
//				return 1; 
//				
//			} catch (IOException e) {
//				e.printStackTrace();
//				return 0;
//			}
//		}
//		return 0;
//	}
	
	// 다이어리 작성
    @PostMapping(value="/diary")
    public int insert(@RequestBody Diary d) throws FileNotFoundException {
        System.out.println("insert");

        int n = service.insert(d);
        String diary_no = service.maxId();
        return 1;
    }
	

//	@GetMapping(value = "/download")  // 추후  /download/{no} (file_no)
//	public void fileDownload(HttpServletResponse response) throws IOException{
//		 byte[] fileByte = FileUtils.readFileToByteArray(new File("C:\\\\SSAFY\\\\SaerokData\\냥이.gif"));
//		 
//		 response.setContentType("application/octet-stream");
//		 response.setContentLength(fileByte.length);
//		 response.setHeader("Content-Disposition", "attachment; FileName=\"" + URLEncoder.encode("cat.jpg", "UTF-8")+"\";");
//		 response.setHeader("Content-Transfer-Encoding",  "binary");
//		 response.getOutputStream().write(fileByte);
//		 
//		 response.getOutputStream().flush();
//		 response.getOutputStream().close();
//	}

}
