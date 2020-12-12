package com.nh.saerok.controller;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nh.saerok.dto.Diary;
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
	
	@PostMapping(value = "/upload")
	public Photo fileUpload(@RequestBody MultipartFile multipartFile) throws FileNotFoundException {
		if(multipartFile != null && !multipartFile.isEmpty()){
			try {
				
				String fileName = multipartFile.getOriginalFilename(); // 파일 원래 이름
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
	
					File file = new File(saveFolder + "\\"+ fileName);
	
					multipartFile.transferTo(file);
				}
				return photo; // 근데 다이어리 넘버 알아야 함
		
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}
	
	// 다이어리 작성
	@PostMapping(value="/diary")
	public int insert(HttpServletRequest request, MultipartFile multipartFile) throws FileNotFoundException {
		Photo photo = fileUpload(multipartFile);
		photo.setDiary_no(request.getParameter("no"));
		
		Diary diary = new Diary();
		diary.setCost(request.getParameter("cost"));
		diary.setContent(request.getParameter("content"));
		diary.setMember_id(request.getParameter("member_id"));
		diary.setBaby_no(request.getParameter("baby_no"));
		diary.setNo(request.getParameter("no"));
		diary.setTitle(request.getParameter("title"));
		
		System.out.println(photo.getUpload_name()); // 객체 들어옴
		try {
			
			service.insert(diary);
			String diary_no = service.maxId();
			photo.setDiary_no(diary_no);
			service.savePhoto(photo);
			
			return 1;  // 작성 완료면 1
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
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
	
	@GetMapping(value = "/download")  // 추후  /download/{no} (file_no)
	public void fileDownload(HttpServletResponse response) throws IOException{
		 byte[] fileByte = FileUtils.readFileToByteArray(new File("C:\\\\SSAFY\\\\SaerokData\\냥이.gif"));
		 
		 response.setContentType("application/octet-stream");
		 response.setContentLength(fileByte.length);
		 response.setHeader("Content-Disposition", "attachment; FileName=\"" + URLEncoder.encode("cat.jpg", "UTF-8")+"\";");
		 response.setHeader("Content-Transfer-Encoding",  "binary");
		 response.getOutputStream().write(fileByte);
		 
		 response.getOutputStream().flush();
		 response.getOutputStream().close();
	}
	
}