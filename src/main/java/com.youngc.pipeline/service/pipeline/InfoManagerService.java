package com.youngc.pipeline.service.pipeline;

import com.github.pagehelper.Page;
import com.youngc.pipeline.bean.context.TreeNode;
import com.youngc.pipeline.model.DevConfigParaModel;
import com.youngc.pipeline.model.ImageModel;
import com.youngc.pipeline.model.PipeInfoModel;
import org.springframework.web.multipart.MultipartFile;
import sun.reflect.generics.tree.Tree;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface InfoManagerService {
     List<TreeNode> getOrgUnitTree();

    Page getList(String keyWord, Long pid, int pageNum, int pageSize);

    PipeInfoModel getInfo(Long id);

    PipeInfoModel updateInfo(PipeInfoModel pipeInfoModel);

    PipeInfoModel insert(PipeInfoModel pipeInfoModel);

    boolean delete(String unitIds);

    List<PipeInfoModel> getInfoByCode(String code);

    List getDevModel();

    List<DevConfigParaModel> getParaName();

    List<DevConfigParaModel> getParaValue(Long unitId);

    List<TreeNode> showFile(Long devId);

    List<DevConfigParaModel> getDeatail(Long devId);

    String excelDownload(HttpServletRequest request, HttpServletResponse response, Long unitId);

    boolean readExcel(Long unitId,MultipartFile file);
}
