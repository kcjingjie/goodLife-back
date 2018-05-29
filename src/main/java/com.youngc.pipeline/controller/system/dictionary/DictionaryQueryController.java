package com.youngc.pipeline.controller.system.dictionary;

import com.youngc.pipeline.model.DictionaryQueryModel;
import com.youngc.pipeline.result.Result;
import com.youngc.pipeline.result.ResultGenerator;
import com.youngc.pipeline.service.system.dictionary.DictionaryQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dict")
public class DictionaryQueryController {

    @Autowired
    private DictionaryQueryService dictionaryQueryService;

    @GetMapping(value = "/get")
    public Result getDictionaryList(@RequestParam String dictName,@RequestParam int pageNum, @RequestParam int pageSize){
        return ResultGenerator.generate(dictionaryQueryService.getList(dictName,pageNum,pageSize));
    }
}
