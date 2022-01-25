package com.zerobase.fastlms.admin.course.controller;

import com.zerobase.fastlms.admin.course.dto.CourseDto;
import com.zerobase.fastlms.admin.course.param.CourseAddInputParam;
import com.zerobase.fastlms.admin.course.param.CourseDeleteParam;
import com.zerobase.fastlms.admin.course.param.CourseListParam;
import com.zerobase.fastlms.admin.course.service.CourseService;
import com.zerobase.fastlms.admin.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AdminCourseController extends BaseController{

    private final CourseService courseService;

    private final CategoryService categoryService;

    @GetMapping("/admin/course/list.do")
    public String list(Model model, CourseListParam parameter) {

        parameter.init();
        List<CourseDto> courseList = courseService.list(parameter);

        long totalCount = 0;
        if (!CollectionUtils.isEmpty(courseList)) {
            totalCount = courseList.get(0).getTotalCount();
        }
        String queryString = parameter.getQueryString();
        String pagerHtml = getPaperHtml(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);

        model.addAttribute("list", courseList);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pagerHtml);

        return "admin/course/list";
    }

    @GetMapping(value = {"/admin/course/add.do", "/admin/course/edit.do"})
    public String add(Model model, HttpServletRequest request
            , CourseAddInputParam parameter) {

        //카테고리 정보를 내려줘야 함.
        model.addAttribute("category", categoryService.list());

        boolean editMode = request.getRequestURI().contains("/edit.do");
        CourseDto detail = new CourseDto();

        if (editMode) {
            long id = parameter.getId();
            CourseDto existCourse = courseService.getById(id);
            if (existCourse == null) {
                // error 처리
                model.addAttribute("message", "강좌정보가 존재하지 않습니다.");
                return "error/error";
            }
            detail = existCourse;
        }

        model.addAttribute("editMode", editMode);
        model.addAttribute("detail", detail);

        return "admin/course/add";
    }

    private String getNewSaveFile(String basePath,String originalFileName){


        String[] dirs = {
                String.format("%s/%d",basePath,LocalDateTime.now().getYear()),
                String.format("%s/%d/%02d/",basePath,
                    LocalDateTime.now().getYear(),LocalDateTime.now().getMonthValue()),
                String.format("%s/%d/%02d/%03d/",basePath,LocalDateTime.now().getYear(),LocalDateTime.now().getMonthValue()
                    ,LocalDateTime.now().getDayOfMonth())

        };

        for(String dir : dirs){
            File file = new File(dir);
            if(!file.isDirectory()){
                file.mkdir();
            }
        }

        String fileExtension = "";

        if(originalFileName != null){
            int dotPos = originalFileName.lastIndexOf(".");
            if(dotPos > -1 ){
                fileExtension = originalFileName.substring(dotPos+1);
            }

        }
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        String newFileName = String.format("%s%s",dirs[2],uuid);
        if(fileExtension.length() > 0){
            newFileName +="." + fileExtension;
        }

        return newFileName;
    }

    @PostMapping(value = {"/admin/course/add.do", "/admin/course/edit.do"})
    public String addSubmit(Model model, HttpServletRequest request
            , CourseAddInputParam parameter, MultipartFile file) {

        if(file != null){
            //String basePath = ""
            String basePath = "/Users/leejunkyu/Desktop/제로베이스/files";
            String orignalFileName = file.getOriginalFilename();
            String saveFilename = getNewSaveFile(basePath,orignalFileName);
            parameter.setFileName(saveFilename);
            try{
                File newFile = new File(saveFilename);

                FileCopyUtils.copy(file.getInputStream(),new FileOutputStream(newFile));

            }catch(Exception e){
                log.info(e.getMessage());
            }
        }

        boolean editMode = request.getRequestURI().contains("/edit.do");

        if (editMode) {
            long id = parameter.getId();
            CourseDto existCourse = courseService.getById(id);
            if (existCourse == null) {
                // error 처리
                model.addAttribute("message", "강좌정보가 존재하지 않습니다.");
                return "common/error";
            }

            boolean result = courseService.set(parameter);

            if(!result){
                model.addAttribute("message", "강좌정보가 존재하지 않습니다.");
                return "common/error";
            }
        } else {
            boolean result = courseService.add(parameter);

            if(!result){
                model.addAttribute("message", "서버에러 발생 ");
                return "common/error";
            }
        }

        return "redirect:/admin/course/list.do";
    }

    @PostMapping("/admin/course/delete.do")
    public String del(Model model, HttpServletRequest request
            , CourseDeleteParam parameter) {

        boolean result = courseService.delete(parameter);

        return "redirect:/admin/course/list.do";
    }
}
