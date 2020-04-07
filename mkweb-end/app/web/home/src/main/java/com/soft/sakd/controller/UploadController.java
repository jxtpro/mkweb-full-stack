package com.soft.sakd.controller;

import com.soft.sakd.biz.mange.AttachmentsMange;
import com.soft.sakd.biz.param.AttachmentsParam;
import com.soft.sakd.biz.vo.AttachmentsVo;
import com.soft.sakd.biz.vo.UploadResultVo;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
/**
 * @author xujie
 * @since 2020/4/5 15:53
 *     <p>没有过多服务器可以实现 nfs服务nginx集群，目前业务也不高
 */
@RestController
@Log4j2
@RequiredArgsConstructor
public class UploadController {

  @Value("${deploy.os.name}")
  private String osName;

  private static String LINUX_UPLOAD_DIR = "/home/files-server/static_files/";
  private static String WINDOW_UPLOAD_DIR = "D:\\files-server\\static_files\\";
  @Autowired private AttachmentsMange attachmentsMange;

  @RequestMapping("/upload")
  public UploadResultVo fileUpload(@RequestParam("file") MultipartFile srcFile) {
    UploadResultVo resultVo = new UploadResultVo();
    resultVo.setStatus(false);
    if (srcFile.isEmpty()) {
      resultVo.setMsg("上传文件为空");
      return resultVo;
    }
    // 选择了文件，开始上传操作
    try {
      // 构建上传目标路径，找到了项目的target的classes目录
      FileManger fileManger = new FileManger(srcFile).storage();
      resultVo.setData(fileManger.getStoreBeforeFileInfo());
      resultVo.setStatus(true);
      return resultVo;
    } catch (IOException e) {
      log.error("上传异常", e);
      resultVo.setMsg("上传异常，请稍后再试.");
      return resultVo;
    }
  }

  private class FileManger {

    private MultipartFile srcFile;
    private byte[] bytes;
    private String newFileName;
    private final ThreadLocal<DateFormat> DATE_FORMATTER =
        ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMM"));
    private String times = DATE_FORMATTER.get().format(new Date());

    public FileManger(MultipartFile srcFile) {
      this.srcFile = srcFile;
    }

    public byte[] getBytes() {
      return bytes;
    }

    public String getNewFileName() {
      return newFileName;
    }

    public AttachmentsVo getStoreBeforeFileInfo() {
      AttachmentsParam param = new AttachmentsParam();
      param.setName(newFileName);
      param.setUid(newFileName);
      param.setSize(bytes.length);
      param.setType(srcFile.getContentType());
      param.setUrl(
          "/static_files/artcle/"
              + times
              + "/"
              + newFileName); // 相对路径, nginx -> location 匹配下载 ,想要做下载接管，还需要 参考： files-server
      return attachmentsMange.insertAttachments(param);
    }

    public FileManger storage() throws IOException {
      // 可以使用System.getProperty("os.name")进行判断系统
      File upload =
          new File("win".equals(osName) ? WINDOW_UPLOAD_DIR : LINUX_UPLOAD_DIR, "article/" + times);
      // 若目标文件夹不存在，则创建
      if (!upload.exists()) {
        upload.mkdirs();
      }
      // 根据srcFile大小，准备一个字节数组
      bytes = srcFile.getBytes();
      // 通过项目路径，拼接上传路径
      Path path = Paths.get(upload.getAbsolutePath() + "/" + srcFile.getOriginalFilename());
      // ** 开始将源文件写入目标地址
      Files.write(path, bytes);
      String uuid = UUID.randomUUID().toString().replaceAll("-", "");
      // 获得文件原始名称
      String fileName = srcFile.getOriginalFilename();
      // 获得文件后缀名称
      String suffixName = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
      // 生成最新的uuid文件名称
      newFileName = uuid + "." + suffixName;
      return this;
    }
  }
}
